import java.util.*;

/**
 * 
 * Models a point in 2-D space. 
 *
 * <p>
 * You may find that you need to modify the code in this class, or add
 * members/methods; feel free, but of course be sure to document. (For example,
 * what if you need to generate a random (x,y) value?)
 * 
 * @author Scott Dexter
 *
 */
class Point implements Cloneable {
	double x, y;
	protected static int MAX = 75;

	public Point(double inX, double inY) {
		x = inX;
		y = inY;
	}
	
	//This creates a random (x,y) if nothing is passed through.
	public Point(){
		Random rand = new Random();
		double randx = rand.nextInt(MAX);
		double randy = rand.nextInt(MAX);
		x = randx;
		y = randy;
	}
	/** 
	 * A little bit of magic to make it easy to copy Points.
	 * 
	 * This is slightly advanced Java; don't worry about grasping the details!
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Point clone() {
		try {
			return (Point) super.clone();
		} catch (CloneNotSupportedException e) {
			//  Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}