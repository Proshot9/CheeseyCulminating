package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import io.game.Main;

public class MainMenuScreen implements Screen {

	Main game;
	Texture logo;
	Texture text;
	Texture bg;
	Actor fade;

	SpriteBatch menu;

	int time = 0;
	boolean visible = true;

	public MainMenuScreen(Main game) {
		this.game = game;
	}

	@Override
	public void show() {
		logo = new Texture("ScamblingLogo.png");
		text = new Texture("AnyKeyTxt.png");
		bg = new Texture("bgColor.png");
		fade = new Actor();

		menu = new SpriteBatch();

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				visible = !visible;
			}
		}, 0, 0.8f); // Start immediately, repeat every 0.8 seconds

		fade.setColor(1f, 1f, 1f, 0f);

		fade.addAction(Actions.fadeIn(0.8f));
	}

	boolean isFade = false;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		ScreenUtils.clear(1, 1, 1, 1);

		// game.batch.begin();
		menu.begin();
		menu.draw(bg, 0, 0, 1080, 720);
		if (!isFade) {
			menu.draw(logo, 270, 240);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			isFade = true;
			this.dispose();

		}

		if (isFade) {
			fade.act(Gdx.graphics.getDeltaTime());
			menu.setColor(fade.getColor());

			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					game.setScreen(new MainGameScreen(game)); // switches from the main menu to the main game once they
																// press any key

				}
			}, 1f);
		}

		if (visible && !isFade) {
			menu.draw(text, 290, 150, 500, 200);
		}
		menu.end();
		// game.batch.end();
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
