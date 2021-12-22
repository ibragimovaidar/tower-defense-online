package ru.kpfu.itis.ibragimovaidar.game.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerState {

	private int id;
	private String username;
	private int health;
	private int money = 100;

	public PlayerState(String username, int health) {
		this.health = health;
		this.username = username;
	}
}
