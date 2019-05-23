package numbergame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FabulousSecretKeeperTest {
    @Test(timeout = 5000)
    public void testNumGuessesAtLeast3() {
        testMinNumGuesses(4, 3);
    }

    private void testMinNumGuesses(int numDigits, int minNumGuesses) {
        FabulousSecretKeeper keeper = new FabulousSecretKeeper(numDigits);
        SecretFinder finder = new FabulousSecretFinder(keeper);

        finder.findSecretNumber();
        int numQueries = keeper.getNumQueries();
        assertTrue("Num queries: " + numQueries, numQueries >= minNumGuesses);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast4() {
        testMinNumGuesses(4, 4);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast5() {
        testMinNumGuesses(4, 5);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast6() {
        testMinNumGuesses(4, 6);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast7() {
        testMinNumGuesses(4, 7);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast8() {
        testMinNumGuesses(4, 8);
    }

    @Test(timeout = 5000)   // Instructor's solution does not pass this test and beyond
    public void testNumGuessesAtLeast9() {
        testMinNumGuesses(4, 9);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast10() {
        testMinNumGuesses(4, 10);
    }

    @Test(timeout = 5000)
    public void testNumGuessesAtLeast11() {
        testMinNumGuesses(4, 11);
    }

    // Test below are written by Burak Kara to compare results with Barış Aktemur's
    @Test(timeout = 5000)
    public void testNumGuesses58() {
        testMinNumGuesses(5, 8);
    }

    @Test(timeout = 5000)
    public void testNumGuesses59() {
        testMinNumGuesses(5, 9);
    }

    @Test(timeout = 5000)
    public void testNumGuesses68() {
        testMinNumGuesses(6, 8);
    }

    @Test(timeout = 5000)
    public void testNumGuesses69() {
        testMinNumGuesses(6, 9);
    }

    @Test(timeout = 5000)
    public void testNumGuesses78() {
        testMinNumGuesses(7, 8);
    }

    @Test(timeout = 5000)
    public void testNumGuesses79() {
        testMinNumGuesses(7, 9);
    }

    @Test(timeout = 5000)
    public void testNumGuesses710() {
        testMinNumGuesses(7, 10);
    }

    @Test(timeout = 5000)
    public void testNumGuesses711() {
        testMinNumGuesses(7, 11);
    }
}
