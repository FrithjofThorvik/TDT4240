package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Application extends Game {

	// Application Globals
	public static String APP_TITLE = "Exercise 2";
	public static int APP_WIDTH = 720; 	// Scaled
	public static int APP_HEIGHT = 420;	// Scaled
	public static int APP_FPS = 60;

	// Game Globals
	public static int V_WIDTH = 720;	// Core
	public static  int V_HEIGHT = 420;	// Core

	// Managers
	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;
}
