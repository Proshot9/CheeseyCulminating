package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;

import io.game.Cost;
import io.game.Enemy;
import io.game.Main;
import io.game.Player;

public class MainGameScreen implements Screen {

	// rolling system variables
	int level = 1;
	int gold = 100;
	roll gacha = new roll();
	int buy = 0;
	String rarity;

	// defines the amount of columns and rows there are within the player animation
	// spritesheet
	private static final int col = 2, row = 1;

	// animation
	Animation<TextureRegion> walkAnimation;
	Animation<TextureRegion> walkAnimationLeft;

	// Player Textures/animation sheets
	Texture walkSheet;
	Texture walkSheetMirrored;
	Texture shopFront;
	Texture playerIdle;
	Texture playerIdleFlipped;

	// player/object collision hitboxes
	Rectangle player_rect;
	Rectangle shopFront_rect;
	Rectangle shopFront_interact;

	// for in-game text
	BitmapFont font;

	// an actor for the fade in transition between the main menu and main game
	// screen
	Actor fade;

	float time;

	// used for the main class
	Main game;

	// used for the map
	TiledMap map;

	// the camera system
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;

	// the mainGameScreen constructor
	public MainGameScreen(Main game) {
		this.game = game;

	}

	// creates player object (hp, passes the main class)
	Player player = new Player(100, game);
	Enemy enemy = new Enemy(400,300);

	@Override
	public void show() {

		// loads the map
		map = new TmxMapLoader().load("MainGameMap.tmx");

		// creates the objects for the camera system
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();

		// the fade in transition
		fade = new Actor();
		fade.setColor(1f, 1f, 1f, 0f);
		fade.addAction(Actions.fadeIn(1.5f)); // adds the fadeIn action to the fade actor

		// creates a font/text
		font = new BitmapFont(Gdx.files.internal("font.fnt"));

		// player sprite/animation sheets
		walkSheet = new Texture("playerWalk0.19.png");
		walkSheetMirrored = new Texture("playerWalk0.19_flipped.png");

		// objects within the game
		shopFront = new Texture("shopFront0.5.png");

		// player/object hitboxes
		player_rect = new Rectangle(player.playerX, player.playerY, 0, 0);
		shopFront_rect = new Rectangle(460, 490, shopFront.getWidth(), shopFront.getHeight() - 30);
		shopFront_interact = new Rectangle(460, 460, 60f, 5f);

		// player animation
		TextureRegion[][] walking = TextureRegion.split(walkSheet, walkSheet.getWidth() / col,
				walkSheet.getHeight() / row);
		TextureRegion[][] walkingLeft = TextureRegion.split(walkSheetMirrored, walkSheetMirrored.getWidth() / col,
				walkSheetMirrored.getHeight() / row);

		TextureRegion[] walkFrames = new TextureRegion[col * row];
		TextureRegion[] walkFramesLeft = new TextureRegion[col * row];
		
		
		//enemy
		

		// gets the texture region for each frame by going through the top row then
		// moves down a row once it reached the last column until its finished
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

		// creates the animation objects (the speed to play the animation, pass the list
		// of texture regions as keyframes)
		walkAnimation = new Animation<TextureRegion>(0.3f, walkFrames);
		walkAnimationLeft = new Animation<TextureRegion>(0.3f, walkFramesLeft);

		time = 0;

	}

	@Override
	public void render(float delta) {
		// activates the sequence of events added to the fade actor
		fade.act(Gdx.graphics.getDeltaTime());
		game.batch.setColor(fade.getColor());

		game.batch.begin();
		// clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		ScreenUtils.clear(0, 0, 0, 0);

		// updates the cameras position to the players location
		camera.position.set(player.playerX, player.playerY, 0);
		camera.update();

		// this renders the camera/viewport
		renderer.setView(camera);
		renderer.render();

		if (shopFront_interact.overlaps(player_rect)) {
			int gain = gold - 100;
			font.getData().setScale(0.2f);
			// displays text
			font.draw(game.batch, "E to Interact", 469f, 515f);
			font.draw(game.batch, "Gold:" + " " + gold, 550f, 450f);
			font.draw(game.batch, "profits: " + gain, 550f, 420f);
			font.draw(game.batch, "Level: " + level + " -" + Cost.percent(level) + " per chest", 550f, 405f);
			if (Gdx.input.isKeyJustPressed(Input.Keys.E) && (gold >= 10)) {
				// rolling for rarity
				buy = gacha.buy();
				// given sell price per chest
				gold -= Cost.percent(level);
				// depends on level depends on rarity
				rarity = Cost.rates(level, buy);
				gold = gold + Cost.rarity(rarity);
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
				int a = Cost.upgrade(gold, level);
				level += 1;
				System.out.print(a);
			}

			font.draw(game.batch, "Rarity: " + rarity, 550f, 435f);
		}

		// shopfront collision detection
		if (shopFront_rect.overlaps(player_rect)) {
			player.playerX = player.prevx;
			player.playerY = player.prevy;
		}

		// the world border
		player.playerBorder();

		// player movement
		player.playerMovement();

		time += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(time, true);
		TextureRegion currentFrameLeft = walkAnimationLeft.getKeyFrame(time, true);

		game.batch.draw(shopFront, 460, 460);

		// draws the player movement animations from the player class
		player.playerAnimation(currentFrame, currentFrameLeft, game);

		// updates the players hitbox
		player_rect = new Rectangle(player.playerX, player.playerY, player.playerIdle.getWidth(),
				player.playerIdle.getHeight());
		
		enemy.spawnEnemy(game);
		enemy.enemyMove(game);

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// controls the camera zoom
		camera.viewportHeight = height / 3f;
		camera.viewportWidth = width / 3f;

		camera.update();
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
		map.dispose();
		renderer.dispose();

	}

}