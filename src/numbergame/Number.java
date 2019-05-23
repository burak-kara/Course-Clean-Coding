package numbergame;

public class Number {
    private int[] digits;

    public Number(int number) {
        digits = new int[String.valueOf(number).length()];
        createDigitList(number);
    }

    // Traverse the array in reverse order
    // because the digits are chopped off from right to left
    private void createDigitList(int number) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = number % 10;
            number = number / 10;
        }
    }

    // Returns whether number is valid or not
    // Takes the number of digits
    // First digit cannot be zero
    public boolean isValid(int numDigits) {
        if(digits.length != numDigits || digits[0] == 0)
            return false;

        boolean isVisited[] = new boolean[10];
        for (int num : this.digits) {
            if (isVisited[num])
                return false;
            isVisited[num] = true;
        }
        return true;
    }

    public Similarity similarityWith(Number guess) {
        int length  = digits.length;
        int samePos = 0;
        int diffPos = 0;
        for (int i = 0; i < length; i++) {
            int secretDigit = this.digits[i];
            for( int j = 0; j < length; j++) {
                int guessDigit = guess.digits[j];
                if (secretDigit == guessDigit) {
                    if (i == j)
                        samePos++;
                    else
                        diffPos++;
                }
            }
        }
        return new Similarity(samePos, diffPos);
    }

    // Returns integer type of number object
    public int asInt() {
        String str = "";
        for (int digit : digits) {
            str += digit;
        }
        return Integer.parseInt(str);
    }

    //
    // DO NOT CHANGE THE METHODS BELOW
    //
    public String toString() {
        return String.valueOf(asInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        return this.asInt() == number.asInt();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.asInt());
    }
}
