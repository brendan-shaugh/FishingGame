package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class GoldFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.goldie);
	
	public GoldFish(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 4f, 75f, 48f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x, position.y + 4f, 75f, 48f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof GoldFish) {
            GoldFish that = (GoldFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
