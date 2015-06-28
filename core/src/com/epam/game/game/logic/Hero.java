package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.LinkedList;
import java.util.List;

public class Hero extends Actor implements Moveable{

    private Texture texture = new Texture(Gdx.files.internal("girl.png"));

    private int _x;
    private int _y;

    private int	_health;

    private List<Item> _items = new LinkedList<Item>();
    private int _sizeOfBag = Constants.SIZE_OF_BAG;
    private int _numberOfItems;

	public Hero(int x, int y) {
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

//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(texture, this.get_x(), this.get_y(), this.getOriginX(),
//                this.getOriginY(), this.getWidth(), this.getHeight(),
//                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
//                texture.getWidth(), texture.getHeight(), false, false);
//    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.get_x(), this.get_y(), this.get_x(),
                this.get_y(), this.getWidth(), this.getHeight(),
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
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
