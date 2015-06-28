package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.epam.game.game.logic.Constants.CELL_SIZE;
import static com.epam.game.game.logic.Constants.HERO_TEXTURE;


public class Cell extends Actor {

    private Texture texture;

    public Cell(int textureIndex){
        switch (textureIndex) {
            case 0:
            case 3:
                texture = new Texture(Gdx.files.internal("black.png"));
                break;
            case 1:
                texture = new Texture(Gdx.files.internal("grass11.png"));
                break;
            case 2:
                texture = new Texture(Gdx.files.internal("grass22.png"));
                break;
            case 4:
                texture = new Texture(Gdx.files.internal("wall444.png"));
                break;
            case 5:
                texture = new Texture(Gdx.files.internal("wall1.png"));
                break;
            case HERO_TEXTURE:
                texture = new Texture(Gdx.files.internal("girl.png"));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), getY(), this.getOriginX(),
                this.getOriginY(), CELL_SIZE, CELL_SIZE,
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

//	public void draw(SpriteBatch batch,int x, int y){
//		sprite.setPosition(x - MazePrisonerGame.FIELD_SIZE/2 - sprite.getWidth()/2,
//						y -  MazePrisonerGame.FIELD_SIZE/2 - sprite.getHeight()/2);
//		sprite.draw(batch);
//	}
}
