package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class Perch extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.perch);
	
	public Perch(float x, float y, World world, int i) {
		super(x, y, world, i, 9f, 8f, 71f, 21f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x + 9f, position.y + 8f, 71f, 21f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Perch) {
            Perch that = (Perch) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
