package hamlet;

import com.mygdx.game.Main;

import stages.Building;
import stages.ChoiceButton;

public class Darkness extends Building {

	public static boolean cave;
	
	public Darkness(ChoiceButton[] tchoices) {
		super(tchoices, "hamlet/darkness");
	}
	
	public Darkness (){
		this (new ChoiceButton [] {
			new ChoiceButton ("Cave"),
		});
	}
	
	public void decisionTree (int index) {
		switch (index) {
		case 0:
			Main.location = "cave-exterior";
			Main.darkness = new Darkness();
			break;
		default:
			Main.location = "hamlet";
			Main.darkness = new Darkness();
			break;
		}
	}
	
	public void updateHelper () {
	}

}
