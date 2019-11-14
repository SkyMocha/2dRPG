package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.TextStages;
import stages.Town;

public class HamletTavern extends TextStages {

	private HamletTavern(ChoiceButton[] t) {
        super(t, "hamlet-tavern");
        // continue doing stuff with t here
    }

    public HamletTavern() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Talk to the lonely patron"),
        	new ChoiceButton ("Interrupt the fight")
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
						Thread.sleep(2500);
						Main.location = "lonely-patron";
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 1:
				if (drawText("You step in to interrupt the fight."))
					try {
						Thread.sleep(2500);
						Main.location = "tutorial-fight";
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			default:
				break;
		}
	}


}
