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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;

public class WorldRenderer {
	//static final float FRUSTUM_WIDTH = 10;
	//static final float FRUSTUM_HEIGHT = 15;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	Affine2 af2;
	ShapeRenderer shapeRender;
	
	private Sprite hook, slappy;
	private float location;
	private float location2;
	private NinePatch greenHealth, redHealth;

	public WorldRenderer (SpriteBatch batch, ShapeRenderer shapeRender, World world, OrthographicCamera guiCam) {
		this.world = world;
		this.cam = guiCam;
		this.cam.position.set(480 / 2, 800 / 2, 0);
		this.batch = batch;
		this.shapeRender = shapeRender;
		
		hook = new Sprite(Assets.hook2);
		hook.setOriginCenter();
		slappy = new Sprite(Assets.slappySeal);
		
		greenHealth = new NinePatch(Assets.greenPatch, 0, 0, 0, 0);
		redHealth = new NinePatch(Assets.redPatch, 0, 0, 0, 0);
	}

	public void render () {
		//if (world.bob.position.y > cam.position.y) cam.position.y = world.bob.position.y;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		/*shapeRender.setProjectionMatrix(cam.combined);
		shapeRender.begin(ShapeRenderer.ShapeType.Line);
		shapeRender.ellipse(world.trout.hitBox.x, world.trout.hitBox.y, world.trout.hitBox.width, world.trout.hitBox.height);
		shapeRender.circle(world.hook.circleBounds.x, world.hook.circleBounds.y, world.hook.circleBounds.radius);
		shapeRender.point(world.trout.position.x, world.trout.position.y, 0f);*/
		renderObjects();
		
	}

	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - 480 / 2, cam.position.y - 800 / 2, 480,
			800);
		batch.end();
	}

	public void renderObjects () {
		batch.enableBlending();
		batch.begin();
		renderText();
		renderHealth();
		renderLine();
		renderSlappy();
		renderHook();
		renderEnemies();
		/*renderPlatforms();
		renderItems();
		renderSquirrels();
		renderCastle();*/
		batch.end();
		shapeRender.end();
	}

	private void renderSlappy() {
		switch(world.hook.state){
		/*case Hook.GOING_UP:
			batch.draw(Assets.slappySeal, -10f, world.hook.position.y - 150f, 125f, 250f);
			break;
		case Hook.GOING_DOWN:
			batch.draw(Assets.slappySeal, -10f, world.hook.position.y - 150f, 125f, 250f);
			break;*/
		case Hook.STILL:
			//batch.draw(Assets.slappySeal, -10f, world.hook.position.y - 150f, 125f, 250f);
			slappy.setPosition(-15f, world.hook.position.y - 155f);
			slappy.draw(batch);
			break;
		case Hook.MOVING:
			//batch.draw(Assets.slappySeal, -10f, world.hook.position.y - 150f, 125f, 250f);
			slappy.setPosition(-15f, world.hook.position.y - 155f);
			slappy.draw(batch);
			break;
		case Hook.HOOKING:
			//batch.draw(Assets.slappySeal, -10f, world.slappy.position.y, 125f, 250f);
			slappy.setPosition(-15f, world.slappy.position.y);
			slappy.draw(batch);
			break;
		/*default:
			batch.draw(Assets.slappySeal, -10f, world.hook.position.y - 150f, 125f, 250f);
			break;*/
		}
		
	}

	private void renderHook(){
		//batch.draw(Assets.hook, world.slappy.position.x+135, world.slappy.position.y+160, 45, 45);
		//batch.draw(Assets.hook, world.slappy.position.x+135, world.slappy.position.y+160, 0, 0, 45, 45, 1, 1, 0f);
		hook.setPosition(world.hook.position.x, world.hook.position.y);
		hook.setRotation(world.hook.rotation);
		hook.draw(batch);
	}
	
	private void renderLine(){
		switch(world.hook.state){
		case Hook.HOOKING:
			//batch.draw(Assets.line, world.slappy.position.x + 100f, world.slappy.position.y + 170f, 5f, 0f, 10f, (world.hook.position.y - world.slappy.position.y) + (world.hook.position.x - world.slappy.position.x) -90f, 1, 1, world.hook.rotation -135f);
			shapeRender.begin(ShapeRenderer.ShapeType.Line);
			shapeRender.setColor(Color.BLACK);
			location = hook.getVertices()[batch.X2];
			location2 = hook.getVertices()[batch.Y2];
			shapeRender.line(world.slappy.position.x + 100f, world.slappy.position.y +180f, world.hook.position.x - world.offsetX, world.hook.position.y - world.offsetY);
			break;
		}
	}
	
	private void renderEnemies(){
		int len = world.enemies.size();
		for (int i = 0; i < len; i++) {
			world.enemies.get(i).s.setPosition(world.enemies.get(i).position.x, world.enemies.get(i).position.y);
			world.enemies.get(i).s.draw(batch);
		}
		/*world.boot.s.setPosition(world.boot.position.x, world.boot.position.y);
		world.boot.s.setRotation(world.boot.angle);
		world.boot.s.draw(batch);
		world.trout.s.setPosition(world.trout.position.x, world.trout.position.y);
		world.trout.s.setRotation(world.trout.angle);
		world.trout.s.draw(batch);*/
	}
	
	private void renderText(){
		Assets.font.draw(batch, world.layout, 280f - world.layout.width, 800f - world.layout.height);
	}
	
	private void renderHealth(){
		redHealth.draw(batch, 10f, 780f, 70f, 15f);
		greenHealth.draw(batch, 10f, 780f, 7f * world.sizeOfHealth, 15f);
	}
}
