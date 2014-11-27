package com.irgsoft.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.irgsoft.zombiebird.gameworld.GameRenderer;
import com.irgsoft.zombiebird.gameworld.GameWorld;
import com.irgsoft.zombiebird.helpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld gameWorld;
	private GameRenderer gameRenderer;

	private float runTime = 0;

	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointY = (int) gameHeight / 2;

		gameWorld = new GameWorld(midPointY);
		Gdx.input.setInputProcessor(new InputHandler(gameWorld, screenWidth
				/ gameWidth, screenHeight / gameHeight));
		gameRenderer = new GameRenderer(gameWorld, (int) gameHeight, midPointY);
		gameWorld.setRenderer(gameRenderer);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		gameWorld.update(delta);
		gameRenderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
