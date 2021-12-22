package ru.kpfu.itis.ibragimovaidar.game.render;

import javafx.event.ActionEvent;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.game.state.StateUpdater;

@Slf4j
public class GameGUIRenderer extends GameRenderer {

	private Text currentMoneyText;

	private Text playerUsernameText;
	private Text enemyUsernameText;
	private Rectangle playerMaxLifeHealthBar;
	private Rectangle enemyMaxLifeHealthBar;
	private Rectangle playerHealthBar;
	private Rectangle enemyHealthBar;

	public GameGUIRenderer(GameInstanceContextHolder context, StateUpdater stateUpdater) {
		super(context, stateUpdater);
		initText();
		initHealthBar(context);
	}

	@Override
	public void renderContextEntities(GameInstanceContextHolder context) {
		context.getRootPane().getChildren().add(currentMoneyText);
		context.getRootPane().getChildren().add(playerUsernameText);
		context.getRootPane().getChildren().add(playerHealthBar);
		context.getRootPane().getChildren().add(playerMaxLifeHealthBar);
		context.getRootPane().getChildren().add(enemyHealthBar);
		context.getRootPane().getChildren().add(enemyMaxLifeHealthBar);
		context.getRootPane().getChildren().add(enemyUsernameText);
	}

	@Override
	public void renderSingleEntity(GameEntity entity) {
		throw new RuntimeException();
	}

	@Override
	public void onTimerTick(ActionEvent actionEvent) {
		currentMoneyText.setText("Money: " + context.getPlayerState().getMoney());
		playerHealthBar.setWidth((double) 100 * ((double) context.getPlayerState().getHealth() / (double) context.getPlanet().getMaxHealth()));
		enemyHealthBar.setWidth((double) 100 * ((double) context.getEnemyState().getHealth() / (double) context.getPlanet().getMaxHealth()));
	}

	private void initHealthBar(GameInstanceContextHolder context){
		// player health bar
		playerUsernameText = new Text(context.getPlayerState().getUsername());
		playerUsernameText.setX(900);
		playerUsernameText.setY(190);
		playerUsernameText.setFill(Color.GOLDENROD);
		playerUsernameText.setFont((Font.font(null, FontWeight.BOLD, 30)));

		playerMaxLifeHealthBar = new Rectangle(100, 30, Color.TRANSPARENT);
		playerMaxLifeHealthBar.setStroke(Color.GREY);
		playerMaxLifeHealthBar.setTranslateX(900);
		playerMaxLifeHealthBar.setTranslateY(200);

		playerHealthBar = new Rectangle((double) 100 * ((double) context.getPlanet().getHealth() / (double) context.getPlanet().getMaxHealth()), 30, Color.LIGHTGREEN);
		playerHealthBar.setTranslateX(900);
		playerHealthBar.setTranslateY(200);

		// enemy health bar
		enemyUsernameText = new Text(context.getEnemyState().getUsername());
		enemyUsernameText.setX(900);
		enemyUsernameText.setY(260);
		enemyUsernameText.setFill(Color.GOLDENROD);
		enemyUsernameText.setFont((Font.font(null, FontWeight.BOLD, 30)));

		enemyMaxLifeHealthBar = new Rectangle(100, 30, Color.TRANSPARENT);
		enemyMaxLifeHealthBar.setStroke(Color.GREY);
		enemyMaxLifeHealthBar.setTranslateX(900);
		enemyMaxLifeHealthBar.setTranslateY(270);

		enemyHealthBar = new Rectangle((double) 100 * ((double) context.getPlanet().getHealth() / (double) context.getPlanet().getMaxHealth()), 30, Color.LIGHTGREEN);
		enemyHealthBar.setTranslateX(900);
		enemyHealthBar.setTranslateY(270);
	}

	private void initText(){
		currentMoneyText = new Text();
		currentMoneyText.setX(30);
		currentMoneyText.setY(70);
		currentMoneyText.setCache(true);
		currentMoneyText.setFill(Color.GOLDENROD);
		currentMoneyText.setFont(Font.font(null, FontWeight.BOLD, 30));
		Reflection r = new Reflection();
		r.setFraction(0.6F);
		currentMoneyText.setEffect(r);
		currentMoneyText.setText("Money: " + context.getPlayerState().getMoney());
	}
}
