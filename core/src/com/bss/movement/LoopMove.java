package com.bss.movement;

import java.util.Random;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.bss.enemy.Enemy;
import com.bss.enemy.EnemyGameObject;

public class LoopMove extends Movement {

	CatmullRomSpline<Vector2> myCatmull;
	Vector2[] points, dataSet;
	public Vector2 first, second, sendBack;
	int counter = 1;
	int k = 7;
	float w;
	private Random r;
	float speed = 0.15f;
    float current = 0f;
    public float place = 0f, t = 0f;
    private EnemyGameObject e;
	
	public LoopMove(Vector2 position, EnemyGameObject e) {
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
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x -1f, position.y -1f);
	    	dataSet[2] = new Vector2(position.x - 25f, 600f);
	    	dataSet[3] = new Vector2(position.x + 25f, 450f);
	    	dataSet[4] = new Vector2(position.x - 30f, 300f);
	    	dataSet[5] = new Vector2(position.x +5f, -100f);
	    	dataSet[6] = new Vector2(position.x + 10f, -100f);
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
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x -1f, position.y -1f);
	    	dataSet[2] = new Vector2(position.x - 25f, 600f);
	    	dataSet[3] = new Vector2(position.x + 25f, 450f);
	    	dataSet[4] = new Vector2(position.x - 30f, 300f);
	    	dataSet[5] = new Vector2(position.x +5f, -100f);
	    	dataSet[6] = new Vector2(position.x + 10f, -100f);
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
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x + 1f, position.y + 1f);
	    	dataSet[2] = new Vector2(position.x + 25f, 300f);
	    	dataSet[3] = new Vector2(position.x - 25f, 450f);
	    	dataSet[4] = new Vector2(position.x + 30f, 600f);
	    	dataSet[5] = new Vector2(position.x -5f, 800f);
	    	dataSet[6] = new Vector2(position.x - 10f, 850f);
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
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x + 1f, position.y + 1f);
	    	dataSet[2] = new Vector2(position.x + 25f, 300f);
	    	dataSet[3] = new Vector2(position.x - 25f, 450f);
	    	dataSet[4] = new Vector2(position.x + 30f, 600f);
	    	dataSet[5] = new Vector2(position.x -5f, 800f);
	    	dataSet[6] = new Vector2(position.x - 10f, 850f);
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else if(position.y >= 400f && position.x >= 480f){
			points = new Vector2[k];
		    dataSet = new Vector2[k];
		    float theY = position.y / 5f;
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x - 1f, position.y - 1f);
	    	dataSet[2] = new Vector2(position.x - 25f - 80f, position.y - theY);
	    	dataSet[3] = new Vector2(position.x + 25f -160f, position.y - (theY*2f));
	    	dataSet[4] = new Vector2(position.x - 30f - 240f, position.y - (theY*3f));
	    	dataSet[5] = new Vector2(position.x +5f - 240f, -100f);
	    	dataSet[6] = new Vector2(position.x + 10f - 240f, -100f);
		    myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
		    for(int i = 0; i < k; ++i)
		    {
		        points[i] = new Vector2();
		        myCatmull.valueAt(points[i], ((float)i)/((float)k-1));
		    }
		}
		else{
			points = new Vector2[k];
		    dataSet = new Vector2[k];
		    float theY = (800f -position.y) / 5f;
		    dataSet[0] = new Vector2(position);
		    dataSet[1] = new Vector2(position.x - 1f, position.y + 1f);
	    	dataSet[2] = new Vector2(position.x + 25f - 80f, position.y + theY);
	    	dataSet[3] = new Vector2(position.x - 25f - 160f, position.y + (theY*2f));
	    	dataSet[4] = new Vector2(position.x + 30f - 240f, position.y + (theY*3f));
	    	dataSet[5] = new Vector2(position.x -5f - 240f, 800f);
	    	dataSet[6] = new Vector2(position.x - 10f - 240f, 850f);
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
	    	current -= 1;
	    	e.finished = true;
	    }
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
