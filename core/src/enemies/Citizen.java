package enemies;

import com.mygdx.game.Entity;

public class Citizen extends Entity {
	public Citizen () {
		name = "Citizen";
		
		repair = avg-4;
		readiness = avg-4;
		force = avg-4;
		finesse = avg-4;
		courage = avg-4;
		charisma = avg-4;
		intelligence = avg-4;
		ingenuity = avg-4;
		
		health = 5;
		maxHealth = 5;
	}
}
