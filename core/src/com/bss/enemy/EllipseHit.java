package com.bss.enemy;

public class EllipseHit {

	float innerPolygonCoef[];
	float outerPolygonCoef[];
	int maxIterations;
	
	public EllipseHit(int maxIterations) {
	    this.maxIterations = maxIterations;
	    innerPolygonCoef = new float[maxIterations+1];
	    outerPolygonCoef = new float[maxIterations+1];
	    for (int t = 0; t <= maxIterations; t++) {
	      int numNodes = 4<<t;
	      innerPolygonCoef[t] = (float) (0.5/Math.cos(4*Math.acos(0.0)/numNodes));
	      outerPolygonCoef[t] = (float) (0.5/(Math.cos(2*Math.acos(0.0)/numNodes)*Math.cos(2*Math.acos(0.0)/numNodes)));
	    }
	  }

	
	// Test for collision between an ellipse of horizontal radius w and vertical radius h at (x0, y0) and
	  // a circle of radius r at (x1, y1)
	  public boolean collide(float x0, float y0, float w, float h, float x1, float y1, float r) {

		  float x = Math.abs(x1 - x0);
		  float y = Math.abs(y1 - y0);

		    if (x*x + (h - y)*(h - y) <= r*r || (w - x)*(w - x) + y*y <= r*r || x*h + y*w <= w*h
		        || ((x*h + y*w - w*h)*(x*h + y*w - w*h) <= r*r*(w*w + h*h) && x*w - y*h >= -h*h && x*w - y*h <= w*w)) {
		      return true;
		    } else {
		      if ((x-w)*(x-w) + (y-h)*(y-h) <= r*r || (x <= w && y - r <= h) || (y <= h && x - r <= w)) {
		        return iterate(x, y, w, 0, 0, h, r*r);
		      }
		      return false;
		    }
	  }

	
	private boolean iterate(float x, float y, float c0x, float c0y, float c2x, float c2y, float rr) {
		for (int t = 1; t <= maxIterations; t++) {
		      float c1x = (c0x + c2x)*innerPolygonCoef[t];
		      float c1y = (c0y + c2y)*innerPolygonCoef[t];
		      float tx = x - c1x;
		      float ty = y - c1y;
		      if (tx*tx + ty*ty <= rr) {
		        return true;
		      }
		      float t2x = c2x - c1x;
		      float t2y = c2y - c1y;
		      if (tx*t2x + ty*t2y >= 0 && tx*t2x + ty*t2y <= t2x*t2x + t2y*t2y &&
		          (ty*t2x - tx*t2y >= 0 || rr*(t2x*t2x + t2y*t2y) >= (ty*t2x - tx*t2y)*(ty*t2x - tx*t2y))) {
		        return true;
		      }
		      float t0x = c0x - c1x;
		      float t0y = c0y - c1y;
		      if (tx*t0x + ty*t0y >= 0 && tx*t0x + ty*t0y <= t0x*t0x + t0y*t0y &&
		          (ty*t0x - tx*t0y <= 0 || rr*(t0x*t0x + t0y*t0y) >= (ty*t0x - tx*t0y)*(ty*t0x - tx*t0y))) {
		        return true;
		      }    
		      float c3x = (c0x + c1x)*outerPolygonCoef[t];
		      float c3y = (c0y + c1y)*outerPolygonCoef[t];
		      if ((c3x-x)*(c3x-x) + (c3y-y)*(c3y-y) < rr) {
		        c2x = c1x;
		        c2y = c1y;
		        continue;
		      }
		      float c4x = c1x - c3x + c1x;
		      float c4y = c1y - c3y + c1y;
		      if ((c4x-x)*(c4x-x) + (c4y-y)*(c4y-y) < rr) {
		        c0x = c1x;
		        c0y = c1y;
		        continue;
		      }
		      float t3x = c3x - c1x;
		      float t3y = c3y - c1y;
		      if (ty*t3x - tx*t3y <= 0 || rr*(t3x*t3x + t3y*t3y) > (ty*t3x - tx*t3y)*(ty*t3x - tx*t3y)) {
		        if (tx*t3x + ty*t3y > 0) {
		          if (Math.abs(tx*t3x + ty*t3y) <= t3x*t3x + t3y*t3y || (x-c3x)*(c0x-c3x) + (y-c3y)*(c0y-c3y) >= 0) {
		            c2x = c1x;
		            c2y = c1y;
		            continue;
		          }
		        } else if (-(tx*t3x + ty*t3y) <= t3x*t3x + t3y*t3y || (x-c4x)*(c2x-c4x) + (y-c4y)*(c2y-c4y) >= 0) {
		          c0x = c1x;
		          c0y = c1y;
		          continue;
		        }
		      }
		      return false;
		    }
		    return false; // Out of iterations so it is unsure if there was a collision. But have to return something.
	}
	
}
