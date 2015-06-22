package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epam.game.MazePrisonerGame;

public abstract class Cell {
	
	Sprite sprite;
	
	public Cell(Texture texture){
		sprite = new Sprite(texture);
		sprite.setSize(1, 1);
	}
	
	public abstract void update(Cell[][] map, int x, int y, Texture texture);
	
	public void draw(SpriteBatch batch,int x, int y){
		sprite.setPosition(x - MazePrisonerGame.FIELD_SIZE/2 - sprite.getWidth()/2,
						y -  MazePrisonerGame.FIELD_SIZE/2 - sprite.getHeight()/2);
		sprite.draw(batch);
	}
}
