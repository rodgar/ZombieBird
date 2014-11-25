package com.irgsoft.zombiebird.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameobjects.ScrollHandler;
import com.irgsoft.zombiebird.helpers.AssetLoader;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scrollHandler;
	private Rectangle ground;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		// The grass should start 66 points below the midPointY
		scrollHandler = new ScrollHandler(midPointY + 66);
		
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}
	
	public void update(float delta) {
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
		}
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
