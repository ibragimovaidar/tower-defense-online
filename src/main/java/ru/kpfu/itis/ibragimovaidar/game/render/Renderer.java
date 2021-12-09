package ru.kpfu.itis.ibragimovaidar.game.render;

import javafx.event.ActionEvent;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;

public interface Renderer {

	void renderContextEntities(GameInstanceContextHolder context);
	void renderSingleEntity(GameEntity entity);
	void onTimerTick(ActionEvent actionEvent);
}
