package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import io.game.Main;
import io.game.Player;

public class MainGameScreen implements Screen {

	private static final int col = 2, row = 1;
	Texture image;
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	Texture shopFront;
	Texture playerIdle;

	Rectangle player_rect;
	Rectangle shopFront_rect;

	float time;
	Player player = new Player(100);
	Main game;

	public MainGameScreen(Main game) {
		this.game = game;
	}

	@Override
	public void show() {

		playerIdle = new Texture("playerIdle.png");
		walkSheet = new Texture("playerSprite_Scale0.4.png");
		shopFront = new Texture("shopFront.png");

		player_rect = new Rectangle(player.playerX, player.playerY,0,0);
		shopFront_rect = new Rectangle(500+25,500+50, shopFront.getWidth()-50, shopFront.getHeight()-50);

		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / col, walkSheet.getHeight() / row);

		// grabs the
		TextureRegion[] walkFrames = new TextureRegion[col * row];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		walkAnimation = new Animation<TextureRegion>(0.2f, walkFrames);

		time = 0;

	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		Gdx.gl.glClearColor(1, 1, 0, 0);
		ScreenUtils.clear(1, 1, 0, 0);
		
		if (shopFront_rect.overlaps(player_rect)) {
			player.playerX = player.prevx;
			player.playerY = player.prevy; 
			System.out.println("Player collided with shopfront");
		}

		player.playerMovement();

		time += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(time, true);
		
		

		game.batch.draw(shopFront, 500,500);
		game.batch.draw(currentFrame, player.playerX, player.playerY);
		

		
		 
		player_rect = new Rectangle(player.playerX, player.playerY, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
		shopFront_rect = new Rectangle(500+25,500+50, shopFront.getWidth()-50, shopFront.getHeight()-50);

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
		game.batch.dispose();
		walkSheet.dispose();
	}

}
