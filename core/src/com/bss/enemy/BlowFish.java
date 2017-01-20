package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.World;

public class BlowFish extends EnemyGameObject {

	public static Sprite s = new Sprite(Assets.blowFish);
	
	public BlowFish(float x, float y, World world, int i) {
		super(x, y, world, i, 30f, 13f, 75f, 54f, s);
		this.badFish = true;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof BlowFish) {
        	BlowFish that = (BlowFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
	
}
