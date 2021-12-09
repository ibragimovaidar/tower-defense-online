package ru.kpfu.itis.ibragimovaidar.game.gui;

import javafx.scene.Node;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class GuiContextView {

	private final List<Node> relatedNodes = new ArrayList<>();
}
