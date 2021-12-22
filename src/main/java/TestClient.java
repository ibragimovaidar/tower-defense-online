import lombok.SneakyThrows;
import ru.kpfu.itis.ibragimovaidar.net.message.Message;
import ru.kpfu.itis.ibragimovaidar.net.message.MessageType;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient {

	public static void main(String[] args) {
		new TestClient().run();
	}

	@SneakyThrows
	private void run() {
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Connected " + socket);

		OutputStream outputStream = socket.getOutputStream();
		System.out.println(outputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		System.out.println(objectOutputStream);

		InputStream inputStream = socket.getInputStream();
		System.out.println(inputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		System.out.println(objectInputStream);


		Message joinRequest = new Message(MessageType.JOIN_REQUEST, "Player");
		objectOutputStream.writeObject(joinRequest);

		Message joinResponse = (Message) objectInputStream.readObject();
		System.out.println(joinResponse);

		Message startGameMessage = (Message) objectInputStream.readObject();
		System.out.println(startGameMessage);

		while (true){
			Message message = (Message) objectInputStream.readObject();
			if (message.getMessageType().equals(MessageType.REQUEST_GAME_STATUS)){
				Message responseGameStatusMessage = new Message(MessageType.RESPONSE_GAME_STATUS, "100");
				objectOutputStream.writeObject(responseGameStatusMessage);
			}
			if (message.getMessageType().equals(MessageType.CREATE_SHIP)){
				System.out.println(message);
			}
		}
	}
}
