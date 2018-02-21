package com.hackerhop.game.core.utils;

/**
 * Utility class helping with setting and getting 2D positions.
 * Helps to store coordinates for an element.
 */
public class Position {
    private double x, y;

    /**
     * Creates a new position, storing both coordinates.
     * @param x The x position of this element.
     * @param y The y position of this element.
     */
    public Position(double x, double y) {
        setPosition(x, y);
    }

    /**
     * Sets the position to new coordinates.
     * @param x The new x position of this element.
     * @param y The new y position of this element.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x position of the element.
     * @return the x position of the element.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y position of the element.
     * @return the y position of the element.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the position to a new value.
     * @param x the new x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the position to a new value.
     * @param y the new y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }
}
