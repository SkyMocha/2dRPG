package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	public String name;
	
	public int health;
	public int repair;
	public int readiness;
	
	public int force;
	public int finesse;
	
	public int courage;
	public int charisma;
	
	public int mp;
	public int intelligence;
	public int ingenuity;
	
	public int avg = 9;
	
	public int maxHealth;
	
	public List<Action> actions;
	
	public Entity () {
		actions = new ArrayList<Action>();
		
		addAction (
				"Fist",
				1,
				100,
				-1
			);
		
	}
	
	public void addAction (String tname, int tpower, int taccuracy, int theal) {
		Action tempAction = new Action(
					tname, tpower, taccuracy, theal
				);
		actions.add(tempAction);
	}
	
	public List<Action> getActions () {
		return actions;
	}
}
