package ru.kpfu.itis.ibragimovaidar.game.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.ibragimovaidar.game.entities.Tower;


@Getter
@Setter
public class TowerGuiContextView extends GuiContextView {

	private final Text text = new Text(900, 600, "Tower");
	private final Text upgradeTowerText = new Text(900, 600, "Upgrade");
	private final Rectangle upgradeTowerButton = new Rectangle(100, 40, Color.GREEN);

	{

		text.setTranslateZ(100);
		text.setFill(Color.WHITE);
		text.setFont(Font.font(null, FontWeight.BOLD, 10));

		upgradeTowerText.setTranslateZ(100);
		upgradeTowerButton.setTranslateX(900);
		upgradeTowerButton.setTranslateY(640);
		upgradeTowerButton.setTranslateZ(100);
	}

	private Tower currentTower;

	public TowerGuiContextView() {
		super.getRelatedNodes().add(text);
		super.getRelatedNodes().add(upgradeTowerButton);
		super.getRelatedNodes().add(upgradeTowerText);
	}
}
