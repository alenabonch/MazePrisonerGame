package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.epam.game.game.logic.Constants.CELL_SIZE;
import static com.epam.game.game.logic.Constants.NUMBER_OF_TEXTURE;


public class Cell extends Actor {

    private Texture texture;
    private int calc;
    static private Texture[] ARRAYSOFTEXTURES = {
            new Texture(Gdx.files.internal("black.png")),
            new Texture(Gdx.files.internal("grass11.png")),
            new Texture(Gdx.files.internal("grass22.png")),
            new Texture(Gdx.files.internal("wall444.png")),
            new Texture(Gdx.files.internal("wall1.png")),
//            new Texture(Gdx.files.internal("girl.png"))
    };

    public Cell(int textureIndex){
        setTextureIndex(textureIndex);
    }

    public void setTextureIndex(int textureIndex) {

        if (textureIndex % NUMBER_OF_TEXTURE == 0){
            texture = ARRAYSOFTEXTURES[0];
        }
        else
        {
            calc = (textureIndex / NUMBER_OF_TEXTURE) * (NUMBER_OF_TEXTURE - 1)
                    + (textureIndex % NUMBER_OF_TEXTURE);
            System.out.println(calc);
            texture = ARRAYSOFTEXTURES[calc];
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), this.getY(), this.getOriginX(),
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
