package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import wsMessages.StartMessage;


public class MessagePanel extends JPanel implements ActionListener {
	
	private JTextField id;
	private JButton start;
	Session session;
	
	
	JTextArea messageArea;
	private String myId;
	
	public MessagePanel(Session session, JTextField id, JButton start) {
		this.session = session;
		this.id = id;
		this.start = start;
		myId = id.getText();	
		id.addActionListener(this);
		start.addActionListener(this);
		messageArea = new JTextArea(8, 25);
		JScrollPane scroller = new JScrollPane(messageArea);
		this.add(scroller);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == id)
			myId = id.getText();
		else if (e.getSource() == start) {
			doStart();
		}

	}

	private void doStart() {
		StartMessage startMsg = new StartMessage(myId);
		try {
			session.getBasicRemote().sendObject(startMsg);
		} catch (IOException | EncodeException e) {
			System.err.println("Problem with sending a start-message.");
		}
		
	}
	
	public void receivePoke(StartMessage startMsg) {
		messageArea.append(startMsg.getID() + " is ready.\n");
		
	}

}
