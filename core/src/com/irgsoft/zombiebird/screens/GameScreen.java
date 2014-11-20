package com.irgsoft.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.irgsoft.zombiebird.gameworld.GameRenderer;
import com.irgsoft.zombiebird.gameworld.GameWorld;

public class GameScreen implements Screen {
	
	private static final String TAG = "GameScreen";
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;

	public GameScreen() {
		Gdx.app.log(TAG, "attached");
		
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld));
	}

	@Override
	public void render(float delta) {
		// Sets a color to fill the screen width (RGB = 10, 15, 230), Opacity of 1 (100%)
//		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
		// Fills the screen with the selected color
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Convert frame rate to String, print it
//		Gdx.app.log("GameScreen FPS", (1/delta) + "");
		
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
