package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Application;
import com.mygdx.game.managers.GameScreenManager;

public abstract class AbstractScreen implements Screen {

    protected  final Application app;

    Stage stage;

    public AbstractScreen(final Application app) {
        this.app = app;
        this.stage = new Stage();
    }

    public abstract void update(float dt);

    @Override
    public void render(float dt) {
        update(dt);

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}