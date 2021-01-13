package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Exercise1;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Exercise1.WIDTH;
		config.height = Exercise1.HEIGHT;
		config.title = Exercise1.TITLE;
		new LwjglApplication(new Exercise1(), config);
	}
}
