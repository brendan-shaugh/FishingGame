package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class ZigZagMove extends Movement {

	CatmullRomSpline<Vector2> myCatmull;
	Vector2[] points, dataSet;
	public Vector2 first, second, sendBack;
	int counter = 0;
	int k = 100;
	float w;
	Random r;
	float speed = 0.15f;
    float current = 0f;
    public float place = 0f, t = 0f;
    private EnemyGameObject e;

	public ZigZagMove(Vector2 position, EnemyGameObject e){
		super(position, e);
		this.e = e;
		r = new Random();
		w = 5f + (float)r.nextInt(15);
		sendBack = new Vector2(position);
		first = new Vector2(position);
		second = new Vector2(position);
	    //points = new Vector2[k];
	    //dataSet = new Vector2[k];
	    
	    if(position.y >= 800f && position.x <= 288f){
	    	points = new Vector2[k];
		    dataSet = new Vector2[k];
	    	for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y - (float)i*9f);
		    	counter++;
		        if(counter == 11){
		        	counter = 0;
		        	w = -w;
		        }
		    }
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; i++)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y >= 800f && position.x > 288f){
			points = new Vector2[k];
		    dataSet = new Vector2[k];
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2(position.x + w, position.y - (float)i*9f);
		    	counter++;
		        if(counter == 11){
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
		    	dataSet[i] = new Vector2(position.x + w, position.y + (float)i*9f);
		    	counter++;
		        if(counter == 11){
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
		    	dataSet[i] = new Vector2(position.x + w, position.y +(float)i*9f);
		    	counter++;
		        if(counter == 11){
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
			float x1 = position.x;
			float y1 = position.y;
			float a = 2.35619f;
			boolean down = false;
			k = 230;
			points = new Vector2[k];
		    dataSet = new Vector2[k];
		    w = 15f;
			//x2 = x1 + (w * Math.sin(a));
			//y2 = y1 + (w * Math.cos(a));
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2((float) (x1 + (w * Math.cos(a))), (float) (y1 + (w * Math.sin(a))));
		    	x1 = (float) (x1 + (w * Math.sin(a)));
		    	y1 = (float) (y1 + (w * Math.cos(a)));
		    	counter++;
		        if(counter == 6){
		        	counter = 0;
		        	w = -w;
		        	if(!down){
		        		a = 1.65806f;
		        		down = true;
		        	}
		        	else{
		        		a = 2.35619f;
		        		down = false;
		        	}
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
			float x1 = position.x;
			float y1 = position.y;
			float a = 0.9f;
			boolean down = false;
			k = 250;
			points = new Vector2[k];
		    dataSet = new Vector2[k];
		    w = 18f;
			//x2 = x1 + (w * Math.sin(a));
			//y2 = y1 + (w * Math.cos(a));
			for(int i = 0; i < k; i++){
		    	dataSet[i] = new Vector2((float) (x1 + (w * Math.cos(a))), (float) (y1 + (w * Math.sin(a))));
		    	x1 = (float) (x1 + (w * Math.sin(a)));
		    	y1 = (float) (y1 + (w * Math.cos(a)));
		    	counter++;
		        if(counter == 6){
		        	counter = 0;
		        	w = -w;
		        	if(!down){
		        		a = 1.309f;
		        		down = true;
		        	}
		        	else{
		        		a = 0.9f;
		        		down = false;
		        	}
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
	    if(current >= 1){
	    	e.finished = true;
	    }
	        //current -= 1;
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
