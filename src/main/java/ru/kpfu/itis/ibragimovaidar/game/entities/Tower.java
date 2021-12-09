package ru.kpfu.itis.ibragimovaidar.game.entities;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;
import ru.kpfu.itis.ibragimovaidar.util.AssetsUtil;
import ru.kpfu.itis.ibragimovaidar.util.PropertiesUtil;

public class Tower extends LifeEntity {

	private final static Image image = loadImage(
			AssetsUtil.getPath(PropertiesUtil.getProperty("tower.image.path")),
			PropertiesUtil.getIntProperty("tower.image.width"),
			PropertiesUtil.getIntProperty("tower.image.height")
	);
	protected int damage = PropertiesUtil.getIntProperty("tower.attack.damage");
	protected int attackRadius = PropertiesUtil.getIntProperty("tower.attack.radius");
	protected double attackRate = PropertiesUtil.getDoubleProperty("tower.attack.rate");

	protected Spaceship currentSpaceship;

	private Ellipse attackRangeEllipse;
	private double lastShotTime = 0;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void render(GameInstanceContextHolder context) {
		context.getRootPane().getChildren().add(imageView);
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);

		attackRangeEllipse = new Ellipse(x + width / 2, y + height / 2, attackRadius, attackRadius);
		attackRangeEllipse.setFill(Color.TRANSPARENT);
		attackRangeEllipse.setStroke(Color.YELLOW);
		context.getRootPane().getChildren().add(attackRangeEllipse);
	}

	@Override
	public void updateState(GameInstanceContextHolder context, double diff) {
		// update coords
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		updateHealthBar(context.getRootPane());

		lastShotTime += diff;
		if (lastShotTime > attackRate){
			if (currentSpaceship == null || !isAbleToAttack(currentSpaceship) || currentSpaceship.health <= 0){
				currentSpaceship = context.getGameEntities().stream()
						.filter(gameEntity -> gameEntity instanceof Spaceship)
						.map(gameEntity -> (Spaceship) gameEntity)
						.filter(this::isAbleToAttack)
						.findFirst()
						.orElse(null);
			}
			if (currentSpaceship != null) {
				currentSpaceship.health -= damage;
				lastShotTime = 0;
				rotateImageView(currentSpaceship);
			}
		}
	}

	private boolean isAbleToAttack(Spaceship spaceship){
		double gX = spaceship.x - x;
		double gY = spaceship.y - y;
		double length = Math.sqrt(gX * gX + gY * gY);
		return attackRadius >= length;
	}

	private void rotateImageView(Spaceship spaceship){
		double gX = spaceship.x - x;
		double gY = spaceship.y - y;

		double angleToTarget = Math.atan2(gY, gX) - Math.PI / 2;

		imageView.setRotate(Math.toDegrees(angleToTarget - 135));
	}

	private Tower(int x, int y){
		super(x, y, 128, 128, PropertiesUtil.getIntProperty("tower.health"), image);
	}

	public static Tower createInstance(int x, int y){
		return new Tower(x, y);
	};
}
