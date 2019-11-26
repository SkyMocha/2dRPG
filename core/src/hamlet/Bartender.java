package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class Bartender extends NPC {

	public static boolean lonelyPatronRemains = true;
	public static boolean lonelyPatronCheck = false;
	public static boolean fightCheck = false;
	
	public Bartender(ChoiceButton[] tchoices) {
		super(tchoices, "hamlet-bartender");
	}
	
	public Bartender() {
		this(new ChoiceButton []{
				new ChoiceButton ("Have you heard talk of the lonely patron?", true), // 0
				new ChoiceButton ("Have you seen the lonely patron dissapear?", false), // 1
				new ChoiceButton ("About that fight...", false), // 2
				new ChoiceButton ("Leave"), // 3
		});
	}
	
	public void decisionTree (int index) {
		
		try {
		
			switch (index) {
				case 0:
					singleStep (1);
					Thread.sleep(2500);
					Main.location = "hamlet-tavern";
					break;
				case 1:
					singleStep (2);
					Thread.sleep(2500);
					Main.location = "hamlet-tavern";
					break;
				case 2:
					singleStep (3);
					Thread.sleep(2500);
					Main.location = "hamlet-tavern";
					break;
				case 3:
					singleStep(-1);
					break;
				default:
					Main.bartender = new Bartender();
					Main.location = "hamlet-tavern";
					break;
			}
		
		} catch(InterruptedException e) {
			System.out.println("Thread got interrupted! >:(");
		}
	}
	
	public void updateHelper () {
		updateButton (0, lonelyPatronRemains);
		updateButton (1, lonelyPatronCheck);
		updateButton (2, fightCheck);
	}

}
