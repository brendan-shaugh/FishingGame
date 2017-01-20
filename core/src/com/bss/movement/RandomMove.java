package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class RandomMove extends Movement {

	CatmullRomSpline<Vector2> myCatmull;
	Vector2[] points, dataSet;
	public Vector2 first, second, sendBack;
	int counter = 1;
	int k;
	int w;
	private Random r;
	float speed = 0.15f;
    float current = 0f;
    public float place = 0f, t = 0f;
    private EnemyGameObject e;
	
	public RandomMove(Vector2 position, EnemyGameObject e) {
		super(position, e);
		this.e = e;
		r = new Random();
		k = r.nextInt(8) + 8;
		w = r.nextInt(2);
		sendBack = new Vector2(position);
		first = new Vector2(position);
		second = new Vector2(position);
	    points = new Vector2[k];
	    dataSet = new Vector2[k];
	    
	    dataSet[0] = new Vector2(position);
	    dataSet[1] = new Vector2(position);
	    
	    for(int i = 2; i < k - 2; i++){
	    	dataSet[i] = new Vector2(((float)r.nextInt(160) + 280f), ((float)r.nextInt(600) + 100f));
	    }
	    
	    if(w == 0){
	    	float xv = ((float)r.nextInt(385) + 96f);
	    	dataSet[k-2] = new Vector2(xv, 850f);
	    	dataSet[k-1] = new Vector2(xv, 850f);
	    }
	    else{
	    	float xv = ((float)r.nextInt(385) + 96f);
	    	dataSet[k-2] = new Vector2(xv, -100f);
	    	dataSet[k-1] = new Vector2(xv, -100f);
	    }
	    
		 myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
	}

	@Override
	public Vector2 update(float deltaTime) {
		current += 0.0009f;
	    if(current >= 1)
	    	e.finished = true;
	    place = current * k;
	    first = points[(int)place];
	    if(((int)place+1) < k)
	    {
	        second = points[(int)place+1];
	    }
	    else
	    {
	        //second = points[0]; //or finish, in case it does not loop.
	    	e.finished = true;
	    }
	    t = place - ((int)place);
	    return sendBack.set((first.x + (second.x - first.x) * t), (first.y + (second.y - first.y) * t));
	}

}
