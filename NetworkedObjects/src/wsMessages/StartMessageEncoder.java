package wsMessages;

import javax.json.*;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class StartMessageEncoder implements Encoder.Text<StartMessage> {

	@Override
	public String encode(StartMessage msg) throws EncodeException {
		JsonObject jsonStartMessage = Json.createObjectBuilder()
				.add("type","start")
                .add("ID", msg.getID())
                .build();

        return jsonStartMessage.toString();
    }	

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

}
