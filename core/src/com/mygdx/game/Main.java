package com.mygdx.game;

import java.io.File;
import java.util.Scanner;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hamlet.Bartender;
import hamlet.Hamlet;
import hamlet.HamletTavern;
import hamlet.IntroFight;
import hamlet.LonelyPatron;
import stages.Intro;
import stages.TextStages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	
	OrthographicCamera camera;
	public static FitViewport viewport;
	
	// STAGES
    CharacterCreation creation;
    Intro intro;
    Hamlet hamlet;
    HamletTavern hamletTavern;
    public static LonelyPatron lonelyPatron;
    IntroFight introFight;
    public static Bartender bartender;
    
    // MUSIC FILES
    public static Music hallways;
    public static Music battle;
    
    Scanner textReader;
    String readText = "";
	
	int tileSize = 32;
	
	public static int SCREEN_WIDTH = 1920;

	public static int SCREEN_HEIGHT = 1080;
	
	public static String location;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
				
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
						
		location = "creation";
		
		// INSTANTIATE LOCATIONS
		if (location.equals("creation")) {
			creation = new CharacterCreation();
		}
		hamlet = new Hamlet();
		hamletTavern = new HamletTavern();
		lonelyPatron = new LonelyPatron();
		introFight = new IntroFight();
		bartender = new Bartender();
		
		// INSTANTIATE AUDIO
		hallways = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 1 - Hallways v.2.mp3"));
		hallways.setLooping(true);
		
		battle = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 2 - J_Syreus_Bach_-_Lesser_Faith.mp3"));
		battle.setLooping(true);
		
		Gdx.input.setInputProcessor(creation.stage);
	}

	@Override
	public void render () {
				
		Gdx.gl.glClearColor( 0, 0, 0, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
				
		camera.update();
	    batch.setProjectionMatrix(camera.combined);
		
		camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0f);
		
        batch.begin();
        
        switch (location) {
        	case "creation":
        		creation.stage.draw();
        		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
        			intro = new Intro();
        			location = "intro";
        			hallways.play();
        		}
        		break;
        	case "intro":
        		Gdx.input.setInputProcessor(intro.stage);
        		intro.update();
        		intro.stage.draw();
        		intro.stage.act();
        		break;
        	case "hamlet":
        		Gdx.input.setInputProcessor(hamlet.stage);
        		hamlet.stage.draw();
        		break;
        	case "hamlet-tavern":
        		Gdx.input.setInputProcessor(hamletTavern.stage);
        		hamletTavern.update();
        		hamletTavern.stage.draw();
        		break;
        	case "lonely-patron":
        		Gdx.input.setInputProcessor(lonelyPatron.stage);
        		lonelyPatron.update("");
        		lonelyPatron.stage.draw();
        		break;
        	case "tutorial-fight":
        		if (introFight.complete())
        			Main.location = "hamlet-tavern";
        		else {
        			hallways.stop();
            		battle.play();
            		Gdx.input.setInputProcessor(introFight.stage);
            		introFight.stage.draw();
        		}
        		break;
        	case "hamlet-bartender":
        		Gdx.input.setInputProcessor(bartender.stage);
        		bartender.update();
        		bartender.stage.draw();
        		break;
        	case "laboratory":
        		break;
        	case "hamlet-square":
        		break;
        	case "closed-off-fields":
        		break;
        	case "darkness":
        		break;
        	case "northern-passage":
        		break;
        	default:
        		break;
        }
        
        System.out.println (location);
        		
        batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		creation.stage.dispose();
		hamlet.stage.dispose();
		intro.stage.dispose();
	}
	
	public void resize(int width, int height) {
        viewport.update(width, height);
    }
	
	
}
