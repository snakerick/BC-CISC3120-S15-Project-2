package wsMessages;

import javax.json.*;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class BeginGameEncoder implements Encoder.Text<BeginGame> {

	@Override
	public String encode(BeginGame msg) throws EncodeException {
		JsonObject jsonStartMessage = Json.createObjectBuilder()
				.add("type","begin")
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
