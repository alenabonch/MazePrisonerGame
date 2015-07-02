package com.epam.game.game.view;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.actors.Hero;
import com.epam.game.game.actors.Item;
import com.epam.game.game.actors.Mob;
import com.epam.game.game.logic.State;

import java.util.List;

import static com.epam.game.game.utils.Constants.*;

public class View {

	private static final String TAG = View.class.getName();

	private Graphics graphics;

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void draw(State state) {
		Gdx.app.log(TAG, "in draw(state)");
		drawMap(state.getCurrentLevel().getMap().getData());
		drawHero(state.getHero());
		drawItems(state.getCurrentLevel().getItemsOnMap(), state.getCurrentLevel().getMap().getData());
//		drawMobs(state.getCurrentLevel().getMobsOnMap(), state.getCurrentLevel().getMap().getData());
	}

	protected void drawMobs(List<Mob> mobs, int[][] data) {
		// TODO
	}

	protected void drawItems(List<Item> items, int[][] data) {
		// TODO
	}

	protected void drawHero(Hero hero) {
        drawTexture(HERO_TEXTURE, hero.getHeroX(), hero.getHeroY());
	}

	protected void drawMap(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				drawTexture(data[i][j], j, i);
			}
		}
	}

	protected void drawTexture(int textureIndex, int x, int y) {
		graphics.fillRect(
				ORIGIN_X + x * CELL_SIZE,
				ORIGIN_Y - y * CELL_SIZE,
				CELL_SIZE, CELL_SIZE,
				textureIndex);
	}
}
