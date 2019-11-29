package hamlet;

import com.mygdx.game.Entity;
import com.mygdx.game.Main;

import enemies.Empty;
import enemies.Zombie;
import stages.ChoiceButton;
import stages.Fight;

public class IntroFight extends Fight {
	public IntroFight(Entity[] t) {
		super (t);
	}
	
	public IntroFight() {
        this(new Entity[] { 
        	new Zombie(),
        	new Empty("Barstool"),
        	new Zombie(),
        	new Empty("Chair"),
        	new Zombie(),
        	new Empty("Chair"),
        });
    }
	
	@Override
	public void onCompletion () {
		System.out.println ("FIGHT DONE");
		Bartender.fightCheck = true;
		Main.towns.play();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}
}
