import java.awt.*;

/**
 * 
 * Describes the fundamental behavior of a SpaceObject. Obviously, there are no implementation details here.
 * 
 * Our design assumes that a SpaceObject 'floats' in two-dimensional space. 
 * 
 * @author sdexter72
 *
 */

public interface SpaceObject {

	/**
	 * Move the SpaceObject in space
	 * @param x number of pixels to move horizontally (positive=rightward)
	 * @param y number of pixels to move vertically (positive=downward)
	 */
	public void move(int x, int y);
	
	/**
	 * Rotate the SpaceObject in space
	 * @param r number or degrees to rotate (positive=clockwise)
	 */
	public void rotate(double r);
	
	/**
	 * Paint the SpaceObject on a suitable component
	 * @param g
	 */
	public void paint(Graphics g);
	
	/**
	 * Deal with a collision with another object
	 * @param obj the other object involved in the collision
	 */
	public void collide(SpaceObject obj);
	
	/**
	 * Returns the Polygon shape which is used to check for collide
	 */
	public Polygon getPoly();
	
}