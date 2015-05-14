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

public class SimpleChecker extends JFrame implements KeyListener {
	private int a = 0;
	private int b = 0;
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
	public void keyReleased(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		a -= 2;
    		model.keypressed(a,b);
    		System.out.println("Right Released");
    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		a += 2;
    		model.keypressed(a,b);
    		System.out.println("Left Released");
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		System.out.println("Up was Released");
    		b -= 2;
    		model.keypressed(a,b);
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		System.out.println("Down was Released");
    		model.keypressed(a,b);
    		b += 2;
    	}
    }
	
    public void keyPressed(KeyEvent e) {

    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		System.out.println("Right was Pressed");
    		model.keypressed(a,b);
    		a += 2;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		System.out.println("Left was Pressed");
    		model.keypressed(a,b);
    		a -= 2;
    	}
      	if(e.getKeyCode() == KeyEvent.VK_UP) {
    		System.out.println("Up was Pressed");
    		model.keypressed(a,b);
    		b -= 2;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    		System.out.println("Down was Pressed");
    		model.keypressed(a,b);
    		b += 2;
    	}
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*SimpleGame game = new SimpleGame("Simple Game", 400, 900);
	game.requestFocus();
	game.startGame();*/
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

