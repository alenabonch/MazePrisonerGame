package com.epam.game.game.logic;

import com.epam.game.game.utils.Constants;

public class Logic {

	private static final String TAG = Logic.class.getName();

	private State _state;

	public Logic(final State state) {
		_state = state;
	}

	boolean moveLeft(){
		if(canGo(-1 ,0))
		{
			_state.getHero().moveLeft();
			activeTexture();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveRight(){
		if(canGo(1, 0))
		{
			_state.getHero().moveRight();
			activeTexture();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveDown(){
		if(canGo(0, -1))
		{
			_state.getHero().moveDown();
			activeTexture();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveUp(){
		if(canGo(0, 1))
		{
			_state.getHero().moveUp();
			activeTexture();
			recalculateVisibility();
			return true;
		}
		return false;

	}
	void recalculateVisibility(){
		for (int i = _state.getHero().getHeroX() - Constants.MAX_VIEW_SIZE_RECALCULATE; i <= _state.getHero().getHeroX() + Constants.MAX_VIEW_SIZE_RECALCULATE; i++) {
			for (int j = _state.getHero().getHeroY() - Constants.MAX_VIEW_SIZE_RECALCULATE; j <= _state.getHero().getHeroY() + Constants.MAX_VIEW_SIZE_RECALCULATE; j++) {
				_state.getCurrentLevel().getMap().hideCell(i, j);
			}
		}
		openEyes(_state.getHero().getHeroX(),_state.getHero().getHeroY());
	}
	void openEyes(final int x,final int y){
		int rX = x, lX = x, uY = y, dY = y;
		while((_state.getCurrentLevel().getMap().ifFree(rX, y)) && (rX <= x + Constants.MAX_VIEW_SIZE)){
			rX++;
		}
		while((_state.getCurrentLevel().getMap().ifFree(lX, y)) && (lX >= x - Constants.MAX_VIEW_SIZE)){
			lX--;
		}
		while((_state.getCurrentLevel().getMap().ifFree(x, uY)) && (uY <= y + Constants.MAX_VIEW_SIZE)){
			uY++;
		}
		while((_state.getCurrentLevel().getMap().ifFree(x, dY)) && (dY >= y - Constants.MAX_VIEW_SIZE)){
			dY--;
		}
		for (int i = lX; i <= rX; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				_state.getCurrentLevel().getMap().showCell(i, j);
			}
		}
		for (int j = dY; j <= uY; j++) {
			for (int i = x - 1; i <= x + 1; i++) {
				_state.getCurrentLevel().getMap().showCell(i, j);
			}
		}
	}

	private boolean canGo(int x, int y){
		if (_state.getCurrentLevel().getMap().ifFree(_state.getHero().getHeroX() + x * Constants.STEP_SIZE, _state.getHero().getHeroY() + y * Constants.STEP_SIZE)){
			return true;
		}
		else
		{
			return false;
		}

	}

	private void activeTexture(){
		int x = _state.getHero().getHeroX();
		int y = _state.getHero().getHeroY();
		switch (_state.getCurrentLevel().getMap().activeTexture(x, y)) {
			case Constants.EXIT_CLASS_TEXTURE_INDEX:
				_state.incLevel();
				break;
			default:
				break;
		}
	}

	public State getState() {
		try {
			return (State) _state.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
