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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HelpScreen5 extends ScreenAdapter {
	MadFishing game;

	OrthographicCamera guiCam;
	Rectangle nextBounds;
	Vector3 touchPoint;
	Texture helpImage;
	TextureRegion helpRegion;
	FitViewport scaleVP;

	public HelpScreen5 (MadFishing game) {
		this.game = game;
		guiCam = new OrthographicCamera(480, 800);
		guiCam.position.set(480 / 2, 800 / 2, 0);
		scaleVP = new FitViewport(480, 800, guiCam);
		nextBounds = new Rectangle(480 - 150, 100, 150, 100);
		touchPoint = new Vector3();
		helpImage = Assets.loadTexture("help5.png");
		helpRegion = new TextureRegion(helpImage, 0, 0, 480, 800);
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			scaleVP.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (nextBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();

		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(helpRegion, 0, 0, 480, 800);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.arrow, 480, 100, -150, 100);
		game.batcher.end();

		gl.glDisable(GL20.GL_BLEND);
	}

	@Override
	public void render (float delta) {
		draw();
		update();
	}

	@Override
	public void hide () {
		helpImage.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		scaleVP.update(width, height);
	}
}