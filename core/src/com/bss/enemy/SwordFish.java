package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class SwordFish extends EnemyGameObject {

	public static Sprite s = new Sprite(Assets.swordfish);
	
	public SwordFish(float x, float y, World world, int i) {
		super(x, y, world, i, 16f, 10f, 55f, 15f, s);
		
		
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof SwordFish) {
            SwordFish that = (SwordFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
	
	

}
