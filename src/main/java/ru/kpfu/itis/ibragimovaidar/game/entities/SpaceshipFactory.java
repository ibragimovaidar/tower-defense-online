package ru.kpfu.itis.ibragimovaidar.game.entities;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kpfu.itis.ibragimovaidar.util.PropertiesUtil;

public class SpaceshipFactory {

	public Spaceship createSpaceship(SpaceshipType type, int x, int y){
		return new Spaceship(x, y, 128, 128, type.maxHealth, type.speed, type.damage, type.image);
	}

	@Getter
	@AllArgsConstructor
	public enum SpaceshipType {

		TANK(
				new Image(PropertiesUtil.getProperty("spaceship.tank.image.path")),
				PropertiesUtil.getIntProperty("spaceship.tank.attack.damage"),
				PropertiesUtil.getIntProperty("spaceship.tank.speed"),
				PropertiesUtil.getIntProperty("spaceship.tank.health")
		),
		PUSHER(
				new Image(PropertiesUtil.getProperty("spaceship.pusher.image.path")),
				PropertiesUtil.getIntProperty("spaceship.pusher.attack.damage"),
				PropertiesUtil.getIntProperty("spaceship.pusher.speed"),
				PropertiesUtil.getIntProperty("spaceship.pusher.health")
		);

		private final Image image;
		private final int damage;
		private final int speed;
		private final int maxHealth;
	}
}
