package ru.kpfu.itis.ibragimovaidar.game.handler;

import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.render.GameEntityRenderer;


@Slf4j
@RequiredArgsConstructor
public class EventHandlerRegisterer {

	private final GameInstanceContextHolder context;
	private final GameEntityRenderer gameEntityRenderer;

	public void register(){
		context.getRootPane().addEventHandler(MouseEvent.MOUSE_CLICKED,
				new TowerAddingMouseEventHandler(context, gameEntityRenderer));
	}
}
