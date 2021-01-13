package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Exercise1;

public class Helicopter {
    // private static final int SPEED = 1;
    private Vector3 position;
    private Vector3 velocity;
    private Texture helicopter;
    private Sprite sprite;
    private Rectangle bounds;

    private Array<Sprite> frames;
    private Animation helicopterAnimation;


    public Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(-2, -5, 0);
        helicopter = new Texture("heli1.png");
        sprite = new Sprite(helicopter);

        // Add Animation
        frames = new Array<Sprite>();
        frames.add(new Sprite(new Texture("heli1.png")));
        frames.add(new Sprite(new Texture("heli2.png")));
        frames.add(new Sprite(new Texture("heli3.png")));
        frames.add(new Sprite(new Texture("heli4.png")));
        helicopterAnimation = new Animation(frames, 0.01f);

        bounds = new Rectangle(x, y, helicopterAnimation.getFrame().getWidth(), helicopterAnimation.getFrame().getHeight());
    }

    public void update(float dt) {
        // Animation Update
        helicopterAnimation.update(dt);

        // Boundaries
        bounds.setPosition(position.x, position.y);

        // Bounce of Walls & Flip
        if((position.y < 0) || (position.y + helicopterAnimation.getFrame().getHeight() > Exercise1.HEIGHT))
            velocity.y = -velocity.y;
        else if ((position.x < 0) || (position.x + helicopterAnimation.getFrame().getWidth() > Exercise1.WIDTH)) {
            velocity.x = -velocity.x;
            helicopterAnimation.flip();
        }

        // Add Velocity
        position.add(velocity.x, velocity.y, 0);
    }

    public String getPositionString() {
            return position.toString();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return helicopter;
    }

    public Sprite getSprite() {
        return helicopterAnimation.getFrame();
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void inverseDirection() {
        velocity.x = -velocity.x;
        velocity.y = -velocity.y;
    }

    public void move(int x, int y) {
        // View X Plane
        if ((x < position.x) && (velocity.x > 0)) {
            velocity.x = -velocity.x;
            helicopterAnimation.flip();
        } else if ((x > (position.x + helicopter.getWidth())) && (velocity.x < 0)){
            velocity.x = -velocity.x;
            helicopterAnimation.flip();
        }

        // View Y-Plane
        if ((y < position.y) && (velocity.y > 0)) {
            velocity.y = -velocity.y;
        } else if ((y > (position.y + helicopter.getHeight())) && (velocity.y < 0)){
            velocity.y = -velocity.y;
        }
    }

}
