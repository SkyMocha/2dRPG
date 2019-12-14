package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class QuietStudent extends NPC{

	public QuietStudent(ChoiceButton[] tchoices) {
		super(tchoices, "hamlet/quiet-student");
	}
	
	public QuietStudent () {
		this (new ChoiceButton[] {
			new ChoiceButton ("Leave"),
		});
	}
	
	public void decisionTree (int index) {
		switch (index) {
		default:
			Main.quietStudent = new QuietStudent();
			Main.location = "laboratory";
			break;
		}
	}

}
