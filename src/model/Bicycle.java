/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Bicycle class is child class
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
public class Bicycle extends AbstractVehicle {

    /**
     * private static instance field for the death time
     * of the Bicycle.
     */
    private static final int MY_DEATH_TIME_COUNTER = 35;

    /**
     * Constructor for the Bicycle class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Bicycle(final int theX, final int theY,
                   final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
    }

    /**
     * The canPass method is whether the Bicycle can move on
     * to the given type of the traffic light and terrain.
     * The Bicycle can be on the grass, rails and on the
     * street except the wall.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return false if the bicycle cannot pass, otherwise
     * it will return true,
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean goThrough = false;
        if (theTerrain == Terrain.TRAIL) {
            goThrough = true;
        }
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            goThrough = true;
        }
        if (theTerrain == Terrain.LIGHT && theLight == Light.YELLOW) {
            goThrough = false;
        }
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            goThrough = false;
        }
        return goThrough;

    }

    /**
     * The chooseDirection method is the direction of the object
     * like to move. The Bicycle drives always go straight ahead in
     * the direction. The trails are guaranteed to be straight, and
     * the bicycle will never start on trail facing the terrain it cannot
     * traverse.
     * @param theNeighbors The map of neighboring terrain.
     * @return Bicycle next direction to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction dir;
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            dir = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            dir = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            dir = getDirection().right();
        } else if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection()) == Terrain.STREET
                || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            dir = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().left()) == Terrain.STREET
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {
            dir = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().right()) == Terrain.STREET
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {
            dir = getDirection().right();
        } else {
            dir = getDirection();
        }
        return dir;
    }

}
