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

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bss.game.World.WorldListener;

public class GameScreen extends ScreenAdapter {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;

	MadFishing game;

	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;
	Vector2 touch;
	long totalTimeStart = 0l, totalTimeEnd = 0l, totalTime = 0l;
	long pauseStartTime = 0l, pauseEndTime = 0l, totalPauseTime = 0l;

	GlyphLayout glyphLayout = new GlyphLayout();
	FitViewport scaleVP;

	public GameScreen (MadFishing game) {
		this.game = game;

		state = GAME_READY;
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480 / 2, 800 / 2, 0);
		scaleVP = new FitViewport(480, 800, guiCam);
		touchPoint = new Vector3();
		worldListener = new WorldListener() {
			@Override
			public void jump () {
				//Assets.playSound(Assets.jumpSound);
			}

			@Override
			public void highJump () {
				//Assets.playSound(Assets.highJumpSound);
			}

			@Override
			public void hit () {
				//Assets.playSound(Assets.hitSound);
			}

			@Override
			public void coin () {
				//Assets.playSound(Assets.coinSound);
			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(game.batcher, game.shapeRender, world, guiCam);
		pauseBounds = new Rectangle(480 - 64, 800 - 64, 64, 64);
		resumeBounds = new Rectangle(100, 500, 300, 100);
		quitBounds = new Rectangle(165, 250, 150, 100);
		lastScore = 0;
		scoreString = "SCORE: 0";
	}

	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
			totalTimeStart = TimeUtils.millis();
		}
	}

	private void updateRunning (float deltaTime) {
		if (Gdx.input.justTouched()) {
			scaleVP.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				state = GAME_PAUSED;
				pauseStartTime = TimeUtils.millis();
				return;
			}
			else if(touchPoint.x > 150f  && !world.hook.hookingNow){
				//world.makeHook(touchPoint.x, touchPoint.y);
				//calculate the sin or whatever angle and send it to world
				//and maybe pass it a true
				touch = new Vector2(touchPoint.x, touchPoint.y);
				world.castHook(touchPoint.x, touchPoint.y, touch);
			}
			else if(touchPoint.x < 97f  && !world.hook.hookingNow){
				//touch = new Vector2(touchPoint.x, touchPoint.y);
				world.moveSlappy(touchPoint.y);
			}
		}
		
		world.update(deltaTime);
		
		totalTimeEnd = TimeUtils.millis();
		totalTime = (totalTimeEnd - totalTimeStart) - totalPauseTime;
		if(totalTime > Assets.getHighScore()){
			Assets.setHighScore(totalTime);
		}
		
		if (world.state == World.WORLD_STATE_GAME_OVER) {
			state = GAME_OVER;
		}
	}

	private void updatePaused () {
		if (Gdx.input.justTouched()) {
			scaleVP.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (resumeBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				state = GAME_RUNNING;
				pauseEndTime = TimeUtils.millis();
				totalPauseTime += pauseEndTime - pauseStartTime;
				return;
			}

			if (quitBounds.contains(touchPoint.x, touchPoint.y)) {
				totalTimeEnd = TimeUtils.millis();
				totalTime = (totalTimeEnd - totalTimeStart) - totalPauseTime;
				if(totalTime > Assets.getHighScore()){
					Assets.setHighScore(totalTime);
				}
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private void updateGameOver () {
		//play an ad
		//set high score
		totalTimeEnd = TimeUtils.millis();
		totalTime = (totalTimeEnd - totalTimeStart) - totalPauseTime;
		if(totalTime > Assets.getHighScore()){
			Assets.setHighScore(totalTime);
		}
		
		if (Gdx.input.justTouched()) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0f, 0f, 0f, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		game.batcher.end();
	}

	private void presentReady () {
		game.batcher.draw(Assets.ready, 165, 350, 250, 100);
	}

	private void presentRunning () {
		game.batcher.draw(Assets.pause, 480 - 32, 800 - 32, 32, 32);
		//Assets.font.draw(game.batcher, scoreString, 16, 480 - 20);
	}

	private void presentPaused () {
		game.batcher.draw(Assets.resume, 100, 500, 300, 100);
		game.batcher.draw(Assets.quit, 165, 250, 150, 100);
		//Assets.font.draw(game.batcher, scoreString, 16, 480 - 20);
	}

	private void presentGameOver () {
		//play ad here i believe
		game.batcher.draw(Assets.gameOver, (480/2) - 75, (800/2) - 100, 200, 150);
		//glyphLayout.setText(Assets.font, scoreString);
		//Assets.font.draw(game.batcher, scoreString, 160 - glyphLayout.width / 2, 480 - 20);
	}

	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}

	@Override
	public void pause () {
		if (state == GAME_RUNNING) state = GAME_PAUSED;
	}
	
	@Override
	public void resize(int width, int height){
		scaleVP.update(width, height);
	}
}