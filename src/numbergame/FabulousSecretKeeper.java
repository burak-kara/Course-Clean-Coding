package numbergame;

import java.util.HashMap;
import java.util.Iterator;

public class FabulousSecretKeeper extends SecretKeeper {
    private int guess;
    private int maxPossible;
    private int numDigits;
    private int similarityCount[][];
    private HashMap<Number, Similarity> validNumbers;

    public FabulousSecretKeeper(int numDigits) {
        super(numDigits);
        this.numDigits = numDigits;
        validNumbers = new HashMap<>();
        setBounds(numDigits);
        createHashMap(guess, maxPossible);
    }

    // Sets the Boundary of possible Valid Numbers
    private void setBounds(int numDigits) {
        guess = 1;
        for (int i = 1; i < numDigits; i++) {
            guess *= 10;
        }
        maxPossible = guess * 10;
    }

    // Creates a HashMap called validNumbers
    // Puts possible secret numbers in validNumbers
    // Takes minimum and maximum number that can be creatable
    // with numDigits
    private void createHashMap(int minBound, int maxBound) {
        for (int i = minBound; i < maxBound; i++) {
            Number number = new Number(i);
            if (number.isValid(numDigits))
                validNumbers.put(number, null);
        }
    }

    @Override
    protected Similarity calculateSimilarity(Number number) {
        process(number);
        Number tempSecretNumber = validNumbers.keySet().iterator().next();
        return tempSecretNumber.similarityWith(number);
    }

    // Process the necessary operations to keep secret
    private void process(Number number) {
        assignSimilaritytoNumbers(number);
        increaseSimilarityCount();
        Similarity frequentSimilarity = findFrequentSimilarity();
        processValidNumbers(frequentSimilarity);
    }

    // Checking the similarity of number with validNumbers's keys
    // Assigns that similarity to that key
    private void assignSimilaritytoNumbers(Number number) {
        for (HashMap.Entry pair : validNumbers.entrySet()) {
            Number tempNumber = (Number) pair.getKey();
            Similarity tempSimilarity = number.similarityWith(tempNumber);
            validNumbers.put(tempNumber, tempSimilarity);
        }
    }

    // Increase counter in the 2-Dimensional array
    // First dimension for positive similarity number
    // Second dimension for negative similarity number
    private void increaseSimilarityCount() {
        similarityCount = new int[numDigits + 1][numDigits + 1];
        for (HashMap.Entry pair : validNumbers.entrySet()) {
            Similarity temp = (Similarity) pair.getValue();
            int positive = temp.getPositive();
            int negative = temp.getNegative();
            similarityCount[positive][negative]++;
        }
    }

    // Iterates the similarityCount 2-Dimensional array
    // Finds the maxCount (the most frequent similarity)
    // Returns the most frequent similarity
    private Similarity findFrequentSimilarity() {
        int maxCount = 0;
        int positive = 0;
        int negative = 0;
        for (int i = 0; i < similarityCount.length; i++) {
            for (int j = 0; j < 5 - i; j++) {
                int count = similarityCount[i][j];
                if (maxCount < count) {
                    maxCount = count;
                    positive = i;
                    negative = j;
                }
            }
        }
        return new Similarity(positive, negative);
    }

    // Removes all pairs of validNumbers except number objects
    // which have the most similarity count (The most frequent)
    // Takes the most frequent Similarity type as a parameter
    private void processValidNumbers(Similarity similarity) {
        Iterator iterator = validNumbers.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) iterator.next();
            Similarity tempSimilarity = (Similarity) pair.getValue();
            if (validNumbers.size() > 1 && !similarity.equals(tempSimilarity))
                iterator.remove();
        }
    }

}
