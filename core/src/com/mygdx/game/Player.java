package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import stages.ChoiceButton;

public class Player {
		
	// MAIN STATISTICS
	public static int health;
	public static int repair;
	public static int readiness;
	
	public static int force;
	public static int finesse;
	
	public static int courage;
	public static int charisma;
	
	public static int mp;
	public static int intelligence;
	public static int ingenuity;
	public static int image;
	
	public static int sanity;
	
	// SECONDARY STATISTICS
	public static int composure;
	public static int perception;
	
	public static List<Action> actions;
				
	public int avg = 15;
	public static String name;
	
	public Player () {
		
		actions = new ArrayList<Action>();
		
		name = "player";
		
		repair = avg;
		readiness = avg;
		
		force = avg;
		finesse = avg;
		
		courage = avg;
		charisma = avg;
		
		intelligence = avg;
		ingenuity = avg;
		image = avg;
		
		health = 8;
		
		sanity = 50;
		
		addAction (
				"Fist",
				1,
				60,
				-1
			);
				
	}
	
	public Player (String args) {
		
	}
	
	public void incHealth () {
		health += repair/3;
	}
	
	public static void addAction (String tname, int tpower, int taccuracy, int theal) {
		Action tempAction = new Action(
					tname, tpower, taccuracy, theal
				);
		actions.add(tempAction);
	}
	
}
