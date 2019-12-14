package hamlet;

import com.mygdx.game.Main;

import stages.Building;
import stages.ChoiceButton;
import stages.TextStages;

public class Laboratory extends Building {
	private Laboratory(ChoiceButton[] t) {
        super(t, "hamlet/laboratory");
        // continue doing stuff with t here
    }

    public Laboratory() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Speak to the Head Inventor"),
        	new ChoiceButton ("Speak to the Assistant Inventor"),
        	new ChoiceButton ("Speak to the Outgoing Student"),
        	new ChoiceButton ("Speak to the Quiet Student"),
        	new ChoiceButton ("Leave"),
        });
    }
    
    @Override
    public void decisionTree (int index) {
		switch (index) {
			case 0:
				Main.location = "head-inventor";
				break;
			case 1:
				Main.location = "assistant-inventor";
				break;
			case 2:
				Main.location = "outgoing-student";
				break;
			case 3:
				Main.location = "quiet-student";
				break;
			case 4:
			default:
				Main.location = "hamlet";
				break;
		}
	}
}
