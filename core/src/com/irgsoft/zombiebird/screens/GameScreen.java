package com.irgsoft.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.irgsoft.zombiebird.gameworld.GameRenderer;
import com.irgsoft.zombiebird.gameworld.GameWorld;
import com.irgsoft.zombiebird.helpers.InputHandler;

public class GameScreen implements Screen {
	
	private static final String TAG = "GameScreen";
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;
	
	private float runTime = 0;

	public GameScreen() {
		Gdx.app.log(TAG, "attached");
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		
		int midPointY = (int) gameHeight / 2;
		
		gameWorld = new GameWorld(midPointY);
		gameRenderer = new GameRenderer(gameWorld, (int) gameHeight, midPointY);
		
		Gdx.input.setInputProcessor(new InputHandler(gameWorld.getBird()));
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		gameWorld.update(delta);
		gameRenderer.render(runTime);
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
