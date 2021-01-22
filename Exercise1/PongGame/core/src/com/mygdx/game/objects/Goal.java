package com.mygdx.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Goal {

    public Body body;
    public Vector2 position;
    public float borderX;
    public float height;

    public Goal(Body body, float borderX, float height) {
        this.body = body;
        position = new Vector2(body.getPosition());
        this.borderX = borderX;
        this.height = height;
    }

    public void handleCollide(Ball ball, boolean leftMovement) {
        if (leftMovement == true) {
            //if (ball.position.x + ball.radius > borderX) ball.changeDirection();
        }
        else if (leftMovement == false){
            //if (ball.position.x < borderX) ball.changeDirection();
        }
    }
}
