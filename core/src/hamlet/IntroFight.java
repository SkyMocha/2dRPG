package hamlet;

import com.mygdx.game.Entity;

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
}
