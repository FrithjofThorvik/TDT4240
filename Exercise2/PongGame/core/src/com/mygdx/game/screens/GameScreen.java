package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Application;
import com.mygdx.game.utils.B2DBodyBuilder;

import static com.mygdx.game.utils.B2DConstants.PPM;

public class GameScreen extends AbstractScreen {

    // Camera
    OrthographicCamera camera;

    // Box2D
    World world;
    Box2DDebugRenderer b2dr;
    Body box;

    public GameScreen(final Application app) {
        super(app); // Passing Application to AbstractScreen

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);

        // Adjust app batches to the camera view (combined = viewport matrix)
        app.batch.setProjectionMatrix(camera.combined);
        app.shapeBatch.setProjectionMatrix(camera.combined);

        this.world = new World(new Vector2(0f, -.1f), false);
        this.b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() { // Runs when camera is activated
        this.box = new B2DBodyBuilder().createBox(this.world, camera.viewportWidth / 2, camera.viewportHeight / 2, 10f, 50f,  "box");
    }

    @Override
    public void update(float delta) {
        this.world.step(1 / Application.APP_FPS, 6, 2);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        this.b2dr.render(this.world, this.camera.combined.cpy().scl(PPM));
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
}
