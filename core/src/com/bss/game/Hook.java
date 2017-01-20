/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.bss.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


public class Hook extends DynamicGameObject {
	//public static final float SQUIRREL_WIDTH = 1;
	//public static final float SQUIRREL_HEIGHT = 0.6f;
	//public static final float SQUIRREL_VELOCITY = 3f;
	public static final float HOOK_SPEED = 110f;
	public static final float HOOK_MOVE_POS_VELOCITY = 5f;
	public static final float HOOK_MOVE_NEG_VELOCITY = -5f;
	
	
	/*static final int GOING_UP = 0;
	static final int GOING_DOWN = 1;*/
	static final int STILL = 0;
	static final int MOVING = 1;
	static final int HOOKING = 2;
	public int state;
	
	public Vector2 direction, movement, hookingVelocity;
	
	public float rotation = 0f;

	float stateTime = 0;
	
	World world;
	
	public Circle circleBounds;
	
	public boolean hookingNow = false;
	
	public boolean topReached, bottomReached, maxReached, firstUpdate;

	private float xMove, yMove;
	
	private float distanceTraveled = 0f;
	private float distance = 0f;
	private int previousState = 0;
	
	public float moveD = 0f;
	public float movetotal = 0f;
	public boolean canMove = true;
	private float previousY = 100f;
	
	
	public Hook (float x, float y, World world) {
		super(x, y, 45f, 45f);
		this.world = world;
		//state = GOING_UP;
		state = STILL;
		//velocity.set(30f, 30f);
		//hookingNow = true;
		maxReached = false;
		firstUpdate = true;
		direction = new Vector2();
		movement = new Vector2();
		velocity.set(0f, 5f);
		hookingVelocity = new Vector2();
		circleBounds = new Circle(position.x + 23f, position.y + 23f, 23f);
	}

	public void update (float deltaTime, float angle) {
		
		switch (state) {
		/*case GOING_UP:
			updateUp(deltaTime);
			break;
		case GOING_DOWN:
			updateDown(deltaTime);
			break;*/
		case STILL:
			//updateStill(deltaTime);
			break;
		case MOVING:
			updateMove(deltaTime);
			break;
		case HOOKING:
			updateHooking(deltaTime, angle);
			break;
		}
		
		circleBounds.x = position.x + 23f;
		circleBounds.y = position.y + 23f;
	}
	
	private void updateHooking(float deltaTime, float angle) {
		if(!maxReached){
			//if(firstUpdate){previousY = position.y;}
			hookingVelocity.set(direction).scl(HOOK_SPEED);
			movement.set(hookingVelocity).scl(deltaTime);
			position.add(movement);
			if(firstUpdate){distance = movement.len();firstUpdate = false;}
			distanceTraveled += distance;
			if(distanceTraveled >= 335f){maxReached = true; firstUpdate = true;}
		}
		else if(maxReached){
			hookingVelocity.set(direction).scl(-HOOK_SPEED);
			movement.set(hookingVelocity).scl(deltaTime);
			position.add(movement);
			distanceTraveled -= distance;
			if(distanceTraveled <= 0f){
				position.x = 80f;
				world.clearHookedFish();
				//position.y = previousY;
				if(previousState == 0){hookingNow = false; state = STILL/*GOING_UP*/; maxReached = false; rotation = 0f;}
				if(previousState == 1){hookingNow = false; state = STILL/*GOING_DOWN*/; maxReached = false; rotation = 0f;}
			}
		}
	}

	private void updateMove(float deltaTime){
		if(hookingNow == false && canMove){
			hookingVelocity.set(direction).scl(50f);
			movement.set(hookingVelocity).scl(deltaTime);
			position.add(movement);
			movetotal += Math.abs(movement.y);
			if(movetotal >= moveD){
				movetotal = 0f;
				state = STILL;
			}
			if((position.y >= 725f || position.y <= 150f)){
				movetotal = 0f;
				canMove = false;
				state = STILL;
			}
		}
		
	}
	
	/*private void updateStill(float deltaTime){
		
	}
	
	private void updateDown(float deltaTime) {
		position.add(0, HOOK_MOVE_NEG_VELOCITY);
		if(position.y <= 160f){ state = GOING_UP;}
	}

	private void updateUp(float deltaTime) {
		//position.add(0, HOOK_MOVE_POS_VELOCITY);
		position.add(velocity);
		if(position.y >= 760f){ state = GOING_DOWN;}
	}*/

	public void hookingNow(boolean b){
		hookingNow = b;
		previousState  = state;
	}
}
