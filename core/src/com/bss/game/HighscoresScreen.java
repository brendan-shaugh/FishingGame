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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HighscoresScreen extends ScreenAdapter {
	MadFishing game;
	OrthographicCamera guiCam;
	Rectangle backBounds;
	Vector3 touchPoint;
	String highScores;
	float xOffset = 0;
	FitViewport fitVP;
	GlyphLayout glyphLayout = new GlyphLayout(Assets.font, "" + Assets.getHighScore()/1000l + " seconds", Color.YELLOW, 0f, 0, false);

	public HighscoresScreen (MadFishing game) {
		this.game = game;

		guiCam = new OrthographicCamera(480f, 800f);
		guiCam.position.set(480f / 2f, 800f / 2f, 0);
		fitVP = new FitViewport(480f, 800f, guiCam);
		backBounds = new Rectangle(0, 100f, 150f, 75f);
		touchPoint = new Vector3();
		highScores = "" + Assets.getHighScore();
		
		
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			fitVP.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (backBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
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
		game.batcher.draw(Assets.backgroundRegion, 0, 0, 480f, 800f);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		/*game.batcher.draw(Assets.highScoresRegion, 10, 360 - 16, 300, 33);

		float y = 230;
		for (int i = 4; i >= 0; i--) {
			Assets.font.draw(game.batcher, highScores[i], xOffset, y);
			y += Assets.font.getLineHeight();
		}

		*/
		game.batcher.draw(Assets.arrowFlipped, 0f, 100f, 150f, 50f);
		Assets.font.draw(game.batcher, glyphLayout, 350f, 600f);
		game.batcher.end();
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}
	
	@Override
	public void resize(int width, int height){
		fitVP.update(width, height);
	}
}
