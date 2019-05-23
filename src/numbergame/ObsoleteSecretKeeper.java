package numbergame;

import java.util.*;

/**
 * @author Burak Kara
 */

public class ObsoleteSecretKeeper {
    private int secretNumber;
    private int numDigits;

    public ObsoleteSecretKeeper(int numDigits) {
        if (numDigits <= 0)
            throw new IllegalArgumentException("Num digits should be positive.");
        this.numDigits = numDigits;
    }

    public int getNumDigits() {
        return numDigits;
    }

    // Accessor method for debugging and testing purposes. Do NOT change this.
    public int getSecretNumber() {
        return secretNumber;
    }

    // Sets a random secret number that has `numDigits` digits.
    public void setSecretNumber() {
        String secretNumberStr  = "";
        Random randomGenerator  = new Random();
        List<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            digitList.add(i);
        }

        int firstDigit = randomGenerator.nextInt(9) + 1;
        digitList.remove(new Integer(firstDigit));
        secretNumberStr += Integer.toString(firstDigit);

        for(int i = 1; i < this.numDigits; i++) {
            Collections.shuffle(digitList);
            int nextDigit = digitList.get(0);
            digitList.remove(new Integer(nextDigit));
            secretNumberStr += nextDigit;
        }

        this.secretNumber = Integer.parseInt(secretNumberStr);
    }

    // Sets the secret number manually
    public void setSecretNumber(int secretNumber) {
        if (!isValidNumber(secretNumber))
            throw new IllegalArgumentException("Secret number is invalid.");
        this.secretNumber = secretNumber;
    }

    // A number is valid if it has `numDigits` digits,
    // and all its digits are unique.
    public boolean isValidNumber(int number) {
        String secretNumStr = Integer.toString(number);
        int numLength  = secretNumStr.length();
        boolean unique = true;

        if(this.numDigits == numLength) {
            for(int i = 0; i < numLength - 1; i++) {
                for(int j = i + 1; j < numLength; j++) {
                    char chrFirst = secretNumStr.charAt(i);
                    char chrNext  = secretNumStr.charAt(j);

                    if(chrFirst == chrNext)
                        unique = false;
                }
            }
        }
        else
            unique = false;

        return unique;
    }

    // Returns the similarity of `number` to `secretNumber`.
    public Similarity getSimilarity(int number) {
        String numStr       = Integer.toString(number);
        String secretNumStr = Integer.toString(this.secretNumber);

        int numLength = numStr.length();
        int samePos   = 0;
        int diffPos   = 0;

        for(int i = 0; i < numLength; i++) {
            char chrNum = numStr.charAt(i);

            for(int j = 0; j < numLength; j++) {
                char chrSecretNum = secretNumStr.charAt(j);
                if(chrNum == chrSecretNum){
                    if(i == j)
                        samePos++;
                    else
                        diffPos++;
                }
            }
        }
        return new Similarity(samePos, diffPos);
    }
}

