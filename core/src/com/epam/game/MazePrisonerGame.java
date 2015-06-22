package com.epam.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.epam.game.game.*;
import com.epam.game.game.objects.Ground;
import com.epam.game.game.objects.Hero;
import com.epam.game.game.objects.Layer;
import com.epam.game.utils.Constants;

import static com.epam.game.utils.Constants.*;

public class MazePrisonerGame implements ApplicationListener {
    private static final String TAG = MazePrisonerGame.class.getName();

    public static float UPDATE_TIME = 0.001f;
    public static final int FIELD_SIZE = 15;

    private WorldController worldController;
    private WorldRenderer worldRenderer;

    private boolean paused;
    float elapsedTime;

    Stage stage;
    Texture girl;
    Texture wall;
    Texture ground;
    TextureRegion[] animationFrames;
    Animation animation;
    SpriteBatch batch;
    OrthographicCamera camera;

    Cell[][] map;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);

        map = new Cell[FIELD_SIZE][FIELD_SIZE];

        wall = new Texture(Gdx.files.internal("wall.png"));
        ground = new Texture(Gdx.files.internal("ground.png"));

        char[][] bmap = (new MazeGenerator()).getMaze(FIELD_SIZE - 1);
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (bmap[i][j] == 0)
                    map[i][j] = new Ground(ground);
                if (bmap[i][j] == 1)
                    map[i][j] = new Wall(wall);
            }


//        batch = new SpriteBatch();
//        girl = new Texture("hero_girl_sprite.png");
//        TextureRegion[][] tmpFrames = TextureRegion.split(girl, 72, 82);
//        animationFrames = new TextureRegion[50];
//
//        int index = 0;
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 10; j++) {
//                animationFrames[index++] = tmpFrames[j][i];
//            }
//        }
//        animation = new Animation(1f/4f, animationFrames);
//
//        Hero hero = new Hero(girl);
//
//        stage = new Stage(new ScreenViewport());
//        stage.addActor(hero);
//        stage.setKeyboardFocus(stage.getActors().first());
//        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//        worldRenderer.resize(width, height);
    }

    @Override
    public void render() {
//        this.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                map[i][j].draw(batch, i, j);
            }
        }
        batch.end();

//        elapsedTime += Gdx.graphics.getDeltaTime();
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);
//        batch.end();

    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void dispose() {
//        worldRenderer.dispose();
    }

//    public void update() {
//
//        Input input = Gdx.input;
//
//        for (int i = 0; i < FIELD_SIZE; i++) {
//            for (int j = 0; j < FIELD_SIZE; j++) {
//                map[i][j].update(map, i, j, wall);
//            }
//        }
//
//        if(input.isKeyPressed(Input.Keys.W))//сдвиг камеры, масштабирование, вращение, ускорение
//            camera.zoom-=Gdx.graphics.getDeltaTime();
//        if(input.isKeyPressed(Input.Keys.S))
//            camera.zoom+=Gdx.graphics.getDeltaTime();
//
//        if(input.isKeyPressed(Input.Keys.Q))
//            camera.rotate(Gdx.graphics.getDeltaTime()*90);
//        if(input.isKeyPressed(Input.Keys.E))
//            camera.rotate(-Gdx.graphics.getDeltaTime()*90);
//
//        if(input.isKeyPressed(Input.Keys.CONTROL_LEFT))
//            UPDATE_TIME+=Gdx.graphics.getDeltaTime();
//        if(input.isKeyPressed(Input.Keys.SHIFT_LEFT))
//            UPDATE_TIME-=Gdx.graphics.getDeltaTime();
//
//        if(input.isKeyPressed(Input.Keys.LEFT))
//            camera.translate(new Vector2(-Gdx.graphics.getDeltaTime()*50,0));
//        if(input.isKeyPressed(Input.Keys.RIGHT))
//            camera.translate(new Vector2(Gdx.graphics.getDeltaTime()*50,0));
//        if(input.isKeyPressed(Input.Keys.UP))
//            camera.translate(new Vector2(0,Gdx.graphics.getDeltaTime()*50));
//        if(input.isKeyPressed(Input.Keys.DOWN))
//            camera.translate(new Vector2(0,-Gdx.graphics.getDeltaTime()*50));
//
//        if(input.isKeyPressed(Input.Keys.SPACE)){//восстановление камеры
//            UPDATE_TIME = 1f;
//            camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
//        }
//
//        camera.update();

//        if (input.isTouched()) {
//            float stepX = Gdx.graphics.getWidth() / FIELD_SIZE;
//            float stepY = Gdx.graphics.getHeight() / FIELD_SIZE;
//            float x = input.getX();
//            float y = input.getY();
//            for (int i = 0; i < FIELD_SIZE; i++)
//                for (int j = 0; j < FIELD_SIZE; j++) {
//                    if (x >= stepX * i && x <= stepX * (i + 1) && y >= stepY * j && y <= stepY * (j + 1)) {
//                        if (map[i][FIELD_SIZE - j - 1] instanceof Ground) {
//                            map[i][FIELD_SIZE - j - 1] = new Unit(texture, map, i, j);
//                        }
//                    }
//                }
//        }

//    }

}
