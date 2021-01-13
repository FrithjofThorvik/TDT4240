package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Exercise1;

public class MenuState extends State {

    private Texture playBtn;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playBtn = new Texture("play.png");
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playBtn, (Exercise1.WIDTH / 2) - (playBtn.getWidth() / 2), (Exercise1.HEIGHT / 2) - (playBtn.getHeight() / 2));
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }
}
