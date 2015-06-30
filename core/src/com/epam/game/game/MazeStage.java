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

    private static final String TAG = MazeStage.class.getName();

    public MazeStage() {
        OrthographicCamera camera = new OrthographicCamera();
        setViewport(new ScreenViewport(camera));
    }

    public void init() {
        Gdx.app.log(TAG, "before create Model, contoller, view");
        final Model model = new Model();
        final Controller controller = new Controller();
        model.addListener(controller);
//todo
        View view = new View() {
            private Cell[][] cells = new Cell[Constants.SIZE_MAP_X][Constants.SIZE_MAP_Y];

            @Override
            protected void drawTexture(int textureIndex, int x, int y) {

                if (cells[x][y] == null) {
                    Cell cell = new Cell(textureIndex);
                    cells[x][y] = cell;
                    MazeStage.this.addActor(cell);
         //           cell.setZIndex(1);
                    cell.setBounds(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    Gdx.app.log(TAG, "created texture " + textureIndex);
                }
                else{
                    cells[x][y].setTextureIndex(textureIndex);
                    Gdx.app.log(TAG, "repaint texture " + textureIndex);
                }
            }

            @Override
            protected void drawHero(Hero hero) {
                MazeStage.this.addActor(hero);
                Gdx.app.log(TAG, "Added actor hero");
                hero.setName("hero");
                hero.setBounds(hero.get_x() * CELL_SIZE, hero.get_y() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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
            protected void drawThings(List<Item> get_things, int[][] data) {

                for (Item item : get_things) {
                    if((data[item.get_y()][item.get_x()] % Constants.NUMBER_OF_TEXTURE ) == Constants.VISIBLE_TEXTURE_INDEX  ) {
                        System.out.println("ITEM !!!");
                        MazeStage.this.addActor(item);
                        Gdx.app.log(TAG, "Added actor item");
         //               item.setZIndex(0);

                        item.setName("item");
                        item.setBounds(item.get_x() * CELL_SIZE, item.get_y() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }

                }
            }
        };
        controller.setView(view);
        controller.setModel(model);
        Gdx.app.log(TAG, "Created Model, contoller, view");

        controller.startGame();
        controller.onChange(model.getState());

        Gdx.input.setInputProcessor(this);

        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:

                        Gdx.app.log(TAG, "MOVE LEFT");
                        Hero hero1 = MazeStage.this.getRoot().findActor("hero");
                        hero1.setIsMovedLeft(true);
                        if (hero1.isMovedRight()) {
                            hero1.setIsMovedRight(false);
                            hero1.setIsFlipped(!hero1.isFlipped());
                        }
                        Gdx.app.log(TAG, "isFlipped " + hero1.isFlipped());
                        Gdx.app.log(TAG, "isMovedRight " + hero1.isMovedRight());
                        Gdx.app.log(TAG, "isMovedLeft " + hero1.isMovedLeft());

                        controller.moveLeft();
                        break;
                    case Input.Keys.RIGHT:

                        Gdx.app.log(TAG, "MOVE RIGHT");
                        Hero hero2 = MazeStage.this.getRoot().findActor("hero");
                        hero2.setIsMovedRight(true);
                        if (hero2.isMovedLeft()) {
                            hero2.setIsMovedLeft(false);
                            hero2.setIsFlipped(!hero2.isFlipped());
                        }

                        Gdx.app.log(TAG, "isFlipped " + hero2.isFlipped());
                        Gdx.app.log(TAG, "isMovedRight " + hero2.isMovedRight());
                        Gdx.app.log(TAG, "isMovedLeft " + hero2.isMovedLeft());

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

        });
    }
}

