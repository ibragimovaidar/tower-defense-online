package ru.kpfu.itis.ibragimovaidar.game.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerState {

	private String username;
	private int health;
	private int money;

	public PlayerState(String username, int health) {
		this.health = health;
		this.username = username;
	}
}
