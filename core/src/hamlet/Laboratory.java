package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.TextStages;

public class Laboratory extends TextStages {
	private Laboratory(ChoiceButton[] t) {
        super(t, "hamlet-tavern");
        // continue doing stuff with t here
    }

    public Laboratory() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Explore the Laboratory"),
        	new ChoiceButton ("Speak to the Head Inventor"),
        	new ChoiceButton ("Speak to the Asisstant Inventor"),
        	new ChoiceButton ("Speak to the Outgoing Student"),
        	new ChoiceButton ("Speak to the Quiet Student"),
        });
    }
    
    @Override
    public void decisionTree (int index) {
		pindex = index;
		System.out.println (pindex);
		switch (index) {
			case 0:
				if (drawText("You begin talking to the lonely patron."))
					try {
						pindex = -1;
						Thread.sleep(2000);
//						Main.location = "lonely-patron";
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 1:
				if (drawText("You step in to interrupt the fight."))
					try {
						pindex = -1;
						Thread.sleep(2000);
//						Main.location = "tutorial-fight";
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			default:
				break;
		}
	}
}
