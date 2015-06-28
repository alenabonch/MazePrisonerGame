package com.epam.game.game.logic;

public class Mob implements Moveable {

	private int _x;
	private int _y;
	private int _health;

	public Mob(Level level) {
		_health = Constants.START_MOBS_HEALTH;
	}
	public int get_x() {
		return _x;
	}

	public int get_y() {
		return _y;
	}

	public int get_health() {
		return _health;
	}

	@Override
	public void moveLeft() {
		_x--;
	}
	@Override
	public void moveRight() {
		_x++;
	}
	@Override
	public void moveDown() {
		_y--;
	}
	@Override
	public void moveUp() {
		_y++;
	}

}
