package com.epam.game.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Model {

	Logic logic;
	List<ModelListener> _listeners = new ArrayList<ModelListener>();

	public Model() {

		State state = new State();
		Hero hero = new Hero(state.getNowLevel().getMap().getStartX(), state.getNowLevel().getMap().getStartY());
		state.setHero(hero);
		logic = new Logic(state);
	}


	public void addListener(ModelListener listener){
		_listeners.add(listener);
	}

	public void removeListener(ModelListener listener){
		_listeners.remove(listener);
	}


	public Logic getLogic() {
		return logic;
	}


	void fireChangedEvent(){
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(logic.getState());
		}
	}

	public void moveLeft(){
		if(logic.moveLeft()){
			fireChangedEvent();
		}
	}
	public void moveRight(){
		if(logic.moveRight()){
			fireChangedEvent();
		}
	}
	public void moveUp(){
		if(logic.moveUp()){
			fireChangedEvent();
		}
	}
	public void moveDown(){
		if(logic.moveDown()){
			fireChangedEvent();
		}
	}
	public void openEyes(){
		logic.openEyes(logic.getState().getHero().get_x(), logic.getState().getHero().get_y());
		fireChangedEvent();
	}
	
}
