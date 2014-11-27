package com.irgsoft.zombiebird.helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.irgsoft.zombiebird.gameobjects.Bird;
import com.irgsoft.zombiebird.gameworld.GameWorld;
import com.irgsoft.zombiebird.ui.SimpleButton;

public class InputHandler implements InputProcessor {

	private GameWorld gameWorld;
	private Bird bird;

	private List<SimpleButton> menuButtons;

	private SimpleButton playButton;

	private float scaleFactorX;
	private float scaleFactorY;

	public InputHandler(GameWorld gameWorld, float scaleFactorX,
			float scaleFactorY) {
		this.gameWorld = gameWorld;
		this.bird = gameWorld.getBird();

		int midPointY = gameWorld.getMidPointY();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(
				136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
				midPointY + 50, 29, 16, AssetLoader.playButtonUp,
				AssetLoader.playButtonDown);
		menuButtons.add(playButton);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        
        if (gameWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        } else if (gameWorld.isReady()) {
        	gameWorld.start();
        	bird.onClick();
        } else if (gameWorld.isRunning()) {
        	bird.onClick();
        }

		if (gameWorld.isGameOver() || gameWorld.isHighScore()) {
			gameWorld.restart();
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (gameWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
            	gameWorld.ready();
                return true;
            }
        }

        return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (gameWorld.isMenu()) {
            	gameWorld.ready();
            } else if (gameWorld.isReady()) {
            	gameWorld.start();
            }

            bird.onClick();

            if (gameWorld.isGameOver() || gameWorld.isHighScore()) {
            	gameWorld.restart();
            }

        }

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

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

}
