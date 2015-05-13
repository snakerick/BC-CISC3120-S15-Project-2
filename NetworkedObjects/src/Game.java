import javax.swing.*;

import java.awt.event.*;


/**
 * 
 * A painted canvas in its own window, updated every 0.01 second. This class sets
 * up the basic logic for a "game canvas" on which the game objects will be
 * displayed and moved.
 * <p>
 * NOTE: You should <em>not</em> change any code in this class. You don't need
 * to understand it at this point, though studying it is a good idea.
 * 
 * @author Scott Dexter
 */

public class Game extends JPanel implements Runnable {
	Thread gameThread;
	protected boolean running;
	
	/**
	 * 
	 * Creates the game canvas. The canvas is a labeled window of the given
	 * dimensions.
	 * 
	 * @param name
	 *            String used to label the game window
	 * @param inWidth
	 *            Width of the game canvas
	 * @param inHeight
	 *            Height of the game canvas
	 */
	
	public Game(String name, int inWidth, int inHeight) {
		JFrame frame = new JFrame(name);
		frame.setSize(inWidth, inHeight);
		frame.add(this);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		running = true;		
		gameThread = new Thread(this);
	}
	
	/**
	 * Starts the 'animation' loop of the game
	 */
	
	public void startGame() {
		gameThread.start();
	}
	
	/**
	 * Specifies the basic action of the game animation
	 */
	
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.err.println("Game interrupted.");
			}
			repaint();
		}

	}


}
