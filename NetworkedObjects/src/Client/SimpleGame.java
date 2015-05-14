package Client;

import GameObjects.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observer;
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

import Client.SimpleGame;

import java.util.Observer;
import java.util.Observable;


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
@ClientEndpoint( )
public class SimpleGame extends Game implements Observer {
	private static CountDownLatch latch;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private SimpleModel model;
	//private static MessagePanel messageArea;
	/**
	 * The lone 'object' in our simple game.
	 */

	
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
	


	/**
	 * This constructor invokes the super constructor, then creates a ship
	 * object (which doesn't do very much)
	 * 
	 * @param name
	 * @param inWidth
	 * @param inHeight
	 */

	public SimpleGame() {
		//super(name, inWidth, inHeight);
		setBackground(Color.BLACK);
		//addKeyListener(new KeyboardAdapter() );
		//createObjects(MAX_OBJECTS);
	}

	/**
	 * Draw the ship in white.
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: ",25,25);
		g.drawString(Integer.toString(model.SCORE),80,25);
		//model.ship.paint(g);
		model.controlShip.paint(g);
		for(int i = 0 ; i < model.MAX_OBJECTS ; i++ ) {
			model.gameObjects[i].paint(g);
		}
		if( model.checkCollusion() ) {
			g.drawString("Game Over", 150, 200);
		} else {
			g.drawString(Integer.toString(model.SCORE), 80, 25);
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		model = (SimpleModel) o;
		repaint();
	}


}
