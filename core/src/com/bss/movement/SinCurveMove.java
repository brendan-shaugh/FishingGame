package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class SinCurveMove extends Movement {

	CatmullRomSpline<Vector2> myCatmull;
	Vector2[] points, dataSet;
	public Vector2 first, second, sendBack;
	int counter = 1;
	int k = 12;
	float w;
	private Random r;
	float speed = 0.15f;
    float current = 0f;
    public float place = 0f, t = 0f;
    private EnemyGameObject e;

	public SinCurveMove(Vector2 position, EnemyGameObject e){
		super(position, e);
		this.e = e;
		r = new Random();
		w = 15f;
		sendBack = new Vector2(position);
		first = new Vector2(position);
		second = new Vector2(position);
	    //points = new Vector2[k];
	    //dataSet = new Vector2[k];
	    
	    if(position.y >= 800f && position.x <= 288f){
	    	points = new Vector2[k];
		    dataSet = new Vector2[k];
	    	for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y - (float)i*90f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y >= 800f && position.x > 288f){
			points = new Vector2[k];
		    dataSet = new Vector2[k];
	    	for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y - (float)i*90f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y <= -100f && position.x <= 288f){
			points = new Vector2[k];
		    dataSet = new Vector2[k];
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y + (float)i*90f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y <= -100f && position.x > 288f){
			points = new Vector2[k];
		    dataSet = new Vector2[k];
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y + (float)i*90f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y >= 400f && position.x >= 480f){
			w = -100f;
			points = new Vector2[k];
		    dataSet = new Vector2[k];
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w -5f, position.y - (float)i*70f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else{
			w = -100f;
			points = new Vector2[k];
		    dataSet = new Vector2[k];
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w -5f, position.y + (float)i*70f);
		    	counter++;
		        if(counter == 2){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
	}
	
	@Override
	public Vector2 update(float deltaTime) {
		current += 0.001f;
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
