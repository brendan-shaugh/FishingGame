package com.bss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MadFishing extends Game {
	// used by all screens
	public SpriteBatch batcher;
	public ShapeRenderer shapeRender;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		shapeRender = new ShapeRenderer();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();

		getScreen().dispose();
	}
}