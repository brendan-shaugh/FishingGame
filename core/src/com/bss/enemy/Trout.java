package com.bss.enemy;

import com.bss.movement.CurvedMove;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class Trout extends EnemyGameObject {

	/*Bezier<Vector2> path;
	public Vector2 tmpV, tmpV2;
	float t = 0f, speed = 0f;
	public Ellipse hitBox;
	public World world;
	public float angle;
	private float differenceX = 0, differenceY = 0;
	private boolean first = true;
	public boolean notHit = true;
	private CurvedMove m;*/
	public static Sprite s = new Sprite(Assets.trout);
	
	public Trout(float x, float y, World world, int i) {
		super(x, y, world, i, 0f, 0f, 79f, 31f, s);
		s.setOriginCenter();
		/*path = new Bezier<Vector2>(new Vector2(200f, 800f), new Vector2(350f, 600f), new Vector2(350f, 200f), new Vector2(200f, -100f));
		tmpV = new Vector2(200f, 800f);
		tmpV2 = new Vector2(200f, 800f);
		hitBox = new Ellipse(position.x, position.y, 79f, 31f);
		this.world = world;
		angle = 0f;
		
		m = new CurvedMove(position, this);*/
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof Trout) {
            Trout that = (Trout) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}
	
	

}
