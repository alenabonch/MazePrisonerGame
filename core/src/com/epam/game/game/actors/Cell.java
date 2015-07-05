package com.epam.game.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.epam.game.game.utils.Constants.CELL_SIZE;
import static com.epam.game.game.utils.Constants.NUMBER_OF_TEXTURE;


public class Cell extends Actor {

    private static final String TAG = Cell.class.getName();

    private Texture texture;
    private int calc;
    static private Texture[] ARRAYS_OF_TEXTURES = {
            new Texture(Gdx.files.internal("fog3.jpg")),
            new Texture(Gdx.files.internal("grass-dark.png")),
            new Texture(Gdx.files.internal("grass.png")),
            new Texture(Gdx.files.internal("wall444.png")),
            new Texture(Gdx.files.internal("wall1.png")),
            new Texture(Gdx.files.internal("castle-mist.png")),
            new Texture(Gdx.files.internal("castle.png")),
            new Texture(Gdx.files.internal("ladder-mist.png")),
            new Texture(Gdx.files.internal("ladder.png")),
    };

    public Cell(int textureIndex){
        setTextureIndex(textureIndex);
    }

    public void setTextureIndex(int textureIndex) {
//        if (textureIndex > ARRAYS_OF_TEXTURES.length - 1) {
//            return;
//        }

        if (textureIndex % NUMBER_OF_TEXTURE == 0){
            texture = ARRAYS_OF_TEXTURES[0];
        } else {
            calc = (textureIndex / NUMBER_OF_TEXTURE) * (NUMBER_OF_TEXTURE - 1)
                    + (textureIndex % NUMBER_OF_TEXTURE);
            System.out.println(calc);
            texture = ARRAYS_OF_TEXTURES[calc];
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), this.getY(), this.getOriginX(),
                this.getOriginY(), CELL_SIZE, CELL_SIZE,
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

}
