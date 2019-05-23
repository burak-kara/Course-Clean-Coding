package numbergame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FabulousSecretFinderTest {
    @Test
    public void testFindSecretNumber1() {
        NaiveSecretKeeper keeper = new NaiveSecretKeeper(4, new Number(9083));
        SecretFinder finder = new FabulousSecretFinder(keeper);

        assertEquals(9083, finder.findSecretNumber().asInt());
    }

    @Test
    public void testFindSecretNumber2() {
        NaiveSecretKeeper keeper = new NaiveSecretKeeper(5, new Number(42061));
        SecretFinder finder = new FabulousSecretFinder(keeper);

        assertEquals(42061, finder.findSecretNumber().asInt());
    }

    @Test(expected = Exception.class)
    public void testFindSecretNumber3() {
        BadSecretKeeper keeper = new BadSecretKeeper(4);
        SecretFinder finder = new FabulousSecretFinder(keeper);

        finder.findSecretNumber();
    }

    @Test // Instructor's solution does not pass this test
    public void testNumGuessesAtMost4() {
        testNumGuesses(4, 1639, 4);
    }

    private void testNumGuesses(int numDigits, int secretNumber, int maxNumGuesses) {
        NaiveSecretKeeper keeper = new NaiveSecretKeeper(numDigits, new Number(secretNumber));
        SecretFinder finder = new FabulousSecretFinder(keeper);

        assertEquals(secretNumber, finder.findSecretNumber().asInt());

        int numQueries = keeper.getNumQueries();
        assertTrue("Num queries: " + numQueries, numQueries <= maxNumGuesses);
    }

    @Test
    public void testNumGuessesAtMost5() {
        testNumGuesses(4, 3905, 5);
    }

    @Test
    public void testNumGuessesAtMost6() {
        testNumGuesses(4, 7832, 6);
    }

    @Test
    public void testNumGuessesAtMost7() {
        testNumGuesses(4, 7832, 7);
    }

    @Test
    public void testNumGuessesAtMost8() {
        testNumGuesses(4, 7032, 8);
    }

    @Test
    public void testNumGuessesAtMost9() {
        testNumGuesses(4, 7032, 9);
    }

    @Test
    public void testNumGuessesAtMost10() {
        testNumGuesses(4, 7032, 10);
    }

    @Test
    public void testNumGuessesAtMost11() {
        testNumGuesses(4, 7032, 11);
    }

    @Test
    public void testNumGuessesAtMost12() {
        testNumGuesses(4, 7032, 12);
    }

    @Test
    public void testNumGuessesAtMost13() {
        testNumGuesses(4, 7032, 13);
    }

    @Test
    public void testNumGuessesAtMost14() {
        testNumGuesses(4, 7032, 14);
    }

    //Tests below are written by Burak Kara to compare results with Barış Aktemur's
    @Test
    public void testNumGuesses1() {
        testNumGuesses(4, 1542, 5);
    }

    @Test
    public void testNumGuesses2() {
        testNumGuesses(4, 2581, 5);
    }

    @Test
    public void testNumGuesses3() {
        testNumGuesses(4, 1802, 8);
    }

    @Test
    public void testNumGuesses4() {
        testNumGuesses(4, 2179, 4);
    }

    @Test
    public void testNumGuesses5() {
        testNumGuesses(4, 5764, 6);
    }

    @Test
    public void testNumGuesses6() {
        testNumGuesses(4, 4281, 7);
    }

    @Test
    public void testNumGuesses7() {
        testNumGuesses(4, 2870, 6);
    }

    @Test
    public void testNumGuesses8() {
        testNumGuesses(4, 2760, 6);
    }

    @Test
    public void testNumGuesses9() {
        testNumGuesses(5, 25870, 6);
    }

    @Test
    public void testNumGuesses10() {
        testNumGuesses(5, 48735, 8);
    }

    @Test
    public void testNumGuesses11() {
        testNumGuesses(5, 79835, 7);
    }

    @Test
    public void testNumGuesses12() {
        testNumGuesses(6, 183502, 7);
    }

    @Test
    public void testNumGuesses13() {
        testNumGuesses(6, 175298, 6);
    }

    @Test
    public void testNumGuesses14() {
        testNumGuesses(6, 678520, 7);
    }

    @Test
    public void testNumGuesses15() {
        testNumGuesses(7, 2831574, 7);
    }

    @Test
    public void testNumGuesses16() {
        testNumGuesses(7, 2964785, 9);
    }

    @Test
    public void testNumGuesses17() {
        testNumGuesses(7, 9163742, 9);
    }
}

class BadSecretKeeper extends SecretKeeper {
    public BadSecretKeeper(int numDigits) {
        super(numDigits);
    }

    @Override
    protected Similarity calculateSimilarity(Number number) {
        return new Similarity(0, getNumDigits() - 1);
    }
}
