package battleship;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BattleFieldTest {
    private BattleField field;
    private Ship boat;
    private Ship submarine1;
    private Ship destroyer;
    private Ship submarine2;

    @Before
    public void setup() {
        field = new BattleField(10, 10);
        boat = new Boat();
        submarine1 = new Submarine();
        destroyer = new Destroyer();
        submarine2 = new Submarine();

        field.put(4, 8, boat);
        field.put(5, 1, submarine1);
        field.put(1, 4, destroyer);
        field.put(6, 5, submarine2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeWidthForBattleField() {
        BattleField field1 = new BattleField(-4, 5);
        fail("PENALTY: 3 points");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeHeightForBattleField() {
        BattleField field1 = new BattleField(4, -5);
        fail("PENALTY: 3 points");
    }

    @Test
    public void testGetReportWithHits1() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(5, 4); // a miss
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 2 points", set.size() > 0);
    }

    @Test
    public void testGetReportWithHits2() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(5, 4); // a miss
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertEquals("PENALTY: 4 points", 2, set.size());
    }

    @Test
    public void testGetReportWithHits3() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(5, 4); // a miss
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 4 points", set.contains(submarine1));
    }

    @Test
    public void testGetReportWithHits4() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(5, 4); // a miss
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 4 points", set.contains(destroyer));
    }

    @Test
    public void testGetReportWithMoreThan3Shots() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(5, 8); // hits the boat
        field.shoot(6, 1); // hits the upper submarine again
        field.shoot(2, 5); // hits the destroyer
        field.shoot(7, 1); // a miss
        field.shoot(4, 4); // a miss
        field.shoot(1, 4); // a miss
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 5 points", set.isEmpty());
    }

    @Test
    public void testGetReportWithLessThan3Shots() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 6 points", set.isEmpty());
    }

    @Test
    public void testGetReportWhenHittingTheSameShipTwice1() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(6, 1); // hits the upper submarine again
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 1 points", set.size() > 0);
    }

    @Test
    public void testGetReportWhenHittingTheSameShipTwice2() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(6, 1); // hits the upper submarine again
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertEquals("PENALTY: 2 points", 2, set.size());
    }

    @Test
    public void testGetReportWhenHittingTheSameShipTwice3() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(6, 1); // hits the upper submarine again
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 3 points", set.contains(submarine1));
    }

    @Test
    public void testGetReportWhenHittingTheSameShipTwice4() {
        field.shoot(5, 2); // hits the upper submarine
        field.shoot(6, 1); // hits the upper submarine again
        field.shoot(2, 5); // hits the destroyer
        Set<Ship> set = field.getReport();

        assertTrue("PENALTY: 3 points", set.contains(destroyer));
    }
}

//package battleship;
//
//import org.junit.Test;
//import java.util.Set;
//import static org.junit.Assert.*;
//
//public class BattleFieldTest {
//    private BattleField createBattleField1() {
//        BattleField field = new BattleField(10, 10);
//        Ship boat = new Boat();
//        Ship submarine1 = new Submarine();
//        Ship destroyer = new Destroyer();
//        Ship submarine2 = new Submarine();
//
//        field.put(4, 8, boat);
//        field.put(5, 1, submarine1);
//        field.put(1, 4, destroyer);
//        field.put(6, 5, submarine2);
//
//        return field;
//    }
//
//    private BattleField createBattleField2(int x, int y) {
//        BattleField field = new BattleField(x, y);
//        return field;
//    }
//
//    @Test
//    public void testProperWidthHeight(){
//        BattleField field = createBattleField2(3, 3);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testProperWidth1() {
//        BattleField field = createBattleField2(0, 10);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testProperWidth2() {
//        BattleField field = createBattleField2(-5, 10);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testProperHeight1() {
//        BattleField field = createBattleField2(10, 0);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testProperHeight2() {
//        BattleField field = createBattleField2(10, -5);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testShipLocation1() {
//        BattleField field = createBattleField1();
//        Ship boat = new Boat();
//        field.put(9, 9, boat);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testShipLocation2() {
//        BattleField field = createBattleField1();
//        Ship submarine = new Submarine();
//        field.put(8, 9, submarine);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testShipLocation3() {
//        BattleField field = createBattleField1();
//        Ship destroyer = new Destroyer();
//        field.put(7, 8, destroyer);
//    }
//
//    @Test
//    public void testShoot1(){
//        BattleField field = createBattleField1();
//        field.shoot(4, 8);
//        field.shoot(20, 30);
//        field.shoot(6, 6);
//
//        Set<Ship> report = field.getReport();
//        assertEquals(report.size(), 2);
//    }
//
//    @Test
//    public void testShoot2(){
//        BattleField field = createBattleField1();
//        field.shoot(4, 8);
//        field.shoot(2, 5);
//        field.shoot(6, 6);
//
//        Set<Ship> report = field.getReport();
//        assertEquals(report.size(), 3);
//    }
//
//    @Test
//    public void testShoot3(){
//        BattleField field = createBattleField1();
//        field.shoot(-5, -6);
//        field.shoot(20, 30);
//        field.shoot(10, 10);
//
//        Set<Ship> report = field.getReport();
//        assertEquals(report.size(), 0);
//    }
//}
