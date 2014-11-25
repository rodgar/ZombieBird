package com.irgsoft.zombiebird.gameworld;

import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameobjects.ScrollHandler;
import com.irgsoft.zombiebird.helpers.AssetLoader;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scrollHandler;
	private boolean isAlive = true;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		// The grass should start 66 points below the midPointY
		scrollHandler = new ScrollHandler(midPointY + 66);
	}
	
	public void update(float delta) {
		bird.update(delta);
		scrollHandler.update(delta);
		
		if (isAlive && scrollHandler.collides(bird)) {
			// Clean up on game over
			scrollHandler.stop();
			AssetLoader.dead.play();
			isAlive = false;
		}
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
