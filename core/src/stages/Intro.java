package stages;

import com.mygdx.game.Main;

public class Intro extends TextStages{
	
	private Intro(ChoiceButton[] t) {
        super(t, "intro");
        // continue doing stuff with t here
    }

    public Intro() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("You walk away"),
        	new ChoiceButton ("[int 5] You investigate the scene"),
        	new ChoiceButton ("[chr 10] You bribe the guard for information"),
        	new ChoiceButton ("[str 12] You threaten the guard for information")
        });
    }
    
    @Override
    public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				Main.location = "hamlet";
				break;
			case 1:
				drawText ("You begin investigating the scene for clues.");
				break;
			case 2:
				drawText ("You bribe the guard for information regarding the incident.");
				break;
			case 3:
				drawText ("You threaten the guard for information regarding the incident.");
				break;
			default:
				break;
		}
	}
}
