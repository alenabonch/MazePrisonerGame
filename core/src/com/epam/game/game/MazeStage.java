package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.epam.game.game.logic.*;

import java.util.List;

import static com.epam.game.game.logic.Constants.*;

public class MazeStage extends Stage {

//    Sprite sprite;

    public MazeStage() {
        OrthographicCamera camera = new OrthographicCamera();
//        camera.setToOrtho(true);
        setViewport(new ScreenViewport(camera));
    }

    public void init() {
        final Model model = new Model();
        final Controller controller = new Controller();
        model.addListener(controller);

        /*final int level = model.getLogic().getState().get_levelNum();*/

        View view = new View() {
            private Cell[][] cells = new Cell[model.getLogic().getState().getNowLevel().getMapSizeX()][model.getLogic().getState().getNowLevel().getMapSizeY()];

            public void updateLevel(Level level) {

            }



            @Override
            protected void drawTexture(int textureIndex, int x, int y) {

                if (cells[x][y] == null) {
                    Cell cell = new Cell(textureIndex);
                    cells[x][y] = cell;
                    MazeStage.this.addActor(cell);
                    cell.setBounds(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
                else{
                    cells[x][y].setTextureIndex(textureIndex);
                }
            }

            @Override
            protected void drawHero(Hero hero) {
                MazeStage.this.addActor(hero);
                hero.setBounds(hero.get_x() * CELL_SIZE, hero.get_y() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//                hero.setPosition(hero.get_x(), hero.get_y());
            }

            @Override
            protected void drawMap(int[][] data) {
                super.drawMap(data);
            }

            @Override
            protected void drawMobs(List<Mob> get_mobs, int[][] data) {
                super.drawMobs(get_mobs, data);
            }

            @Override
            protected void drawThings(List<Thing> get_things, int[][] data) {
                super.drawThings(get_things, data);
            }
        };
        controller.setView(view);
        controller.setModel(model);

        Gdx.input.setInputProcessor(this);

        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        controller.moveLeft();
                        break;
                    case Input.Keys.RIGHT:
                        controller.moveRight();
                        break;
                    case Input.Keys.DOWN:
                        controller.moveDown();
                        break;
                    case Input.Keys.UP:
                        controller.moveUp();
                        break;
                }
                return true;
            }

            @Override
            public boolean scrolled(InputEvent event, float x, float y, int amount) {
                return super.scrolled(event, x, y, amount);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                return super.keyUp(event, keycode);
            }
        });
    }
}

