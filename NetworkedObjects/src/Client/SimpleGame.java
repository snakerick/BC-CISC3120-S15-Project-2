package Client;

import GameObjects.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import wsMessages.MessageDecoder;
import wsMessages.PokeMessageEncoder;
import wsMessages.PrickleMessageEncoder;
import wsMessages.ProdMessageEncoder;
import Client.SimpleGame;

/**
 * A very simple example of how to use the Game base class.
 * 
 * Here, we provide a constructor for our game, override the JPanel
 * paintComponent() method, and write a simple main() method that creates and
 * starts the game.
 * 
 * @author sdexter72
 *
 */
@ClientEndpoint(decoders = { MessageDecoder.class }, encoders = {
		PokeMessageEncoder.class, ProdMessageEncoder.class, PrickleMessageEncoder.class })
public class SimpleGame extends Game {
	private static CountDownLatch latch;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	//private static MessagePanel messageArea;
	/**
	 * The lone 'object' in our simple game.
	 */
	SimpleSpaceObject ship;
	ControlledObject controlShip;
	SpaceObject object;
	SpaceObject [] gameObjects;
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		try {
			session.getBasicRemote().sendText("start");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s",
				session.getId(), closeReason));
		latch.countDown();
	}
	
	//Variables needed for game to keep track of game
	protected static int MAX_OBJECTS = 4;
	protected static int LEVELS = 5;
	protected static int SCORE = 0;
	protected static int MAX_X = 400;
	protected Random randNum = new Random();


	/**
	 * This constructor invokes the super constructor, then creates a ship
	 * object (which doesn't do very much)
	 * 
	 * @param name
	 * @param inWidth
	 * @param inHeight
	 */

	public SimpleGame(String name, int inWidth, int inHeight) {
		super(name, inWidth, inHeight);
		setBackground(Color.BLACK);
		addKeyListener(new KeyboardAdapter() );
		Point[] shipShape = { new Point(210, 100), new Point(190, 90),
				new Point(200, 100), new Point(190, 110) };
		ship = new SimpleSpaceObject(shipShape, new Point(200, 300), -90);
		controlShip = new ControlledObject(ship);
		createObjects(MAX_OBJECTS);
	}

	/**
	 * Draw the ship in white.
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: ",25,25);
		g.drawString(Integer.toString(SCORE),80,25);
		controlShip.paint(g);
		controlShip.move();
		
		//This paints, move and rotates the gameObjects. It also check if space collides
		for(int i = 0 ; i < gameObjects.length ; i++ ){
			gameObjects[i].paint(g);
			gameObjects[i].move(LEVELS, LEVELS);
			gameObjects[i].rotate(LEVELS);
			
			//If ship collides the area will get set to 0; 
			ship.collide(gameObjects[i]);
			if(ship.getPoly().findArea() == 0) {		//If the area is 0 then the game is over
				g.drawString("Game Over", 150, 200);
			} 
			else{ 										//If not the score continue to add up
				SCORE++;
				g.drawString(Integer.toString(SCORE),80,25);
			}
		}
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

	
	private static void createAndShowGUI(Session session) {
		System.out.println("YAOOO");
		//SimpleGame game = new SimpleGame("Simple Game", 400, 900);
		//game.requestFocus();
		//game.startGame();
	}
	
	/**
	 * In main, we create a new SimpleGame, make sure it has the keyboard focus
	 * (which it will need when we implement code to control game action with
	 * keyboard), and start the game.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		latch = new CountDownLatch(1);

		Session peer;
		ClientManager client = ClientManager.createClient();
		try {
			peer = client.connectToServer(Game.class, new URI("ws://localhost:8025/websockets/game"));
			createAndShowGUI(peer);
			latch.await();

		} catch (DeploymentException | URISyntaxException
				| InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
