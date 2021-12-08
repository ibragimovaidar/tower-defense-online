package ru.kpfu.itis.ibragimovaidar.game.render;

import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

@Slf4j
public class GameGUIRenderer extends GameRenderer {

	public GameGUIRenderer(GameInstanceContextHolder context, StateUpdater stateUpdater) {
		super(context, stateUpdater);
	}

	@Override
	public void renderContextEntities(GameInstanceContextHolder context) {

	}

	@Override
	public void renderSingleEntity(GameEntity entity) {

	}

	@Override
	public void onTimerTick(ActionEvent actionEvent) {

	}
}
