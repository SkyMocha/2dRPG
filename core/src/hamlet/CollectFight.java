package hamlet;

import com.mygdx.game.Entity;

import enemies.Citizen;
import stages.Fight;

public class CollectFight extends Fight{

	public CollectFight(Entity[] tenemies) {
		super(tenemies);
		// TODO Auto-generated constructor stub
	}
	
	public CollectFight () {
		this (new Entity[] {
			new Citizen (),
		});
	}

	@Override
	public void onCompletion() {
		HamletCollectHouse.fingerCut = true;
		HamletCollectHouse.killed = true;
		LonelyTable.collectionHouse = false;
	}

	@Override
	public void onStart() {
		enemies[0].addAction("Knife", 4, 50, 0);
	}

}
