package stages;

import java.util.ArrayList;
import java.util.List;
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
    
    // NPC with only one step of dialogue
	public NPC(ChoiceButton[] tchoices, String textFile) {
		define();
		
		singleChoices = tchoices;
		
		// Generates the readText based of the text file
		FileHandle f = Gdx.files.internal("text/" + textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
    	
    	// Creates the label for the text
    	text = new Label (readText, labelStyle);
		text.setY(i + 10, Align.bottomLeft);
		text.setX(Align.bottomLeft);
		text.setWidth(1880);
		text.setWrap(true);
		text.pack();
	
		stage.addActor(text);
		
		j = 0;
		// Adds actions for each button
		for (ChoiceButton choice : singleChoices) {			
			// Adds button listener
			choice.index = j;
			choice.button.addListener(new ChangeListener() {
				int index = j;
				@Override
				public void changed (ChangeEvent event, Actor actor) {
					decisionTree (index);
				}
			});
			j++;
		}
		
		// Shows the buttons
		i = 0;
		showButtons();
		
		singleStep (0);
	}
	
	// NPC with multiple stages of dialogue
	public NPC(ChoiceButton[][] tchoices, String textFile) {
		define();
		
		i = 0;
		for (ChoiceButton choice : tchoices[0]) {
			choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
			choice.button.setY(i);
			if (choice.show)
				stage.addActor(choice.button);
			i += 50;
		}
		
		j = 0;
		for (ChoiceButton[] choiceList : tchoices) {
			i = 0;
			for (ChoiceButton choice : choiceList) {
				// Sets button position
				choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
				choice.button.setY(i);
				i += 50;
				
				// Adds button listener
				choice.index = j;
				choice.button.addListener(new ChangeListener() {
					int index = j;
					@Override
					public void changed (ChangeEvent event, Actor actor) {
						decisionTree (index);
					}
				});
				j++;
			}
		}	
		
		FileHandle f = Gdx.files.internal("text/" + textFile + ".txt");
		textReader = new Scanner(f.read());
    	while (textReader.hasNext())
    		readText += textReader.nextLine() + "\n";
    	
    	choices = tchoices;
    	
    	changeStep (0);
	}
	
	// Defines the variables in both NPC types
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
		text.setX(Align.topLeft);
		text.setY(Main.SCREEN_HEIGHT, Align.topLeft);
		text.setWidth(1880);
		text.setWrap(true);
		text.pack();
	}
	
	// General structure of a decision tree for a conversation
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
	
	// Changes the current step of dialogue
	public void changeStep (int index) {
		
		if (index != 0)
			for (ChoiceButton choice : choices[currStep])
				choice.button.remove();
		
		currStep = index;
		
		// Formats the current text
		currText = readText.split("(break)")[index];
		currText = currText.replace("(", "").replace(")", "");
		currText = currText.replaceFirst(System.getProperty("line.separator"), "");
		while (Character.isDigit(currText.charAt(0)))
			currText = currText.substring(1);
		
		text.setText(currText);
		text.layout();
		text.pack();
		
		// Changes the y of the text so that longer peices of text still fit.
		text.setY(Main.SCREEN_HEIGHT, Align.topLeft);
		
		stage.addActor(text);
		
		System.out.println (choices);
		
		for (ChoiceButton choice : choices[currStep]) 
			if (choice.show)
				stage.addActor(choice.button);
			
	}
	
	// The single step for single step dialogue choices
	public void singleStep (int index) {	
		// Formats the current text
		currText = readText.split("(break)")[index];
		currText = currText.replace("(", "").replace(")", "");
		currText = currText.replaceFirst(System.getProperty("line.separator"), "");
		while (Character.isDigit(currText.charAt(0)))
			currText = currText.substring(1);
		
		System.out.println (currText);
		
		text.setText(currText);
		text.layout();
		text.pack();
		
		// Changes the y of the text so that longer peices of text still fit.
		text.setY(Main.SCREEN_HEIGHT, Align.topLeft);
		
		stage.addActor(text);
		
		// Returns if its the first index of the text file
		if (index == 0)
			return;
		
		// Adds the leave button
		for (ChoiceButton choice : singleChoices)
			choice.button.remove();
		
		ChoiceButton leaveButton = new ChoiceButton ("Leave");
		leaveButton.button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				decisionTree (-1);
			}
		});
		leaveButton.button.align(Align.bottomRight);
		leaveButton.button.setX(Main.SCREEN_WIDTH - 100);
		stage.addActor(leaveButton.button);
		
	}
	
	// Updates the buttons to see if they should be shown or not
	public void updateButton (int x, int y, boolean bool) {
		choices[x][y].show = bool;
	}
	public void updateButton (int x, boolean bool) {
		singleChoices[x].show = bool;
	}
	public void updateHelper() {
		
	}
	// GETS CALLED EACH FRAME
	public void update () {
		updateHelper();
		i = 0;
		for (ChoiceButton choice : singleChoices)
			if (choice.show) {
				choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
				choice.button.setY(i);
				i += 50;
				choice.button.setVisible(true);
			}
			else
				choice.button.setVisible(false);
	}
	
	public void update (String args) {
		updateHelper();
		j = 0;
		for (ChoiceButton[] choiceList : choices) {
			i = 0;
			for (ChoiceButton choice : choiceList) {
				if (choice.show) {
					choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
					choice.button.setY(i);
					i += 50;
					choice.button.setVisible(true);
				}
				else
					choice.button.setVisible(false);
			}
		}
	}
	
	// Shows the buttons initially
	public void showButtons () {
		i = 0;
		for (ChoiceButton choice : singleChoices) {
			if (choice.show) {
				choice.button.setX(Main.SCREEN_WIDTH, Align.bottomRight);
				choice.button.setY(i);
				stage.addActor(choice.button);
				i += 50;
			}
			else {
				choice.button.setVisible(false);
				stage.addActor(choice.button);
			}
		}
	}
	
	// Turns a single number index into a ChoiceButton from choices
	public ChoiceButton indexToChoice (int index) {
		
		List <ChoiceButton> choices1D = new ArrayList<ChoiceButton>();
		for (ChoiceButton[] choiceList : choices)
			for (ChoiceButton choice : choiceList)
				choices1D.add(choice);
		return choices1D.get(index);
		
	}
	
}
