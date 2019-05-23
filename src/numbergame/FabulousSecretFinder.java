package numbergame;

import java.util.*;

public class FabulousSecretFinder extends SecretFinder {
    private int guess;
    private int maxPossible;
    private int numDigits;
    private Number activeGuess;
    private List<Number> validNumbers;

    public FabulousSecretFinder(SecretKeeper secretKeeper) {
        super(secretKeeper);
        validNumbers = new LinkedList<>();
        setBounds(secretKeeper);
        createValidNumbers(guess, maxPossible);
    }

    // Sets the Boundary of possible Valid Numbers
    private void setBounds(SecretKeeper secretKeeper) {
        numDigits = secretKeeper.getNumDigits();
        guess     = 1;
        for (int i = 1; i < numDigits; i++) {
            guess *= 10;
        }
        maxPossible = guess * 10;
    }

    // Creates validNumbers list in specific range
    // Takes minimum and maximum number that can be creatable
    // with numDigits
    private void createValidNumbers(int minBound, int maxBound) {
        for (int i = minBound; i < maxBound; i ++) {
            Number number = new Number(i);
            if (number.isValid(numDigits))
                validNumbers.add(number);
        }
    }

    @Override
    protected Number makeAGuess() {
        if (validNumbers.size() == 0)
            throw new RuntimeException("That's not possible, SecretKeeper is a LIAR!!!");
        activeGuess = validNumbers.get(0);
        return activeGuess;
    }

    @Override
    protected void process(Similarity similarity) {
        Iterator<Number> iterator = validNumbers.iterator();
        while (iterator.hasNext()) {
            Number number = iterator.next();
            Similarity tempSimilarity = activeGuess.similarityWith(number);
            if (!similarity.equals(tempSimilarity))
                iterator.remove();
        }
    }

}
