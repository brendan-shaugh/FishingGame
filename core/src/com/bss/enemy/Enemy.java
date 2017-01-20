package com.bss.enemy;

import com.badlogic.gdx.math.Vector2;
import com.bss.game.World;

public interface Enemy {
	public void update(float deltaTime);
	public World getWorld();
	public boolean equals(Object o);
	public Vector2 getPosition();
}
