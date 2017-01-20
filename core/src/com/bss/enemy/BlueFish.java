package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class BlueFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.blueFish);
	
	public BlueFish(float x, float y, World world, int i) {
		super(x, y, world, i, 11f, 10f, 89f, 25f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x + 11f, position.y + 10f, 89f, 25f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof BlueFish) {
            BlueFish that = (BlueFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
