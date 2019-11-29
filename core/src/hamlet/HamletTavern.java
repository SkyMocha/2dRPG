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
        	new ChoiceButton ("Talk to the lonely patron."), // 0
        	new ChoiceButton ("Interrupt the fight."), // 1
        	new ChoiceButton ("Sit at the lighthearted table."), // 2
        	new ChoiceButton ("Sit at the lonely table."), // 3
        	new ChoiceButton ("Speak with the bartender."), // 4
        	new ChoiceButton ("Leave."), // 5
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
						Main.introFight = new IntroFight();
						pindex = -1;
						Thread.sleep(2000);
						Main.location = "tutorial-fight";
						setText();
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 2:
				if (drawText("You sit at the lighthearted table."))
					try {
						pindex = -1;
						Thread.sleep(2000);
						Main.location = "lighthearted-table";
						setText();
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 3:
				if (drawText("You sit at the lonely table.\nIt seems less lonely than the single stool the lonely patron inhabits,\nMaybe you can get some answers from them."))
					try {
						pindex = -1;
						Thread.sleep(2000);
						Main.location = "lonely-table";
						setText();
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			case 4:
					pindex = -1;
					Main.location = "hamlet-bartender";
					setText();
				break;
			case 5:
				pindex = -1;
				Main.location = "hamlet";
				setText();
			default:
				break;
		}
	}


}
