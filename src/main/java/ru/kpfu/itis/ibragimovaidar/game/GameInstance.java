package ru.kpfu.itis.ibragimovaidar.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.handler.EventHandlerRegisterer;
import ru.kpfu.itis.ibragimovaidar.game.render.GameEntityRenderer;
import ru.kpfu.itis.ibragimovaidar.game.render.GameGUIRenderer;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

import static ru.kpfu.itis.ibragimovaidar.config.Config.getBackgroundImage;
import static ru.kpfu.itis.ibragimovaidar.config.Config.getGameEntitiesSupplier;
import static ru.kpfu.itis.ibragimovaidar.config.Config.getPlanet;

public class GameInstance {

	private final GameEntityRenderer entityRenderer;
	private final GameGUIRenderer guiRenderer;
	private final GameInstanceContextHolder gameContextHolder;
	private final EventHandlerRegisterer eventHandlerRegisterer;
	private final Timeline gameTimeline;

	{
		gameTimeline = new Timeline(
				new KeyFrame(Duration.millis(40), this::onTimerTick)
		);
		gameTimeline.setCycleCount(Animation.INDEFINITE);
		gameTimeline.play();
	}

	private void onTimerTick(ActionEvent actionEvent) {
		entityRenderer.onTimerTick(actionEvent);

	}

	public GameInstance(Pane rootPane) {
		gameContextHolder = new GameInstanceContextHolder(rootPane, getPlanet(), getGameEntitiesSupplier(), getBackgroundImage());
		entityRenderer = new GameEntityRenderer(gameContextHolder, new StateUpdater());
		guiRenderer = new GameGUIRenderer(gameContextHolder, new StateUpdater());
		eventHandlerRegisterer = new EventHandlerRegisterer(gameContextHolder, entityRenderer);
		initGame();
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
