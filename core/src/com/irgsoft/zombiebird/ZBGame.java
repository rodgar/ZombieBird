package com.irgsoft.zombiebird;

import com.badlogic.gdx.Game;
import com.irgsoft.zombiebird.helpers.AssetLoader;
import com.irgsoft.zombiebird.screens.SplashScreen;

public class ZBGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
