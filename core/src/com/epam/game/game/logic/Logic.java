package com.epam.game.game.logic;

import com.epam.game.game.actors.Item;
import com.epam.game.game.utils.Constants;

import java.util.List;

public class Logic {

	private static final String TAG = Logic.class.getName();

	private State state;

	public Logic(final State state) {
		this.state = state;
	}

	boolean moveLeft(){
		if(canGo(-1 ,0))
		{
			state.getHero().moveLeft();
            processMovement();
			return true;
		}
		return false;
	}

    boolean moveRight(){
		if(canGo(1, 0))
		{
			state.getHero().moveRight();
            processMovement();
			return true;
		}
		return false;
	}

	boolean moveDown(){
		if(canGo(0, -1))
		{
			state.getHero().moveDown();
            processMovement();
			return true;
		}
		return false;
	}

	boolean moveUp(){
		if(canGo(0, 1))
		{
			state.getHero().moveUp();
            processMovement();
			return true;
		}
		return false;
	}

//    boolean moveToCoordinates(float x, float y){
//        if(canGo(x, y))
//        {
//            state.getHero().moveUp();
//            processMovement();
//            return true;
//        }
//        return false;
//    }

    private void processMovement() {
        activeTexture();
        checkCollisions();
        recalculateVisibility();
    }

	void recalculateVisibility(){
		for (int i = state.getHero().getHeroX() - Constants.MAX_VIEW_SIZE_RECALCULATE; i <= state.getHero().getHeroX() + Constants.MAX_VIEW_SIZE_RECALCULATE; i++) {
			for (int j = state.getHero().getHeroY() - Constants.MAX_VIEW_SIZE_RECALCULATE; j <= state.getHero().getHeroY() + Constants.MAX_VIEW_SIZE_RECALCULATE; j++) {
				state.getCurrentLevel().getMap().hideCell(i, j);
			}
		}
		openEyes(state.getHero().getHeroX(), state.getHero().getHeroY());
	}
	void openEyes(final int x,final int y){
		int rX = x, lX = x, uY = y, dY = y;
		while((state.getCurrentLevel().getMap().ifFree(rX, y)) && (rX <= x + Constants.MAX_VIEW_SIZE)){
			rX++;
		}
		while((state.getCurrentLevel().getMap().ifFree(lX, y)) && (lX >= x - Constants.MAX_VIEW_SIZE)){
			lX--;
		}
		while((state.getCurrentLevel().getMap().ifFree(x, uY)) && (uY <= y + Constants.MAX_VIEW_SIZE)){
			uY++;
		}
		while((state.getCurrentLevel().getMap().ifFree(x, dY)) && (dY >= y - Constants.MAX_VIEW_SIZE)){
			dY--;
		}
		for (int i = lX; i <= rX; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				state.getCurrentLevel().getMap().showCell(i, j);
			}
		}
		for (int j = dY; j <= uY; j++) {
			for (int i = x - 1; i <= x + 1; i++) {
				state.getCurrentLevel().getMap().showCell(i, j);
			}
		}
	}

	private boolean canGo(int x, int y){
		if (state.getCurrentLevel().getMap().ifFree(state.getHero().getHeroX() +
				x * Constants.STEP_SIZE, state.getHero().getHeroY() + y * Constants.STEP_SIZE)){
			return true;
		}
		return false;
	}

    private void activeTexture(){
        int x = state.getHero().getHeroX();
        int y = state.getHero().getHeroY();
        switch (state.getCurrentLevel().getMap().activeTexture(x, y)) {
            case Constants.EXIT_CLASS_TEXTURE_INDEX:
				if(state.getCurrentLevel().getItemsOnMap().isEmpty()) {
					state.incLevel();
				}
                break;
            default:
                break;
        }
    }

    private void checkCollisions(){
        Item item = getItemUnderHero();
        if (item != null) {
            onCollisionHeroWithItem(item);
        }
    }

    private Item getItemUnderHero(){
        int heroX = state.getHero().getHeroX();
        int heroY = state.getHero().getHeroY();

        List<Item> itemsOnMap = state.getCurrentLevel().getItemsOnMap();

        for (Item item : itemsOnMap) {
            if (item.getItemX() == heroX && item.getItemY() == heroY) {
                return item;
            }
        }
        return null;
    }

    private void onCollisionHeroWithItem(Item item){
        List<Item> itemsOnMap = state.getCurrentLevel().getItemsOnMap();
        List<Item> itemsInBag = state.getHero().getItemsInBag();

        itemsInBag.add(item);
        itemsOnMap.remove(item);
        item.getStage().getRoot().removeActor(item);
    }

	public State getState() {
		try {
			return (State) state.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
