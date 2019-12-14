package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;
import stages.TextStages;

public class LonelyPatron extends NPC {
	
	public static boolean robbed = false;

	public LonelyPatron(ChoiceButton[][] tchoices, String textFile) {
		super (tchoices, textFile);
	}
	
	public LonelyPatron() {
        this(new ChoiceButton[][] { 
        	// currStep 0
        	{
        		// Case 0
        		new ChoiceButton ("Hello."),
        		// Case 1
                new ChoiceButton ("[dex 3] Rob", true),
                // Case 2
                new ChoiceButton ("Leave"),
        	},
        	// currStep 1
        	{
        		// Case 3
        		new ChoiceButton ("Repeat yourself."),
        		// Case 4
        		new ChoiceButton ("Tap him on the shoulder."),
        		// Case 5
                new ChoiceButton ("Leave"),
        	},
        	// currStep 2
        	{
        		// Case 6
        		new ChoiceButton ("Hello."),
        		// Case 7
                new ChoiceButton ("Leave"),
        	},
        	// currStep 3
        	{
        		// Case 8
        		new ChoiceButton ("Do you have any idea as to where we are?"),
        		// Case 9
                new ChoiceButton ("Leave"),
        	},
        	// currStep 4
        	{
        		// Case 10
        		new ChoiceButton ("Sorry for disturbing you, do you have any idea as to where we are?"),
        		// Case 11
                new ChoiceButton ("Leave"),
        	},
        	// currStep 5
        	{
        		// Case 10
        		new ChoiceButton ("Leave"),
        	},
      
        }, "hamlet/lonely-patron");
    }
	
	public void decisionTree (int index) {
//		if (!choices[currStep][index].choice())
//			return;
		switch (index) {
			case 0:
				changeStep(1);
				break;
			case 1:
				changeStep(2);
				robbed = true;
				break;
			case 2:
				decisionTree(-1);
				break;
			case 3:
				changeStep(3);
				break;
			case 4:
				changeStep(4);
				break;
			case 5:
				decisionTree(-1);
				break;
			case 6:
				changeStep (1);
				break;
			case 7:
				decisionTree(-1);
				break;
			case 8:
				changeStep (5);
				break;
			case 9:
				decisionTree(-1);
				break;
			case 10:
				Bartender.lonelyPatronCheck = true;
				Bartender.lonelyPatronRemains = false;
				changeStep (5);
				break;
			case 11:
				decisionTree(-1);
				break;
			default:
				Main.lonelyPatron = new LonelyPatron();
				Main.location = "hamlet";
				break;
		}
	}
	
	public void updateHelper () {
		updateButton (0, 1, !robbed);
	}

}
