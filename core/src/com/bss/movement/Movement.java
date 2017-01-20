package com.bss.movement;

import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;

public abstract class Movement {
	public Movement(Vector2 position, Enemy e) {
	}
	
	public abstract Vector2 update(float deltaTime);
}
