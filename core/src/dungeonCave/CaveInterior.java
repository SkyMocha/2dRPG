package dungeonCave;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.TextStages;

public class CaveInterior extends TextStages{	
	public CaveInterior(ChoiceButton[] tchoices) {
		super(tchoices, "darkness-cave/caveInterior");
		// TODO Auto-generated constructor stub
	}
	
	public CaveInterior () {
		this (new ChoiceButton [] {
			new ChoiceButton ("[Finesse 11] Sneak past"),
			new ChoiceButton ("[Finesse 10] Fight with surprise"),
			new ChoiceButton ("Fight"),
		});
	}
	
	public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				Main.location = "cave-clearing";
				Main.caveInterior = new CaveInterior();
				break;
			case 1:
				Main.location = "darkness";
				Main.caveInterior = new CaveInterior();
				break;
			case 2:
				Main.location = "darkness";
				Main.caveInterior = new CaveInterior();
				break;
			default:
				break;
		}
	}

}

