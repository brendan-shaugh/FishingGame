package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class GreenFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.greenFish);
	
	public GreenFish(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 0f, 60f, 26f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x, position.y, 60f, 26f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof GreenFish) {
            GreenFish that = (GreenFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
