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

public class SlappySeal extends DynamicGameObject {

	public static final int SLAPPY_STATE_MOVING = 0;
	public static final int SLAPPY_STATE_HOOKING = 1;
	public static final int SLAPPY_STATE_BOTTOMED = 2;
	public static final float SLAPPY_MOVE_POS_VELOCITY = 5f;
	public static final float SLAPPY_MOVE_NEG_VELOCITY = -5f;
	public static final float SLAPPY_WIDTH = 125;
	public static final float SLAPPY_HEIGHT = 250;

	int state;
	float stateTime;
	
	private boolean topReached, bottomReached;
	public boolean hookingNow;

	public SlappySeal (float x, float y) {
		super(x, y, SLAPPY_WIDTH, SLAPPY_HEIGHT);
		state = SLAPPY_STATE_MOVING;
		stateTime = 0;
		//velocity.y = SLAPPY_MOVE_POS_VELOCITY;
		topReached = false;
		bottomReached = false;
		hookingNow = false;
		position.x = -15f;
		position.y = 0f;
	}

	public void update (float y, boolean b) {
		if(!b){
			position.y = y;
		}
		/*if(hookingNow){
			velocity.y = 0;
		}
		else if(!hookingNow){
			if(position.y <= 600 && !topReached){
				velocity.y = SLAPPY_MOVE_POS_VELOCITY;
				position.add(0, velocity.y);
			}
			else if(position.y >= 600 && !bottomReached){
				velocity.y = SLAPPY_MOVE_NEG_VELOCITY;
				topReached = true;
				position.add(0, velocity.y);
			}
			else if(position.y <= 600 && position.y >= 0 && !bottomReached){
				velocity.y = SLAPPY_MOVE_NEG_VELOCITY;
				position.add(0, velocity.y);
			}
			else if(position.y <= 0 && topReached){
				velocity.y = SLAPPY_MOVE_POS_VELOCITY;
				bottomReached = false;
				topReached = false;
				position.add(0, velocity.y);
			}*/
	}
	
	public void hookingNow(boolean b){
		hookingNow = b;
		
	}
}
