/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This ATV class is child class
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
public class Atv extends AbstractVehicle {
    /**
     * private static instance field for the death time
     * of the ATV.
     */
    private static final int MY_DEATH_TIME_COUNTER = 25;
    /**
     * Constructor for the Taxi class.
     * @param theX The X coordinate of the vehicle.
     * @param theY The Y coordinate of the vehicle.
     * @param theDir The direction of the vehicle.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME_COUNTER);
    }

    /**
     * The canPass method is whether the ATV can move on
     * to the given type of the traffic light and terrain.
     * The ATV can travel everywhere except the wall.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return false if the terrain drives on the wall.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain != Terrain.WALL) {
            return true;
        }
        return false;
    }

    /**
     * The chooseDirection method is the direction of the object
     * like to move. The ATV drives through all traffic lights
     * without stopping.
     * @param theNeighbors The map of neighboring terrain.
     * @return ATV next direction to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction randomDir = Direction.random();
        while (getDirection().reverse() == randomDir
                || !canPass(theNeighbors.get(randomDir), Light.GREEN)) {
            randomDir = Direction.random();
        }
        return randomDir;
    }

}
