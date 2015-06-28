package com.epam.game.game.logic;

public class Logic {

	private State _state;

	public Logic(final State state) {
		_state = state;
	}

	boolean moveLeft(){
		if(_state.getNowLevel().get_map().ifFree(_state.getHero().get_x() - Constants.STEP_SIZE, _state.getHero().get_y()))
		{
			_state.getHero().moveLeft();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveRight(){
		if(_state.getNowLevel().get_map().ifFree(_state.getHero().get_x() + Constants.STEP_SIZE, _state.getHero().get_y()))
		{
			_state.getHero().moveRight();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveDown(){
		if(_state.getNowLevel().get_map().ifFree(_state.getHero().get_x(), _state.getHero().get_y() - Constants.STEP_SIZE))
		{
			_state.getHero().moveDown();
			recalculateVisibility();
			return true;
		}
		return false;
	}

	boolean moveUp(){
		if(_state.getNowLevel().get_map().ifFree(_state.getHero().get_x(), _state.getHero().get_y() + Constants.STEP_SIZE))
		{
			_state.getHero().moveUp();
			recalculateVisibility();
			return true;
		}
		return false;

	}
	void recalculateVisibility(){
		for (int i = _state.getHero().get_x() - Constants.MAX_VIEW_SIZE_RECALCULATE; i <= _state.getHero().get_x() + Constants.MAX_VIEW_SIZE_RECALCULATE; i++) {
			for (int j = _state.getHero().get_y() - Constants.MAX_VIEW_SIZE_RECALCULATE; j <= _state.getHero().get_y() + Constants.MAX_VIEW_SIZE_RECALCULATE; j++) {
				_state.getNowLevel().get_map().hideCell(i, j);
			}
		}
		openEyes(_state.getHero().get_x(),_state.getHero().get_y());
	}
	void openEyes(int x, int y){
		int rX = x, lX = x, uY = y, dY = y;
		while((_state.getNowLevel().get_map().ifFree(rX, y)) && (rX <= x + Constants.MAX_VIEW_SIZE)){
			rX++;
		}
		while((_state.getNowLevel().get_map().ifFree(lX, y)) && (lX >= x - Constants.MAX_VIEW_SIZE)){
			lX--;
		}
		while((_state.getNowLevel().get_map().ifFree(x, uY)) && (uY <= y + Constants.MAX_VIEW_SIZE)){
			uY++;
		}
		while((_state.getNowLevel().get_map().ifFree(x, dY)) && (dY >= y - Constants.MAX_VIEW_SIZE)){
			dY--;
		}
		for (int i = lX; i <= rX; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				_state.getNowLevel().get_map().showCell(i, j);
			}
		}
		for (int j = dY; j <= uY; j++) {
			for (int i = x - 1; i <= x + 1; i++) {
				_state.getNowLevel().get_map().showCell(i, j);
			}
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
