package enemies;

import com.mygdx.game.Entity;

public class Empty extends Entity {
	public Empty (String tname) {
		name = tname;
		
		repair = 2;
		readiness = 2;
		force = 2;
		finesse = 2;
		courage = 2;
		charisma = 2;
		intelligence = 2;
		ingenuity = 2;
		
		health = 0;
		maxHealth = 0;
		
	}
}
