package com.epam.game.game.objects;

/**
 * Created by Alenka on 21.06.2015.
 */
public class Layer {

    private static int[][] layer = {
            {1, 1, 1, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 1},
    };

    public static int[][] getLayer() {
        return layer;
    }
}
