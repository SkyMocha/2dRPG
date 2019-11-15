package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Action {
	public TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	String name;
	int power;
	int accuracy;
	int heal;
	
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
		
		// Defining variables
		name = tname;
		power = tpower;
		accuracy = taccuracy;
		heal = theal;
	}
}
