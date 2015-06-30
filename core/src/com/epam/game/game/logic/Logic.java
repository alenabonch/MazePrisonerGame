package com.epam.game.game.logic;

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
		for (int i = _state.getHero().get_x() - Constants.MAX_VIEW_SIZE_RECALCULATE; i <= _state.getHero().get_x() + Constants.MAX_VIEW_SIZE_RECALCULATE; i++) {
			for (int j = _state.getHero().get_y() - Constants.MAX_VIEW_SIZE_RECALCULATE; j <= _state.getHero().get_y() + Constants.MAX_VIEW_SIZE_RECALCULATE; j++) {
				_state.getNowLevel().getMap().hideCell(i, j);
			}
		}
		openEyes(_state.getHero().get_x(),_state.getHero().get_y());
	}
	void openEyes(final int x,final int y){
		int rX = x, lX = x, uY = y, dY = y;
		while((_state.getNowLevel().getMap().ifFree(rX, y)) && (rX <= x + Constants.MAX_VIEW_SIZE)){
			rX++;
		}
		while((_state.getNowLevel().getMap().ifFree(lX, y)) && (lX >= x - Constants.MAX_VIEW_SIZE)){
			lX--;
		}
		while((_state.getNowLevel().getMap().ifFree(x, uY)) && (uY <= y + Constants.MAX_VIEW_SIZE)){
			uY++;
		}
		while((_state.getNowLevel().getMap().ifFree(x, dY)) && (dY >= y - Constants.MAX_VIEW_SIZE)){
			dY--;
		}
		for (int i = lX; i <= rX; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				_state.getNowLevel().getMap().showCell(i, j);
			}
		}
		for (int j = dY; j <= uY; j++) {
			for (int i = x - 1; i <= x + 1; i++) {
				_state.getNowLevel().getMap().showCell(i, j);
			}
		}
	}

	private boolean canGo(int x, int y){
		if (_state.getNowLevel().getMap().ifFree(_state.getHero().get_x() + x * Constants.STEP_SIZE, _state.getHero().get_y() + y * Constants.STEP_SIZE)){
			return true;
		}
		else
		{
			return false;
		}

	}

	private void activeTexture(){
		int x = _state.getHero().get_x();
		int y = _state.getHero().get_y();
		switch (_state.getNowLevel().getMap().ActiveTeture(x, y)) {
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
