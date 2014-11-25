package com.irgsoft.zombiebird.helpers;

import com.badlogic.gdx.InputProcessor;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	
	private GameWorld gameWorld;
	private Bird bird;
	
	public InputHandler(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.bird = gameWorld.getBird();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (gameWorld.isReady()) {
			gameWorld.start();
		}
		
		bird.onClick();
		
		if (gameWorld.isGameOver()) {
			gameWorld.restart();
		}
		
		return true;	// Return true to say we handled the touch.
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
