package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class BigOrange extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.bigOrange);
	
	public BigOrange(float x, float y, World world, int i) {
		super(x, y, world, i, 23f, 16f, 74f, 43f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x + 23f, position.y + 16f, 74f, 43f);*/
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof BigOrange) {
            BigOrange that = (BigOrange) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
