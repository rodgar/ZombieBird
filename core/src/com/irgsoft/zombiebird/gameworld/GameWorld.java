package com.irgsoft.zombiebird.gameworld;

import sun.security.jgss.GSSCaller;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameobjects.ScrollHandler;
import com.irgsoft.zombiebird.helpers.AssetLoader;

public class GameWorld {
	
	public enum GameState {
		READY, RUNNING, GAMEOVER
	}

	private Bird bird;
	private ScrollHandler scrollHandler;
	private Rectangle ground;
	private int score = 0;
	private GameState currentState;
	private int midPointY;
	
	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		bird = new Bird(33, midPointY - 5, 17, 12);
		// The grass should start 66 points below the midPointY
		scrollHandler = new ScrollHandler(this, midPointY + 66);
		
		ground = new Rectangle(0, midPointY + 66, 136, 11);
		this.midPointY = midPointY;
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
		}
		
		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
			scrollHandler.stop();
			bird.die();
			bird.decelerate();
			currentState = GameState.GAMEOVER;
		}
	}
	
	public void update(float delta) {
		switch (currentState) {
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
		default:
			updateRunning(delta);
			break;
		}
	}
	
	public void updateReady(float delta) {
		
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int increment) {
		score += increment;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scrollHandler.onRestart();
		currentState = GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}
}
