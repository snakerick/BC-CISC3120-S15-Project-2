package GameObjects;
import java.awt.Graphics;
import java.awt.event.*;



/**
 * This was the previously used to controll the ship
 * 
 * @author RICKY
 *
 */
public class ControlledObject extends SpaceObjectDecorator {
	static int a,b;
	public ControlledObject(SpaceObject decoratedObject) {
		super(decoratedObject);

	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public void move() {
		super.move(a,b);
	}
	
	@Override
	public void collide(SpaceObject obj) {
		super.collide(obj);
	}
	
	//This allows the user to move the ship left, right up, down
	public static void keyReleased(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		System.out.println("Right was Pressed");
    		a += 1;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		System.out.println("Left was Pressed");
    		a -= 1;
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		System.out.println("Up was Pressed");
    		b -= 1;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		System.out.println("Down was Pressed");
    		b += 1;
    	}
    }
	
    public static  void keyPressed(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		a -= 1;
    		System.out.println("Right Released");
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		a += 1;
    		System.out.println("Left Released");
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		System.out.println("Up was Released");
    		b -= 1;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		System.out.println("Down was Released");
    		b += 1;
    	}
    }

    
	@Override
	public Polygon getPoly() {
		// TODO Auto-generated method stub
		return decoratedObject.getPoly();
	}
	
}
