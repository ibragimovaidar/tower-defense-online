package ru.kpfu.itis.ibragimovaidar.entities;

import ru.kpfu.itis.ibragimovaidar.game.GameContextHolder;

import java.nio.file.Path;

public class Tower extends LifeEntity {

	public Tower(int x, int y, Path imagePath, int width, int height, int maxHealth) {
		super(x, y, imagePath, width, height, maxHealth);
	}

	@Override
	public void render(GameContextHolder context) {
		context.getGraphicsContext().drawImage(imageView.getImage(), x, y);
	}

	@Override
	public void updateState(GameContextHolder context, double diff) {
	}
}
