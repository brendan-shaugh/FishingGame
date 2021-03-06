package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class Dolphin extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	World world;*/
	public static Sprite s = new Sprite(Assets.dolphin);
	
	public Dolphin(float x, float y, World world, int i) {
		super(x, y, world, i, 9f, 11f, 71f, 21f, s);
		this.badFish = true;
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x + 9f, position.y + 11f, 71f, 21f);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Dolphin) {
            Dolphin that = (Dolphin) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}


}
