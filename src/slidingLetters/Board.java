package slidingLetters;

enum Direction {
    LEFT, RIGHT, UP, DOWN
}

public class Board {
    private final int dimension;
    private final char tiles[][];

    public Board(int dimension) {
        this.dimension = dimension;
        tiles = new char[dimension][dimension];
        initTiles();
    }

    // Initialize tiles from A to Z and last one is ' '
    private void initTiles() {
        int chr = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = (char) ('A' + chr);
                chr++;
            }
        }
        tiles[dimension -1][dimension - 1] = ' ';
    }

    // Returns the letter at row i, column j.
    // The top-left corner is 0, 0.
    public char getTile(int i, int j) {
        return tiles[i][j];
    }

    // A board is "solved" if it is in the initial state.
    // That is, the top-left tile value is 'A',
    // the empty slot is at the bottom-right corner,
    // and all the other tile values increase by one in row-major order.
    public boolean isSolved() {
        boolean isSolved = true;
        int chr     = 0;
        int lastRow = tiles.length - 1;
        int lastCol = tiles[lastRow].length - 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (!(i == lastRow && j == lastCol)) {
                    if (tiles[i][j] != (char) ('A' + chr))
                        isSolved = false;
                    chr++;
                } else {
                    if(tiles[lastRow][lastCol] != ' ')
                        isSolved = false;
                }
            }
        }
        return isSolved;
    }

    // Shift "amount"-many tiles towards the empty slot.
    // If "amount" is not positive simply quit.
    // If it is larger than the number of tiles that can be shifted,
    // shift as many tiles as possible and stop.
    // Do not throw an exception or an error.
    public void shift(Direction direction, int amount) {
        if (amount <= 0)
            return;

        int emptySlotLocation[] = getEmptySlotLocation();
        int i = emptySlotLocation[0];
        int j = emptySlotLocation[1];

        if (direction == Direction.LEFT)
            shiftLeft(amount, i, j);
        if (direction == Direction.RIGHT)
            shiftRight(amount, i, j);
        if (direction == Direction.DOWN)
            shiftDown(amount, i, j);
        if (direction == Direction.UP)
            shiftUp(amount, i, j);
    }

    // Returns the location of Empty Slot
    private int[] getEmptySlotLocation() {
        int location[] = new int[2];
        for (int i = 0; i <  tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++) {
                if ( getTile(i, j) == ' ') {
                    location[0] = i;
                    location[1] = j;
                    return location;
                }
            }
        }
        return location;
    }

    // Functions below are doing shift operation
    // It depends on the direction
    private void shiftLeft(int amount, int i, int j) {
        for (int c = 0; c < amount; c++) {
            if (j == tiles[i].length - 1)
                break;
            tiles[i][j] = tiles[i][j + 1];
            j++;
        }
        tiles[i][j] = ' ';
    }

    private void shiftRight(int amount, int i, int j) {
        for (int c = 0; c < amount; c++) {
            if(j == 0)
                break;
            tiles[i][j] = tiles[i][j - 1];
            j--;
        }
        tiles[i][j] = ' ';
    }

    private void shiftDown(int amount, int i, int j) {
        for (int c = 0; c < amount; c++) {
            if(i == 0)
                break;
            tiles[i][j] = tiles[i - 1][j];
            i--;
        }
        tiles[i][j] = ' ';
    }

    private void shiftUp(int amount, int i, int j) {
        for (int c = 0; c < amount; c++) {
            if(i == tiles.length - 1)
                break;
            tiles[i][j] = tiles[i + 1][j];
            i++;
        }
        tiles[i][j] = ' ';
    }

    // toString() is already implemented for you.
    // Do NOT change it.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                builder.append(getTile(i, j));
                builder.append(" ");
            }
            if (i != dimension - 1)
                builder.append("\n");
        }
        return builder.toString();
    }
}
