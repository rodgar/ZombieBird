package com.irgsoft.zombiebird.gameworld;

import com.irgsoft.zombiebird.gameobjects.Bird;


public class GameWorld {

	private Bird bird;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
	}
	
	public void update(float delta) {
		bird.update(delta);
	}
	
	public Bird getBird() {
		return bird;
	}
	
}
