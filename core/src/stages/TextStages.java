package stages;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.mygdx.game.Main;

public class TextStages {
	public Stage stage;
	BitmapFont font;
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label text;
    ScrollPane scrollPane;
    
    Scanner textReader; // Reads a text file then adds it to readText
    String readText = ""; // The text that will be in this readText
    
    float stringCompleteness = 1; // How complete the String is in drawText
    float baseTextSpeed = 65f; // The base text speed [modifiable]
    float textSpeed = baseTextSpeed; // The actual text speed
    
    static int j;
    protected static int pindex = 0;
    static float y;
    
    protected ChoiceButton[] choices;
    
    boolean flag;
    boolean choosen;
    
    String textFile;
    
	public TextStages (ChoiceButton[] tchoices, String ttextFile) {		
		stage = new Stage (Main.viewport);
				
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YeonSung-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		generator.scaleForPixelHeight((int)Math.ceil(Main.SCREEN_HEIGHT));
		parameter.size = (int)Math.ceil(40);
		
		parameter.minFilter = TextureFilter.Nearest;
		parameter.magFilter = TextureFilter.MipMapLinearNearest;
		
		font = generator.generateFont(parameter);
		
		textButtonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle();
						
		textButtonStyle.font = font;
		labelStyle.font = font;
		
		choices = tchoices;
		
		// Adds the choice button
		int i = 0;
		for (ChoiceButton choice : choices) {
			choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
			choice.button.setY(i);
			stage.addActor(choice.button);
			i += 50;
		}
		
		// Creates the readText variable based on what the dialogue file says
		textFile = ttextFile;
		setText();
		
    	text = new Label ("", labelStyle);
		text.setY(i + 10, Align.bottomLeft);
		text.setX(Align.bottomLeft);
		text.setWidth(1880);
		text.setWrap(true);
		stage.addActor(text);
		
	}
	
	// Updates every frame; required since it uses a typewriter effect for text
	public void update () {	 	
		if (Gdx.input.isKeyPressed(Input.Keys.TAB))
			textSpeed = baseTextSpeed * 6;
		else
			textSpeed = baseTextSpeed;
		
		// Checks to see if the text is completed, if not, then it continues drawing the text
		if (Math.round(stringCompleteness) <= readText.length() && !flag)
			drawText(readText);
		
		// Creates the action listeners for the buttons
		else if (!flag) {
			// Completes the text just in case
			stringCompleteness = readText.length()-1;
			drawText(readText);
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
	
	// Typewriter effect for text
	// Returns true when complete
	public boolean drawText (String typeText) {	
				
		stringCompleteness += textSpeed * Gdx.graphics.getDeltaTime();
		
		if (stringCompleteness > typeText.length())
			return true;
		
		text.setText(typeText.substring (0, (int)stringCompleteness + 1));
		text.invalidate();
		text.pack();
		text.layout();
		
		return false;
		
	}
	
	public void setText () {
		readText = "";
		FileHandle f = Gdx.files.internal("text/" + textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
    	flag = false;
	}
}