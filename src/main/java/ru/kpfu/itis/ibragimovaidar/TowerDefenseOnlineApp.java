package ru.kpfu.itis.ibragimovaidar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kpfu.itis.ibragimovaidar.game.CanvasGameInstance;
import ru.kpfu.itis.ibragimovaidar.util.PropertiesUtil;

public class TowerDefenseOnlineApp extends Application {

	private CanvasGameInstance gameInstanceContext;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(
				PropertiesUtil.getIntProperty("canvas.width"),
				PropertiesUtil.getIntProperty("canvas.height")
		);
		gameInstanceContext = new CanvasGameInstance(canvas.getGraphicsContext2D());

		VBox vBox = new VBox(canvas);
		Scene scene = new Scene(vBox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		TowerDefenseOnlineApp.launch(args);
	}
}
