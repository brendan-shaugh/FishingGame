package com.bss.enemy;

import com.bss.movement.*;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class AngelFish extends EnemyGameObject {

	/*public Ellipse hitBox;
	public boolean notHit = true;
	public World world;
	private Movement m;*/
	public static Sprite s = new Sprite(Assets.angel);
	
	public AngelFish(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 0f, 60f, 100f, s);
		/*this.world = world;
		s.setOriginCenter();
		hitBox = new Ellipse(position.x, position.y, 60f, 100f);
		m = GetMovement.gMovement(i, position, this);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof AngelFish) {
            AngelFish that = (AngelFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
}
