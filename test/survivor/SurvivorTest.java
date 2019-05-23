package survivor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SurvivorTest {
    @Test
    public void testInitialGrid() {
        Survivor survivor = new Survivor(5, 5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertFalse(survivor.isLive(i, j));
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumRows() {
        Survivor survivor = new Survivor(-2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumCols() {
        Survivor survivor = new Survivor(2, -3);
    }

    @Test
    public void testIsLive1() {
        Survivor survivor = new Survivor(5, 5);
        assertFalse("Coordinate out of bounds, isLive must return false!", survivor.isLive(6, 4));
    }

    @Test
    public void testIsLive2() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3 ,3);
        assertTrue("Cell 3, 3 is living, isLive method should return true!", survivor.isLive(3, 3));
    }

    @Test
    public void testSetLive1() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3 ,3);
        assertTrue("Cannot setLive specific cell!", survivor.isLive(3, 3));
    }

    @Test
    public void testSetLive2() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(6 ,3);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertFalse("Coordinate out of bounds, do nothing on grid!", survivor.isLive(i, j));
            }
        }
    }

    @Test
    public void testSetDead1() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3 ,3);
        survivor.setDead(3, 3);
        assertFalse(survivor.isLive(3, 3));
    }

    @Test
    public void testSetDead2() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3 ,3);
        survivor.setDead(6, 3);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != 3 && j != 3)
                    assertFalse("Coordinate out of bounds, do nothing on grid!", survivor.isLive(i, j));
            }
        }
        assertTrue(survivor.isLive(3, 3));
    }

    @Test
    public void testPassADayNoLive() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3, 3);
        survivor.passADay();
        assertFalse("No live neighbor, must be death!", survivor.isLive(3, 3));
    }

    @Test
    public void testPassADayOneLive() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3, 3);
        survivor.setLive(3, 4);
        survivor.passADay();
        assertFalse("One live neighbor, must be death!", survivor.isLive(3, 3));
        assertFalse("One live neighbor, must be death!", survivor.isLive(3, 4));
    }

    @Test // Dead cell with 3 living neighbors
    public void testPassADay1() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3, 3);
        survivor.setLive(3, 4);
        survivor.setLive(2, 3);
        survivor.passADay();
        assertTrue("Cell 3, 3 has two live neighbors, must survive!", survivor.isLive(3, 3));
        assertTrue("Cell 3, 4 has two live neighbors, must survive!", survivor.isLive(3, 4));
        assertTrue("Cell 2, 3 has two live neighbors, must survive!", survivor.isLive(2, 3));
        assertTrue("Cell 2, 4 has three live neighbors, must be live!", survivor.isLive(2, 4));
    }

    @Test
    public void testPassADay2() {
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3, 2);
        survivor.setLive(3, 3);
        survivor.setLive(2, 3);
        survivor.setLive(2, 2);
        survivor.setLive(2, 1);
        survivor.passADay();
        assertFalse("Cell 3, 2 has four live neighbors, must be death!", survivor.isLive(3, 2));
    }

    @Test
    public void testPassADay3() {
        Survivor survivor = new Survivor(5, 5);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                survivor.setLive(i, j);
            }
        }
        survivor.passADay();
        assertFalse("Cell 1, 1 has five live neighbors, must be death!", survivor.isLive(1, 1));
    }

    @Test
    public void testPassADay4() {
        Survivor survivor = new Survivor(5, 5);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                survivor.setLive(i, j);
            }
        }
        survivor.setLive(2, 0);
        survivor.passADay();
        assertFalse("Cell 1, 1 has six live neighbors, must be death!", survivor.isLive(1, 1));
    }

    @Test
    public void testPassADay5() {
        Survivor survivor = new Survivor(5, 5);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                survivor.setLive(i, j);
            }
        }
        survivor.setLive(2, 0);
        survivor.setLive(2, 1);
        survivor.passADay();
        assertFalse("Cell 1, 1 has seven live neighbors, must be death!", survivor.isLive(1, 1));
    }

    @Test
    public void testPassADay6(){
        Survivor survivor = new Survivor(5, 5);
        survivor.setLive(3, 1);
        survivor.setLive(2, 3);
        survivor.setLive(4, 3);
        survivor.passADay();
        assertTrue("Cell 3, 2 has three live neighbors, must be live!", survivor.isLive(3, 2));
        assertFalse("Cell 3, 1 has no live neighbors, must be death!", survivor.isLive(3, 1));
        assertFalse("Cell 2, 3 has no live neighbors, must be death!", survivor.isLive(2, 3));
        assertFalse("Cell 4, 3 has no live neighbors, must be death!", survivor.isLive(4, 3));
    }

    @Test
    public void testPassADayEightNeighborCell() {
        Survivor survivor = new Survivor(5, 5);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                survivor.setLive(i, j);
            }
        }
        survivor.passADay();
        assertFalse("Cell 1, 1 has eight live neighbors, must be death!", survivor.isLive(1, 1));
        assertTrue("Cell 3, 1 has three live neighbors, must be live!", survivor.isLive(3, 1));
        assertTrue("Cell 1, 3 has three live neighbors, must be live!", survivor.isLive(1, 3));
        assertTrue("Cell 0, 0 has three live neighbors, must survive!", survivor.isLive(0, 0));
        assertTrue("Cell 2, 0 has three live neighbors, must survive!", survivor.isLive(2, 0));
        assertTrue("Cell 0, 2 has three live neighbors, must survive!", survivor.isLive(0, 2));
        assertTrue("Cell 2, 2 has three live neighbors, must survive!", survivor.isLive(2, 2));
    }
}

