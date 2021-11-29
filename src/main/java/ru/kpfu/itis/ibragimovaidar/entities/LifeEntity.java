package ru.kpfu.itis.ibragimovaidar.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.nio.file.Path;

public abstract class LifeEntity extends GameEntity {

	protected int health;
	protected int maxHealth;

	public LifeEntity(int x, int y, Path imagePath, int width, int height) {
		super(x, y, imagePath, width, height);
	}

	public LifeEntity(int x, int y, Path imagePath, int width, int height, int maxHealth){
		super(x, y, imagePath, width, height);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	protected void drawHealthBar(GraphicsContext context){
		context.setStroke(Color.RED);
		context.strokeRect(x, y - 20, width, 10);
		context.setFill(Color.RED);
		context.setFill(Color.GREEN);
		context.fillRect(x, y - 20, width * (health / maxHealth), 10);
		context.strokeText(String.valueOf(health), x + width / 2, y - 10);
	}
}
