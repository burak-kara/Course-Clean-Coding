package battleship;

public abstract class Ship {
    // (x,y): The location of the top-left corner of the imaginary
    // box that surrounds the ship.
    public abstract void setLocation(int x, int y);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLocationX();

    public abstract int getLocationY();

    public abstract boolean[][] getShip();
}
