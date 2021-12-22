package ru.kpfu.itis.ibragimovaidar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.kpfu.itis.ibragimovaidar.config.Config;
import ru.kpfu.itis.ibragimovaidar.game.GameInstance;

import java.util.Scanner;


public class TowerDefenseOnlineApp extends Application {

	private Pane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Pane();
		root.setPrefSize(856, 856);
		root.getChildren().add(Config.getBackgroundImage());

		Scanner scanner = new Scanner(System.in);
		String nickname = scanner.next();
		GameInstance gameInstance = new GameInstance(root, nickname);

		Scene scene = new Scene(root, 1024, 856);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		TowerDefenseOnlineApp.launch(args);
	}
}
