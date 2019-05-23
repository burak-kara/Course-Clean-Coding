package ocr;

import java.util.ArrayList;
import java.util.Arrays;

enum Digit {
    ZERO ("0", "010202212000"),
    ONE  ("1", "000002002000"),
    TWO  ("2", "010012210000"),
    THREE("3", "010012012000"),
    FOUR ("4", "000212002000"),
    FIVE ("5", "010210012000"),
    SIX  ("6", "010210212000"),
    SEVEN("7", "010002002000"),
    EIGHT("8", "010212212000"),
    NINE ("9", "010212012000"),
    A("A", "010212202000"),
    B("B", "000210212000"),
    C("C", "010200210000"),
    D("D", "000012212000"),
    E("E", "010210210000"),
    F("F", "010210200000");

    private String digit;
    private String digitCode;

    Digit(String digit, String digitCode) {
        this.digit = digit;
        this.digitCode = digitCode;
    }

    public String getDigit() {
        return digit;
    }

    public String getDigitCode() {
        return digitCode;
    }
}

public class Recognizer {
    private ArrayList<String> lines = new ArrayList<>();
    private static final String NEW_ROW     = "\n";
    private static final String MISSING_ROW = "\n\n";
    private static final String NULL_LINE   = "\n\n\n";
    private static final String EMPTY     = "";
    private static final String COMMA     = ",";
    private static final String QUESTION  = "?";
    private static final int FIRST_INDEX  = 0;
    private static final int DIGIT_LENGTH = 3;
    private static final int ROW_COUNT    = 4;
    private static final int MINUS        = - 1;

    // Recognizer.convert also supports not 3x4 inputs
    // They are written before the last announcement
    public String convert(String input) {
        splitMultiLine(input);
        return getRecognizedInput();
    }

    private void splitMultiLine(String input) {
        String[] rows    = input.split(NEW_ROW, MINUS);
        int rowsLength   = rows.length;
        int extraLength  = rowsLength % ROW_COUNT;
        int properLength = rowsLength - extraLength;

        for (int i = 0; i < properLength; i += ROW_COUNT)
            lines.add(getLine(rows, i, i + ROW_COUNT));

        // For lines which have different row count from ROW_COUNT
        if (extraLength != 0)
            lines.add(getLine(rows, properLength, rowsLength));
    }

    // Manuel String.join method
    // For lines which have different row count from ROW_COUNT
    private String getLine(String[] rows, int startIndex, int lastIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < lastIndex; i++) {
            builder.append(rows[i]);
            builder.append(i == lastIndex - 1 ? EMPTY : NEW_ROW);
        }
        return builder.toString();
    }

    private String getRecognizedInput() {
        StringBuilder builder = new StringBuilder();
        int lineCount = lines.size();
        for (int i = 0; i < lineCount; i++) {
            // Internal check for row lengths which are not multiplies of 3
            builder.append(isInvalidLine(lines.get(i)) ? QUESTION : getRecognizedLine(lines.get(i)));
            builder.append(i == lineCount - 1 ? EMPTY : COMMA);
        }
        return builder.toString();
    }

    private boolean isInvalidLine(String line) {
        return line.equals(NULL_LINE) || line.equals(MISSING_ROW) || line.equals(NEW_ROW) || line.equals(EMPTY);
    }

    private String getRecognizedLine(String line) {
        if (isInvalidLine(line))
            return EMPTY;
        else if(isSurplusEnd(line))
            return QUESTION;

        String[] rows    = line.split(NEW_ROW, MINUS);
        String rawDigit  = getRawDigit(rows);
        String digitCode = getDigitCode(rawDigit);
        String recognizedDigit = getRecognizedDigit(digitCode);
        line = deleteFirstDigit(rows);
        return recognizedDigit + getRecognizedLine(line);
    }

    // Check for row lengths which are not multiplies of 3
    // Returns whether a row has surplus characters
    private boolean isSurplusEnd(String line) {
        return line.equals(" ") || line.equals("  ");
    }

    private String getRawDigit(String[] rows) {
        StringBuilder builder = new StringBuilder();
        for (String row : rows)
            // Internal check for row lengths which are not multiplies of 3
            builder.append(row.length() < DIGIT_LENGTH ? row : row.substring(FIRST_INDEX, DIGIT_LENGTH));
        return builder.toString();
    }

    private String getDigitCode(String rawDigit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rawDigit.length(); i++) {
            switch (rawDigit.charAt(i)) {
                case ' ':
                    builder.append("0");
                    break;
                case '_':
                    builder.append("1");
                    break;
                case '|':
                    builder.append("2");
                    break;
                default:
                    builder.append(QUESTION);
                    break;
            }
        }
        return builder.toString();
    }

    private String getRecognizedDigit(String code) {
        for (Digit digit : Digit.values())
            if (digit.getDigitCode().equals(code))
                return digit.getDigit();
        return QUESTION;
    }

    private String deleteFirstDigit(String[] rows) {
        // Internal check for row lengths which are not multiplies of 3
        Arrays.setAll(rows, i -> (rows[i].length() < DIGIT_LENGTH) ? EMPTY : rows[i].substring(DIGIT_LENGTH));
        return getLine(rows, FIRST_INDEX, rows.length);
    }
}
