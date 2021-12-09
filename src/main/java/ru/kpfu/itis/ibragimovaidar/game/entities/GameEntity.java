package ru.kpfu.itis.ibragimovaidar.game.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.ibragimovaidar.game.context.GameInstanceContextHolder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Getter
@Setter
public abstract class GameEntity {

	protected double x;
	protected double y;
	protected ImageView imageView;
	protected int width;
	protected int height;

	protected GameEntity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	protected GameEntity(int x, int y, int width, int height, Image image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageView = new ImageView(image);
	}

	public GameEntity(int x, int y, Path imagePath, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageView = loadImageView(imagePath);
	}

	protected ImageView loadImageView(Path imagePath){
		return new ImageView(loadImage(imagePath, width, height));
	}

	protected static Image loadImage(Path imagePath, int width, int height){
		try (InputStream imageInputStream = Files.newInputStream(imagePath)){
			return new Image(imageInputStream, width, height, false, false);
		} catch (IOException e) {
			log.error("Load entity image error", e);
			throw new RuntimeException("Load entity image error", e);
		}
	}

	public abstract void render(GameInstanceContextHolder context);

	public abstract void updateState(GameInstanceContextHolder context, double diff);
}
