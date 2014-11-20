package com.irgsoft.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.irgsoft.zombiebird.screens.GameScreen;

public class ZBGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "created");
		setScreen(new GameScreen());
	}

}
