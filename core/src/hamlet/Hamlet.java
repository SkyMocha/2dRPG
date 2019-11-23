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
        	new ChoiceButton ("Tavern"),
        	new ChoiceButton ("Laboratory"),
        	new ChoiceButton ("Hamlet Square"),
        	new ChoiceButton ("Closed Off Fields"),
        	new ChoiceButton ("Darkness"),
        	new ChoiceButton ("Northern Passage"),
        });
    }
    
    @Override
    public void decisionTree (int index) {
		switch (index) {
			case 0:
				Main.location = "hamlet-tavern";
				break;
			case 1:
				Main.location = "laborotory";
				break;
			case 2:
				Main.location = "hamlet-square";
				break;
			case 3:
				Main.location = "closed-off-fields";
				break;
			case 4:
				Main.location = "darkness";
				break;
			case 5:
				Main.location = "northern-passage";
				break;
			default:
				break;
		}
	}
}
