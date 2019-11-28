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
import hamlet.HamletCollectHouse;
import hamlet.HamletTavern;
import hamlet.IntroFight;
import hamlet.LightheartedTable;
import hamlet.LonelyPatron;
import hamlet.LonelyTable;
import stages.Intro;
import stages.Intro2;
import stages.NPC;
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
    Intro2 intro2;
    Hamlet hamlet;
    HamletTavern hamletTavern;
    public static LonelyPatron lonelyPatron;
    IntroFight introFight;
    public static Bartender bartender;
    public static LightheartedTable lightheartedTable;
    public static LonelyTable lonelyTable;
    public static HamletCollectHouse hamletCollectHouse;
    
    // MUSIC FILES
    public static Music hallways;
    public static Music battle;
    public static Music towns;
    
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
		lightheartedTable = new LightheartedTable();
		lonelyTable = new LonelyTable();
		hamletCollectHouse = new HamletCollectHouse();
		
		// INSTANTIATE AUDIO
		hallways = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 1 - Lonely Hallway v.2.mp3"));
		hallways.setLooping(true);
		hallways.setVolume(0f);
		
		battle = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 2 - Lonely Battle.mp3"));
		battle.setLooping(true);
		battle.setVolume(0f);
		
		towns = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 3 - Lonely Town.mp3"));
		towns.setLooping(true);
		towns.setVolume(0f);
		
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
        			intro2 = new Intro2();
        			location = "intro";
        			hallways.play();
        		}
        		break;
        	case "intro":
        		textStage(intro);
        		break;
        	case "intro2":
        		textStage(intro2);
        		break;
        	case "hamlet":
        		hallways.stop();
        		towns.play();
        		hamlet.update();
        		Gdx.input.setInputProcessor(hamlet.stage);
        		hamlet.stage.draw();
        		break;
        	case "hamlet-tavern":
        		textStage (hamletTavern);
        		break;
        	case "lonely-patron":
        		npc (lonelyPatron, "");
        		break;
        	case "tutorial-fight":
        		if (introFight.complete())
        			Main.location = "hamlet-tavern";
        		else {
        			towns.stop();
            		battle.play();
            		Gdx.input.setInputProcessor(introFight.stage);
            		introFight.stage.draw();
        		}
        		break;
        	case "hamlet-bartender":
        		npc (bartender);
        		break;
        	case "lonely-table":
        		npc (lonelyTable, "");
        		break;
        	case "lighthearted-table":
        		npc (lightheartedTable);
        		break;
        	case "hamlet-collection-house":
        		npc (hamletCollectHouse, "");
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
	
	public void textStage (TextStages textStage) {
		Gdx.input.setInputProcessor(textStage.stage);
		textStage.update();
		textStage.stage.draw();
		textStage.stage.act();
	}
	public void npc (NPC npc) {
		Gdx.input.setInputProcessor(npc.stage);
		npc.update();
		npc.stage.draw();
	}
	public void npc (NPC npc, String s) {
		Gdx.input.setInputProcessor(npc.stage);
		npc.update("");
		npc.stage.draw();
	}
	
	
}
