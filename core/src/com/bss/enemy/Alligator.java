package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.World;

public class Alligator extends EnemyGameObject {

public static Sprite s = new Sprite(Assets.alligator);
	
	public Alligator(float x, float y, World world, int i) {
		super(x, y, world, i, 40f, 20f, 100f, 19f, s);
		this.badFish = true;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Alligator) {
        	Alligator that = (Alligator) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
}
