package ru.kpfu.itis.ibragimovaidar.game.render;

import javafx.event.ActionEvent;
import lombok.RequiredArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

import java.time.Instant;

@RequiredArgsConstructor
public abstract class GameRenderer {

	protected final GameInstanceContextHolder context;
	protected final StateUpdater stateUpdater;

	protected Instant lastUpdateInstant = null;

	public abstract void renderContextEntities(GameInstanceContextHolder context);
	public abstract void renderSingleEntity(GameEntity entity);
	public abstract void onTimerTick(ActionEvent actionEvent);
}
