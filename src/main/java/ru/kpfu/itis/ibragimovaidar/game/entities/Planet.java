package ru.kpfu.itis.ibragimovaidar.game.entities;

import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;

import java.nio.file.Path;

public class Planet extends LifeEntity {

	public Planet(int x, int y, Path imagePath, int width, int height, int maxHealth) {
		super(x, y, imagePath, width, height, maxHealth);
	}

	@Override
	public void render(GameInstanceContextHolder context) {
		context.getRootPane().getChildren().add(imageView);
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		drawHealthBar(context.getRootPane());
	}

	@Override
	public void updateState(GameInstanceContextHolder context, double diff) {
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		updateHealthBar(context.getRootPane());
	}
}
