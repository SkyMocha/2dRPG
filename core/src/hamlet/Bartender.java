package hamlet;

import com.mygdx.game.Main;
import com.mygdx.game.Player;

import stages.ChoiceButton;
import stages.NPC;

public class Bartender extends NPC {

	public static boolean lonelyPatronRemains = true;
	public static boolean lonelyPatronCheck = false;
	public static boolean fightCheck = false;
	
	Player player = new Player("");
	
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
			switch (index) {
			case 0:
				singleStep (1);
				break;
			case 1:
				singleStep (2);
				fightCheck = false;
				player.addAction("Primitive Dagger", 3, 75, 0);
				break;
			case 2:
				singleStep (3);
				break;
			case 3:
				decisionTree(-1);
				break;
			default:
				Main.bartender = new Bartender();
				Main.location = "hamlet-tavern";
				break;
		}
	}
	
	public void updateHelper () {
		updateButton (0, lonelyPatronRemains);
		updateButton (1, lonelyPatronCheck);
		updateButton (2, fightCheck);
	}

}
