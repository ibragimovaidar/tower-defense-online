package ru.kpfu.itis.ibragimovaidar.game.entities;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;

@Getter
@Setter
public class Spaceship extends LifeEntity {

	protected int speed;
	protected int damage;

	protected double angle = 0;

	public Spaceship(int x, int y, int width, int height, int maxHealth, int speed, int damage, Image image) {
		super(x, y, width, height, maxHealth, image);
		this.speed = speed;
		this.damage = damage;
	}

	@Override
	public void render(GameInstanceContextHolder context) {
		context.getRootPane().getChildren().add(imageView);
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		drawHealthBar(context.getRootPane());

		// rotate imageView to planet
		Planet planet = context.getPlanet();
		double gX = planet.x - x;
		double gY = planet.y - y;
		double length = Math.sqrt(gX * gX + gY * gY);
		gX /= length;
		gY /= length;
		double angleToTarget = Math.atan2(gY, gX) - Math.PI / 2;

		imageView.setRotate(-Math.toDegrees(angleToTarget));
	}

	@Override
	public void updateState(GameInstanceContextHolder context, double diff) {
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);

		Planet planet = context.getPlanet();
		double gX = planet.x - x;
		double gY = planet.y - y;
		double length = Math.sqrt(gX * gX + gY * gY);

		gX /= length;
		gY /= length;

		x += gX * speed * diff;
		y += gY * speed * diff;

		if (planet.getHeight() >= length){
			planet.health -= damage;
			health = 0;
		}

		updateHealthBar(context.getRootPane());
	}
}
