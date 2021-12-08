package ru.kpfu.itis.ibragimovaidar.config;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.kpfu.itis.ibragimovaidar.entities.GameEntity;
import ru.kpfu.itis.ibragimovaidar.entities.Planet;
import ru.kpfu.itis.ibragimovaidar.entities.Spaceship;
import ru.kpfu.itis.ibragimovaidar.entities.Tower;
import ru.kpfu.itis.ibragimovaidar.util.AssetsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Config {

	private static final Planet PLANET = new Planet(384, 384, AssetsUtil.getPath("/img/planet.png"),128, 128, 1000);

	private static final Supplier<List<GameEntity>> gameEntitiesSupplier = () -> {
		List<GameEntity> gameEntities = new ArrayList<>();
		gameEntities.add(PLANET);

		Spaceship tankShip = new Spaceship(100, 100, AssetsUtil.getPath("/img/tankShip.png"), 128, 128, 100, 30, 100);
		gameEntities.add(tankShip);
		Spaceship tankShip2 = new Spaceship(800, 800, AssetsUtil.getPath("/img/tankShip.png"), 128, 128, 100, 30, 100);
		gameEntities.add(tankShip2);
		Spaceship pusher = new Spaceship(100, 500, AssetsUtil.getPath("/img/Pusher.png"), 128, 128, 100, 50, 100);
		gameEntities.add(pusher);
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
