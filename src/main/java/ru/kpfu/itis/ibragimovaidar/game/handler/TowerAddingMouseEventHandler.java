package ru.kpfu.itis.ibragimovaidar.game.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.entities.Tower;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.render.GameEntityRenderer;

@Slf4j
@RequiredArgsConstructor
public class TowerAddingMouseEventHandler implements EventHandler<MouseEvent> {

	private final GameInstanceContextHolder context;
	private final GameEntityRenderer gameEntityRenderer;

	@Override
	public void handle(MouseEvent event) {
		Tower tower = Tower.createInstance((int) event.getX(), (int) event.getY());
		context.getGameEntities().add(tower);
		gameEntityRenderer.renderSingleEntity(tower);
	}
}
