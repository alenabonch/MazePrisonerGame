package com.epam.game;

import com.badlogic.gdx.Game;
import com.epam.game.game.MazeScreen;

public class MazePrisonerGame extends Game {
    @Override
    public void create() {
        setScreen(new MazeScreen());
    }
}
