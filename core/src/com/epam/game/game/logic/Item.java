package com.epam.game.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.epam.game.game.logic.Constants.NUMBER_OF_TEXTURE;

public class Item extends Actor {

    private static final String TAG = Item.class.getName();
    private int _x;
    private int _y;
    private Texture texture;
    private int calc;
    static private Texture[] ARRAYS_OF_TEXTURES = {
            new Texture(Gdx.files.internal("key1.png")),
            new Texture(Gdx.files.internal("key1.png")),
            new Texture(Gdx.files.internal("key2.png")),
            new Texture(Gdx.files.internal("key2.png")),
//            new Texture(Gdx.files.internal("gold.png")),
//            new Texture(Gdx.files.internal("chest.png")),
//            new Texture(Gdx.files.internal("castle.png")),
//            new Texture(Gdx.files.internal("door-opened.png")),
//            new Texture(Gdx.files.internal("door-close.png")),
//            new Texture(Gdx.files.internal("door-close-mist.png")),
//            new Texture(Gdx.files.internal("door-locked.png")),
//            new Texture(Gdx.files.internal("door-locked-mist.png")),

    };

    public Item(int x, int y, int itemID) {
        Gdx.app.log(TAG, "in constructor, hero created in x: " + x + ", y: " + y);
        _x = x;
        _y = y;
        texture = ARRAYS_OF_TEXTURES[itemID];
    }

    public int get_x() {
        return _x;
    }


    public int get_y() {
        return _y;
    }

    public static int getNumberOfItems(){
        return ARRAYS_OF_TEXTURES.length;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), this.getOriginX(),
                this.getOriginY(), Constants.CELL_SIZE, Constants.CELL_SIZE,
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }



    public void setTextureIndex(int textureIndex) {

        if (textureIndex % NUMBER_OF_TEXTURE == 0){
            texture = ARRAYS_OF_TEXTURES[0];
        }
        else
        {
            calc = (textureIndex / NUMBER_OF_TEXTURE) * (NUMBER_OF_TEXTURE - 1)
                    + (textureIndex % NUMBER_OF_TEXTURE);
            System.out.println(calc);
            texture = ARRAYS_OF_TEXTURES[calc];
        }
    }
}
