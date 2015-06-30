package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;

import java.util.List;

import static com.epam.game.game.logic.Constants.*;

public class View {

	private static final String TAG = View.class.getName();

	private Graphics _graphics;

	public void setGraphics(Graphics graphics) {
		this._graphics = graphics;
	}

	public void draw(State state) {
		Gdx.app.log(TAG, "in draw(state)");
		drawMap(state.getNowLevel().getMap().getData());
		drawHero(state.getHero());
		drawThings(state.getNowLevel().get_things(),state.getNowLevel().getMap().getData());
//		drawMobs(state.getNowLevel().get_mobs(), state.getNowLevel().getMap().getData());
	}

	protected void drawMobs(List<Mob> get_mobs, int[][] data) {
		// TODO Auto-generated method stub
	}

	protected void drawThings(List<Item> get_things,int[][] data) {
		// TODO
	}

	protected void drawHero(Hero hero) {
        drawTexture(HERO_TEXTURE, hero.get_x(), hero.get_y());
	}

	protected void drawMap(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				drawTexture(data[i][j], j, i);
			}
		}
	}

	protected void drawTexture(int textureIndex, int x, int y) {
		_graphics.fillRect(
                ORIGIN_X + x * CELL_SIZE,
				ORIGIN_Y - y * CELL_SIZE,
                CELL_SIZE, CELL_SIZE,
                textureIndex);
	}
}
