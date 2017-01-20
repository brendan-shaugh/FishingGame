package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class ClownFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.clown);
	
	public ClownFish(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 0f, 98f, 51f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x, position.y, 98f, 51f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof ClownFish) {
            ClownFish that = (ClownFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
