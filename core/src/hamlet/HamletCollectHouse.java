package hamlet;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.NPC;

public class HamletCollectHouse extends NPC {

	public static boolean fingerCut = false;
	public static boolean killed = false;
	public static boolean spared = false;
	
	public HamletCollectHouse(ChoiceButton[][] tchoices) {
		super(tchoices, "hamlet/hamlet-collect-house");
		// TODO Auto-generated constructor stub
	}
	public HamletCollectHouse() {
		this (new ChoiceButton[][] {
				{
					new ChoiceButton ("Calm down, I have no intent on harming you,\nI have just come to collect money on behalf of the city."),
					new ChoiceButton ("If that’s how it is; it seems more than a finger shall be returned."),
				},
				{
					new ChoiceButton ("Spare him."),
					new ChoiceButton ("Kill him in mercy."),
					new ChoiceButton ("Kill him in understanding."),
					new ChoiceButton ("Kill him for greed."),
				},
				{
					new ChoiceButton ("Spare him."),
					new ChoiceButton ("Kill him in mercy."),
					new ChoiceButton ("Kill him in understanding."),
					new ChoiceButton ("Kill him for greed."),
				},
				{
					new ChoiceButton ("Leave."),
				},
				{
					new ChoiceButton ("Leave."),
				},
				{
					new ChoiceButton ("Leave."),
				},
				{
					new ChoiceButton ("Leave."),
				}
			} 
		);
	}
	
	public void decisionTree (int index) {
		switch (index) {
			case 0:
				changeStep(1);
				break;
			case 1:
				System.out.println ("FIGHT INITIATED");
				Main.location = "hamlet-collect-fight";
				break;
			case 2:
				changeStep(2);
				break;
			case 3:
				changeStep(4);
				spared = false;
				break;
			case 4:
				changeStep(5);
				killed = true;
				break;
			case 5:
				changeStep(6);
				fingerCut = true;
				killed = true;
				break;
			case 6:
				changeStep(3);
				spared = true;
				break;
			case 7:
				changeStep(4);
				killed = true;
				break;
			case 8:
				changeStep(5);
				killed = true;
				break;
			case 9:
				changeStep(6);
				fingerCut = true;
				killed = true;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			default:
				LonelyTable.collectionHouse = false;
				Main.location = "hamlet";
				break;
		}
	}

}
