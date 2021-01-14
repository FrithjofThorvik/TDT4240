package com.mygdx.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mygdx.game.utils.B2DConstants.PPM;

public class Paddle {

    public Body body;
    public Vector2 position;
    public float width;
    public float height;

    public Paddle(Body body, float width, float height) {
        this.body = body;
        this.width = width / PPM;
        this.height = height / PPM;
        position = new Vector2(body.getPosition());
    }

    public void move(float mouseLerp) {
        body.setTransform(position.x, mouseLerp, body.getAngle());
    }

    public void handleCollide(Ball ball, boolean leftMovement) {
        if (leftMovement == true) {
            //if ((ball.position.x < position.x + (width / 2)) && !(ball.position.x < position.x - (width / 2))) ball.changeDirection();

            if (true) ball.changeDirection();
        } else if (leftMovement == false) {
            //if ((ball.position.x + ball.radius > position.x - (width / 2)) && !(ball.position.x > position.x + (width / 2))) ball.changeDirection();
            //if (ball.position.x + ball.radius > position.x - (width / 2)) ball.changeDirection();
        }
    }
}
