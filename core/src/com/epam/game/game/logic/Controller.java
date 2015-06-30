package com.epam.game.game.logic;

public class Controller implements ModelListener{

	private static final String TAG = Controller.class.getName();

	private View _view;
	private Model _model;

	public void startGame(){
		_model.openEyes();
	}

	@Override
	public void onChange(State state) {
		_view.draw(state);
	}

	public void setModel(Model model) {
		_model = model;
	}
	public void setView(View view) {
		_view = view;		
	}

	public void moveLeft() {
		_model.moveLeft();
	}

	public void moveRight() {
		_model.moveRight();
	}

	public void moveUp() {
		_model.moveUp();
	}

	public void moveDown() {
		_model.moveDown();
	}
}
