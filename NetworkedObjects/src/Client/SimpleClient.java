package Client;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.swing.*;
import javax.websocket.*;

import org.glassfish.tyrus.client.ClientManager;

import Client.MessagePanel;
import wsMessages.*;
import GameServer.*;

//import pokeClient.MessagePanel;


/**
 * This class combines (a bit awkwardly) GUI setup code and WebSockets
 * client-endpoint code. Note that most of the user interaction is handled by
 * the MessagePanel class. Note how the onMessage() method decides what to do
 * depending on the type of message received.
 * 
 * @author sdexter72
 *
 */

@ClientEndpoint( decoders={ MessageDecoder.class }, encoders={StartMessageEncoder.class, BeginGameEncoder.class}  )
public class SimpleClient {
	JButton start;
	JLabel idLabel;
	JFrame frame;
	private static CountDownLatch latch;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static MessagePanel messageArea;

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		try {
			session.getBasicRemote().sendText("start");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@OnMessage
	public void onMessage(Session session, Message message) {
		logger.info("Received ...." + message.toString());

		if (message instanceof StartMessage) {
			messageArea.receiveStart((StartMessage) message);
		} 
		if( message instanceof BeginGame) {
			messageArea.recieveBegin((BeginGame) message);
		}
	}


	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s",
				session.getId(), closeReason));
		latch.countDown();
	}

	public static void main(String[] args) {
		latch = new CountDownLatch(1);

		Session peer;
		ClientManager client = ClientManager.createClient();
		try {
			peer = client.connectToServer(SimpleClient.class, new URI(
					"ws://localhost:8025/websockets/game"));
			createAndShowGUI(peer);
			latch.await();

		} catch (DeploymentException | URISyntaxException
				| InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void createAndShowGUI(Session session) {
		JFrame frame = new JFrame("Game Lobby");
		JLabel idLabel = new JLabel("Your ID:");
		JTextField idField = new JTextField(String.valueOf(Math.round(Math
				.random() * 100000)),10);
		idField.setEditable(true);
		JButton start = new JButton("start");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(idLabel);
		buttonPanel.add(idField);
		buttonPanel.add(start);
		messageArea = new MessagePanel(session, idField, start);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.add(messageArea, BorderLayout.WEST);
		frame.setVisible(true);
	}
}
