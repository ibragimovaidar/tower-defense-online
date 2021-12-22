package ru.kpfu.itis.ibragimovaidar.net.client;

import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.entities.Spaceship;
import ru.kpfu.itis.ibragimovaidar.game.entities.SpaceshipFactory;
import ru.kpfu.itis.ibragimovaidar.game.render.GameEntityRenderer;
import ru.kpfu.itis.ibragimovaidar.net.message.Message;
import ru.kpfu.itis.ibragimovaidar.net.message.MessageType;
import ru.kpfu.itis.ibragimovaidar.util.PropertiesUtil;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class ClientThread implements Runnable, AutoCloseable {

	private final Connection connection;
	private final GameInstanceContextHolder context;
	private final GameEntityRenderer gameEntityRenderer;

	private final SpaceshipFactory spaceshipFactory = new SpaceshipFactory();

	public ClientThread(GameInstanceContextHolder context, GameEntityRenderer gameEntityRenderer) {
		this.context = context;
		this.gameEntityRenderer = gameEntityRenderer;
		try {
			Socket socket = new Socket(PropertiesUtil.getProperty("server.host"), PropertiesUtil.getIntProperty("server.port"));
			connection = new Connection(socket);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		Message joinRequestMessage = new Message(MessageType.JOIN_REQUEST, context.getPlayerState().getUsername());
		connection.writeMessage(joinRequestMessage);
		Message joinResponseMessage = connection.readMessage(MessageType.JOIN_RESPONSE);
		context.getPlayerState().setId(Integer.parseInt(joinResponseMessage.getPayload()));
		Message startGameMessage = connection.readMessage(MessageType.START_GAME);
		context.setGameState(GameState.IN_PROCESS);
		boolean gameCycle = true;
		while (gameCycle){
			Message message = connection.readMessage();
			if (message.getMessageType().equals(MessageType.REQUEST_GAME_STATUS)){
				Message responseGameStatusMessage =
						new Message(MessageType.RESPONSE_GAME_STATUS, context.getPlayerState().getHealth() + "");
				connection.writeMessage(responseGameStatusMessage);
			}
			if (message.getMessageType().equals(MessageType.CREATE_SHIP)){
				String[] payload = message.getPayload().split("/");
				int shipType = Integer.parseInt(payload[0]);
				int side = Integer.parseInt(payload[1]);
				createShip(shipType, side);
			}
			if (message.getMessageType().equals(MessageType.END_GAME)){
				gameCycle = false;
				int winnerId = Integer.parseInt(message.getPayload());
				if (winnerId == context.getPlayerState().getId()){
					context.setGameState(GameState.WON);
				} else {
					context.setGameState(GameState.LOSS);
				}
			}
		}
	}

	private void createShip(int shipType, int side){
		int x = 0;
		int y = 0;
		if (side == 1){
			x = 0;
			y = 0;
		}
		if (side == 2){
			x = 0;
			y = 856;
		}
		if (side == 3){
			x = 856;
			y = 0;
		}
		if (side == 4){
			x = 856;
			y = 856;
		}
		Spaceship spaceship = spaceshipFactory.createSpaceship(SpaceshipFactory.SpaceshipType.values()[shipType], x, y);
		gameEntityRenderer.getSpaceshipsToRender().add(spaceship);
	}

	@Override
	public void close() throws Exception {
		connection.close();
	}
}
