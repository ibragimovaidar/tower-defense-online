package ru.kpfu.itis.ibragimovaidar.game.context;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;
import ru.kpfu.itis.ibragimovaidar.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.entities.Planet;

import java.util.List;
import java.util.function.Supplier;

@Getter
public class GameInstanceContextHolder {

	private final Pane rootPane;
	private final Planet planet;
	private final List<GameEntity> gameEntities;
	private final ImageView backgroundImage;

	public GameInstanceContextHolder(Pane rootPane, Planet planet, Supplier<List<GameEntity>> gameEntitiesSupplier, ImageView backgroundImage) {
		this.rootPane = rootPane;
		this.planet = planet;
		this.gameEntities = gameEntitiesSupplier.get();
		this.backgroundImage = backgroundImage;
	}
}
