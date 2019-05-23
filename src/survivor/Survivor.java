package survivor;

public class Survivor {
    // Creates a world where each cell is initially dead
    // Throws IllegalArgumentException if numRows or numCols is not positive.
    public Survivor(int numRows, int numCols) {

    }

    // Makes the specified cell live.
    // Does nothing if the specified coordinate is out of bounds
    public void setLive(int row, int col) {

    }

    // Makes the specified cell dead
    // Does nothing if the specified coordinate is out of bounds
    public void setDead(int row, int col) {

    }

    // Returns false if the specified coordinate is out of bounds
    public boolean isLive(int row, int col) {
        throw new Error("Not implemented.");
    }

    // Make the cells live or dead according to the following rules:
    // * A live cell with one or no live neighbors dies.
    // * A live cell with four or more neighbors dies.
    // * A live cell with two or three neighbors survives.
    // * A dead cell with three live neighbors becomes live.
    public void passADay() {

    }
}
