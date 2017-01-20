package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class BigFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	public World world;*/
	public static Sprite s = new Sprite(Assets.big);
	
	public BigFish(float x, float y, World world, int i) {
		super(x, y, world, i, 23f, 21f, 77f, 55f, s);
		/*this.world = world;
		 * s.setOriginCenter();
		hitBox = new Ellipse(position.x, position.y, 100f, 96f);*/
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof BigFish) {
            BigFish that = (BigFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
