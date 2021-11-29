package ru.kpfu.itis.ibragimovaidar.game.state;

import ru.kpfu.itis.ibragimovaidar.entities.LifeEntity;
import ru.kpfu.itis.ibragimovaidar.game.GameContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class StateUpdaterImpl implements StateUpdater {

	@Override
	public void updateStateOnTick(GameContextHolder context, double diff) {
		context.getGameEntities().forEach(gameEntity -> gameEntity.updateState(context, diff));

		// removing dead entities
		List<LifeEntity> deadEntitiesToRemove = context.getGameEntities().stream()
				.filter(gameEntity -> gameEntity instanceof LifeEntity)
				.map(gameEntity -> (LifeEntity) gameEntity)
				.filter(lifeEntity -> lifeEntity.getHealth() <= 0)
				.collect(Collectors.toList());
		context.getGameEntities().removeAll(deadEntitiesToRemove);
	}
}
