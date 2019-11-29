package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class LonelyTable extends NPC {
	
	public static boolean collectionHouse = false;

	public LonelyTable(ChoiceButton[][] tchoices) {
		super(tchoices, "lonely-table");
	}
	
	public LonelyTable () {
		this (new ChoiceButton[][] {
			{
				// Case 0
				new ChoiceButton ("I cant help but notice,\nbut you seem to have kept yourself in isolation from the rest of this tavern."),
			},
			{
				// Case 1
				new ChoiceButton ("If so, what can you tell me about the lonely patron."),
			},
			{
				// Case 2
				new ChoiceButton ("[Image 12] I see that you tried to rob me."),
				// Case 3
				new ChoiceButton ("Leave."),
			},
			{
				// Case 4
				new ChoiceButton ("Leave."),
			},
		});
	}
	
	public void decisionTree (int index) {
		System.out.println (indexToChoice(index).button.getText());
		System.out.println (indexToChoice(index).choice());
		
		if (!indexToChoice(index).choice())
			return;
		
		switch (index) {
			case 0:
				changeStep(1);
				break;
			case 1:
				changeStep(2);
				indexToChoice(index + 1).choice();
				break;
			case 2:
				changeStep(3);
				break;
			case 3:
			case 4:
				collectionHouse = true;
				Main.collectFight = new CollectFight();
			default:
				Main.lonelyTable = new LonelyTable();
				Main.location = "hamlet-tavern";
				break;
		}
	}
	
	public void updateHelper () {
	
	}

}
