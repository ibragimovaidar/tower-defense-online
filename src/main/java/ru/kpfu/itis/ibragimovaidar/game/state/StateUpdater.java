package ru.kpfu.itis.ibragimovaidar.game.state;

import javafx.scene.image.ImageView;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.entities.LifeEntity;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class StateUpdater {

	public void updateStateOnTick(GameInstanceContextHolder context, double diff) {
		context.getGameEntities().forEach(gameEntity -> gameEntity.updateState(context, diff));

		removeDeadEntities(context);
		updatePlayerState(context);
	}

	private void removeDeadEntities(GameInstanceContextHolder context){
		List<LifeEntity> deadEntitiesToRemove = context.getGameEntities().stream()
				.filter(gameEntity -> gameEntity instanceof LifeEntity)
				.map(gameEntity -> (LifeEntity) gameEntity)
				.filter(lifeEntity -> lifeEntity.getHealth() <= 0)
				.collect(Collectors.toList());
		List<ImageView> deadEntitiesImageViews = deadEntitiesToRemove.stream()
				.map(GameEntity::getImageView)
				.collect(Collectors.toList());
		deadEntitiesToRemove.forEach(e -> e.removeHealthBar(context.getRootPane()));
		context.getRootPane().getChildren().removeAll(deadEntitiesImageViews);
		context.getGameEntities().removeAll(deadEntitiesToRemove);
		if (!deadEntitiesToRemove.isEmpty()){
			updatePlayerMoney(context, 15 * deadEntitiesToRemove.size());
			log.info("Removed dead gameEntities {}", deadEntitiesToRemove);
		}
	}

	private void updatePlayerState(GameInstanceContextHolder context){
		context.getPlayerState().setHealth(context.getPlanet().getHealth());
	}

	private void updatePlayerMoney(GameInstanceContextHolder context, int money){
		context.getPlayerState().setMoney(context.getPlayerState().getMoney() + money);
	}
}
