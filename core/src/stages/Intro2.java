package stages;

import com.mygdx.game.Main;

public class Intro2 extends TextStages {
	private Intro2(ChoiceButton[] t) {
        super(t, "intro2");
        // continue doing stuff with t here
    }

    public Intro2() {
        this(new ChoiceButton[] { 
        	new ChoiceButton ("Walk into town."),
        });
    }
    
    @Override
    public void decisionTree (int index) {
		pindex = index;
		if (!choices[index].choice())
			return;
		switch (index) {
			case 0:
				if (drawText("As you walk into town, you realize that people go about their normal lives\nas if the darkness has become their sight."))
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
