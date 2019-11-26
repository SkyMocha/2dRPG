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
        	new ChoiceButton ("Talk to the lonely patron."),
        	new ChoiceButton ("Interrupt the fight."),
        	new ChoiceButton ("[chr 15] Sit at the familiar, yet painful table."),
        	new ChoiceButton ("[chr 12] Sit at the lonely, yet familiar table."),
        	new ChoiceButton ("Sit at the lighthearted table."),
        	new ChoiceButton ("Sit at the lonely table."),
        	new ChoiceButton ("Speak with the bartender."),
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
						Main.location = "lonely-patron";
						setText();
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 1:
				if (drawText("You step in to interrupt the fight."))
					try {
						pindex = -1;
						Thread.sleep(2000);
						Main.location = "tutorial-fight";
						setText();
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 6:
					pindex = -1;
					Main.location = "hamlet-bartender";
					setText();
					
				break;
			default:
				break;
		}
	}


}
