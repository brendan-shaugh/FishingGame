package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class CurvedMove extends Movement {

	Bezier<Vector2> path;
	public Vector2 tmpV, tmpV2;
	float t = 0f, speed = 0f;
	private Random r;
	private EnemyGameObject e;
	
	public CurvedMove(Vector2 position, EnemyGameObject e){
		super(position, e);
		this.e = e;
		
		r = new Random();
		tmpV = new Vector2(position);
		if(position.y >= 800f && position.x <= 288f){
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x + (float)r.nextInt(192), position.y - (float)r.nextInt(200)), new Vector2(position.x + (float)r.nextInt(192), position.y - 400f - (float)r.nextInt(200)), new Vector2(position.x, -100f));
		}
		else if(position.y >= 800f && position.x > 288f){
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x - (float)r.nextInt(192), position.y - (float)r.nextInt(200)), new Vector2(position.x - (float)r.nextInt(192), position.y - 400f - (float)r.nextInt(200)), new Vector2(position.x, -100f));
		}
		else if(position.y <= -100f && position.x <= 288f){
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x + (float)r.nextInt(192), position.y + (float)r.nextInt(200)), new Vector2(position.x + (float)r.nextInt(192), position.y + 400f + (float)r.nextInt(200)), new Vector2(position.x, 800f));
		}
		else if(position.y <= -100f && position.x > 288f){
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x - (float)r.nextInt(192), position.y + (float)r.nextInt(200)), new Vector2(position.x - (float)r.nextInt(192), position.y + 400f + (float)r.nextInt(200)), new Vector2(position.x, 800f));
		}
		else if(position.y >= 400f && position.x >= 480f){
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x - (float)r.nextInt(384), position.y + (float)r.nextInt(100)), new Vector2(position.x - (float)r.nextInt(384), position.y - (float)r.nextInt(200)), new Vector2(100f + (float)r.nextInt(300), -100f));
		}
		else{
			path = new Bezier<Vector2>(new Vector2(position), new Vector2(position.x - (float)r.nextInt(384), position.y - (float)r.nextInt(50)), new Vector2(position.x - (float)r.nextInt(384),  position.y + (float)r.nextInt(200)), new Vector2(100f + (float)r.nextInt(300), 800f));
		}
	}
	
	@Override
	public Vector2 update(float deltaTime) {
		t += 0.002f;
		if(t >= 1){
			//t = 0;
			e.finished = true;
		}
		path.valueAt(tmpV, t);
		return tmpV;
	}

}
