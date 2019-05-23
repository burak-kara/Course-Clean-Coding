package ocr;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecognizerTest {
    @Test
    public void testDigit0() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "| |",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("0", output);
    }

    @Test
    public void testDigit1() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "   ",
                "  |",
                "  |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("1", output);
    }

    @Test
    public void testDigit2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                " _|",
                "|_ ",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("2", output);
    }

    @Test
    public void testDigit3() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                " _|",
                " _|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("3", output);
    }

    @Test
    public void testDigit4() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "   ",
                "|_|",
                "  |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("4", output);
    }

    @Test
    public void testDigit5() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_ ",
                " _|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("5", output);
    }

    @Test
    public void testDigit6() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_ ",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("6", output);
    }

    @Test
    public void testDigit7() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "  |",
                "  |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("7", output);
    }

    @Test
    public void testDigit8() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("8", output);
    }

    @Test
    public void testDigit9() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                " _|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("9", output);
    }

    @Test
    public void testDigitA() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                "| |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("A", output);
    }

    @Test
    public void testDigitB() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "   ",
                "|_ ",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("B", output);
    }

    @Test
    public void testDigitC() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|  ",
                "|_ ",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("C", output);
    }

    @Test
    public void testDigitD() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "   ",
                " _|",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("D", output);
    }

    @Test
    public void testDigitE() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_ ",
                "|_ ",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("E", output);
    }

    @Test
    public void testDigitF() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_ ",
                "|  ",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("F", output);
    }

    @Test // Also, it tests multiline input
    public void testAllDigits() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "    _        _  _ ",
                "  | _||_ |_||_ |_ ",
                "  ||_ |_|  | _||_|",
                "                  ",
                " _  _  _ ",
                "  ||_||_|",
                "  ||_| _|",
                "         ",
                " _  _     _     _ ",
                "| ||_||_ |   _||_ ",
                "|_|| ||_||_ |_||_ ",
                "                  ",
                " _ ",
                "|_ ",
                "|  ",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("12B456,789,0ABCDE,F", output);
    }

    @Test // 1 and 7 are written at wrong indexes
    public void testBeginError() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "    _  _  _    ",
                " |  _| _||_ |_ ",
                " | |_  _| _||_|",
                "               ",
                "_   _  _ ",
                " | |_|| |",
                " | |_||_|",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("?235B,?80", output);
    }

    @Test // C and E are written at wrong indexes
    public void testMidError() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _      _   ",
                "|_||_  |   |",
                "| ||_| |_  |",
                "            ",
                "     _ _ ",
                " _| |_|_ ",
                "|_| |_|  ",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("AB?1,D?F", output);
    }

    @Test // Invalid inputs which are contains blanks
    public void testEndError() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _  _   _ ",
                " _| _|  _|",
                "|_  _| |_ ",
                "          ",
                "    _  _  _ ",
                "|_||_ |_|| |",
                "  ||   _|| |",
                "            ");

        String output = recognizer.convert(input);
        assertEquals("23??,4F9?", output);
    }

    @Test // Characters at the 4th rows of all each line
    public void testLastRow() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _    ",
                "  ||_ |_||_|",
                "  ||_||_|  |",
                "       _    ",
                " _  _  _ ",
                "  ||_ |_|",
                "  ||_|| |",
                "        |",
                " _  _  _  _ ",
                "|_||_|| ||_ ",
                "  | _||_||  ",
                " _          ");

        String output = recognizer.convert(input);
        assertEquals("1B?4,76?,?90F", output);
    }

    @Test
    public void testInvalidCharacters() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _     -  _  _  _  _  _  _ ",
                "  ||, |)||_|  ||_ |_||_||_||+||_ ",
                "  |;_||_|  |  ||_|| |<_| _||_|| .",
                "                                 ");

        String output = recognizer.convert(input);
        assertEquals("1??4?6A?9??", output);
    }

    // Tests below from this line are for inputs which are not typically 3x4
    // I wrote them before the last announcement
    @Test
    public void testNullRows() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");

        String output = recognizer.convert(input);
        assertEquals("?,?", output);
    }

    @Test
    public void testBlankRow() {
        Recognizer recognizer = new Recognizer();
        String input = "            ";

        String output = recognizer.convert(input);
        assertEquals("????", output);
    }

    @Test
    public void testNullInput() {
        Recognizer recognizer = new Recognizer();
        String output = recognizer.convert("");
        assertEquals("?", output);
    }

    @Test // A new line operator as an input
    public void testInvalidInput1() {
        Recognizer recognizer = new Recognizer();
        String output = recognizer.convert("\n");
        assertEquals("?", output);
    }

    @Test // A new line operator in the 4th row
    public void testInvalidInput2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _    ",
                "   |_ |_|   ",
                "  ||_||_||_|",
                "            ",
                " _  _  _  _  _  _    ",
                "  ||_ |_||_||_|| || |",
                "  ||_|| | _| _||_||  ",
                "         \n          ");

        String output = recognizer.convert(input);
        assertEquals("?B8?,76A????,????", output);
    }

    @Test // A new line operator in the middle row
    public void testInvalidInput3() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _    ",
                "   |_ |_|   ",
                "  ||_||_||_|",
                "            ",
                " _  _  _  _     _  _  _ ",
                "  ||_ |_||_|\n |_|| || |",
                "  ||_|| | _|    _||_||  ",
                "                        ");

        String output = recognizer.convert(input);
        assertEquals("?B8?,????????,????????", output);
    }

    @Test // On the each line, 2nd & 3rd rows are shorter
    public void testShortRow1() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _  _  _ ",
                "|_ | || ",
                "|_ |_||_",
                "         ",
                " _  _  _ ",
                "|_||  ",
                "|_||_ ",
                "         ",
                " _     _    ",
                "",
                "",
                "            ");

        String output = recognizer.convert(input);
        assertEquals("E0?,8C?,????", output);
    }

    @Test // On the each line, 3rd & 4th rows are shorter
    public void testShortRow2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _     _ ",
                "| |  ||_ ",
                "|_|  || ",
                "        ",
                " _  _ ",
                "|_||_ ",
                "|_|",
                "   ",
                " _  _ ",
                "| ||_ ",
                "",
                "");

        String output = recognizer.convert(input);
        assertEquals("01?,8?,??", output);
    }

    @Test // On the each line 1st row is longer
    public void testLongRow1() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _           ",
                "  ||_ |_||_|",
                "  ||_||_|  |",
                "            ",

                " _  _  _     ",
                "  ||_ |_|",
                "  ||_|| |",
                "         ",
                " _  _  _  _  ",
                "|_||_|| ||_ ",
                " _| _||_||  ",
                "            ");

        String output = recognizer.convert(input);
        assertEquals("1B84???,76A??,990F?", output);
    }

    @Test // On the each line 4th row is longer
    public void testLongRow2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "       _    ",
                "  ||_ |_||_|",
                "  ||_||_|  |",
                "                   ",
                " _  _  _ ",
                "  ||_ |_|",
                "  ||_|| |",
                "             ",
                " _  _  _  _ ",
                "|_||_|| ||_ ",
                " _| _||_||  ",
                "             ");

        String output = recognizer.convert(input);
        assertEquals("1B84???,76A??,990F?", output);
    }

    @Test // On the each line, a row is null
    public void testMissingRow1() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "",
                "  | _| _|",
                "  ||_  _|",
                "         ",
                "    _  _  _ ",
                "",
                "  | _||  |_|",
                "            ",
                " _  _ ",
                "|_|| |",
                "",
                "      ");

        String output = recognizer.convert(input);
        assertEquals("???,????,??", output);
    }

    @Test // On the each line, 4th row is missing
    public void testMissingRow2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _          ",
                "  | _||_ |_|",
                "  ||_||_|  |",
                " _  _  _ ",
                " _|| ||_|",
                " _||_| _|");

        String output = recognizer.convert(input);
        assertEquals("????,???", output);
    }
}
