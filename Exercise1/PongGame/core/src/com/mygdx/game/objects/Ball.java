package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Random;

import static com.mygdx.game.utils.B2DConstants.PPM;

public class Ball {

    public Body body;
    public Vector2 position;
    public Vector2 velocity;
    public float radius;

    public Ball(Body body, float radius) {
        this.body = body;
        this.radius = radius / PPM;
        position = new Vector2(body.getPosition());
        velocity = new Vector2(5f / PPM, 0f / PPM);
    }

    public void move() {
        position.add(velocity);
        body.setTransform(position.x, position.y, 0);
    }

    public void increaseSpeed() {
        if (velocity.x > 0) velocity.x += (.5f / PPM);
        else if (velocity.x < 0) velocity.x -= (.5f / PPM);
    }

    public void changeDirection() {
        float randFloat = randFloat(-1f, 1f);
        velocity.y = 4f / PPM;
        velocity.x = -(velocity.x);
        velocity.y = randFloat*velocity.y;
    }

    public void centerPosition() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public static float randFloat(float min, float max) {

        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;

    }


}
