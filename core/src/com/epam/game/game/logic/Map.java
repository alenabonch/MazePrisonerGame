package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.epam.game.game.GeneratorMap;

public class Map implements Cloneable{

    private static final String TAG = Map.class.getName();


    private int _startHeroX;
    private int _startHeroY;

    private int _exitY;
    private int _exitX;

    private int[][] _map;
    private int _width;
    private int _heigth;


    Map(final int width,final int height) {
        _map = createMap(width, height);
        findExit();
        _heigth = _map.length;
        _width = _map[_heigth - 1].length;
        Gdx.app.log(TAG, "Map created, width = " + width + " height = " + height);
	}

    public int get_exitY() {
        return _exitY;
    }

    public int get_exitX() {
        return _exitX;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeigth() {
        return _heigth;
    }

    public boolean inMap(final int x,final int y){
        return ((x >= 0) && (x < _width) && (y >= 0) && (y < _heigth));
    }

    public boolean ifFree(final int x,final int y){
        if(inMap(x,y)){
            if ((_map[y][x] / Constants.NUMBER_OF_TEXTURE) == Constants.WALL_CLASS_TEXTURE_INDEX){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean ifExit(final int x,final int y){
        if(inMap(x,y)){
            if ((_map[y][x] / Constants.NUMBER_OF_TEXTURE) == Constants.EXIT_CLASS_TEXTURE_INDEX){
                return true;
            }
            return false;
        }
        return false;
    }

    public int ActiveTeture (final int x,final int y){
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
        return _map;
    }

    private int[][] createMap(int wigth, int heigth){
        _startHeroX = 1;
        _startHeroY = 1;
        if (wigth % 2 == 0)
            wigth++;

        if (heigth % 2 == 0)
            heigth++;

        return GeneratorMap.generateLabyrinth(new int[heigth][wigth]);
    }
    public int getStartX() {
        return _startHeroX;
    }


    public int getStartY() {
        return _startHeroY;
    }


    public void hideCell(final int x,final int y){
        if((x >= 0) && (x < _width) && (y >= 0) && (y < _heigth)){
            if((_map[y][x] % Constants.NUMBER_OF_TEXTURE) == Constants.VISIBLE_TEXTURE_INDEX){
                _map[y][x]--;
            }
        }
    }
    public void showCell(final int x,final int y){
        if((x >= 0) && (x < _width) && (y >= 0) && (y < _heigth)){
            _map[y][x] = _map[y][x] - (_map[y][x] % Constants.NUMBER_OF_TEXTURE) + Constants.VISIBLE_TEXTURE_INDEX;
        }
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    private void findExit(){
        OUTER:
        for (int i = 0; i < _map.length; i++) {
            for (int j = 0; j < _map[i].length; j++) {
                if ((_map[i][j] / Constants.NUMBER_OF_TEXTURE) == Constants.EXIT_CLASS_TEXTURE_INDEX){
                    _exitX = j;
                    _exitY = i;
                    break OUTER;
                }
            }
        }
    }
}
