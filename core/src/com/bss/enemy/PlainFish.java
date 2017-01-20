package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class PlainFish extends EnemyGameObject {
	
	public static Sprite s = new Sprite(Assets.plainFish);

	public PlainFish(float x, float y, World world, int i) {
		super(x, y, world, i, 10f, 10f, 90f, 22f, s);
		//this.world = world;
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof PlainFish) {
            PlainFish that = (PlainFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
