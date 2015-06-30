package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.LinkedList;
import java.util.List;

public class Hero extends Actor implements Moveable{

    private static final String TAG = Hero.class.getName();

    private Texture texture = new Texture(Gdx.files.internal("boy.png"));

    private int _x;
    private int _y;

    private boolean isFlipped = true;
    private boolean isMovedLeft = false;
    private boolean isMovedRight = true;
    private int	_health;

    private List<Item> _items = new LinkedList<Item>();
    private int _sizeOfBag = Constants.SIZE_OF_BAG;
    private int _numberOfItems;

	public Hero(int x, int y) {
        Gdx.app.log(TAG, "in constructor, hero created in x: " + x + ", y: " + y);
		_health = Constants.START_HERO_HEALTH;
		_x = x;
		_y = y;
	}

    @Override
    public void moveLeft() {
        _x--;
    }
    @Override
    public void moveRight() {
        _x++;
    }
    @Override
    public void moveDown() {
        _y--;
    }
    @Override
    public void moveUp() {
        _y++;
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

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public int get_health() {
        return _health;
    }

    public boolean takeItem(Item item){
        if(++_numberOfItems < _sizeOfBag){
            _items.add(item);
            return true;
        }
        else
        {
            _numberOfItems--;
            return false;
        }
    }

    public void removeItem(Item item){
        _items.remove(item);
    }
}
