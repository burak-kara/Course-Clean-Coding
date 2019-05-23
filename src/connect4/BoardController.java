package connect4;

import java.awt.event.*;

public class BoardController {
    private Board board;
    private BoardView view;

    public BoardController(Board board) {
        initGame(board);
    }

    private void initGame(Board board) {
        this.board = board;
        this.view  = new BoardView(this);
    }

    public void startGame() {
        view.setSize(450, 525);
        view.setVisible(true);
        instructionMessage();
    }

    private void instructionMessage() {
        String message = " ***   During all game play   ***\n"
                        + " * Q - Quit game\n"
                        + " * R - Restart Game\n";
        String title   = "Welcome Connect4";
        view.sendInfoMessage(message, title);
    }

    public void keyClicked(int key) {
        if (key == KeyEvent.VK_Q) {
            quitGameMessage();
        } else if (key == KeyEvent.VK_R) {
            restartGameMessage();
        }
    }

    private void quitGameMessage() {
        String message = "Are you sure to quit game?";
        String title   = "Quit";
        int selected = view.sendQuestionMessage(message, title);
        if (selected == 0)
            System.exit(0);
    }

    private void restartGameMessage() {
        String message = "Are you sure to restart game?";
        String title   = "Restart";
        int selected = view.sendQuestionMessage(message, title);
        if (selected == 0) {
            view.dispose();
            initGame(new Board());
            startGame();
        }
    }

    public void mouseClicked(int column) {
        String player = board.getCurrentPlayer().toString();
        try {
            board.insertChipAt(column + 1);
            updateView(player, column);
        } catch (IllegalArgumentException exception) {
            updateView(player, column);
            tieGameMessage();
            newGameMessage();
        } catch (RuntimeException exception) {
            fullColumnMessage(player);
        }
    }

    private void updateView(String player, int column) {
        view.updateBoard(player, column);
        view.updateLabel(board.getCurrentPlayer().toString());
        if (isWinner()) {
            view.markWinnerChips();
            winGameMessage(player);
            newGameMessage();
        }
    }

    private boolean isWinner() {
        return board.getWinner() != Chip.NONE;
    }

    private void winGameMessage(String player) {
        String message = "Winner is " + player;
        String title   = "Winner";
        view.sendInfoMessage(message, title);
    }

    private void newGameMessage() {
        String message = "Do you want a new game?";
        String title   = "New Game";
        int selected = view.sendQuestionMessage(message, title);
        if (selected == 1) {
            System.exit(0);
        } else {
            view.dispose();
            initGame(new Board());
            startGame();
        }
    }

    private void tieGameMessage() {
        String message = "Game Over! \nThere is no winner. Game is Tie";
        String title   = "Warning";
        view.sendErrorMessage(message, title);
    }

    private void fullColumnMessage(String player) {
        String message = "Player: " + player + " column is full!\nTry another column!";
        String title   = "Warning";
        view.sendErrorMessage(message, title);
    }

    public Board getBoard() {
        return board;
    }
}
