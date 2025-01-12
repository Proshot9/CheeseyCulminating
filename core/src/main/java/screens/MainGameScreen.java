package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;

import io.game.Main;
import io.game.Player;

public class MainGameScreen implements Screen {
	
	roll buy = new roll();

	private static final int col = 2, row = 1;
	Texture image;
	Animation<TextureRegion> walkAnimation;
	Animation<TextureRegion> walkAnimationLeft;
	Texture walkSheet;
	Texture walkSheetMirrored;
	Texture shopFront;
	Texture playerIdle;
	Texture playerIdleFlipped;

	Rectangle player_rect;
	Rectangle shopFront_rect;
	Rectangle shopFront_interact;

	BitmapFont font;
	OrthographicCamera font_cam;

	Actor fade;
	float time;
	Player player = new Player(100);
	Main game;

	public MainGameScreen(Main game) {
		this.game = game;
	}

	@Override
	public void show() {

		fade = new Actor();
		fade.setColor(1f, 1f, 1f, 0f);
		fade.addAction(Actions.fadeIn(4f)); // adds the fadeIn action to the fade actor

		font = new BitmapFont(Gdx.files.internal("font.fnt"));

		playerIdle = new Texture("playerIdle.png");
		playerIdleFlipped = new Texture("playerIdle_Flipped.png");
		walkSheet = new Texture("playerSprite_Scale0.4.png");
		walkSheetMirrored = new Texture("playerSprite_Scale0.4_Flipped.png");

		shopFront = new Texture("shopFront.png");

		player_rect = new Rectangle(player.playerX, player.playerY, 0, 0);
		shopFront_rect = new Rectangle(500, 500, shopFront.getWidth(), shopFront.getHeight());
		shopFront_interact = new Rectangle(485, 485, 60f, 15f);

		TextureRegion[][] walking = TextureRegion.split(walkSheet, walkSheet.getWidth() / col,
				walkSheet.getHeight() / row);
		TextureRegion[][] walkingLeft = TextureRegion.split(walkSheetMirrored, walkSheetMirrored.getWidth() / col,
				walkSheetMirrored.getHeight() / row);

		TextureRegion[] walkFrames = new TextureRegion[col * row];
		TextureRegion[] walkFramesLeft = new TextureRegion[col * row];

		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				walkFramesLeft[index++] = walking[i][j];

			}

		}

		int index2 = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				walkFrames[index2++] = walkingLeft[i][j];

			}
		}

		walkAnimation = new Animation<TextureRegion>(0.3f, walkFrames);
		walkAnimationLeft = new Animation<TextureRegion>(0.3f, walkFramesLeft);

		time = 0;

	}

	@Override
	public void render(float delta) {
		fade.act(Gdx.graphics.getDeltaTime());
		game.batch.setColor(fade.getColor());

		game.batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		ScreenUtils.clear(0, 0, 0, 0);

		if (shopFront_interact.overlaps(player_rect)) {
			font.getData().setScale(0.4f);
			font.draw(game.batch, "E to Interact", 468f, 575f);
			if (Gdx.input.isKeyJustPressed(Input.Keys.E))
			System.out.println(String.valueOf(buy.setRarity(buy.buy())));
			
		}

		// shopfront collision detection
		if (shopFront_rect.overlaps(player_rect)) {
			player.playerX = player.prevx;
			player.playerY = player.prevy;
		}

		// the world border
		if (player.playerX < 0 || player.playerX > 1000 || player.playerY > 645 || player.playerY < 0) {
			player.playerX = player.prevx;
			player.playerY = player.prevy;
		}

		player.playerMovement();

		time += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(time, true);
		TextureRegion currentFrameLeft = walkAnimationLeft.getKeyFrame(time, true);

		game.batch.draw(shopFront, 460, 460);

		// determines which player state to display
		if (!player.isMoving && !player.isMovingLeft) {
			game.batch.draw(playerIdle, player.playerX, player.playerY);

		} else if (!player.isMoving && player.isMovingLeft) {
			game.batch.draw(playerIdleFlipped, player.playerX, player.playerY);

		} else if (player.isMovingLeft && player.isMoving) {
			game.batch.draw(currentFrame, player.playerX, player.playerY);
		} else if (!player.isMovingLeft && player.isMoving) {
			game.batch.draw(currentFrameLeft, player.playerX, player.playerY);
		}

		player_rect = new Rectangle(player.playerX, player.playerY, playerIdle.getWidth(), playerIdle.getHeight());
		shopFront_rect = new Rectangle(485, 525, shopFront.getWidth() - 50, shopFront.getHeight() - 65);

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