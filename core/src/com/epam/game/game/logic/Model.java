package com.epam.game.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Model {

	Logic _logic;
	List<ModelListener> _listeners = new ArrayList<ModelListener>();

	public Model() {

		State state = new State();
		Hero hero = new Hero(state.getNowLevel().get_map().getStartX(), state.getNowLevel().get_map().getStartY());
		state.setHero(hero);
		_logic = new Logic(state);

	}


	public void addListener(ModelListener listener){
		_listeners.add(listener);
	}

	public void removeListener(ModelListener listener){
		_listeners.remove(listener);
	}

	void fireChangedEvent(){
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(_logic.getState());
		}
	}

	public void moveLeft(){
		if(_logic.moveLeft()){
			fireChangedEvent();
		}
	}
	public void moveRight(){
		if(_logic.moveRight()){
			fireChangedEvent();
		}
	}
	public void moveUp(){
		if(_logic.moveUp()){
			fireChangedEvent();
		}
	}
	public void moveDown(){
		if(_logic.moveDown()){
			fireChangedEvent();
		}
	}
	public void openEyes(){
		_logic.openEyes(_logic.getState().getHero().get_x(), _logic.getState().getHero().get_y());
		fireChangedEvent();
	}
	
}
