package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.objects.Ball;

public class B2DContactListener implements ContactListener {

    private Ball ball;

    public B2DContactListener(Ball ball) {
        this.ball = ball;
    }

    // Called when two Fixtures collide
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() == "paddle") {
            ball.changeDirection();
            //ball.increaseSpeed();
        } else if (fixtureA.getUserData() == "goal"){
            ball.centerPosition();
        }
    }

    // Called when two Fixtures no longer collide
    @Override
    public void endContact(Contact contact) {
        System.out.println("End Contact");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
