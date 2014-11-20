package com.irgsoft.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;

public class GameRenderer {
	
	private GameWorld gameWorld;

	public GameRenderer(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public void render() {
		Gdx.app.log("GameRenderer", "render");
	}

}
