package com.bss.movement;

import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class GetMovement {

	public GetMovement(){
		
	}
	
	public static Movement gMovement(int i, Vector2 position, EnemyGameObject e){
		return new RandomMove(position, e);
	}
	
}
