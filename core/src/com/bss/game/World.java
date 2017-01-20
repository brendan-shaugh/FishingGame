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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.bss.enemy.Alligator;
import com.bss.enemy.AngelFish;
import com.bss.enemy.BigFish;
import com.bss.enemy.BigOrange;
import com.bss.enemy.BlowFish;
import com.bss.enemy.BlueFish;
import com.bss.enemy.ClownFish;
import com.bss.enemy.Dolphin;
import com.bss.enemy.EllipseHit;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;
import com.bss.enemy.GoldFish;
import com.bss.enemy.GreenFish;
import com.bss.enemy.OldFish;
import com.bss.enemy.Perch;
import com.bss.enemy.PlainFish;
import com.bss.enemy.RareFish;
import com.bss.enemy.Shark;
import com.bss.enemy.SwordFish;
import com.bss.enemy.Trout;
import com.bss.enemy.Whale;

public class World {
	public interface WorldListener {
		public void jump ();

		public void highJump ();

		public void hit ();

		public void coin ();
	}

	public static final float WORLD_WIDTH = 10;
	//public static final float WORLD_HEIGHT = 15 * 20;
	public static final int WORLD_STATE_RUNNING = 0;
	//public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	//public static final Vector2 gravity = new Vector2(0, -12);

	public final SlappySeal slappy;
	public final Hook hook;
	public Trout trout;
	/*public final List<Platform> platforms;
	public final List<Spring> springs;
	public final List<Squirrel> squirrels;
	public final List<Coin> coins;
	public Castle castle;*/
	//need pufferfish and tuna and stuff here
	public final WorldListener listener;
	public final Random rand;

	//public float heightSoFar;
	//public int score;
	public int state;
	
	public float hookSlope = 0;
	public float hookSlopeRadians = 90f;
	private float hookxv = 0;
	private float hookyv = 0;
	public float angle;
	public float rotatedX, rotatedY, angleRadians, offsetX, offsetY;
	
	public Vector2 pointPosition;
	
	public Intersector i;
	
	private int random, r, r2;
	private float xLoc, yLoc;
	
	private boolean notHit = true;
	
	private EllipseHit eh;
	
	public ArrayList<EnemyGameObject> enemies;
	
	private ArrayList<EnemyGameObject> hookedEnemies;
	
	
	int time;
	public String timeText;
	public GlyphLayout layout;
	long prevTime = 0l;
	long tracker = 0l;
	
	private int hookedFish;
	public float sizeOfHealth = 10;
	
	public World (WorldListener listener) {
		this.slappy = new SlappySeal(-10f, 95f);
		//
		this.hook = new Hook(80f, 250f, this);
		//this.boot = new Boot(480f, 400f, this, 0);
		//this.trout = new Trout(200f, 800f, this, 0);
		pointPosition = new Vector2();
		//this.hook.velocity.setAngle(90f);
		//
		/*this.platforms = new ArrayList<Platform>();
		this.springs = new ArrayList<Spring>();
		this.squirrels = new ArrayList<Squirrel>();
		this.coins = new ArrayList<Coin>();*/
		this.listener = listener;
		rand = new Random();
		//generateLevel();

		//this.heightSoFar = 0;
		//this.score = 0;
		
		time = 10;
		timeText = "" + time;
		layout = new GlyphLayout(Assets.font, timeText, Color.YELLOW, 0f, 0, false);
		//layout.setText(font, timeText, Color.BLACK, 0f, 0, false);
		
		this.state = WORLD_STATE_RUNNING;
		i = new Intersector();
		eh = new EllipseHit(10);
		
		enemies = new ArrayList<EnemyGameObject>();
		hookedEnemies = new ArrayList<EnemyGameObject>();
		setEnemiesFirst();
		hookedFish = 0;
	}

	public void update (float deltaTime) {
		//updateSlappy(deltaTime);
		updateHook(deltaTime);
		//updateBoot(deltaTime);
		//updateTrout(deltaTime);
		
		updateEnemies(deltaTime);
		updateClock();
		
		checkCollisions();
		
		//updateHook(deltaTime, accelX);
		//updatePlatforms(deltaTime);
		//updateSquirrels(deltaTime);
		//updateCoins(deltaTime);
		//if (slappy.state != slappy.SLAPPY_STATE_BOTTOMED) checkCollisions();
		//checkGameOver();
	}

	public void castHook(float Xx, float Yy, Vector2 touch){
		angle = (float) Math.atan2((double)(Yy - hook.position.y - 25f), (double)(Xx - hook.position.x -25f));
		angleRadians = angle* (180f/(float)Math.PI);
		this.hook.rotation = angleRadians;//+45f
		
		
		/*
		 * var rotatedX = Math.cos(angle) * (point.x - center.x) - Math.sin(angle) * (point.y-center.y) + center.x;
		   var rotatedY = Math.sin(angle) * (point.x - center.x) + Math.cos(angle) * (point.y - center.y) + center.y;
		 */
		rotatedX = (float) (Math.cos(angle) * ((hook.position.x + 45f) - (hook.position.x +23f)) - Math.sin(angle) * ((hook.position.y +24f) - (hook.position.y +23f)) + hook.position.x + 23f);
		rotatedY = (float) (Math.sin(angle) * ((hook.position.x + 45f) - (hook.position.x +23f)) + Math.cos(angle) * ((hook.position.y +24f) - (hook.position.y +23f)) + hook.position.y + 23f);
		pointPosition.set(rotatedX, rotatedY);
		hook.hookingNow(true);
		hook.direction.set(touch).sub(pointPosition).nor();
		//hook.direction.set(touch).sub(hook.position).nor();
		
		offsetX = hook.position.x - (float) (Math.cos(angle) * ((hook.position.x + 4f) - (hook.position.x +23f)) - Math.sin(angle) * ((hook.position.y +24f) - (hook.position.y +23f)) + hook.position.x + 23f);
		offsetY = hook.position.y - (float) (Math.sin(angle) * ((hook.position.x + 4f) - (hook.position.x +23f)) + Math.cos(angle) * ((hook.position.y +24f) - (hook.position.y +23f)) + hook.position.y + 23f);
		
		//hook.hookingNow(true);
		hook.state = 2;
		slappy.position.y = hook.position.y - 155f;
	}
	
	public void moveSlappy(float y){
		
		Vector2 move = new Vector2(80f, y);
		hook.movetotal = 0f;
		hook.moveD = (float) Math.sqrt(Math.pow(y - hook.position.y, 2));
		hook.direction.set(move).sub(hook.position).nor();
		if((hook.position.y > 725f && y < 725f) || (hook.position.y < 150f && y > 150f)){
			hook.canMove = true;
		}
		hook.state = 1;
	}
	
	//public void 
	
	//IMPROTANT!!
	private void updateSlappy (float deltaTime) {
		//if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
		//if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
		//slappy.update(hook.position.y - 150f, hook.hookingNow);
		//heightSoFar = Math.max(bob.position.y, heightSoFar);
	}

	private void updateHook(float deltaTime) {
		//if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
		//if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
		hook.update(deltaTime, angle);
		//heightSoFar = Math.max(bob.position.y, heightSoFar);
	}
	
	private void updateEnemies(float deltaTime){
		int len = enemies.size();
		for (int i = 0; i < len; i++){
			enemies.get(i).update(deltaTime);
			if(enemies.get(i).finished){
				terminateFish(i);
				enemies.add(generateEnemy());
			}
		}
	}
	
	private void updateTrout(float deltaTime){
		trout.update(deltaTime);
	}

	private void updateClock(){
		tracker += TimeUtils.timeSinceMillis(prevTime);
		if(tracker > 1000l){
			tracker = 0l;
			time -= 1;
			if(time < 0){
				time = 10;
				sizeOfHealth -= 1f;
				checkEnd();
			}
			timeText = "" + time;
			layout.setText(Assets.font, timeText, Color.BLACK, 0f, 0, false);
		}
		prevTime = System.currentTimeMillis();
	}
	
	private void setEnemiesFirst(){
		for(int i = 0; i < 4; i++)
			enemies.add(generateEnemy());
	}
	
	//should have different method for setting initial fish in static positions
	private EnemyGameObject generateEnemy(){
		random = rand.nextInt(18);
		
		r = rand.nextInt(5);
		r2 = rand.nextInt(2);
		
		if(r != 4){
			xLoc = (float)rand.nextInt(210) + 150f;
			if(r2 == 0)
				yLoc = 800f;
			else
				yLoc = -100f;
		}
		else{
			yLoc = (float)rand.nextInt(700);
			xLoc = 480f;
		}
		
		int x = 5;
		EnemyGameObject ego = null;
		switch(random){
		case 0:
			ego = new Shark(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 1:
			ego = new Trout(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 2:
			ego = new AngelFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 3:
			ego = new BigFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 4:
			ego = new BigOrange(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 5:
			ego = new BlueFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 6:
			ego = new ClownFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 7:
			ego = new Dolphin(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 8:
			ego = new GoldFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 9:
			ego = new GreenFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 10:
			ego = new OldFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 11:
			ego = new Perch(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 12:
			ego = new PlainFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 13:
			ego = new RareFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 14:
			ego = new BlowFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 15:
			ego = new SwordFish(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 16:
			ego = new Alligator(xLoc, yLoc, this, rand.nextInt(x));
			break;
		case 17:
			ego = new Whale(xLoc, yLoc, this, rand.nextInt(x));
			break;
		}
		return ego;
	}
	
	private void checkCollisions() {
		/*checkPlatformCollisions();
		checkSquirrelCollisions();
		checkItemCollisions();
		checkCastleCollisions();*/
		
		int len = enemies.size();
		for (int i = 0; i < len; i++){
			if(enemies.get(i).position.x - hook.position.x <= 45f && enemies.get(i).notHit == true){
				if(eh.collide(enemies.get(i).hitBox.x + (enemies.get(i).hitBox.width/2f), 
						enemies.get(i).hitBox.y + (enemies.get(i).hitBox.height/2f), 
						enemies.get(i).hitBox.width/2f, 
						enemies.get(i).hitBox.height/2f, 
						hook.circleBounds.x, hook.circleBounds.y, hook.circleBounds.radius)){
					hook.maxReached = true;
					enemies.get(i).notHit = false;
					hookedFish++;
					hookedEnemies.add(enemies.get(i));
				}
			}
		}
		
		
		
		/*if(Intersector.overlaps(hook.circleBounds, boot.hitbox1) || Intersector.overlaps(hook.circleBounds, boot.hitbox2)){
			hook.maxReached = true;
			boot.notHit = false;
			//boot.
		}
		if(trout.position.x - hook.position.x <= 45f){
			if(eh.collide(trout.hitBox.x + 39f, trout.hitBox.y + 19f, trout.hitBox.width/2f, trout.hitBox.height/2f, hook.circleBounds.x, hook.circleBounds.y, hook.circleBounds.radius)){
				hook.maxReached = true;
				trout.notHit = false;
			}
		}*/
	}

	public void terminateFish(int i){
		enemies.remove(i);
	}
	
	/*
	 * called in hook when hooking set back to false
	 */
	public void clearHookedFish(){
		if(hookedFish == 1){
			time = 10;
			tracker = 0l;
			timeText = "" + time;
			layout.setText(Assets.font, timeText, Color.BLACK, 0f, 0, false);
			prevTime = System.currentTimeMillis();
			//need health stuff here
			hookedFish = 0;
			for(int i = 0; i < hookedEnemies.size(); i++){
				if(hookedEnemies.get(i).badFish){
					sizeOfHealth -= 1f;
					checkEnd();
				}
			}
			hookedEnemies.removeAll(hookedEnemies);
		}
		else if(hookedFish == 2){
			time = 10;
			tracker = 0l;
			timeText = "" + time;
			layout.setText(Assets.font, timeText, Color.BLACK, 0f, 0, false);
			prevTime = System.currentTimeMillis();
			//need health stuff here
			hookedFish = 0;
			int c = 0;
			for(int i = 0; i < hookedEnemies.size(); i++){
				if(hookedEnemies.get(i).badFish){
					sizeOfHealth -= 1f;
					checkEnd();
				}
				else{
					c++;
				}
			}
			if( c == 2){
				sizeOfHealth += 1f;
			}
			hookedEnemies.removeAll(hookedEnemies);
		}
		else if(hookedFish == 3){
			time = 10;
			tracker = 0l;
			timeText = "" + time;
			layout.setText(Assets.font, timeText, Color.BLACK, 0f, 0, false);
			prevTime = System.currentTimeMillis();
			//need health stuff here
			hookedFish = 0;
			int c = 0;
			for(int i = 0; i < hookedEnemies.size(); i++){
				if(hookedEnemies.get(i).badFish){
					sizeOfHealth -= 1f;
					checkEnd();
				}
				else{
					c++;
				}
			}
			if(c == 3){
				sizeOfHealth += 2f;
			}
			else if(c == 2){
				sizeOfHealth += 1f;
			}
			hookedEnemies.removeAll(hookedEnemies);
		}
		else if(hookedFish == 4){
			time = 10;
			tracker = 0l;
			timeText = "" + time;
			layout.setText(Assets.font, timeText, Color.BLACK, 0f, 0, false);
			prevTime = System.currentTimeMillis();
			//need health stuff here
			hookedFish = 0;
			int c = 0;
			for(int i = 0; i < hookedEnemies.size(); i++){
				if(hookedEnemies.get(i).badFish){
					sizeOfHealth -= 1f;
					checkEnd();
				}
				else{
					c++;
				}
			}
			if(c == 4){
				sizeOfHealth += 3f;
			}
			else if(c == 3){
				sizeOfHealth += 2f;
			}
			else if(c == 2){
				sizeOfHealth += 1f;
			}
			hookedEnemies.removeAll(hookedEnemies);
		}
	}
	
	private void checkEnd(){
		if(sizeOfHealth <= 0f){
			this.state = WORLD_STATE_GAME_OVER;
		}
	}
}
