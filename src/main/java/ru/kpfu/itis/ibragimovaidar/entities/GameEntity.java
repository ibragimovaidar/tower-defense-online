package ru.kpfu.itis.ibragimovaidar.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.kpfu.itis.ibragimovaidar.game.GameContextHolder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class GameEntity {

	protected int x;
	protected int y;
	protected ImageView imageView;
	protected int width;
	protected int height;


	public GameEntity(int x, int y, Path imagePath, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageView = loadImage(imagePath);
	}

	protected ImageView loadImage(Path imagePath){
		try (InputStream imageInputStream = Files.newInputStream(imagePath)) {
			return new ImageView(new Image(imageInputStream, width, height, false, false));
		} catch (IOException e) {
			throw new RuntimeException("Load entity image error", e);
		}
	}

	public abstract void render(GameContextHolder context);

	public abstract void updateState(GameContextHolder context, double diff);
}
