package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.Town;

public class Hamlet extends Town {
	private Hamlet(ChoiceButton[] t) {
        super(t);
        // continue doing stuff with t here
    }

    public Hamlet() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Carpenters House"),
        	new ChoiceButton ("Blacksmiths House"),
        	new ChoiceButton ("Tavern"),
        	new ChoiceButton ("Royal Keep"),
        	new ChoiceButton ("Town Square")
        });
    }
    
    @Override
    public void decisionTree (int index) {
		switch (index) {
			case 0:
				Main.location = "hamlet-carpenter";
				break;
			case 1:
				Main.location = "hamlet-blacksmith";
				break;
			case 2:
				Main.location = "hamlet-tavern";
				break;
			case 3:
				Main.location = "hamlet-keep";
				break;
			case 4:
				Main.location = "hamlet-square";
				break;
			default:
				break;
		}
	}
}
