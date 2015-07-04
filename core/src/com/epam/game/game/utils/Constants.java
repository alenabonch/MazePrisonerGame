package com.epam.game.game.utils;


import java.util.Random;

public class Constants {

    static public Random RANDOM = new Random();

    public static final int CELL_SIZE = 30;
    public static final int MAX_LEVEL = 20;
    public static final int START_HERO_HEALTH = 3;
    public static final int START_MOBS_HEALTH = 3;

    public static final int STEP_SIZE = 1;
    public static final int MAX_VIEW_SIZE = 3;
    public static final int MAX_VIEW_SIZE_RECALCULATE = MAX_VIEW_SIZE + 2;

    public static final int NUMBER_OF_TEXTURE = 3;
    public static final int VISIBLE_TEXTURE_INDEX = 2;

    public static final int WALL_TEXTURE_INDEX = 3;
    public static final int WALL_CLASS_TEXTURE_INDEX = 1;

    public static final int GROUND_TEXTURE_INDEX = 0;
    public static final int GROUND_CLASS_TEXTURE_INDEX = 0;

    public static final int EXIT_TEXTURE_INDEX = 6;
    public static final int EXIT_CLASS_TEXTURE_INDEX = 2;

    public static final int SIZE_MAP_X = 19;
    public static final int SIZE_MAP_Y = 15;

    public static final int HERO_TEXTURE = 9;

    public static final int TEXTURE_SIZE = 20;

    public static final int ORIGIN_X = 50;
    public static final int ORIGIN_Y = 750;
    public static final int SIZE_OF_BAG = 5;
}
