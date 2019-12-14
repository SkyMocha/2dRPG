package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class HeadInventor extends NPC {

	public static boolean howToHelp = false;
	
	public HeadInventor(ChoiceButton[] tchoices) {
		super(tchoices, "hamlet/head-inventor");
	}
	
	public HeadInventor () {
		this (new ChoiceButton[] {
			new ChoiceButton ("Why this small hamlet for a laboratory?"),
			new ChoiceButton ("What experiments are currently running?"),
			new ChoiceButton ("Can you help me? I seem to have lost sight of myself.\nAlthough I see, I no longer see, although I know, I no longer know myself."),
			new ChoiceButton ("How can I help?", false),
		});
	}
	
	public void decisionTree (int index) {
		switch (index) {
		case 0:
			singleStep(1);
			break;
		case 1:
			singleStep(2);
			break;
		case 2:
			howToHelp = true;
			singleStep(3);
			break;
		case 3:
			howToHelp = true;
			AssistantInventor.help = true;
			singleStep(4);
			break;
		default:
			Main.headInventor = new HeadInventor();
			Main.location = "laboratory";
			break;
		}
	}
	
	public void updateHelper() {
		updateButton (3, howToHelp);
	}

}
