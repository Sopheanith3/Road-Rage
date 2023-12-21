/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Taxi class is child class
 * for the subclasses that implement the
 * vehicle interface.
 */
package model;
import java.util.Map;
/**
 * @author Sopheanith Ny
 * @version Autumn 2023
 *
 * Represent chooseDirection, and canPass.
 */
public class Taxi extends AbstractVehicle {
    /**
     * private static instance field for the death time
     * of the Taxi.
     */
    private static final int MY_DEATH_TIME_COUNTER = 15;
    /**
     * private static instance field for default counts
     * for the red light at the crosswalk.
     */
    private static final int MY_CLOCK_CYCLE = 3;
    /**
     * private instance field for the count till the
     * taxi can move forward.
     */
    private int myRedCount;

    /**
     * Constructor for the Taxi class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
        myRedCount  = MY_CLOCK_CYCLE;
    }

    /**
     * The canPass method is whether the Taxi can move on
     * to the given type of the traffic light and terrain.
     * The taxi stops on the red light, and it can ignore yellow
     * and green light. Also, a car stops on the red and yellow
     * crosswalk and drives through green light without stopping.
     * The taxi will stop at a red crosswalk for 3second and
     * will continue to move forward.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return False when the terrain is wall, trail and lights.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = true;
        if (theTerrain == Terrain.WALL || theTerrain == Terrain.TRAIL
                || theTerrain == Terrain.GRASS) {
            pass = false;
        }
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            pass = false;
        }
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            pass = false;
            if (myRedCount == 0) {
                myRedCount = MY_CLOCK_CYCLE;
                pass = true;
            }
            myRedCount--;
        }
        return pass;
    }

    /**
     * The chooseDirection method is the direction of the object
     * like to move. The taxi prefers to drive straight, if it cannot
     * move ahead, it will turn left, if not, it will turn right possible
     * and it not it will turn around without reversing the taxi.
     * @param theNeighbors The map of neighboring terrain.
     * @return the taxi next direction to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction direction;
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection()) == Terrain.STREET
                || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            direction = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().left()) == Terrain.STREET
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {
            direction = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().right()) == Terrain.STREET
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {
            direction = getDirection().right();
        } else {
            direction = getDirection().reverse();
        }
        return direction;
    }
}
