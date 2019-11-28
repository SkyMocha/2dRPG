package hamlet;

import stages.ChoiceButton;
import stages.NPC;

public class LightheartedTable extends NPC{

	public LightheartedTable(ChoiceButton[][] tchoices) {
		super(tchoices, "lonely-table");
	}
	
	public LightheartedTable () {
		this (new ChoiceButton[][] {
			{
				
			},
			{
				
			},
		});
	}
	
}
