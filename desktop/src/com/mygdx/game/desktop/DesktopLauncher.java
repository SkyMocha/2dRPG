package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "NickStudios 2dRPG";
//		config.width = 1080;
//	    config.height = 720;
//	    config.resizable = false;
	    config.fullscreen = true;
	    config.foregroundFPS = 60;
		
		new LwjglApplication(new Main(), config);
	}
}
