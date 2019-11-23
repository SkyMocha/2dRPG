package enemies;

import com.mygdx.game.Entity;

public class Zombie extends Entity{	
	public Zombie () {
		name = "Zombie";
		
		repair = avg-2;
		readiness = avg-2;
		force = avg-2;
		finesse = avg-2;
		courage = avg-2;
		charisma = avg-2;
		intelligence = avg-2;
		ingenuity = avg-2;
		
		health = 3;
		maxHealth = 3;
	}
}
