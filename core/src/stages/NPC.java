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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Main;

public class NPC {
	
	public Stage stage;
	BitmapFont font;
	TextButton button;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    Label text;
    ScrollPane scrollPane;
    
    Scanner textReader;
    String readText = "";
    String currText = "";
    
    static int j = 0, i = 0;
    static int pindex;
    
    protected ChoiceButton choices[][];
    protected int currStep = 0;
    ChoiceButton singleChoices[];
    
	public NPC(ChoiceButton[] tchoices, String textFile) {
		define();
		
		int i = 0;
		for (ChoiceButton choice : tchoices) {
			choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
			choice.button.setY(i);
			stage.addActor(choice.button);
			i += 50;
		}
		
		FileHandle f = Gdx.files.internal("text/" + textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
    	
    	text = new Label (readText, labelStyle);
		text.setY(i + 10, Align.bottomLeft);
		text.setX(Align.bottomLeft);
		text.setWidth(1880);
		text.setWrap(true);
		text.pack();
	
		stage.addActor(text);
		
	}
	
	public NPC(ChoiceButton[][] tchoices, String textFile) {
		define();
		
		i = 0;
		for (ChoiceButton choice : tchoices[0]) {
			choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
			choice.button.setY(i);
			stage.addActor(choice.button);
			i += 50;
		}
		
		for (ChoiceButton[] choiceList : tchoices) {
			i = 0;
			for (ChoiceButton choice : choiceList) {
				choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
				choice.button.setY(i);
				i += 50;
			}
		}
				
		
		FileHandle f = Gdx.files.internal("text/" + textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
    	
    	choices = tchoices;
    	
    	changeStep (0);
	}
	
	private void define () {
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
		
		text = new Label ("", labelStyle);
		text.setY(i + 10, Align.bottomLeft);
		text.setX(Align.bottomLeft);
		text.setWidth(1880);
		text.setWrap(true);
		text.pack();
	}
	
	public void decisionTree (int index) {
		pindex = index;
		if (!choices[currStep][index].choice())
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
	
	public void changeStep (int index) {
		
		if (index != 0)
			for (ChoiceButton choice : choices[currStep])
				choice.button.remove();
		
		currStep = index;
		
		currText = readText.split("(break)")[index];
		    	
		text.setText(currText);
		
		stage.addActor(text);
		
		for (ChoiceButton choice : choices[currStep]) {
			choice.index = j;
			choice.button.addListener(new ChangeListener() {
				int index = j;
				@Override
				public void changed (ChangeEvent event, Actor actor) {
					decisionTree (index);
				}
			});
			j++;
			stage.addActor(choice.button);
		}
			
	}
	
}
