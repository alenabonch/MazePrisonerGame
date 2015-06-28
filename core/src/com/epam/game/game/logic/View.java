package com.epam.game.game.logic;

import java.util.List;

import static com.epam.game.game.logic.Constants.*;

public class View {

	private Graphics _graphics;

	public void setGraphics(Graphics graphics) {
		this._graphics = graphics;
	}

	public void draw(State state) {
		drawMap(state.getNowLevel().get_map().getData());
		drawHero(state.getHero());
//		drawThings(state.getNowLevel().get_things(), state.getNowLevel().get_map().getData());
//		drawMobs(state.getNowLevel().get_mobs(), state.getNowLevel().get_map().getData());
	}

	protected void drawMobs(List<Mob> get_mobs, int[][] data) {
		// TODO Auto-generated method stub
	}

	protected void drawThings(List<Thing> get_things, int[][] data) {
		// TODO Auto-generated method stub
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
