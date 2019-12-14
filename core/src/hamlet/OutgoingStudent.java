package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class OutgoingStudent extends NPC {

	public OutgoingStudent(ChoiceButton[] tchoices) {
		super(tchoices, "hamlet/outgoing-student");
	}
	
	public OutgoingStudent () {
		this (new ChoiceButton[] {
			new ChoiceButton ("Greetings."),
		});
	}
	
	public void decisionTree (int index) {
		switch (index) {
			case 0:
				singleStep (1);
				break;
		default:
			Main.outgoingStudent = new OutgoingStudent();
			Main.location = "laboratory";
			break;
		}
	}
	
	public void updateHelper() {
		
	}

}
