package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.actors.Item;

import java.util.LinkedList;
import java.util.List;

import static com.epam.game.game.utils.Constants.SIZE_MAP_X;
import static com.epam.game.game.utils.Constants.SIZE_MAP_Y;

public class Level {

    private static final String TAG = Level.class.getName();

	private Map map;
	private List<Mob> mobs = new LinkedList<Mob>();
	private List<Item> items = new LinkedList<Item>();
    private int itemNumber = 4;

    public Level(int level) {
		map = new Map(SIZE_MAP_X, SIZE_MAP_Y);
        for (int i = 0; i < itemNumber; i++) {
            items.add(ItemGenerator.createItem(map, items));
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

	public List<Mob> getMobs() {
		return mobs;
	}

	public List<Item> getItems() {
		return items;
	}
}
