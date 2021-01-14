package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Application;
import com.mygdx.game.objects.Ball;
import com.mygdx.game.objects.Goal;
import com.mygdx.game.objects.Paddle;
import com.mygdx.game.utils.B2DBodyBuilder;
import com.mygdx.game.utils.B2DConstants;
import com.mygdx.game.utils.B2DContactListener;

import static com.mygdx.game.utils.B2DConstants.PPM;

public class GameScreen extends AbstractScreen {

    OrthographicCamera camera;

    // Box2D
    World world;
    Box2DDebugRenderer b2dr;

    // Game Bodies
    //Body ball, paddleLeft, goalLeft, paddleRight, goalRight;

    // Objects
    Ball pong;
    Paddle paddleLeft;
    Paddle paddleRight;
    Goal goalLeft;
    Goal goalRight;


    public GameScreen(final Application app) {
        super(app);

        this.camera = new OrthographicCamera(); // Cameras are lightweight (Not Views...)
        this.camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);

        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        world = new World(new Vector2(0f, 0f), false); // Gravity => 0
        initArena();
        world.setContactListener(new B2DContactListener(pong));

        app.batch.setProjectionMatrix(camera.combined);
        app.shapeBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void update(float dt) {
        world.step(1f / Application.APP_FPS, 6, 2);

        // Get Mouse Position & Move Paddle
        float mousePosToWorld = -(Gdx.input.getY() - camera.viewportHeight) / PPM;
        // float mouseLerp = paddleLeft.position.y + (mousePosToWorld - paddleLeft.position.y) * .2f;

        // Smooth Paddle Movement
        /*
            if (mouseLerp * PPM > camera.viewportHeight - 20f) {
               mouseLerp = (camera.viewportHeight - 20f) / PPM;
            } else if (mouseLerp * PPM < 20f) {
                mouseLerp = 20f / PPM;
            }
         */
        paddleLeft.move(mousePosToWorld);
        paddleRight.move(mousePosToWorld);

        // Ball Movement
        pong.move();

        // Handle Wall Collide
        if (pong.position.y + pong.radius > camera.viewportHeight / PPM || pong.position.y - pong.radius < 0)
            pong.velocity.y = -pong.velocity.y;

        stage.act(dt);
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        b2dr.render(world, camera.combined.cpy().scl(PPM));
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }

    private void initArena() {
        createWalls();

        // Setup Pong Ball
        pong = new Ball(B2DBodyBuilder.createBall(world, camera.viewportWidth / 2, camera.viewportHeight / 2, 6f, B2DConstants.BIT_BALL, (short) (B2DConstants.BIT_PADDLE | B2DConstants.BIT_GOAL), "pong"), 6f);

        // Setup Paddles
        paddleLeft = new Paddle(B2DBodyBuilder.createBox(world, 40, camera.viewportHeight / 2, 10, 50, false, false, B2DConstants.BIT_PADDLE, (short) (B2DConstants.BIT_BALL), "paddle"), 10, 50);
        paddleRight = new Paddle(B2DBodyBuilder.createBox(world, camera.viewportWidth - 40, camera.viewportHeight / 2, 10, 50, false, false, B2DConstants.BIT_PADDLE, B2DConstants.BIT_BALL, "paddle"), 10, 50);

        // Setup Goals
        goalLeft = new Goal(B2DBodyBuilder.createBox(world, 5, camera.viewportHeight / 2, 10, camera.viewportHeight, true, true, B2DConstants.BIT_GOAL, (short) (B2DConstants.BIT_BALL), "goal"), 10, camera.viewportHeight);
        goalRight = new Goal(B2DBodyBuilder.createBox(world, camera.viewportWidth - 5, camera.viewportHeight / 2, 10, camera.viewportHeight, true, true, B2DConstants.BIT_GOAL, B2DConstants.BIT_BALL, "goal"), 10, camera.viewportHeight);

        // Setup Paddle Joints
        //B2DJointBuilder.createPrismaticJoint(world, goalLeft, paddleLeft, camera.viewportHeight / 2, -camera.viewportHeight / 2, new Vector2(36 / PPM, 0), new Vector2(0,0)); // Left Paddle
        //B2DJointBuilder.createPrismaticJoint(world, goalRight, paddleRight, camera.viewportHeight / 2, -camera.viewportHeight / 2, new Vector2(- 36 / PPM, 0), new Vector2(0,0)); // Left Paddle
    }

    private void createWalls() {
        Vector2[] verts = new Vector2[5];
        verts[0] = new Vector2(1f / PPM, 1f / PPM);
        verts[1] = new Vector2(camera.viewportWidth / PPM, 1f / PPM);
        verts[2] = new Vector2(camera.viewportWidth / PPM, (camera.viewportHeight - 0f) / PPM);
        verts[3] = new Vector2(1f / PPM, (camera.viewportHeight - 0f) / PPM);
        verts[4] = new Vector2(1f / PPM, 1f / PPM);
        B2DBodyBuilder.createChainShape(world, verts);
    }
}
