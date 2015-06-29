package com.epam.game.game.logic;

import java.util.LinkedList;
import java.util.List;

public class Level {

	private Map map;
	private int mapSizeX;
	private int mapSizeY;
	private List<Mob> _mobs = new LinkedList<Mob>();
	private List<Item> _things = new LinkedList<Item>();


    public Level(int level) {
		mapSizeX = Constants.MIN_SIZE_MAP_X + level * Constants.SIZE_STEP_MAP;
		mapSizeY = Constants.MIN_SIZE_MAP_Y + level * Constants.SIZE_STEP_MAP;
		map = new Map(mapSizeX, mapSizeY);
	}

	public Map getMap() {
		try {
			return (Map) map.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getMapSizeX() {
		return mapSizeX;
	}

	public int getMapSizeY() {
		return mapSizeY;
	}

	public List<Mob> get_mobs() {
		return _mobs;
	}

	public List<Item> get_things() {
		return _things;
	}
}
