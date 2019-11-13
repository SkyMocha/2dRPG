package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;

public class CharacterCreation {
	Stage stage;
	BitmapFont font;
	CharSequence str = "Hello World!";
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label text;
    Label statText;
    
    int avaliablePoints;
	
	CharacterCreation () {
		Player player = new Player();
		avaliablePoints = 5;
		
		stage = new Stage (Main.viewport);
		
		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle();
		
		font.getData().setScale(3.5f, 3.5f);
		
		textButtonStyle.font = font;
		labelStyle.font = font;
		
		
		StatButton[] stats = new StatButton[] { 
				new StatButton ("str", 1), 
				new StatButton ("str", -1), 
				new StatButton ("dex", 1), 
				new StatButton ("dex", -1),
				new StatButton ("chr", 1),
				new StatButton ("chr", -1),
				new StatButton ("int", 1),
				new StatButton ("int", -1),
				new StatButton ("wis", 1),
				new StatButton ("wis", -1)
			};
		
		int i = 0;
		for (StatButton button : stats) {
			button.button.setY(Main.SCREEN_HEIGHT - button.button.getHeight() - i);
			button.button.setX(Main.SCREEN_WIDTH/2, Align.center);
			i += 70;
		}
		
		font.getData().setScale(2.5f, 2.5f);
		labelStyle.font = font;
		
		statText = new Label("STATS: \n" + Player.strength + "\n" + Player.dexterity + "\n" + Player.charisma + "\n" + Player.intelligence + "\n" + Player.wisdom + "\nPoints: " + avaliablePoints, labelStyle);
		statText.setX(Main.SCREEN_WIDTH*2/3, Align.center);
		statText.setY(Main.SCREEN_HEIGHT - statText.getHeight() - 20);
	
		stage.addActor(statText);
		
	}
	
	class StatButton {
		
		TextButton button;
		StatButton (final String stat, final int inc) {
			
			button = new TextButton(stat + " " + inc, textButtonStyle);
			
			button.addListener(new ChangeListener() {
				@Override
				public void changed (ChangeEvent event, Actor actor) {
					if (avaliablePoints <= 0 && inc > 0)
						return;
					
					if ((Player.strength <= 1 || Player.dexterity <= 1 || Player.charisma <= 1 || Player.intelligence <= 1 || Player.wisdom <= 1) && inc < 0)
						return;
					
					if (stat.equals( "str"))
						Player.strength += inc;
					else if (stat.equals("dex"))
						Player.dexterity += inc;
					else if (stat.equals("chr"))
						Player.charisma += inc;
					else if (stat.equals("int"))
						Player.intelligence += inc;
					else if (stat.equals("wis"))
						Player.wisdom += inc;
					
					if (inc > 0)
						avaliablePoints--;
					if (inc < 0)
						avaliablePoints++;
					
					statText.remove();
					statText = new Label("STATS: \n" + Player.strength + "\n" + Player.dexterity + "\n" + Player.charisma + "\n" + Player.intelligence + "\n" + Player.wisdom + "\nPoints: " + avaliablePoints, labelStyle);
					statText.setX(Main.SCREEN_WIDTH*2/3, Align.center);
					statText.setY(Main.SCREEN_HEIGHT - statText.getHeight() - 20);
				
					stage.addActor(statText);
					
			    }
			});
			
			stage.addActor(button);
			
		}
	}

}
