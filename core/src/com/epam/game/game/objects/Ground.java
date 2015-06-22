package com.epam.game.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.epam.game.game.Cell;

/**
 * Created by Alenka on 21.06.2015.
 */
public class Ground extends Cell {

    public Ground(Texture texture) {
        super(texture);
    }

    @Override
    public void update(Cell[][] map, int x, int y, Texture texture) {

    }
}
