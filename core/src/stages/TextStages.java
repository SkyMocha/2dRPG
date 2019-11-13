package stages;

import java.io.File;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.mygdx.game.Main;
import com.mygdx.game.Player;

public class TextStages {
	public Stage stage;
	BitmapFont font;
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label text;
    
    Scanner textReader;
    String readText = "";
    
    float stringCompleteness = 1;
    float timeElapsed;
    float textSpeed = 25f * 4;
    
    static int j;
    static int pindex = 0;
    
    ChoiceButton[] choices;
    
    boolean flag;
    boolean choosen;
    
	public TextStages (ChoiceButton[] tchoices, String textFile) {		
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
		
		choices = tchoices;
		
		int i = 0;
		for (ChoiceButton choice : choices) {
			choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
			choice.button.setY(i);
			stage.addActor(choice.button);
			i += 70;
		}
		
		FileHandle f = Gdx.files.internal(textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
		
	}
	
	public void update () {
		if (stringCompleteness + 1 <= readText.length() && !flag)
			drawText(readText);
		else if (!flag) {
			for (j = 0; j < choices.length; j++) {
				choices[j].index = j;
				choices[j].button.addListener(new ChangeListener() {
					int index = j;
					@Override
					public void changed (ChangeEvent event, Actor actor) {
						decisionTree (index);
						choosen = !choosen;
			    	}
				});
			}
			flag = !flag;
			stringCompleteness = 0;
		}
		if (stringCompleteness + 1 <= readText.length() && choosen)
			decisionTree(pindex);
	}
	
	public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				Main.location = "hamlet";
				break;
			default:
				Main.location = "hamlet";
				break;
		}
	}
	
	public void drawText (String typeText) {	
		
		System.out.println (typeText);
		
		stringCompleteness += textSpeed * Gdx.graphics.getDeltaTime();
		
		if (stringCompleteness > typeText.length())
			return;
		
		text.remove();
		
		text = new Label (typeText.substring(0, (int)stringCompleteness + 1), labelStyle);
		text.setX(10, Align.left);
		text.setY(Main.SCREEN_HEIGHT - text.getHeight() - 20);
	
		stage.addActor(text);
		
	}
}
