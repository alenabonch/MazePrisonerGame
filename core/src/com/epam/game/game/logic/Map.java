package com.epam.game.game.logic;

public class Map implements Cloneable{

	private int _startX;
	private int _startY;
	private int[][] _map;
	private int _width;
	private int _heigth;

	Map(final int width,final int height) {
		_map = createMap(width, height);
		_heigth = _map.length;
		_width = _map[_heigth - 1].length;
	}

	public boolean ifFree(final int x,final int y){
		if((x >= 0) && (x < _width) && (y >= 0) && (y < _heigth)){
			if ((_map[y][x] / Constants.NUMBER_OF_TEXTURE) == 0){
				return true;
			}
		}
		return false;
	}

	public int[][] getData() {
		return _map;
	}


	private int[][] createMap(int width, int height){
		_startX = 3;
		_startY = 3;
		if (width % 2 == 0)
			width++;

		if (height % 2 == 0)
			height++;

		return generateLabyrinth(new int[height][width]);
	}
	public int getStartX() {
		return _startX;
	}


	public int getStartY() {
		return _startY;
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

	private int[][] generateLabyrinth(int[][] cleanArray){
		for (int i = 0; i < cleanArray.length; i++) {
			for (int j = 0; j < cleanArray[i].length; j++) {
				if((i == 0) || (j == 0) || (i == cleanArray.length - 1) || (j == cleanArray[i].length - 1)){
					cleanArray[i][j] = 3;//todo
				}
			}


		}
		return cleanArray;
	}
}
