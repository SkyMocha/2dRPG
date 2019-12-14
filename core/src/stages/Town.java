package stages;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Main;

public class Town {
	
	public Stage stage;
	BitmapFont font;
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label text;
        
    ChoiceButton[] locations;
    
    static int j;
	
	public Town (ChoiceButton[] tlocations) {
		stage = new Stage (Main.viewport);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(50);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		font = generator.generateFont(parameter);
		
		textButtonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle();
						
		textButtonStyle.font = font;
		labelStyle.font = font;
		
		text = new Label ("", labelStyle);
		
		locations = tlocations;
		
		int i = 0;
		for (ChoiceButton choice : locations) {
			choice.button.setX(0, Align.bottomLeft);
			choice.button.setY(Main.SCREEN_HEIGHT - choice.button.getHeight() - i);
			stage.addActor(choice.button);
			i += 70;
		}
		
		for (j = 0; j < locations.length; j++) {
			locations[j].index = j;
			locations[j].button.addListener(new ChangeListener() {
				int index = j;
				@Override
				public void changed (ChangeEvent event, Actor actor) {
					decisionTree (index);
		    	}
			});
		}
	}
	
	public void decisionTree (int index) {
		switch (index) {
			case 0:
				Main.location = "hamlet";
				break;
			default:
				Main.location = "hamlet";
				break;
		}
	}
	
	public void update () {
		updateHelper();
		int i = 0;
		for (ChoiceButton choice : locations) {
			choice.update();
			if (choice.show) {			
				choice.button.setX(0, Align.bottomLeft);
				choice.button.setY(Main.SCREEN_HEIGHT - choice.button.getHeight() - i);
				i += 70;
				choice.button.setVisible(true);
			}
			else
				choice.button.setVisible(false);
		}
	}
	
	public void updateHelper() {
		
	}
	
	public void updateButton (int x, boolean bool) {
		locations[x].show = bool;
	}
}