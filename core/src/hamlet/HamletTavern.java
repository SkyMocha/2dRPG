package hamlet;

import com.mygdx.game.Main;

import stages.Building;
import stages.ChoiceButton;
import stages.TextStages;
import stages.Town;

public class HamletTavern extends Building {

	private HamletTavern(ChoiceButton[] t) {
        super(t, "hamlet/hamlet-tavern");
        // continue doing stuff with t here
    }

    public HamletTavern() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Talk to the lonely patron."), // 0
        	new ChoiceButton ("Interrupt the fight."), // 1
        	new ChoiceButton ("Sit at the lighthearted table."), // 2
        	new ChoiceButton ("Sit at the lonely table."), // 3
        	new ChoiceButton ("Speak with the bartender."), // 4
        	new ChoiceButton ("Leave."), // 5
        });
    }
    
    public void decisionTree (int index) {
    	setText();
    	
		switch (index) {
			case 0:
				Main.location = "lonely-patron";
				break;
			case 1:
				Main.introFight = new IntroFight();
				Main.location = "tutorial-fight";
				break;
			case 2:
				Main.location = "lighthearted-table";
				break;
			case 3:
				Main.location = "lonely-table";
				break;
			case 4:
				Main.location = "hamlet-bartender";
				break;
			case 5:
				Main.location = "hamlet";
			default:
				break;
		}
	}


}
