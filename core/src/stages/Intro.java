package stages;

import com.mygdx.game.Main;

public class Intro extends TextStages{
	
	private Intro(ChoiceButton[] t) {
        super(t, "intro");
        // continue doing stuff with t here
    }

    public Intro() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("You walk forwards"),
        });
    }
    
    @Override
    public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				if (drawText("You walk forwards, stumbling through the darkness."))
					try {
						Thread.sleep(2500);
						Main.location = "hamlet";
					} catch(InterruptedException e) {
					    System.out.println("Thread got interrupted! >:(");
					}
				break;
			default:
				break;
		}
	}
}