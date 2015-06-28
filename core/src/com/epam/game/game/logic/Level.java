package com.epam.game.game.logic;

import java.util.LinkedList;
import java.util.List;

public class Level {

	Map _map;
	List<Mob> _mobs = new LinkedList<Mob>();
	List<Item> _things = new LinkedList<Item>();

	public Level(int level) {
		_map = new Map(Constants.MIN_SIZE_MAP_X + level * Constants.SIZE_STEP_MAP,
				Constants.MIN_SIZE_MAP_Y + level * Constants.SIZE_STEP_MAP);
	}

	public Map get_map() {
		try {
			return (Map) _map.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Mob> get_mobs() {
		return _mobs;
	}

	public List<Item> get_things() {
		return _things;
	}
}
