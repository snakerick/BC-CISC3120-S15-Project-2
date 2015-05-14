package GameServer;
import java.io.IOException;
import java.util.logging.*;

import javax.websocket.*;


import javax.websocket.server.ServerEndpoint;
import wsMessages.*;

/**
 * What does this server do when it receives a message from a client?
 * 
 * @author sdexter72
 *
 */
 
@ServerEndpoint(value = "/game", decoders={ MessageDecoder.class }, encoders={StartMessageEncoder.class} )
public class ServerEndPoints {
	private static int Players = 0;
	private static int startNum = 0;
    private Logger logger = Logger.getLogger(this.getClass().getName());
 
    @OnOpen
    public void onOpen(Session peer) {
    	//This keeps count of the number of players in the game;
    	Players++;
        logger.info("Connected ... " + peer.getId());
    }
 
   @OnMessage
    public void onMessage(Session peer, Message msg) throws EncodeException {
        logger.log(Level.FINE, "Message {0} from {1}", new Object[]{msg, peer.getId()});
        startNum++;
        for (Session other : peer.getOpenSessions()) {
            try {
                other.getBasicRemote().sendObject(msg);
            } catch (IOException ex) {
                Logger.getLogger(ServerEndPoints.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	//When a player disconnects
    	Players--;
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
    
 
}
