import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public class KeyboardAdapter extends KeyAdapter implements KeyListener {
	public void keyReleased(KeyEvent e) {
    	ControlledObject.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
    	ControlledObject.keyPressed(e);
    }

}