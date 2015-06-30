package com.epam.game.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HeroScratched extends Image {

    Texture img;
    TextureRegion region;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    public HeroScratched(Texture img) {
        region = new TextureRegion(img);
        setBounds(getX(), getY(), getWidth(), getHeight());
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.NUM_1:
                        MoveToAction moveToAction = new MoveToAction();
                        moveToAction.setPosition(200f, 200f);
                        moveToAction.setDuration(5f);
                        HeroScratched.this.addAction(moveToAction);
                        break;

                    case Input.Keys.NUM_2:
                        MoveByAction moveByAction = new MoveByAction();
                        moveByAction.setAmount(-200f, 0f);
                        moveByAction.setDuration(3f);
                        HeroScratched.this.addAction(moveByAction);
                        break;

                    case Input.Keys.NUM_3:
                        ColorAction colorAction = new ColorAction();
                        colorAction.setEndColor(Color.CYAN);
                        colorAction.setDuration(5f);
                        HeroScratched.this.addAction(colorAction);
                        break;

                    case Input.Keys.NUM_4:

                        MoveToAction mta = new MoveToAction();
                        mta.setPosition(Gdx.graphics.getWidth()-200f, Gdx.graphics.getHeight() - 200f);
                        mta.setDuration(3f);

                        ScaleByAction sba = new ScaleByAction();
                        sba.setAmount(2f);
                        sba.setAmount(3f);

                        RotateByAction rba = new RotateByAction();
                        rba.setAmount(90f);
                        rba.setDuration(3f);
                        ParallelAction pa = new ParallelAction(mta, sba, rba);
                        HeroScratched.this.addAction(pa);
                        break;

                    case Input.Keys.NUM_5:

                        MoveToAction mta1 = new MoveToAction();
                        mta1.setPosition(Gdx.graphics.getWidth()-200f, Gdx.graphics.getHeight() - 200f);
                        mta1.setDuration(3f);

                        ScaleByAction sba1 = new ScaleByAction();
                        sba1.setAmount(2f);
                        sba1.setAmount(3f);

                        RotateByAction rba1 = new RotateByAction();
                        rba1.setAmount(90f);
                        rba1.setDuration(3f);
                        SequenceAction pa1 = new SequenceAction(mta1, sba1, rba1);
                        HeroScratched.this.addAction(pa1);
                        break;

                    case Input.Keys.SPACE:
                        addAction(parallel(
                                moveTo(200f, 200f, 3f),
                                scaleTo(2f, 3f),
                                rotateTo(90f, 3f)
                        ));
                        break;
                }
                return true;
            }
        });
    }
}
