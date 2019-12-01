package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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
    
    public StatButton[] stats;
	
	CharacterCreation () {
		Player player = new Player();
		avaliablePoints = 5;
		
		stage = new Stage (Main.viewport);
		
		stage.addActor(Main.backgroundSprite1);
		
		textButtonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(50);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		font = generator.generateFont(parameter);
		
		textButtonStyle.font = font;
		labelStyle.font = font;
		
		stats = new StatButton[] { 
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
				new StatButton ("image", 1),
				new StatButton ("image", -1),
			};
				
		placement (stats);
		
	}
	
	public void placement (StatButton[] stats) {
		int i = 225;
		for (int q = 0; q < stats.length; q+=2) {
			Label statLabel = new Label (stats[q].stat, labelStyle);
			statLabel.setX(Main.SCREEN_WIDTH/2, Align.center);
			statLabel.setY(Main.SCREEN_HEIGHT - stats[q].button.getHeight() - i);
			stage.addActor(statLabel);
			
			statText = new Label("", labelStyle);
			
			String stat = stats[q].stat;
			
			// Sets the numerical value for the stat depending on the "stat" button
			if (stat.equals("repair"))
				statText.setText(Player.repair);
			else if (stat.equals("readiness"))
				statText.setText(Player.readiness);
			else if (stat.equals("force"))
				statText.setText(Player.force);
			else if (stat.equals("finesse"))
				statText.setText(Player.finesse);
			else if (stat.equals("courage"))
				statText.setText(Player.courage);
			else if (stat.equals("charisma"))
				statText.setText(Player.charisma);
			else if (stat.equals("intelligence"))
				statText.setText(Player.intelligence);
			else if (stat.equals("ingenuity"))
				statText.setText(Player.ingenuity);
			else if (stat.equals("image"))
				statText.setText(Player.image);
			
			statText.setX(Main.SCREEN_WIDTH/2 + 225, Align.center);
			statText.setY(Main.SCREEN_HEIGHT - stats[q].button.getHeight() - i + 25);
			stage.addActor(statText);
			
			stats[q].button.setY(Main.SCREEN_HEIGHT - stats[q].button.getHeight() - i);
			stats[q].button.setX(Main.SCREEN_WIDTH/2 - 150, Align.center);
			
			stats[q+1].button.setY(Main.SCREEN_HEIGHT - stats[q+1].button.getHeight() - i);
			stats[q+1].button.setX(Main.SCREEN_WIDTH/2 + 150, Align.center);
			
			i += 75;
		}
	}
	
	class StatButton {
		
		TextButton button;
		
		public String stat;
		
		StatButton (final String tstat, final int inc) {
			
			stat = tstat;
			
			button = new TextButton(stat + " " + inc, textButtonStyle);
			
			if (inc > 0)
				button.setText("+");
			else
				button.setText("-");
			
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
					else if (stat.equals("image") && (Player.image > 1 || inc > 0))
						Player.ingenuity += inc;
					else
						return;
					
					// Adds or decreases the available points
					if (inc > 0)
						avaliablePoints--;
					if (inc < 0)
						avaliablePoints++;
					
					// Removes all actors in the x column for the stat text
					if (statText != null)
						for (Actor label : stage.getActors())
							if (label.getX() == Main.SCREEN_WIDTH/2 + 225)
								label.remove();
					
					// Updates text
					placement (stats);
					
			    }
			});
			
			stage.addActor(button);
			
		}
	}

}
