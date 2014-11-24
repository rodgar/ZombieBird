package com.irgsoft.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.helpers.AssetLoader;

public class GameRenderer {

	private GameWorld gameWorld;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) {
		this.gameWorld = gameWorld;

		this.midPointY = midPointY;
		this.gameHeight = gameHeight;

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 136, 204);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(camera.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

	public void render(float runTime) {
		Gdx.app.log("GameRenderer", "render");

		Bird bird = gameWorld.getBird();

		// We draw a black background. This precents flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY * 66);

		// Draw Grass
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY * 66, 136, 11);

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY * 77, 136, 52);

		// End ShapeRenderer
		shapeRenderer.end();

		// Begin SpriteBatch
		batcher.begin();
		// Disable transparency
		// This is good for performance when drawing images that do not require
		// transparency.
		batcher.disableBlending();
		batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

		// The bird needs transparency
		batcher.enableBlending();
		// Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
		// Pass in the runTime variable to get the current frame.
		batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime),
				bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());

		// End SpriteBatch
		batcher.end();

	}

}
