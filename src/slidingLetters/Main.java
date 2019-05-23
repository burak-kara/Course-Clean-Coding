package slidingLetters;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(5);

        System.out.println("Initial:");
        System.out.println(board);
        System.out.println("isSolved? " + board.isSolved() + "\n");


        board.shift(Direction.RIGHT, 2);
        System.out.println("After shift-right by 2:");
        System.out.println(board);
        System.out.println("isSolved? " + board.isSolved() + "\n");

        board.shift(Direction.DOWN, 3);
        System.out.println("After shift-down by 3:");
        System.out.println(board);
        System.out.println("isSolved? " + board.isSolved() + "\n");

        // Randomly shift several times
        Direction directions[] = Direction.values();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int shiftAmount = random.nextInt(5);
            Direction direction = directions[random.nextInt(directions.length)];
            board.shift(direction, shiftAmount);
        }
        System.out.println("After shifting randomly:");
        System.out.println(board);
        System.out.println("isSolved? " + board.isSolved() + "\n");
    }
}
