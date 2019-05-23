package numbergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSecretKeeper extends SecretKeeper {
    private Number secretNumber;
    private List<Integer> digitList;

    public RandomSecretKeeper(int numDigits) {
        super(numDigits);
        digitList = new ArrayList<>();
        setSecretNumber();
    }

    // Sets a random secret number that has `numDigits` digits.
    private void setSecretNumber() {
        Random random = new Random();
        String secretNumberStr = "";

        for (int i = 0; i < 10; i++) {
            digitList.add(i);
        }
        int firstDigit = random.nextInt(9) + 1;
        secretNumberStr += Integer.toString(firstDigit);
        digitList.remove(new Integer(firstDigit));
        Collections.shuffle(digitList);
        for (int i = 1; i < this.getNumDigits(); i++) {
            secretNumberStr += Integer.toString(digitList.get(i));
        }
        secretNumber = new Number(Integer.parseInt(secretNumberStr));
    }

    // Accessor method for debugging and testing purposes. Do NOT change this.
    public Number getSecretNumber() {
        return secretNumber;
    }

    @Override
    public Similarity calculateSimilarity(Number number) {
        return secretNumber.similarityWith(number);
    }
}
