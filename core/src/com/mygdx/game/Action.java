package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import stages.ChoiceButton;

// 
public class Action {
	public TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    
    public TextButton[] targets;
    
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	public String name;
	public int power;
	public int accuracy;
	public int heal;
	
	public Action (String tname, int tpower, int taccuracy, int theal) {
		// Button
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(40);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		textButtonStyle = new TextButtonStyle();
		font = generator.generateFont(parameter);
		
		textButtonStyle.font = font;
		button = new TextButton(tname, textButtonStyle);
		
		// Sets the text for the 6 targets that are available in a fight
		targets = new TextButton[6];
		for (int i = 0; i < targets.length; i++)
			targets[i] = new TextButton (Integer.toString(i+1), textButtonStyle);
		int i = 0;
		for (TextButton button : targets) {
			button.setX(Main.SCREEN_WIDTH/2 + xCircle(100, i));
			button.setY(Main.SCREEN_HEIGHT/2 + yCircle(100, i));
			i += 1;
		}
		
		// Defining variables
		name = tname;
		power = tpower;
		accuracy = taccuracy;
		heal = theal;
	}
	
	private int xCircle (int r, int theta) {
		return (int) (r * Math.sin(theta));
	}
	private int yCircle (int r, int theta) {
		return (int) (r * Math.cos(theta));
	}
	
}
