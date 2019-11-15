package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import stages.ChoiceButton;

public class Player extends Entity {
		
	public static int strength;
	public static int dexterity;
	public static int charisma;
	public static int intelligence;
	public static int wisdom;
	
	public static List<Action> actions;
	
	public Player () {
		actions = new ArrayList<Action>();
		
		strength = avg;
		dexterity = avg;
		charisma = avg;
		intelligence = avg;
		wisdom = avg;
		
		addAction (
				"Fist",
				5,
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
	
	public Action[] getActions () {
		return (Action[]) actions.toArray();
	}
	
	
}
