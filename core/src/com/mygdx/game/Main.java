package com.mygdx.game;

import java.io.File;
import java.util.Scanner;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;

import dungeonCave.CaveExterior;
import dungeonCave.CaveInterior;
import hamlet.AssistantInventor;
import hamlet.Bartender;
import hamlet.CollectFight;
import hamlet.Darkness;
import hamlet.Hamlet;
import hamlet.HamletCollectHouse;
import hamlet.HamletTavern;
import hamlet.HeadInventor;
import hamlet.IntroFight;
import hamlet.Laboratory;
import hamlet.LightheartedTable;
import hamlet.LonelyPatron;
import hamlet.LonelyTable;
import hamlet.OutgoingStudent;
import hamlet.QuietStudent;
import stages.Building;
import stages.Intro;
import stages.Intro2;
import stages.NPC;
import stages.TextStages;
import stages.Town;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    public static IntroFight introFight;
    public static Bartender bartender;
    public static LightheartedTable lightheartedTable;
    public static LonelyTable lonelyTable;
    public static HamletCollectHouse hamletCollectHouse;
    public static CollectFight collectFight;
    public static Laboratory lab;
    public static HeadInventor headInventor;
    public static AssistantInventor assistantInventor;
    public static OutgoingStudent outgoingStudent;
    public static QuietStudent quietStudent;
    public static Darkness darkness;
    public static CaveExterior caveExterior;
    public static CaveInterior caveInterior;
    
    // MUSIC FILES
    public static Music hallways;
    public static Music battle;
    public static Music towns;
    
    Texture backgroundTexture1;
    public static Image backgroundSprite1;
    Texture backgroundTexture2;
    public static Image backgroundSprite2;
    
    Scanner textReader;
    String readText = "";
		
	public static int SCREEN_WIDTH = 1920;

	public static int SCREEN_HEIGHT = 1080;
	
	public static String location;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
				
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
		
		defineImages();
				
		location = "creation";
		
		// INSTANTIATE LOCATIONS
		if (location.equals("creation")) {
			creation = new CharacterCreation();
		}
		hamlet = new Hamlet();
		hamletTavern = new HamletTavern();
		lonelyPatron = new LonelyPatron();
		bartender = new Bartender();
		lightheartedTable = new LightheartedTable();
		lonelyTable = new LonelyTable();
		hamletCollectHouse = new HamletCollectHouse();
		
		lab = new Laboratory();
		headInventor = new HeadInventor();
		assistantInventor = new AssistantInventor ();
		outgoingStudent = new OutgoingStudent();
		quietStudent = new QuietStudent();
		
		darkness = new Darkness();
		caveExterior = new CaveExterior();
		caveInterior = new CaveInterior();
		
		// INSTANTIATE AUDIO
		hallways = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 1 - Lonely Hallway v.2.mp3"));
		hallways.setLooping(true);
		hallways.setVolume(0.08f);
//		hallways.setVolume(0.0f);
		
		battle = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 2 - Lonely Battle.mp3"));
		battle.setLooping(true);
		battle.setVolume(0.08f);
//		battle.setVolume(0.0f);
		
		towns = Gdx.audio.newMusic(Gdx.files.internal("tracks/Track 3 - Lonely Town v.2.mp3"));
		towns.setLooping(true);
		towns.setVolume(0.05f);
//		towns.setVolume(0.0f);
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
        		Gdx.input.setInputProcessor(creation.stage);
        		creation.stage.draw();
        		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
        			intro = new Intro();
        			defineImages();
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
        		Gdx.input.setInputProcessor(hamlet.stage);
        		hamlet.update();
        		hamlet.stage.act();
        		hamlet.stage.draw();
        		break;
        	case "hamlet-tavern":
        		building (hamletTavern);
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
        	case "hamlet-collect-fight":
        		if (collectFight.complete())
        			Main.location = "hamlet";
        		else {
        			towns.stop();
            		battle.play();
            		Gdx.input.setInputProcessor(collectFight.stage);
            		collectFight.stage.draw();
        		}
        		break;
        	case "laboratory":
        		building(lab);
        		break;
        	case "head-inventor":
        		npc(headInventor);
        		break;
        	case "assistant-inventor":
        		npc(assistantInventor);
        		break;
        	case "outgoing-student":
        		npc(outgoingStudent);
        		break;
        	case "quiet-student":
        		npc(quietStudent);
        		break;
        	case "hamlet-square":
        		break;
        	case "closed-off-fields":
        		break;
        	case "darkness":
        		building (darkness);
        		break;
        	case "darkness-cave":
        		building (darkness);
        		break;
        	case "cave-exterior":
        		textStage (caveExterior);
        		break;
        	case "cave-interior":
        		textStage (caveInterior);
        		break;
        	case "northern-passage":
        		break;
        	default:
        		break;
        }
        
//        System.out.println (location);
        
//        System.out.println (location);
//        String s = "";
//        for (Action action : Player.actions)
//        	s += action.name + " ";
//        System.out.println (s);       

        batch.end();
        
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backgroundTexture1.dispose();
		backgroundTexture2.dispose();
		creation.stage.dispose();
		hamlet.stage.dispose();
	}
	
	public void resize(int width, int height) {
        viewport.update(width, height);
    }
	
	private void defineImages () {
		backgroundTexture1 = new Texture(Gdx.files.internal("images/Background.png"));
		backgroundSprite1 = new Image(backgroundTexture1);
		backgroundTexture2 = new Texture(Gdx.files.internal("images/Background 2.png"));
		backgroundSprite2 = new Image(backgroundTexture2);
		
		backgroundSprite1.setWidth(SCREEN_WIDTH);
		backgroundSprite1.setHeight(SCREEN_HEIGHT);
		
		backgroundSprite1.setX(SCREEN_WIDTH/2, Align.center);
		backgroundSprite1.setY(SCREEN_HEIGHT/2, Align.center);
		
		backgroundSprite2.setWidth(SCREEN_WIDTH);
		backgroundSprite2.setHeight(SCREEN_HEIGHT);
		
		backgroundSprite2.setX(SCREEN_WIDTH/2, Align.center);
		backgroundSprite2.setY(SCREEN_HEIGHT/2, Align.center);

	}
	
	public void textStage (TextStages textStage) {
		Gdx.input.setInputProcessor(textStage.stage);
		textStage.update();
		textStage.stage.act();
		textStage.stage.draw();
	}
	public void npc (NPC npc) {
		Gdx.input.setInputProcessor(npc.stage);
		npc.update();
		npc.stage.act();
		npc.stage.draw();
	}
	public void npc (NPC npc, String s) {
		Gdx.input.setInputProcessor(npc.stage);
		npc.update("");
		npc.stage.act();
		npc.stage.draw();
	}
	public void building (Building b) {
		Gdx.input.setInputProcessor(b.stage);
		b.update();
		b.stage.act();
		b.stage.draw();
	}
	public void town (Town t) {
		Gdx.input.setInputProcessor(t.stage);
		t.update();
		t.stage.act();
		t.stage.draw();
	}
	
	
}