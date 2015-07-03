package com.epam.game.game.logic;


import com.badlogic.gdx.Gdx;
import com.epam.game.game.actors.Hero;
import com.epam.game.game.utils.Constants;

public class State implements Cloneable{

	private static final String TAG = State.class.getName();

	Level[] levels = new Level[Constants.MAX_LEVEL];
	Hero hero;
	int levelNum;

	public State() {
		levelNum = 0;
		levels[levelNum] = new Level(levelNum);
		Gdx.app.log(TAG, "In state constructor");
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero _hero) {
		this.hero = _hero;
	}

	public int getLevelNum() {
		return levelNum;
	}

	public Level getCurrentLevel(){
		return levels[levelNum];
	}

	public Level getLevel(int levelNum){
		return levels[levelNum];
	}

	public void incLevel(){

		levelNum++;
		if(levelNum >= Constants.MAX_LEVEL){
			//Game End!! YOU WIN!
		}else if (levels[levelNum] == null){
			levels[levelNum] = new Level(levelNum);
			hero.setHeroX(getCurrentLevel().getMap().getStartX());
			hero.setHeroY(getCurrentLevel().getMap().getStartY());
		}
	}

	public void decLevel(){
		levelNum--;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
