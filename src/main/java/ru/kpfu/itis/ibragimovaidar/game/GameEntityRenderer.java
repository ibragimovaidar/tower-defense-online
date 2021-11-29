package ru.kpfu.itis.ibragimovaidar.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import ru.kpfu.itis.ibragimovaidar.entities.LifeEntity;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class GameEntityRenderer {

	private final GameContextHolder context;
	private final StateUpdater stateUpdater;
	private final Timeline timeline;

	{
		timeline = new Timeline(
				new KeyFrame(Duration.millis(40), this::onTimerTick)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private Instant lastUpdateInstant = null;

	public GameEntityRenderer(GameContextHolder context, StateUpdater stateUpdater) {
		this.context = context;
		this.stateUpdater = stateUpdater;
	}

	public void render(double diff){
		context.getGraphicsContext().drawImage(context.getBackgroundImage(), 0, 0);
		context.getGameEntities().forEach(gameEntity -> gameEntity.render(context));
	}

	private void onTimerTick(ActionEvent actionEvent) {
		Instant now = Instant.now();
		double diff = 0;
		if (lastUpdateInstant != null){
			diff = (double) java.time.Duration.between(lastUpdateInstant, now).toMillis() / 1000;
		}

		stateUpdater.updateStateOnTick(context, diff);
		render(diff);

		lastUpdateInstant = Instant.now();
	}
}
