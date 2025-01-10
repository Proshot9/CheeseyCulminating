package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import io.game.Main;

public class MainMenuScreen implements Screen {

	Main game;
	Texture logo;
	Texture text;
	Texture bg;
	
	int time = 0;
	boolean visible = true;

	public MainMenuScreen(Main game) {
		this.game = game;
	}

	@Override
	public void show() {
		logo = new Texture("ScamblingLogo.png");
		text = new Texture("AnyKeyText.png");
		bg = new Texture("bgColor.png");
		
		Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                visible = !visible; 
            }
        }, 0, 0.8f); // Start immediately, repeat every 0.5 seconds
    }
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		ScreenUtils.clear(0, 0, 1, 0);

		game.batch.begin();
		game.batch.draw(bg, 0,0, 2000,1000);
		game.batch.draw(logo, 270, 240);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			this.dispose();
			game.setScreen(new MainGameScreen(game));
		
		}
		
		if (visible) {
			game.batch.draw(text, 270, 50);
		}
		
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
