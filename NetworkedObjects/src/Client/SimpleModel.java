package Client;


import java.awt.Graphics;
import java.util.Observable;

import GameObjects.*;

import java.util.Random;

import GameObjects.*;

public class SimpleModel extends Observable {
	Thread gameThread;
	SpaceObject ship;
	ControlledObject controlShip;
	SpaceObject object;
	SpaceObject [] gameObjects;
	protected static int MAX_OBJECTS = 4;
	protected static int LEVELS = 2;
	protected static int SCORE = 0;
	protected static int MAX_X = 5;
	protected Random randNum = new Random();
	protected Polygon shape;
	
	public SimpleModel(){
		Point[] shipShape = { new Point(210, 100), new Point(190, 90),
				new Point(200, 100), new Point(190, 110) };
		ship = new SimpleSpaceObject(shipShape, new Point(200, 500), -90);
		controlShip = new ControlledObject(ship);
		//notifyObservers();	
		gameThread = new Thread();
		gameThread.start();
		setChanged();
	}
	
	//This creates the game Objects depending on how many objects you want to create
	public void createObjects(int objSize) {
		gameObjects = new SpaceObject[objSize];
		for(int i = 0 ; i < gameObjects.length ; i++){
			int randX = randNum.nextInt(MAX_X);
			Point[] testObj = { new Point(), new Point(), new Point(), new Point(), new Point() };
			object = new SimpleSpaceObject(testObj, new Point(randX, 50), -90);
			gameObjects[i] = new FallingObject(new SpinningObject(object));
		}
	}
	
	public void newGame() {
		createObjects(MAX_OBJECTS);
		notifyObservers();	
			
	}
	
	public boolean checkCollusion() {
		//This paints, move and rotates the gameObjects. It also check if space collides
		for(int i = 0 ; i < gameObjects.length ; i++ ){
			gameObjects[i].move(LEVELS, LEVELS);
			gameObjects[i].rotate(LEVELS);
			
			//If ship collides the area will get set to 0; 
			ship.collide(gameObjects[i]);
			if(ship.getPoly().findArea() == 0) {		//If the area is 0 then the game is over
				return true;
			} 
			else{ 										//If not the score continue to add up
				SCORE++;
			}
		}
		notifyObservers();
		setChanged();
		return false;
	}
	
	public void keypressed(int a, int b) {
		try {
			Thread.sleep(10);
			ship.move(a,b);
			notifyObservers();
			setChanged();
		}catch (InterruptedException e) {
			System.err.println("Game interrupted.");
		}
		
	}
		
}
