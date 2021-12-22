package ru.kpfu.itis.ibragimovaidar.game.context;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.entities.Planet;
import ru.kpfu.itis.ibragimovaidar.net.client.GameState;

import java.util.List;
import java.util.function.Supplier;

@Getter
public class GameInstanceContextHolder {

	@Setter
	private volatile GameState gameState = GameState.PREPARE;
	private final Pane rootPane;
	private final Planet planet;
	private final List<GameEntity> gameEntities;
	private final ImageView backgroundImage;
	private final PlayerState playerState;
	private final PlayerState enemyState;

	@Setter
	private volatile boolean isInitialized;

	public GameInstanceContextHolder(Pane rootPane, Planet planet, Supplier<List<GameEntity>> gameEntitiesSupplier, ImageView backgroundImage, PlayerState playerState, PlayerState enemyState) {
		this.rootPane = rootPane;
		this.planet = planet;
		this.gameEntities = gameEntitiesSupplier.get();
		this.backgroundImage = backgroundImage;
		this.playerState = playerState;
		this.enemyState = enemyState;
	}
}
