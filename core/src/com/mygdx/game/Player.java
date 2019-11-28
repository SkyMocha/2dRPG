package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import stages.ChoiceButton;

public class Player extends Entity {
		
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
			
	public Player () {
		super();
		
		name = "Player";
		
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
				
	}
	
	public Player (String args) {
		
	}
	
	public void incHealth () {
		health += repair/3;
	}
	
}
