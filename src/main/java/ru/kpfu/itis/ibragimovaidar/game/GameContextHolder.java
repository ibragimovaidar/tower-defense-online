package ru.kpfu.itis.ibragimovaidar.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.kpfu.itis.ibragimovaidar.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.entities.Planet;

import java.util.List;
import java.util.function.Supplier;

public class GameContextHolder {

	private final GraphicsContext graphicsContext;
	private final Planet planet;
	private final List<GameEntity> gameEntities;
	private final Image backgroundImage;


	public GameContextHolder(GraphicsContext graphicsContext, Planet planet, Supplier<List<GameEntity>> gameEntitiesSupplier, Image backgroundImage) {
		this.graphicsContext = graphicsContext;
		this.planet = planet;
		this.gameEntities = gameEntitiesSupplier.get();
		this.backgroundImage = backgroundImage;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public Planet getPlanet() {
		return planet;
	}

	public List<GameEntity> getGameEntities() {
		return gameEntities;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}
}
