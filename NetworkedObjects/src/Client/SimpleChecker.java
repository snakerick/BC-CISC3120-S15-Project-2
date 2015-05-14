package Client;

import GameObjects.ControlledObject;
import GameObjects.Point;
import GameObjects.SimpleSpaceObject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

/**
 * This is the "Controller" of the MVC this allows the player to move the object the way they want
 * the movement is a bit sluggish cause of the "repaint"
 * 
 * @author RICKY
 *
 */

public class SimpleChecker extends JFrame implements KeyListener {
	private int a = -1;
	private int b = -1;
	private SimpleModel model;
	private SimpleGame paintGame;
	Thread gameThread;
	SimpleSpaceObject ship;
	
	public SimpleChecker() {
		super("Simple Game");
		setSize(400,900);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		model = new SimpleModel();
		paintGame = new SimpleGame();
		add(paintGame);
		model.addObserver(paintGame);
		model.newGame();
		addKeyListener(this);
		setVisible(true);
	}
    public void keyPressed(KeyEvent e) {

    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		a += 1;
    		model.keypressed(a,b);
    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		a -= 1;
    		model.keypressed(a,b);
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		b -= 1;
    		model.keypressed(a,b);
    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		b += 1;
    		model.keypressed(a,b);
    		
    	}
    }
	public void keyReleased(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		a += 1;
    		model.keypressed(a,b);
    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		a -= 1;
    		model.keypressed(a,b);
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		b -= 1;
    		model.keypressed(a,b);
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		b += 1;
    		model.keypressed(a,b);
    		
    	}
    }
	

	@Override
	public void keyTyped(KeyEvent e) {} //not needed
	
	public static void main(String[] args) {

		EventQueue.invokeLater(
				new Runnable() {
					@Override
					public void run() {
						SimpleChecker game = new SimpleChecker();

					}
				});
	}
}

