package ru.kpfu.itis.ibragimovaidar.game.render;

import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.entities.Spaceship;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class GameEntityRenderer extends GameRenderer  {

	@Getter
	private final Queue<Spaceship> spaceshipsToRender = new LinkedList<>();

	public GameEntityRenderer(GameInstanceContextHolder context, StateUpdater stateUpdater) {
		super(context, stateUpdater);
	}

	public void renderContextEntities(GameInstanceContextHolder context){
		context.getGameEntities().forEach(gameEntity -> gameEntity.render(context));
		log.info("Game entities rendered {}", context.getGameEntities());
	}

	@Override
	public void renderSingleEntity(GameEntity entity) {
		context.getGameEntities().add(entity);
		entity.render(context);
		log.info("Game entity rendered {}", entity);
	}

	public void onTimerTick(ActionEvent actionEvent) {
		Instant now = Instant.now();
		double diff = 0;
		if (lastUpdateInstant != null){
			diff = (double) java.time.Duration.between(lastUpdateInstant, now).toMillis() / 1000;
		}

		while (spaceshipsToRender.size() > 0){
			renderSingleEntity(spaceshipsToRender.poll());
		}
		stateUpdater.updateStateOnTick(context, diff);
		lastUpdateInstant = Instant.now();
	}
}
