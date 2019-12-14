package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class AssistantInventor extends NPC {

	public static boolean help;
	
	public AssistantInventor (ChoiceButton[] tchoices) {
		super(tchoices, "hamlet/assistant-inventor");
	}
	
	public AssistantInventor () {
		this (new ChoiceButton[] {
			new ChoiceButton ("How can I help?", false),
			new ChoiceButton ("Leave"),
		});
	}
	
	public void decisionTree (int index) {
		switch (index) {
			case 0:
				singleStep (1);
				break;
			case 1:
		default:
			Main.assistantInventor = new AssistantInventor();
			Main.location = "laboratory";
			break;
		}
	}
	
	public void updateHelper() {
		updateButton (0, help);
	}
	
}
