package ru.kpfu.itis.ibragimovaidar.game.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.entities.Tower;
import ru.kpfu.itis.ibragimovaidar.game.gui.GuiContext;
import ru.kpfu.itis.ibragimovaidar.game.gui.TowerGuiContextView;

@Slf4j
@RequiredArgsConstructor
public class TowerContextMouseEventHandler implements EventHandler<MouseEvent> {

	private final GameInstanceContextHolder context;
	private final Tower tower;

	@Override
	public void handle(MouseEvent event) {
		((TowerGuiContextView) GuiContext.TOWER.getGuiContextView()).setCurrentTower(tower);
		context.getRootPane().getChildren().addAll(GuiContext.TOWER.getGuiContextView().getRelatedNodes());
		log.info("handled");
		event.consume();
	}
}
