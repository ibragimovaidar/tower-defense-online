package ru.kpfu.itis.ibragimovaidar.game.gui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GuiContext {
	TOWER(new TowerGuiContextView());

	private final GuiContextView guiContextView;
}
