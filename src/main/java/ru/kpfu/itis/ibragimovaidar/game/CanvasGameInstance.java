package ru.kpfu.itis.ibragimovaidar.game;

import javafx.scene.canvas.GraphicsContext;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdaterImpl;

import static ru.kpfu.itis.ibragimovaidar.config.Config.*;

public class CanvasGameInstance {

	private final GraphicsContext graphicsContext;
	private final GameEntityRenderer renderer;
	private final GameContextHolder gameContextHolder;
	public CanvasGameInstance(GraphicsContext graphicsContext) {
		gameContextHolder = new GameContextHolder(graphicsContext, getPlanet(), getGameEntitiesSupplier(), getBackgroundImage());
		renderer = new GameEntityRenderer(gameContextHolder, new StateUpdaterImpl());
		this.graphicsContext = graphicsContext;
	}
}
