package ru.kpfu.itis.ibragimovaidar.game.state;

import ru.kpfu.itis.ibragimovaidar.game.GameContextHolder;

@FunctionalInterface
public interface StateUpdater {

	void updateStateOnTick	(GameContextHolder context, double diff);
}
