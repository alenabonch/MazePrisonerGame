package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.GeneratorItem;

import java.util.LinkedList;
import java.util.List;

import static com.epam.game.game.logic.Constants.SIZE_MAP_X;
import static com.epam.game.game.logic.Constants.SIZE_MAP_Y;

public class Level {

    private static final String TAG = Level.class.getName();

	private Map map;
	private List<Mob> _mobs = new LinkedList<Mob>();
	private List<Item> _things = new LinkedList<Item>();
    private int itemNumber = 4;

    public Level(int level) {
		map = new Map(SIZE_MAP_X, SIZE_MAP_Y);
        for (int i = 0; i < itemNumber; i++) {
            _things.add(GeneratorItem.CreateItem(map,_things));
        }
        Gdx.app.log(TAG, "Level created: " + level);
	}

	public Map getMap() {
		try {
			return (Map) map.clone();
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
