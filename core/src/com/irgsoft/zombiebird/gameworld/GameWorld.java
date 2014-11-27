package com.irgsoft.zombiebird.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameobjects.ScrollHandler;
import com.irgsoft.zombiebird.helpers.AssetLoader;

public class GameWorld {

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	private Bird bird;
	private ScrollHandler scrollHandler;
	private Rectangle ground;
	private int score = 0;
	private float runTime;
	private int midPointY;
	private GameRenderer renderer;

	private GameState currentState;

	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		this.midPointY = midPointY;
		bird = new Bird(33, midPointY - 5, 17, 12);
		// The grass should start 66 points below the midPointY
		scrollHandler = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}
	}

	public void updateReady(float delta) {
		bird.updateReady(runTime);
		scrollHandler.updateReady(delta);
	}

	public void updateRunning(float delta) {
		// Add a delta cap so that if our game takes too long
		// to update, we will not break our collision detection.

		if (delta > .15f) {
			delta = .15f;
		}

		bird.update(delta);
		scrollHandler.update(delta);

		if (scrollHandler.collides(bird) && bird.isAlive()) {
			// Clean up on game over
			scrollHandler.stop();
			bird.die();
			AssetLoader.dead.play();
			renderer.prepareTransition(255, 255, 255, .3f);

			AssetLoader.fall.play();
		}

		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
			if (bird.isAlive()) {
				AssetLoader.dead.play();
				renderer.prepareTransition(255, 255, 255, .3f);

				bird.die();
			}
			scrollHandler.stop();
			bird.decelerate();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}

	public Bird getBird() {
		return bird;
	}

	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}

	public int getMidPointY() {
		return midPointY;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scrollHandler.onRestart();
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
}
