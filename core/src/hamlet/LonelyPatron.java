package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;
import stages.TextStages;

public class LonelyPatron extends NPC {

	public LonelyPatron(ChoiceButton[][] tchoices, String textFile) {
		super (tchoices, textFile);
	}
	
	public LonelyPatron() {
        this(new ChoiceButton[][] { 
        	{
        		new ChoiceButton ("Hello."),
                new ChoiceButton ("[dex 3] Rob"),
                new ChoiceButton ("Leave"),
        	},
        	{
        		new ChoiceButton ("Three"),
                new ChoiceButton ("Four"),
        	},
      
        }, "lonely-patron");
    }
	
	public void decisionTree (int index) {
//		if (!choices[currStep][index].choice())
//			return;
		switch (index) {
			case 0:
				changeStep (1);
				break;
			case 1:
				changeStep (2);
				break;
			case 2:
				Main.location = "hamlet";
				break;
			case 3:
				Main.location = "hamlet";
				break;
			default:
				System.out.println("BROKE");
				break;
		}
	}

}
