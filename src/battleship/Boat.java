package battleship;

public class Boat extends Ship {
    private static final int WIDTH = 2;
    private static final int HEIGHT = 1;
    private int x;
    private int y;
    boolean[][] ship = new boolean[WIDTH][HEIGHT];

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getLocationX() {
        return x;
    }

    public int getLocationY() {
        return y;
    }

    public boolean[][] getShip() {
        createShip();
        return ship;
    }

    private void createShip() {
        ship[0][0] = true;
        ship[1][0] = true;
    }
}
