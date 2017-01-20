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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class MainMenuScreen extends ScreenAdapter {
	MadFishing game;
	OrthographicCamera guiCam;
	FitViewport fitVP;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;

	public MainMenuScreen (MadFishing game) {
		this.game = game;
		
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480/2, 800/2, 0);
		fitVP = new FitViewport(480, 800, guiCam);
		soundBounds = new Rectangle(240-32, 550, 64, 64);
		playBounds = new Rectangle(10, 400, 460, 100);
		highscoresBounds = new Rectangle(10, 250, 460, 100);
		helpBounds = new Rectangle(10, 100, 460, 100);
		touchPoint = new Vector3();
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			fitVP.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new HighscoresScreen(game));
				return;
			}
			if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new HelpScreen(game));
				return;
			}
			if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				//Settings.soundEnabled = !Settings.soundEnabled;
				Assets.setSound(!Assets.getSound());
				if (Assets.getSound())
					Assets.music.play();
				else
					Assets.music.pause();
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0f, 0f, 0f, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);

		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0, 480, 800);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.logo, 90, 600, 300, 200);
		game.batcher.draw(Assets.play, 160, 400, 160, 100);
		game.batcher.draw(Assets.highscores, 80, 250, 320, 100);
		game.batcher.draw(Assets.help, 35, 100, 400, 100);
		//turn into help button
		game.batcher.draw(Assets.getSound() ? Assets.soundOn : Assets.soundOff, 240-32, 550, 64, 64);
		game.batcher.end();	
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}

	@Override
	public void pause () {
		//Settings.save();
		//need new save
	}
	
	@Override
	public void resize(int width, int height){
		fitVP.update(width, height);
	}
}
