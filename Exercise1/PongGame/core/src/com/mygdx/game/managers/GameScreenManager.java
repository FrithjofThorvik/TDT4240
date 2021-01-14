package com.mygdx.game.managers;

import com.mygdx.game.Application;
import com.mygdx.game.screens.AbstractScreen;
import com.mygdx.game.screens.GameScreen;

import java.util.HashMap;

public class GameScreenManager {

    public final Application app;

    private HashMap<STATE, AbstractScreen> gameScreens;

    public enum STATE {
        MAIN_MENU,
        PLAY,
        SETTINGS
    }

    public GameScreenManager(final Application app) {
        this.app = app;

        initGameScreens();
        setScreen(STATE.PLAY);
    }

     private void initGameScreens() {
        this.gameScreens = new HashMap<STATE, AbstractScreen>();
        this.gameScreens.put(STATE.PLAY, new GameScreen(app));
    }

    public void setScreen(STATE nextScreen) {
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose() {
        for (AbstractScreen screen : gameScreens.values()) {
            if (screen != null) {
                screen.dispose();
            }
        }
    }
}
