package com.irgsoft.zombiebird.gameworld;

import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameobjects.ScrollHandler;


public class GameWorld {

	// Game objects
	private Bird bird;
	private ScrollHandler scrollHandler;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		// The grass should start 66 points below the midPointY
		scrollHandler = new ScrollHandler(midPointY + 66);
	}
	
	public void update(float delta) {
		bird.update(delta);
		scrollHandler.update(delta);
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ScrollHandler getScrollHandler() {
		return scrollHandler;
	}
}
