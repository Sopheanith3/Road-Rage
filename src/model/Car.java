/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Car class is child class
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
public class Car extends AbstractVehicle {
    /**
     * private static instance field for the death time
     * of the Car.
     */
    private static final int MY_DEATH_TIME_COUNTER = 15;
    /**
     * Constructor for the Car class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
    }

    /**
     * The chooseDirection method is the direction of the object
     * like to move. The car prefers to drive straight, if it cannot
     * move ahead, it will turn left, if not, it will turn right possible
     * and it not it will turn around without reversing the car.
     * @param theNeighbors The map of neighboring terrain.
     * @return Car next direction to move.
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

    /**
     * The canPass method is whether the Car can move on
     * to the given type of the traffic light and terrain.
     * The car stops on the red light, and it can ignore yellow
     * and green light. Also, a car stops on the red and yellow
     * crosswalk and drives through green light without stopping.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.STREET) {
            canPass = true;
        } else if (theTerrain == Terrain.LIGHT
                && (theLight == Light.GREEN || theLight == Light.YELLOW)) {
            canPass =  true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            canPass = true;
        }
        return canPass;
    }
}
