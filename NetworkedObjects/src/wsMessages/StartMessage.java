package wsMessages;

public class StartMessage extends Message {

	String id; 
	
	public StartMessage(String id) {
		this.id = id;
	}
	
	public String getID() { return id; }
}
