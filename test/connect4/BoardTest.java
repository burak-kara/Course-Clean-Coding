package connect4;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest {

    @Test
    public void testInitialPlayer() {
        Board board = new Board();
        Chip expectedPlayer = Chip.RED;
        assertEquals(expectedPlayer, board.getCurrentPlayer());
    }

    @Test
    public void testInitialWinner() {
        Board board = new Board();
        Chip expectedWinner = Chip.NONE;
        assertEquals(expectedWinner, board.getWinner());
    }

    @Test
    public void testInitialConfiguration() {
        Board board = new Board();
        String expectedConfig = "";
        assertEquals(expectedConfig, board.getConfiguration());
    }

    @Test
    public void testInitialBoard() {
        Board board = new Board();
        String expected = "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testConfiguredPlayer() {
        Board board = new Board("457732");
        Chip expectedPlayer = Chip.RED;
        assertEquals(expectedPlayer, board.getCurrentPlayer());
    }

    @Test
    public void testConfiguredWinner() {
        Board board = new Board("457732");
        Chip winner = Chip.NONE;
        assertEquals(winner, board.getWinner());
    }

    @Test
    public void testConfiguredConfiguration() {
        Board board = new Board("457732");
        String expectedConfig = "457732";
        assertEquals(expectedConfig, board.getConfiguration());
    }

    @Test
    public void testConfiguredBoard() {
        Board board = new Board("457732");
        String expected = "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ Y|\n"
                + "|_ Y R R Y _ R|";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testNullConfiguredPlayer() {
        Board board = new Board("");
        Chip expectedPlayer = Chip.RED;
        assertEquals(expectedPlayer, board.getCurrentPlayer());
    }

    @Test
    public void testNullConfiguredWinner() {
        Board board = new Board("");
        Chip winner = Chip.NONE;
        assertEquals(winner, board.getWinner());
    }

    @Test
    public void testNullConfiguredConfiguration() {
        Board board = new Board("");
        String expectedConfig = "";
        assertEquals(expectedConfig, board.getConfiguration());
    }

    @Test
    public void testNullConfiguredBoard() {
        Board board = new Board("");
        String expected = "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ _ _ _|";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testInsertChip() {
        Board board = new Board();
        board.insertChipAt(5);
        board.insertChipAt(5);
        board.insertChipAt(5);
        board.insertChipAt(4);
        board.insertChipAt(5);
        board.insertChipAt(5);
        String expected = "|_ _ _ _ _ _ _|\n"
                + "|_ _ _ _ Y _ _|\n"
                + "|_ _ _ _ R _ _|\n"
                + "|_ _ _ _ R _ _|\n"
                + "|_ _ _ _ Y _ _|\n"
                + "|_ _ _ Y R _ _|";
        assertEquals(expected, board.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testConfigurationException1() {
        Board board = new Board("1111111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfigurationException2() {
        Board board = new Board("12121212");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfigurationException3() {
        Board board = new Board("123a45");
    }

    @Test(expected = RuntimeException.class)
    public void testConfigurationException4() {
        Board board = new Board("1256975");
    }

    @Test
    public void testBottomHorizontalWin() {
        Board board = new Board("232323");
        board.insertChipAt(4);
        board.insertChipAt(3);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testTopHorizontalWin() {
        Board board = new Board("2332232323");
        board.insertChipAt(2);
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testLeftBottomVerticalWin() {
        Board board = new Board("112233");
        board.insertChipAt(4);
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testRightBottomVerticalWin() {
        Board board = new Board("445566676");
        board.insertChipAt(7);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testMidBottomVerticalWin() {
        Board board = new Board("21467656");
        board.insertChipAt(3);
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testTopVerticalWin() {
        Board board = new Board("12345671234567234567112345671234567172635");
        board.insertChipAt(4);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testRightDiagonalWin1() {
        Board board = new Board("1223633444");
        board.insertChipAt(4);
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testRightDiagonalWin2() {
        Board board = new Board("23452345345345226");
        board.insertChipAt(2);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testRightDiagonalWin3() {
        Board board = new Board("456745674567566477");
        board.insertChipAt(7);
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testLeftDiagonalWin1() {
        Board board = new Board("456745764567765");
        board.insertChipAt(4);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testLeftDiagonalWin2() {
        Board board = new Board("23456234563435246");
        board.insertChipAt(3);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testLeftDiagonalWin3() {
        Board board = new Board("12345123412364121162757");
        board.insertChipAt(3);
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameTie() {
        Board board = new Board("77777766666635555554444443333322222211111");
        board.insertChipAt(1);
    }
}
