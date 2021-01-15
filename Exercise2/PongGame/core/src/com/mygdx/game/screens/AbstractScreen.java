package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Application;
import com.mygdx.game.managers.GameScreenManager;

public abstract class AbstractScreen implements Screen {

    protected final Application app; // Only one instance of app (final)

    public AbstractScreen(final Application app) {
        this.app = app;
    }

    public abstract void update(float delta); // Abstract function

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Calls update for our subclasses
        update(delta);

        // Renders background color for our subclasses
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
