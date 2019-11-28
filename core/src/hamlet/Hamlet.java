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
        	new ChoiceButton ("Collection House", false),
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
				Main.location = "hamlet-collection-house";
				break;
			case 2:
				Main.location = "laborotory";
				break;
			case 3:
				Main.location = "hamlet-square";
				break;
			case 4:
				Main.location = "closed-off-fields";
				break;
			case 5:
				Main.location = "darkness";
				break;
			case 6:
				Main.location = "northern-passage";
				break;
			default:
				break;
		}
	}
    
    public void updateHelper () {
    	updateButton (1, LonelyTable.collectionHouse);
    }
}
