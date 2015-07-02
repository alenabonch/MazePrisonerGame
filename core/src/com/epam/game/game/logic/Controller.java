package com.epam.game.game.logic;

import com.epam.game.game.view.View;

public class Controller implements ModelListener{

	private static final String TAG = Controller.class.getName();

	private View view;
	private Model model;

	public void startGame(){
		model.openEyes();
	}

	@Override
	public void onChange(State state) {
		view.draw(state);
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void moveLeft() {
		model.moveLeft();
	}

	public void moveRight() {
		model.moveRight();
	}

	public void moveUp() {
		model.moveUp();
	}

	public void moveDown() {
		model.moveDown();
	}

//	public void moveToCoordinates(float x, float y) {
//		model.moveToCoordinates(x, y);
//	}
}
