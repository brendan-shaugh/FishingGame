package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.World;

public class Whale extends EnemyGameObject {

public static Sprite s = new Sprite(Assets.whale);
	
	public Whale(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 58f, 80f, 15f, s);
		this.badFish = true;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Whale) {
        	Whale that = (Whale) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
	
}
