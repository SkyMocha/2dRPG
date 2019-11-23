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
				new StatButton ("repair", 1), 
				new StatButton ("repair", -1), 
				new StatButton ("force", 1), 
				new StatButton ("force", -1),
				new StatButton ("finesse", 1),
				new StatButton ("finesse", -1),
				new StatButton ("courage", 1),
				new StatButton ("courage", -1),
				new StatButton ("charisma", 1),
				new StatButton ("charisma", -1),
				new StatButton ("intelligence", 1),
				new StatButton ("intelligence", -1),
				new StatButton ("ingenuity", 1),
				new StatButton ("ingenuity", -1),
			};
		
		int i = 0;
		for (StatButton button : stats) {
			button.button.setY(Main.SCREEN_HEIGHT - button.button.getHeight() - i);
			button.button.setX(Main.SCREEN_WIDTH/2, Align.center);
			i += 70;
		}
		
		font.getData().setScale(2.5f, 2.5f);
		labelStyle.font = font;
		
		statText = new Label("STATS: \n" + Player.repair + "\n" + Player.readiness + "\n" + Player.force + "\n" + Player.finesse + "\n" + Player.courage + "\n" + Player.courage + "\n" + Player.intelligence + "\n" + Player.ingenuity + "\nPoints: " + avaliablePoints, labelStyle);
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
					// Checks to see if there are still available points
					if (avaliablePoints <= 0 && inc > 0)
						return;
												
					// Adds to the value for the player
					// Doesn't go below 1
					if (stat.equals("repair") && (Player.repair > 1 || inc > 0))
						Player.repair += inc;
					else if (stat.equals("readiness") && (Player.readiness > 1 || inc > 0))
						Player.readiness += inc;
					else if (stat.equals("force") && (Player.force > 1 || inc > 0))
						Player.force += inc;
					else if (stat.equals("finesse") && (Player.finesse > 1 || inc > 0))
						Player.finesse += inc;
					else if (stat.equals("courage") && (Player.courage > 1 || inc > 0))
						Player.courage += inc;
					else if (stat.equals("charisma") && (Player.charisma > 1 || inc > 0))
						Player.charisma += inc;
					else if (stat.equals("intelligence") && (Player.intelligence > 1 || inc > 0))
						Player.intelligence += inc;
					else if (stat.equals("ingenuity") && (Player.ingenuity > 1 || inc > 0))
						Player.ingenuity += inc;
					else
						return;
					
					// Adds or decreases the available points
					if (inc > 0)
						avaliablePoints--;
					if (inc < 0)
						avaliablePoints++;
					
					// Updates text
					statText.setText("STATS: \n" + Player.repair + "\n" + Player.readiness + "\n" + Player.force + "\n" + Player.finesse + "\n" + Player.courage + "\n" + Player.courage + "\n" + Player.intelligence + "\n" + Player.ingenuity + "\nPoints: " + avaliablePoints);
				
					stage.addActor(statText);
					
			    }
			});
			
			stage.addActor(button);
			
		}
	}

}
