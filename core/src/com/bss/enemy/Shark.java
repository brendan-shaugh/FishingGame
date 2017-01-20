package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class Shark extends EnemyGameObject {

	public static Sprite s = new Sprite(Assets.shark);
	
	public Shark(float x, float y, World world, int i) {
		super(x, y, world, i, 20f, 16f, 100f, 15f, s);
		this.badFish = true;
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Shark) {
            Shark that = (Shark) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
