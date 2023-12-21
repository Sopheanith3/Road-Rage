/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This Truck class is child class
 * for the subclasses that implement the
 * vehicle interface.
 */
package model;
import java.util.Map;
/**
 * @author Sopheanith Ny
 * @version Autumn 2023
 *
 * Represent chooseDirection, canPass and
 * help method, for canPass.
 */
public class Truck extends AbstractVehicle {

    /**
     * private static instance field for the death time
     * of the Truck.
     */
    private static final int MY_DEATH_TIME_COUNTER = 0;

    /**
     * Constructor for the Truck class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
    }

    /**
     * ChooseDirection method is the direction of the object
     * like to move. The truck can randomly go right, left
     * and straight without stopping. Also, the truck will
     * reverse when there is no move.
     * @param theNeighbors The map of neighboring terrain.
     * @return The Truck next direction.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction turn = getDirection().reverse();
        if (theNeighbors.get(getDirection()) == Terrain.STREET
                || theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            turn = randomDir(theNeighbors);
        }
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            turn = randomDir(theNeighbors);
        }
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            turn = randomDir(theNeighbors);
        }
        return turn;
    }

    /**
     * The canPass method is whether the Truck can move on
     * to the given type of the traffic light and terrain.
     * The truck shouldn't pass the crosswalk when the light
     * turns red, but it can drive through yellow or green
     * crosswalk light without stopping.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return false whether the object may not go through the
     * giving type traffic light and terrain.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.LIGHT) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight != Light.RED
                || theTerrain == Terrain.STREET) {
            return true;
        }
        return false;
    }

    /**
     * The randomDir method is a helper method for a random direction.
     * The loop is when the direction is not valid and it's loops and
     * recall that direction again until it is valid.
     * @param theNeighbors
     * @return random truck next direction to move.
     */
    protected Direction randomDir(final Map<Direction, Terrain> theNeighbors) {
        Direction random = Direction.random();
        while (getDirection().reverse() == random
                || !canPass(theNeighbors.get(random), Light.GREEN)) {
            random = Direction.random();
        }
        return random;
    }


}
