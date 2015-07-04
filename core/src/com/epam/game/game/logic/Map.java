package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.utils.Constants;

public class Map implements Cloneable{

    private static final String TAG = Map.class.getName();


    private int startHeroX;
    private int startHeroY;

    private int exitY;
    private int exitX;

    private int[][] map;
    private int width;
    private int height;


    Map(final int width,final int height) {
        map = createMap(width, height);
        findOut();
        findIn();
        this.height = map.length;
        this.width = map[this.height - 1].length;
        Gdx.app.log(TAG, "Map created, width = " + width + " height = " + height);
	}

    public int getExitY() {
        return exitY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean inMap(final int x,final int y){
        return ((x >= 0) && (x < width) && (y >= 0) && (y < height));
    }

    public boolean ifFree(final int x,final int y){
        if(inMap(x,y)){
            if ((map[y][x] / Constants.NUMBER_OF_TEXTURE) == Constants.WALL_CLASS_TEXTURE_INDEX){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean ifExit(final int x,final int y){
        if(inMap(x,y)){
            if ((map[y][x] / Constants.NUMBER_OF_TEXTURE) == Constants.EXIT_CLASS_TEXTURE_INDEX){
                return true;
            }
            return false;
        }
        return false;
    }

    public int activeTexture(final int x, final int y){
        if(inMap(x,y)){
            if (ifExit(x, y)){
                return Constants.EXIT_CLASS_TEXTURE_INDEX;
            }
            else
            {
                return 0;
            }
        }
        return 0;
    }


    public int[][] getData() {
        return map;
    }

    private int[][] createMap(int width, int height){
        if (width % 2 == 0)
            width++;

        if (height % 2 == 0)
            height++;

        return MapGenerator.generateMaze(new int[height][width]);
    }
    public int getStartX() {
        return startHeroX;
    }


    public int getStartY() {
        return startHeroY;
    }


    public void hideCell(final int x,final int y){
        if((x >= 0) && (x < width) && (y >= 0) && (y < height)){
            if((map[y][x] % Constants.NUMBER_OF_TEXTURE) == Constants.VISIBLE_TEXTURE_INDEX){
                map[y][x]--;
            }
        }
    }
    public void showCell(final int x,final int y){
        if((x >= 0) && (x < width) && (y >= 0) && (y < height)){
            map[y][x] = map[y][x] - (map[y][x] % Constants.NUMBER_OF_TEXTURE) + Constants.VISIBLE_TEXTURE_INDEX;
        }
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    private void findOut(){
        OUTER:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ((map[i][j] / Constants.NUMBER_OF_TEXTURE) == Constants.EXIT_CLASS_TEXTURE_INDEX){
                    exitX = j;
                    exitY = i;
                    break OUTER;
                }
            }
        }
    }
    private void findIn(){
        map = MapGenerator.searchEndOfMedian(map, exitX, exitY, Constants.IN_TEXTURE_INDEX);
        OUTER:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Constants.IN_TEXTURE_INDEX){
                    startHeroY = i;
                    startHeroX = j;
                    map[i][j] = Constants.GROUND_TEXTURE_INDEX;
                    break OUTER;
                }
            }
        }
    }
}
