package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Exercise1;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State {

    private Helicopter helicopter;
    private Helicopter helicopterEnemy;
    private BitmapFont font;
    private int delay;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = new Helicopter(50, 300);
        helicopterEnemy = new Helicopter(20, 500);
        font = new BitmapFont();
        delay = 0;
    }

    public void handleCollision() {
        if (helicopterEnemy.collides(helicopter.getBounds()) && delay == 0) {
            helicopterEnemy.inverseDirection();
            helicopter.inverseDirection();
            delay = 10;
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Exercise1.HEIGHT - Gdx.input.getY();
            helicopter.move(x, y);
        }
    }

    @Override
    public void update(float dt) {
        if (delay > 0) {
            if (delay != 0) {
                delay--;
            }
        }
        handleCollision();
        handleInput();
        helicopter.update(dt);
        helicopterEnemy.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(helicopter.getSprite(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.draw(helicopterEnemy.getSprite(), helicopterEnemy.getPosition().x, helicopterEnemy.getPosition().y);
        font.draw(sb, helicopter.getPositionString(), 10,  Exercise1.HEIGHT - 10);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
