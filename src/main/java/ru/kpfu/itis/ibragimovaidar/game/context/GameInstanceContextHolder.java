package ru.kpfu.itis.ibragimovaidar.game.context;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.entities.Planet;

import java.util.List;
import java.util.function.Supplier;

@Getter
public class GameInstanceContextHolder {

	private final Pane rootPane;
	private final Planet planet;
	private final List<GameEntity> gameEntities;
	private final ImageView backgroundImage;
	private final GameState gameStat = new GameState();
	private final PlayerState playerState;
	private final PlayerState enemyState;

	public GameInstanceContextHolder(Pane rootPane, Planet planet, Supplier<List<GameEntity>> gameEntitiesSupplier, ImageView backgroundImage, PlayerState playerState, PlayerState enemyState) {
		this.rootPane = rootPane;
		this.planet = planet;
		this.gameEntities = gameEntitiesSupplier.get();
		this.backgroundImage = backgroundImage;
		this.playerState = playerState;
		this.enemyState = enemyState;
	}
}
