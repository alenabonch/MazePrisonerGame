package com.epam.game.game.logic;


public class State implements Cloneable{

	Level[] _levels = new Level[Constants.MAX_LEVEL];
	Hero _hero;
	int _levelNum;

	public State() {
		_levelNum = 0;
		_levels[_levelNum] = new Level(_levelNum);

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
