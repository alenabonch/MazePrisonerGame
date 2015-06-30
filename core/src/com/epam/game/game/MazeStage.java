package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.epam.game.game.actors.Cell;
import com.epam.game.game.actors.Hero;
import com.epam.game.game.actors.Item;
import com.epam.game.game.logic.*;
import com.epam.game.game.utils.Constants;
import com.epam.game.game.view.View;

import java.util.List;

import static com.epam.game.game.utils.Constants.*;

public class MazeStage extends Stage {

    private static final String TAG = MazeStage.class.getName();

    public MazeStage() {
        OrthographicCamera camera = new OrthographicCamera();
        setViewport(new ScreenViewport(camera));
    }

    public void init() {
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
                hero.setBounds(hero.getHeroX() * CELL_SIZE, hero.getHeroY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            @Override
            protected void drawMap(int[][] data) {
                super.drawMap(data);
            }

            @Override
            protected void drawMobs(List<Mob> mobs, int[][] data) {
                super.drawMobs(mobs, data);
            }

            @Override
            protected void drawItems(List<Item> items, int[][] data) {

                for (Item item : items) {
                    if((data[item.getItemY()][item.getItemX()] % Constants.NUMBER_OF_TEXTURE ) == Constants.VISIBLE_TEXTURE_INDEX  ) {
                        Gdx.app.log(TAG, "ITEM!!!!");
                        MazeStage.this.addActor(item);
                        Gdx.app.log(TAG, "Added actor item");

                        item.setName("item");
                        item.setBounds(item.getItemX() * CELL_SIZE, item.getItemY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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
                        Hero hero1 = MazeStage.this.getRoot().findActor("hero");
                        hero1.setIsMovedLeft(true);
                        if (hero1.isMovedRight()) {
                            hero1.setIsMovedRight(false);
                            hero1.setIsFlipped(!hero1.isFlipped());
                        }
                        controller.moveLeft();
                        break;

                    case Input.Keys.RIGHT:
                        Hero hero2 = MazeStage.this.getRoot().findActor("hero");
                        hero2.setIsMovedRight(true);
                        if (hero2.isMovedLeft()) {
                            hero2.setIsMovedLeft(false);
                            hero2.setIsFlipped(!hero2.isFlipped());
                        }
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

