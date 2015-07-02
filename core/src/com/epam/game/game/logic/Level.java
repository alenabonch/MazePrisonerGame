package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.actors.Item;
import com.epam.game.game.actors.Mob;

import java.util.LinkedList;
import java.util.List;

import static com.epam.game.game.utils.Constants.SIZE_MAP_X;
import static com.epam.game.game.utils.Constants.SIZE_MAP_Y;

public class Level {

    private static final String TAG = Level.class.getName();

	private Map map;
	private List<Mob> mobsOnMap = new LinkedList<Mob>();
	private List<Item> itemsOnMap = new LinkedList<Item>();
    private int itemNumber = 3;

    public Level(int level) {
		map = new Map(SIZE_MAP_X, SIZE_MAP_Y);
        for (int i = 0; i < itemNumber; i++) {
            itemsOnMap.add(ItemGenerator.createItem(map, itemsOnMap));
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

	public List<Mob> getMobsOnMap() {
		return mobsOnMap;
	}

	public List<Item> getItemsOnMap() {
		return itemsOnMap;
	}
}
