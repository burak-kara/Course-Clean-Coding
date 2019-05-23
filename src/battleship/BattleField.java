package battleship;

import java.util.Set;
import java.util.HashSet;

public class BattleField {
    private int width;
    private int height;
    private int shoots;
    private boolean[][] field;
    private HashSet<Ship> ships;
    private HashSet<Ship> hitShips;

    public BattleField(int width, int height) {
        assignBorders(width, height);
        shoots = 0;
        field = new boolean[width][height];
        ships = new HashSet<>();
        hitShips = new HashSet<>();
    }

    private void assignBorders(int width, int height) {
        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
        } else
            throw new IllegalArgumentException();
    }

    public void put(int x, int y, Ship ship) {
        if (isShipFit(x, y, ship)) {
            ship.setLocation(x, y);
            ships.add(ship);
            addShip(x, y, ship);
        } else
            throw new IllegalArgumentException();
    }

    private boolean isShipFit(int x, int y, Ship ship) {
        boolean widthBound = x + ship.getWidth() < width;
        boolean heightBound = y + ship.getHeight() < height;
        return x > -1 && y > -1 && widthBound && heightBound;
    }

    private void addShip(int x, int y, Ship ship) {
        boolean[][] shipArea = ship.getShip();
        int locationX = 0;
        int locationY = 0;
        for (int i = x; i < x + ship.getWidth(); i ++) {
            for (int j = y; j < y + ship.getHeight(); j++) {
                field[i][j] = shipArea[locationX][locationY];
                locationX++;
                locationY++;
            }
        }
    }

    public void shoot(int x, int y) {
        shoots++;
        if (field[x][y]) {

        }
    }

    public Set<Ship> getReport() {
        return shoots < 3 ? null : hitShips;
    }
}