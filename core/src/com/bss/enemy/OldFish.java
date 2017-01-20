package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class OldFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.oldFish);
	
	public OldFish(float x, float y, World world, int i) {
		super(x, y, world, i, 5f, 2f, 95f, 32f, s);
		this.badFish = true;
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x + 5f, position.y + 2f, 95f, 32f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof OldFish) {
            OldFish that = (OldFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
