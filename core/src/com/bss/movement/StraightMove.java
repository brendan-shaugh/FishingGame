package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class StraightMove extends Movement {

	private EnemyGameObject e;
	private float yVelocity;
	private float xVelocity = 0f;
	private Vector2 position, originalPosition;
	private float yTotal = 0f, xTotal = 0f, total = 0f;
	private boolean topBottom = false, right = false;
	private Random r;
	
	public StraightMove(Vector2 position, EnemyGameObject e){
		super(position, e);
		this.position = position;
		originalPosition = position;
		this.e = e;
		r = new Random();
		if(position.y >= 800f && position.x <= 288f){
			yVelocity = -2f;
			xVelocity = r.nextFloat();
			topBottom = true;
		}
		else if(position.y >= 800f && position.x > 288f){
			yVelocity = -2f;
			xVelocity = r.nextFloat();
			topBottom = true;
		}
		else if(position.y <= -100f && position.x <= 288f){
			yVelocity = 2f;
			xVelocity = r.nextFloat();
			topBottom = true;
		}
		else if(position.y <= -100f && position.x > 288f){
			yVelocity = 2f;
			xVelocity = r.nextFloat();
			topBottom = true;
		}
		else if(position.y >= 400f && position.x >= 480f){
			yVelocity = -2f;
			xVelocity = -1.1f;
			right = true;
		}
		else{
			yVelocity = 2f;
			xVelocity = -1.1f;
			right = true;
		}
	}
	
	/*
	 * 800 -> -100 \\ + = 700 - = 900
	 * 
	 * -100 -> 800 \\ + = 700 - = -900
	 */
	@Override
	public Vector2 update(float deltaTime) {
		yTotal += yVelocity;
		xTotal += xVelocity;
		if(Math.abs(yTotal) >= 900f && topBottom){
			e.finished = true;
		}
		if(right && (position.y >= 800f || position.y <= -100f)){
			e.finished = true;
		}
		return position.add(xVelocity, yVelocity);
	}

}
