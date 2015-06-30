package com.epam.game.game.logic;

import com.epam.game.game.utils.Constants;

public class Mob implements Moveable {

	private int mobX;
	private int mobY;
	private int health;

	public Mob(Level level) {
		health = Constants.START_MOBS_HEALTH;
	}

	public int getMobX() {
		return mobX;
	}

	public int getMobY() {
		return mobY;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public void moveLeft() {
		mobX--;
	}

	@Override
	public void moveRight() {
		mobX++;
	}

	@Override
	public void moveDown() {
		mobY--;
	}

	@Override
	public void moveUp() {
		mobY++;
	}

}
