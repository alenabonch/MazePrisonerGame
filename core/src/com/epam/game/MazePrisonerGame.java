package com.epam.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epam.game.game.MazeScreen;

public class MazePrisonerGame extends Game {
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new MazeScreen());
    }
}
