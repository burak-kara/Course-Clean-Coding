package slidingLetters;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest {
    @Test // 7 points
    public void testInitialState() {
        Board board = new Board(5);
        char tile = 'A';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4) {
                    assertEquals(' ', board.getTile(i, j));
                } else {
                    assertEquals(tile, board.getTile(i, j));
                    tile++;
                }
            }
        }
    }

    @Test // 7 points
    public void testInitialStateIsSolved() {
        Board board = new Board(5);
        assertTrue(board.isSolved());
    }

    @Test // 2 points
    public void testIsSolved() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 2);
        board.shift(Direction.LEFT, 2);
        assertTrue(board.isSolved());
    }

    @Test // 1 points
    public void testNoShifting() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 0);
        assertTrue(board.isSolved());
    }

    @Test // 1 points
    public void testNegativeShifting() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, -2);
        assertTrue(board.isSolved());
    }

    @Test // 2 points
    public void testExcessShifting1() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 8);
        String expected = "A B C D E \n"
                + "F G H I J \n"
                + "K L M N O \n"
                + "P Q R S T \n"
                + "  U V W X ";

        assertEquals(expected, board.toString());
    }

    @Test // 2 points
    public void testExcessShifting2() {
        Board board = new Board(5);
        board.shift(Direction.LEFT, 2);
        assertTrue(board.isSolved());
    }

    @Test // 7 points
    public void testShift1() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 2);
        String expected = "A B C D E \n"
                + "F G H I J \n"
                + "K L M N O \n"
                + "P Q R S T \n"
                + "U V   W X ";

        assertEquals(expected, board.toString());
    }

    @Test // 7 points
    public void testShift2() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 2);
        board.shift(Direction.DOWN, 3);
        String expected = "A B C D E \n"
                + "F G   I J \n"
                + "K L H N O \n"
                + "P Q M S T \n"
                + "U V R W X ";
        assertEquals(expected, board.toString());
    }

    @Test // 7 points
    public void testShift3() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 2);
        board.shift(Direction.DOWN, 3);
        board.shift(Direction.LEFT, 4);
        String expected = "A B C D E \n"
                + "F G I J   \n"
                + "K L H N O \n"
                + "P Q M S T \n"
                + "U V R W X ";
        assertEquals(expected, board.toString());
    }

    @Test // 7 points
    public void testShift4() {
        Board board = new Board(5);
        board.shift(Direction.RIGHT, 2);
        board.shift(Direction.DOWN, 3);
        board.shift(Direction.LEFT, 4);
        board.shift(Direction.UP, 3);
        String expected = "A B C D E \n"
                + "F G I J O \n"
                + "K L H N T \n"
                + "P Q M S X \n"
                + "U V R W   ";
        assertEquals(expected, board.toString());
    }
}
