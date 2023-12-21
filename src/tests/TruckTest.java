/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Truck Test class is a test
 * class that tests all the method in
 * truck class.
 */
package tests;
import static org.junit.jupiter.api.Assertions.*;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Sopheanith Ny
 * @version Autumn 2023
 *
 * Represent Unit test for Truck class.
 */
public class TruckTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int myTiredRandom = 50;

    /** Test method for Human constructor. */
    @Test
    void testTruckConstructor() {
        final Truck myTruck = new Truck(5, 7, Direction.NORTH);
        assertEquals(5, myTruck.getX(), "Truck X coordinate not initialized correctly!");
        assertEquals(7, myTruck.getY(), "Truck Y coordinate not initialized correctly!");
        assertEquals(Direction.NORTH, myTruck.getDirection(),"Truck direction not initialized correctly!");
        assertEquals(0, myTruck.getDeathTime(), "Truck death time not initialized correctly!");
    }

    /** Test method for Human setters. */
    @Test
    void TestTruckSetter() {
        final Truck myTruck = new Truck(5, 7, Direction.NORTH);
        myTruck.setX(14);
        assertEquals(14, myTruck.getX(),"Truck set X is failed!");
        myTruck.setY(9);
        assertEquals(9, myTruck.getY(),"Truck set Y is failed!");
        myTruck.setDirection(Direction.WEST);
        assertEquals(Direction.WEST, myTruck.getDirection(), "Truck setDirection is failed!");
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    void TestTruckCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);

        final Truck myTruck = new Truck (0,0, Direction.NORTH);
        for (final Terrain destinationTerrain : Terrain.values()) {
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    assertTrue(myTruck.canPass(destinationTerrain, currentLightCondition),
                            "Truck should able to drive on the street! " + currentLightCondition);
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    if (currentLightCondition == Light.RED) {
                        assertFalse(myTruck.canPass(destinationTerrain, currentLightCondition),
                                "Truck should not pass on " + destinationTerrain
                                        + ", with light " + currentLightCondition);
                    } else {
                        assertTrue(myTruck.canPass(destinationTerrain, currentLightCondition),
                                "Truck should pass " + destinationTerrain
                                        + ", with light " + currentLightCondition);
                    }
                }
            }
        }
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    void testTruckChooseDirectionSurroundedByStreet() {
        final Map<Direction, Terrain> theNeighbors = new HashMap<Direction, Terrain>();
        theNeighbors.put(Direction.SOUTH, Terrain.STREET);
        theNeighbors.put(Direction.NORTH, Terrain.STREET);
        theNeighbors.put(Direction.WEST, Terrain.STREET);
        theNeighbors.put(Direction.EAST, Terrain.STREET);

        boolean seenSouth = false;
        boolean seenNorth = false;
        boolean seenWest = false;
        boolean seenEast = false;

        final Truck myTruck = new Truck(0,0, Direction.NORTH);

        for(int count = 0; count < myTiredRandom; count++) {
            final Direction myTruckDir = myTruck.chooseDirection(theNeighbors);
            if (myTruckDir == Direction.SOUTH) {
                seenSouth = true;
            } else if (myTruckDir == Direction.NORTH) {
                seenNorth = true;
            } else if (myTruckDir == Direction.WEST) {
                seenWest = true;
            } else if (myTruckDir == Direction.EAST) {
                seenEast = true;
            }
        }
        assertTrue( seenNorth && seenWest && seenEast,
                "Truck chooseDirection() fail to select randomly "
                        + "among all possible valid choices!");

        assertFalse(seenSouth, "Human chooseDirection() reversed direction when not necessary!");
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    void testChooseDirectionMustReverse() {
        for (final Terrain myTerrain : Terrain.values()) {
            if (myTerrain == Terrain.GRASS) {
                final Map<Direction, Terrain> theNeighbors = new HashMap<Direction, Terrain>();
                theNeighbors.put(Direction.WEST, myTerrain);
                theNeighbors.put(Direction.NORTH, myTerrain);
                theNeighbors.put(Direction.EAST, myTerrain);
                theNeighbors.put(Direction.SOUTH, myTerrain);

                final Truck myTruck = new Truck(0 ,0, Direction.NORTH);
                assertEquals(Direction.SOUTH, myTruck.chooseDirection(theNeighbors),
                        "Truck chooseDirection() failed when reverse was the only valid choice!");
            }
        }
    }
}
