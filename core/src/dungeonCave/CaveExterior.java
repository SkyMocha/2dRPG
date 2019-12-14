package dungeonCave;

import com.mygdx.game.Main;

import stages.ChoiceButton;
import stages.TextStages;

public class CaveExterior extends TextStages {

	public CaveExterior(ChoiceButton[] tchoices) {
		super(tchoices, "darkness-cave/caveExterior");
		// TODO Auto-generated constructor stub
	}
	
	public CaveExterior () {
		this (new ChoiceButton [] {
			new ChoiceButton ("No"),
			new ChoiceButton ("Yes"),
		});
	}
	
	public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				Main.location = "cave-interior";
				Main.caveExterior = new CaveExterior();
				break;
			case 1:
				Main.location = "darkness";
				Main.caveExterior = new CaveExterior();
				break;
			default:
				break;
		}
	}


}
