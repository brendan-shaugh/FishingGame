package com.bss.enemy;

import com.bss.movement.GetMovement;
import com.bss.movement.Movement;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class EnemyGameObject extends DynamicGameObject implements Enemy {

	public Ellipse hitBox;
	public boolean notHit = true;
	public World world;
	public Sprite s;
	private Movement m;
	private float differenceX = 0, differenceY = 0;
	private boolean first = true;
	public boolean finished = false;
	private float xOffset, yOffset;
	public boolean badFish;
	
	public EnemyGameObject(float x, float y, World world, int i, float xOffset, float yOffset, float eWidth, float eHeight, Sprite s) {
		super(x, y, 0f, 0f);
		this.s = s;
		this.world = world;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		badFish = false;
		hitBox = new Ellipse(position.x + xOffset, position.y + yOffset, eWidth, eHeight);
		m = GetMovement.gMovement(i, position, this);
	}

	@Override
	public void update(float deltaTime) {
		if(notHit){
			position.set(m.update(deltaTime));
		}
		else{
			if(first){
				differenceX = (position.x - world.hook.circleBounds.x);
				differenceY = (position.y - world.hook.circleBounds.y);
				first = false;
			}
			position.x = world.hook.circleBounds.x + differenceX;
			position.y = world.hook.circleBounds.y + differenceY;
			if(position.x <= 115f + differenceX){
				finished = true;
			}
		}
		hitBox.setPosition(position.x + xOffset, position.y + yOffset);
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}
	
	

}
