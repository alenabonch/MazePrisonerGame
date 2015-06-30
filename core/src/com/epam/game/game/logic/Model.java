package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final String TAG = Model.class.getName();

	Logic logic;
    State state;
    Hero hero;
	List<ModelListener> _listeners = new ArrayList<ModelListener>();

	public Model() {
		state = new State();
		hero = new Hero(state.getNowLevel().getMap().getStartX(), state.getNowLevel().getMap().getStartY());
		state.setHero(hero);
		logic = new Logic(state);
        Gdx.app.log(TAG, "Model created with hero: " + hero);
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


    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
