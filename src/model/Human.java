/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Human class is child class
 * for the subclasses that implement the
 * vehicle interface.
 */
package model;
import java.util.Map;
/**
 * @author Sopheanith Ny
 * @version Autumn 2023
 *
 * Represent chooseDirection, canPass
 * and help method for chooseDirection.
 */
public class Human extends AbstractVehicle {
    /**
     * private static instance field for the death time
     * of the Human.
     */
    private static final int MY_DEATH_TIME_COUNTER = 45;
    /**
     * Constructor for the Human class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
    }
    /**
     * The canPass method is whether the Human can move on
     * to the given type of the traffic light and terrain.
     * The Human cannot be on the street, it can only on the
     * grass and should cross the street when it is
     * yellow and red.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return false if the Human cannot pass, otherwise
     * it will return true,
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        Boolean canPass = false;
        if (theTerrain == Terrain.GRASS) {
            canPass = true;
        }
        if (theTerrain == Terrain.CROSSWALK
                && (theLight == Light.RED || theLight == Light.YELLOW)) {
            canPass = true;
        }
        return canPass;
    }

    /**
     * The chooseDirection method is the direction of the object
     * like to move. The Human moves in a random direction straight,
     * left, right, but it cannot reverse, and it always walks on the
     * grass or crosswalks.
     * @param theNeighbors The map of neighboring terrain.
     * @return Human next direction to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction dir = getDirection().reverse();
        if (canMoveTer(theNeighbors.get(getDirection()))
                || canMoveTer(theNeighbors.get(getDirection().left()))
                || canMoveTer(theNeighbors.get(getDirection().right()))) {
            if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
                dir = getDirection();
            } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
                dir = getDirection().left();
            } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
                dir = getDirection().right();
            } else {
                while (getDirection().reverse() == dir
                        || !canPass(theNeighbors.get(dir), Light.GREEN)) {
                    dir = Direction.random();
                }
            }
        }
        return dir;
    }
    /**
     * Help method to check if the terrain is crosswalk and grass.
     * @param theTerrain
     * @return True if the terrain is on the crosswalk and grass
     */
    protected static boolean canMoveTer(final Terrain theTerrain) {
        return theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.GRASS;
    }

}
