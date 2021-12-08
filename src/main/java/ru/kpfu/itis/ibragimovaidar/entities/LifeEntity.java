package ru.kpfu.itis.ibragimovaidar.entities;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
public abstract class LifeEntity extends GameEntity {

	protected int health;
	protected int maxHealth;
	private Rectangle maxHealthBarStrokeRectangle;
	private Rectangle currentHealthBarRectangle;

	protected LifeEntity(int x, int y, int width, int height, int maxHealth){
		super(x, y, width, height);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		initHealthBar();
	}

	protected LifeEntity(int x, int y, int width, int height, int maxHealth, Image image){
		super(x, y, width, height, image);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		initHealthBar();
	}

	public LifeEntity(int x, int y, Path imagePath, int width, int height, int maxHealth){
		super(x, y, imagePath, width, height);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		initHealthBar();
	}

	private void initHealthBar(){
		maxHealthBarStrokeRectangle = new Rectangle(width, 10, Color.TRANSPARENT);
		maxHealthBarStrokeRectangle.setStroke(Color.RED);
		maxHealthBarStrokeRectangle.setTranslateX(x);
		maxHealthBarStrokeRectangle.setTranslateY(y - 20);

		currentHealthBarRectangle = new Rectangle((double) width * ((double) health / (double) maxHealth), 10, Color.GREEN);
		currentHealthBarRectangle.setTranslateX(x);
		currentHealthBarRectangle.setTranslateY(y - 20);
	}

	protected void drawHealthBar(Pane rootPane){
		rootPane.getChildren().add(maxHealthBarStrokeRectangle);
		rootPane.getChildren().add(currentHealthBarRectangle);
	}

	protected void updateHealthBar(Pane rootPane){
		maxHealthBarStrokeRectangle.setTranslateX(x);
		maxHealthBarStrokeRectangle.setTranslateY(y - 20);

		currentHealthBarRectangle.setTranslateX(x);
		currentHealthBarRectangle.setTranslateY(y - 20);
		currentHealthBarRectangle.setWidth((double) width * ((double) health / (double)maxHealth));
	}

	public void removeHealthBar(Pane rootPane){
		rootPane.getChildren().remove(currentHealthBarRectangle);
		rootPane.getChildren().remove(maxHealthBarStrokeRectangle);
	}
}
