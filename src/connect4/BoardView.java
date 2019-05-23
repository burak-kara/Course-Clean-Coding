package connect4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BoardView extends JFrame {
    private final int NUM_COLUMNS = 7;
    private final int NUM_ROWS = 6;
    private BoardController controller;
    private JButton buttons[][];

    public BoardView(final BoardController controller) {
        this.buttons    = new JButton[NUM_COLUMNS][NUM_ROWS];
        this.controller = controller;
        setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createView();
    }

    private void createView() {
        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[j][i] = new JButton();
                add(buttons[j][i]);
                listenMouse(buttons[j][i], j);
                listenKeyBoard(buttons[j][i]);
            }
        }
    }

    private void listenMouse(JButton button, int column) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.mouseClicked(column);
            }
        });
    }

    private void listenKeyBoard(JButton button) {
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int key = event.getKeyCode();
                controller.keyClicked(key);
            }
        });
    }

    public void updateBoard(String player, int column) {
        int row = getNullRow(column) - 1;
        Color color = (player.equals("RED")) ? Color.RED : Color.YELLOW;
        buttons[column][row].setBackground(color);
    }

    private int getNullRow(int column) {
        for (int i = 0; i < NUM_ROWS; i++) {
            Chip chip = controller.getBoard().getChipAt(i, column);
            if (chip.equals(Chip.NONE))
                return i;
        }
        return NUM_ROWS;
    }

    public void updateLabel(String player) {
        setTitle("Player " + player + "'s turn.");
    }

    public int sendQuestionMessage(String message, String title) {
        return JOptionPane.showConfirmDialog(new JFrame(), message, title, JOptionPane.YES_NO_OPTION);
    }

    public void sendInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void sendErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void markWinnerChips() {
        ArrayList<Integer> winnerChips = controller.getBoard().getWinnerChips();
        for (int i = 0; i < winnerChips.size(); i += 2) {
            int column = winnerChips.get(i);
            int row = winnerChips.get(i + 1);
            buttons[column][row].setText("X");
        }
    }
}
