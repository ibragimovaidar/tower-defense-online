package ru.kpfu.itis.ibragimovaidar.game.state;

import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.entities.LifeEntity;
import ru.kpfu.itis.ibragimovaidar.net.client.GameState;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class StateUpdater {

	private final Text endGameText;
	{
		endGameText = new Text();
		endGameText.setX(100);
		endGameText.setY(400);
		endGameText.setCache(true);
		endGameText.setFont(Font.font(null, FontWeight.BOLD, 64));
		Reflection r = new Reflection();
		r.setFraction(0.6F);
		endGameText.setEffect(r);
	}

	private boolean isEndGameTextInitialized = false;

	public void updateStateOnTick(GameInstanceContextHolder context, double diff) {
		if (!isEndGameTextInitialized){
			context.getRootPane().getChildren().add(endGameText);
			isEndGameTextInitialized = true;
		}
		if (context.getGameState().equals(GameState.LOSS)){
			endGameText.setFill(Color.RED);
			endGameText.setText("You lost! ");
			return;
		}
		if (context.getGameState().equals(GameState.WON)){
			endGameText.setFill(Color.LIGHTGREEN);
			endGameText.setText("Grats, "  + context.getPlayerState().getUsername() + " you won!");
			return;
		}
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
