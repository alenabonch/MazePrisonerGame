package com.epam.game.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.epam.game.game.utils.Constants;
import com.epam.game.game.logic.Moveable;

import java.util.LinkedList;
import java.util.List;

public class Hero extends Actor implements Moveable {

    private static final String TAG = Hero.class.getName();

    private Texture texture = new Texture(Gdx.files.internal("boy.png"));

    private int heroX;
    private int heroY;

    private int health;

    private boolean isFlipped = true;
    private boolean isMovedLeft = false;
    private boolean isMovedRight = true;

    private List<Item> itemsInBag = new LinkedList<Item>();

    private int sizeOfBag = Constants.SIZE_OF_BAG;
    private int numberOfItems;

	public Hero(int x, int y) {
		heroX = x;
		heroY = y;
        health = Constants.START_HERO_HEALTH;
        Gdx.app.log(TAG, "hero created in x: " + x + ", y: " + y);
	}

    @Override
    public void moveLeft() {
        heroX--;
    }
    @Override
    public void moveRight() {
        heroX++;
    }
    @Override
    public void moveDown() {
        heroY--;
    }
    @Override
    public void moveUp() {
        heroY++;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), this.getOriginX(),
                this.getOriginY(), Constants.CELL_SIZE, Constants.CELL_SIZE,
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), isFlipped, false);
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setIsFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    public boolean isMovedLeft() {
        return isMovedLeft;
    }

    public void setIsMovedLeft(boolean isMovedLeft) {
        this.isMovedLeft = isMovedLeft;
    }

    public boolean isMovedRight() {
        return isMovedRight;
    }

    public void setIsMovedRight(boolean isMovedRight) {
        this.isMovedRight = isMovedRight;
    }

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public void setHeroX(int xHero) {
        this.heroX = xHero;
    }

    public void setHeroY(int yHero) {
        this.heroY = yHero;
    }

    public int getHealth() {
        return health;
    }

    public List<Item> getItemsInBag() {
        return itemsInBag;
    }

//    public void setItemsInBag(List<Item> itemsInBag) {
//        this.itemsInBag = itemsInBag;
//    }

//        public boolean takeItem(Item item){
//        if(++numberOfItems < sizeOfBag){
//            itemsInBag.add(item);
//            return true;
//        }
//        else
//        {
//            numberOfItems--;
//            return false;
//        }
//    }
//
//    public void removeItem(Item item){
//        itemsInBag.remove(item);
//    }
}
