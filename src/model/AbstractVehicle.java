/**
 * Course: TCSS 305 C, Programming Practicum
 * Instructor: Tom Capaul
 * Programming Assignment 4: Road Rage
 *
 * This AbstractVehicle class is Parent class
 * for the subclasses that implement the
 * vehicle interface.
 */
package model;
/**
 * @author Sopheanith Ny
 * @version Autumn 2023
 *
 * Represent a vehicle collided, XY coordinate,
 * deathTime, direction, image, isAlive.
 */

public abstract class AbstractVehicle implements Vehicle {

    /**
     * private instance field for X coordinate of the vehicle.
     */
    private int myX;
    /**
     * private instance field for Y coordinate of the vehicle.
     */
    private int myY;
    /**
     * private instance field for initial X coordinate of the vehicle.
     */
    private final int myInitialX;
    /**
     * private instance field for initial Y coordinate of the vehicle.
     */
    private final int myInitialY;
    /**
     * private instance field for a Default direction of the vehicle.
     */
    private Direction myDirection;
    /**
     * private instance field for an initial default direction of the vehicle.
     */
    private final Direction myInitialDir;
    /**
     * private instance field death time of the vehicle.
     */
    private int myDeathTime;
    /**
     * private instance field if the car alive.
     */
    private boolean myIsAlive;
    /**
     * private instance field for poke count till the car alive.
     */
    private int myPokeCount;
    /**
     * Constructor for AbstractVehicle where all the private
     * instances field initialized.
     * @param theX the x coordinate of the vehicle.
     * @param theY the y coordinate of the vehicle.
     * @param theDir the direction of the vehicle.
     * @param theDeathTime the death count time for the vehicle.
     */
    protected AbstractVehicle(final int theX, final int theY,
                              final Direction theDir, final int theDeathTime) {
        this.myX = theX;
        this.myY = theY;
        myInitialX = theX;
        myInitialY = theY;
        myDeathTime = theDeathTime;
        this.myDirection = theDir;
        myInitialDir = theDir;
        myIsAlive = true;
        myPokeCount = 0;
    }

    /**
     * The collide method is when the vehicle has collided with
     * the other vehicle.
     * @param theOther The other object.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (getDeathTime() > theOther.getDeathTime() && isAlive()
                && theOther.isAlive()) {
            myIsAlive = false;
        }
    }

    /**
     * Getter for death time of the vehicle.
     * @return death time of the vehicle.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Setter for death time of the vehicle.
     * @param theDeathTime the death time the vehicle.
     */
    public void setDeathTime(final int theDeathTime) {
        this.myDeathTime = theDeathTime;
    }

    /**
     * Return the image the file for all vehicles for the GUI.
     * @return the dead vehicle and alive vehicle.
     */
    @Override
    public String getImageFileName() {
        final String image;
        if (isAlive()) {
            return getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            return getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }

    }

    /**
     * Getter for the get direction.
     * @return the directions of the vehicle facing.
     */
    @Override
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Getter for X coordinates.
     * @return X coordinates.
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Getter for Y coordinates.
     * @return Y coordinates.
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * Boolean if the car is alive or dead.
     * @return
     */
    @Override
    public boolean isAlive() {
        return myIsAlive;
    }

    /**
     * The Poke method is keeping track of the move
     * of a dead vehicle.
     */
    @Override
    public void poke() {
        if (!isAlive()) {
            if (myPokeCount == 0) {
                myIsAlive = true;
                setDirection(Direction.random());
                myPokeCount = myDeathTime;
            }
            myPokeCount--;
        }
    }

    /**
     * This Reset method is to reset the vehicle to
     * the original position where they start.
     */
    @Override
    public void reset() {
        myX = myInitialX;
        myY = myInitialY;
        myDirection = myInitialDir;
        myIsAlive = true;
    }

    /**
     *  Setter for the set direction of the vehicle.
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }

    /**
     * Setter for X coordinate.
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(final int theX) {
        this.myX = theX;
    }

    /**
     * Setter for Y coordinate.
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(final int theY) {
        this.myY = theY;
    }
}
