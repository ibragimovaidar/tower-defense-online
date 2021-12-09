package ru.kpfu.itis.ibragimovaidar.config;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.kpfu.itis.ibragimovaidar.game.entities.*;
import ru.kpfu.itis.ibragimovaidar.util.AssetsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Config {

	private static final Planet PLANET = new Planet(384, 384, AssetsUtil.getPath("/img/planet.png"),128, 128, 1000);

	private static final Supplier<List<GameEntity>> gameEntitiesSupplier = () -> {
		List<GameEntity> gameEntities = new ArrayList<>();
		gameEntities.add(PLANET);

		SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
		gameEntities.add(spaceshipFactory.createSpaceship(SpaceshipFactory.SpaceshipType.TANK, 100, 200));
		gameEntities.add(spaceshipFactory.createSpaceship(SpaceshipFactory.SpaceshipType.PUSHER, 100, 200));
		gameEntities.add(spaceshipFactory.createSpaceship(SpaceshipFactory.SpaceshipType.PUSHER, 300, 200));
		gameEntities.add(Tower.createInstance(300, 300));
		return gameEntities;
	};

	public static Planet getPlanet() {
		return PLANET;
	}

	public static Supplier<List<GameEntity>> getGameEntitiesSupplier(){
		return gameEntitiesSupplier;
	}

	public static ImageView getBackgroundImage(){
		ImageView imageView = new ImageView(new Image(AssetsUtil.getInputStream("/img/space.png")));
		imageView.setTranslateX(0);
		imageView.setTranslateY(0);
		return imageView;
	}
}
