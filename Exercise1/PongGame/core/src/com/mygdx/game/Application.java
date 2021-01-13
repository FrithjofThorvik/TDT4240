package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.GameScreenManager;

public class Application extends Game {

	// Application Globals
	public static String APP_TITLE = "Pong Game";
	public static int APP_DESKTOP_WIDTH = 720;	// Scaled Width
	public static int APP_DESKTOP_HEIGHT = 420;	// Scaled Height
	public static int APP_FPS = 60;

	// Game Globals
	public static int V_WIDTH = 720; 	// Core Width
	public static int V_HEIGHT = 420;	// Core Height

	// Managers
	public GameScreenManager gsm;
	public AssetManager assets;

	// Batches
	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;
	
	@Override
	public void create () {
		gsm = new GameScreenManager(this);
		assets = new AssetManager();
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeBatch.dispose();
		assets.dispose();
	}
}
