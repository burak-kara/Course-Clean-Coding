package connect4;

import java.util.ArrayList;

enum Chip {
    NONE, RED, YELLOW;

    protected char toChar() {
        if (this.equals(Chip.RED)) {
            return 'R';
        } else if (this.equals(Chip.YELLOW)) {
            return 'Y';
        }
        return '_';
    }
}

public class Board {
    public static final int NUM_COLUMNS = 7;
    public static final int NUM_ROWS = 6;
    private final Chip board[][];
    private String configuration;
    private Chip currentPlayer;
    private Chip winner;
    private ArrayList<Integer> winnerChips;

    /**
     * Creates an empty Connect4 board.
     * The first player to play will be the RED one.
     */
    public Board() {
        this.board = new Chip[NUM_COLUMNS][NUM_ROWS];
        createBoard();
        this.configuration = "";
        this.currentPlayer = Chip.RED;
        this.winner = Chip.NONE;
        this.winnerChips = new ArrayList<>();
    }

    private void createBoard() {
        for (int i = 0; i <  NUM_COLUMNS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                board[i][j] = Chip.NONE;
            }
        }
    }

    /**
     * Creates a Connect4 board with the given initial configuration.
     * For the format of the configuration, see
     * http://blog.gamesolver.org/solving-connect-four/02-test-protocol/
     *
     * @param configuration The initial configuration of the board.
     *
     * @throws IllegalArgumentException if <code>configuration</code> is not a valid configuration.
     * A configuration is not valid if it is an impossible combination
     * or it contains moves after forming a 4-connection.
     */
    public Board(String configuration) {
        this();
        initConfiguration(configuration);
    }

    private void initConfiguration(String configuration) {
        if (isValidConfig(configuration)) {
            for (int i = 0; i < configuration.length(); i++) {
                char chr = configuration.charAt(i);
                insertChipAt(Character.getNumericValue(chr));
            }
        }
    }

    private boolean isValidConfig(String config) {
        if (config.equals("")) {
            return false;
        } else if (config.matches("[0-9]+")) {
            if (winner.equals(Chip.NONE)) {
                return true;
            } else
            throw new IllegalArgumentException("Not a valid configuration!");
        } else
        throw new IllegalArgumentException("Invalid configuration! It should contain numbers only!");
    }

    /**
     * Inserts a chip for the current player at the specified <code>column</code>
     * (indexing starts at 1). After the chip is inserted, it wil be the other player's turn.
     *
     * @param column The index of the column into which to put a chip.
     *
     * @throws IllegalArgumentException if no column at the specified index exists.
     * @throws RuntimeException if inserting a chip at the specified column is not possible (e.g. when the column is full)
     */
    public void insertChipAt(int column) {
        column--;
        if (isValidInsertion(column)) {
            int row = getNullRow(column);
            board[column][row] = currentPlayer;
            process(column, row);
        }
    }

    private boolean isValidInsertion(int column) {
        if (0 > column || column >= NUM_COLUMNS) {
            throw new IllegalArgumentException("Invalid column!");
        } else if (isGameTie()) {
          throw new IllegalArgumentException("Tie");
        } else if (board[column][NUM_ROWS - 1] != Chip.NONE) {
            throw new RuntimeException("Full column");
        } else if (!winner.equals(Chip.NONE))
            throw new IllegalArgumentException("Game over! \nThere is a winner");
        return true;
    }

    private int getNullRow(int column) {
        for (int i = 0; i < NUM_ROWS; i++) {
            if (board[column][i] == Chip.NONE) {
                return i;
            }
        }
        return 0;
    }

    private void process(int column, int row) {
        checkWinOrTie(column, row);
        configuration += ++column;
        setNextPlayer();
    }

    private void checkWinOrTie(int column, int row) {
        checkGameWin(column, row);
        if (winner.equals(Chip.NONE) && isGameTie()) {
            throw new IllegalArgumentException("Tie");
        }
    }

    private boolean isGameTie() {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            if (board[i][NUM_ROWS - 1] == Chip.NONE) {
                return false;
            }
        }
        return true;
    }

    private void checkGameWin(int column, int row) {
        winner = (isConnect4(column, row)) ? currentPlayer : winner;
    }

    private boolean isConnect4(int column, int row) {
        Chip player = currentPlayer;
        boolean isVerticalWin   = isVerticalConnect(player, column, row);
        boolean isHorizontalWin = isHorizontalConnect(player, column, row);
        boolean isDiagonalWin   = isDiagonalConnect(player, column, row);
        return isVerticalWin || isHorizontalWin || isDiagonalWin;
    }

    private boolean isVerticalConnect(Chip player, int column, int row) {
        ArrayList<Integer> winList = new ArrayList<>();
        while(column >= 0 && board[column][row] == player)
            column--;

        for (int i = ++column; i < NUM_COLUMNS; i++) {
            if (board[i][row] != player)
                break;
            winList.add(i);
            winList.add(row);
        }
        boolean isConnected = winList.size() >= 8;
        winnerChips = isConnected ? winList : winnerChips;
        return isConnected;
    }

    private boolean isHorizontalConnect(Chip player, int column, int row) {
        ArrayList<Integer> winList = new ArrayList<>();
        for (int i = row; i >= 0; i--) {
            if (board[column][i] != player)
                break;
            winList.add(column);
            winList.add(i);
        }
        boolean isConnected = winList.size() >= 8;
        winnerChips = isConnected ? winList : winnerChips;
        return isConnected;
    }

    private boolean isDiagonalConnect(Chip player, int column, int row) {
        boolean isRightDiagonalWin = isRightDiagonalConnect(player, column, row);
        boolean isLeftDiagonalWin  = isLeftDiagonalConnect(player, column, row);
        return isRightDiagonalWin || isLeftDiagonalWin;
    }

    private boolean isRightDiagonalConnect(Chip player, int column, int row) {
        ArrayList<Integer> winList = new ArrayList<>();
        while (column >= 0 && row >= 0 && board[column][row] == player) {
            column--;
            row--;
        }
        for (int i = ++column, j = ++row; i < NUM_COLUMNS && j < NUM_ROWS; i++, j++) {
            if (board[i][j] != player)
                break;
            winList.add(i);
            winList.add(j);
        }
        boolean isConnected = winList.size() >= 8;
        winnerChips = isConnected ? winList : winnerChips;
        return isConnected;
    }

    private boolean isLeftDiagonalConnect(Chip player, int column, int row) {
        ArrayList<Integer> winList = new ArrayList<>();
        while (column < NUM_COLUMNS && row >= 0 && board[column][row] == player) {
            column++;
            row--;
        }
        for (int i = --column, j = ++row; i >= 0 && j < NUM_ROWS; i--, j++) {
            if (board[i][j] != player)
                break;
            winList.add(i);
            winList.add(j);
        }
        boolean isConnected = winList.size() >= 8;
        winnerChips = isConnected ? winList : winnerChips;
        return isConnected;
    }

    private void setNextPlayer() {
        if (currentPlayer.equals(Chip.RED)) {
            currentPlayer = Chip.YELLOW;
        } else if (currentPlayer.equals(Chip.YELLOW)) {
            currentPlayer = Chip.RED;
        }
    }

    /**
     * @return The Chip of the current player.
     */
    public Chip getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     *
     * @return the winner Chip (RED or YELLOW) if there exists a 4-connection;
     *         NONE, otherwise.
     */
    public Chip getWinner() {
        return this.winner;
    }

    /**
     * @return The current state of the board as a "configuration".
     */
    public String getConfiguration() {
        return this.configuration;
    }

    /**
     * @return The chip at the given location.
     * row and column parameters are 1-based indices.
     */
    public Chip getChipAt(int row, int column) {
        return board[column][row];
    }

    /**
     * @return The locations in order column, row, column, row etc.
     */
    public ArrayList<Integer> getWinnerChips() {
        return winnerChips;
    }

    /**
     * @return this board's string representation.
     * E.g. here is the output for the configuration 3432554:
     *
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ R R Y _ _|
     * |_ Y R Y R _ _|
     *
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = NUM_ROWS - 1; 0 <= i; i--) {
            builder.append("|");
            for (int j = 0; j < NUM_COLUMNS; j++) {
                builder.append(board[j][i].toChar());
                if (j != NUM_COLUMNS - 1)
                    builder.append(" ");
            }
            builder.append("|");
            if (i != 0)
                builder.append("\n");
        }
        return builder.toString();
    }
}
