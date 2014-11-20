package com.irgsoft.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.irgsoft.zombiebird.gameworld.GameRenderer;
import com.irgsoft.zombiebird.gameworld.GameWorld;

public class GameScreen implements Screen {
	
	private static final String TAG = "GameScreen";
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;

	public GameScreen() {
		Gdx.app.log(TAG, "attached");
		
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld);
	}

	@Override
	public void render(float delta) {
		gameWorld.update(delta);
		gameRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(TAG, "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log(TAG, "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log(TAG, "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log(TAG, "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log(TAG, "resume called");
	}

	@Override
	public void dispose() {
		
	}

}
