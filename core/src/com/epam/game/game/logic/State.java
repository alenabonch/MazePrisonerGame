package com.epam.game.game.logic;


import com.badlogic.gdx.Gdx;

public class State implements Cloneable{

	private static final String TAG = State.class.getName();

	Level[] _levels = new Level[Constants.MAX_LEVEL];
	Hero _hero;
	int _levelNum;

	public State() {
		_levelNum = 0;
		_levels[_levelNum] = new Level(_levelNum);
		Gdx.app.log(TAG, "In state constructor");
	}
	public Hero getHero() {
		return _hero;
	}

	public void setHero(Hero _hero) {
		this._hero = _hero;
	}

	public int get_levelNum() {
		return _levelNum;
	}

	public Level getNowLevel(){
		return _levels[_levelNum];
	}
	public Level getLevel(int levelNum){
		return _levels[levelNum];
	}

	public void incLevel(){
		_levelNum++;
		if(_levelNum > Constants.MAX_LEVEL){
			//Game End!! YOU WIN!
		}else if (_levels[_levelNum] == null){
			_levels[_levelNum] = new Level(_levelNum);
			_hero.set_x(getNowLevel().getMap().getStartX());
			_hero.set_y(getNowLevel().getMap().getStartY());
		}
	}
	public void decLevel(){
		_levelNum--;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
