package ru.kpfu.itis.ibragimovaidar.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.context.PlayerState;
import ru.kpfu.itis.ibragimovaidar.game.handler.EventHandlerRegisterer;
import ru.kpfu.itis.ibragimovaidar.game.render.GameEntityRenderer;
import ru.kpfu.itis.ibragimovaidar.game.render.GameGUIRenderer;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;
import ru.kpfu.itis.ibragimovaidar.net.client.ClientThread;
import ru.kpfu.itis.ibragimovaidar.net.client.GameState;

import static ru.kpfu.itis.ibragimovaidar.config.Config.getBackgroundImage;
import static ru.kpfu.itis.ibragimovaidar.config.Config.getGameEntitiesSupplier;
import static ru.kpfu.itis.ibragimovaidar.config.Config.getPlanet;

public class GameInstance {

	private final ClientThread clientThread;
	private final GameEntityRenderer entityRenderer;
	private final GameGUIRenderer guiRenderer;
	private final GameInstanceContextHolder gameContextHolder;
	private final EventHandlerRegisterer eventHandlerRegisterer;
	private final Timeline gameTimeline;

	private void onTimerTick(ActionEvent actionEvent) {
		entityRenderer.onTimerTick(actionEvent);
		guiRenderer.onTimerTick(actionEvent);
	}

	public GameInstance(Pane rootPane, String nickname) {
		gameContextHolder = new GameInstanceContextHolder(rootPane, getPlanet(), getGameEntitiesSupplier(), getBackgroundImage(), new PlayerState(nickname, 1000), new PlayerState("Player 2", 1000));
		entityRenderer = new GameEntityRenderer(gameContextHolder, new StateUpdater());
		guiRenderer = new GameGUIRenderer(gameContextHolder, new StateUpdater());
		eventHandlerRegisterer = new EventHandlerRegisterer(gameContextHolder, entityRenderer);
		clientThread = new ClientThread(gameContextHolder, entityRenderer);
		new Thread(clientThread).start();
		while (gameContextHolder.getGameState().equals(GameState.IN_PROCESS)){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		initGame();
		gameContextHolder.setInitialized(true);
		gameTimeline = new Timeline(
				new KeyFrame(Duration.millis(40), this::onTimerTick)
		);
		gameTimeline.setCycleCount(Animation.INDEFINITE);
		gameTimeline.play();
	}

	private void initGame(){
		render();
		eventHandlerRegisterer.register();
	}

	private void render(){
		entityRenderer.renderContextEntities(gameContextHolder);
		guiRenderer.renderContextEntities(gameContextHolder);
	}
}
