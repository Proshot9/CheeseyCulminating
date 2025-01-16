package io.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player {
	public int Health;

	// starting player speed and coordinates
	public float speed = 1f; // controls the players movement speed
	public float playerX = 480; // the players x coordinate on the 2d scene
	public float playerY = 160; // the players y coordinate on the 2d scene

	// creates a new variable for where the player was before it was moved // Used
	// for collisions
	public float prevx = 480;
	public float prevy = 160;

	// variables for movement
	public boolean isMovingLeft = false;
	public boolean isMoving = false;
	
	//textures
	public Texture playerIdle = new Texture("playerIdle0.38.png");
	public Texture playerIdleFlipped = new Texture("playerIdle0.38_flipped.png");
	
	//the player constructor
	public Player(int Health, Main game) {
		this.Health = Health;
		
	}
	
	
	// Controls the players movement with the keys "W", "A", "S", "D"
	public void playerMovement() {
		isMoving = false;
		System.out.println(prevx + " " + prevy + " " + playerX + " " + playerY + " " + isMovingLeft);

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			isMoving = true;
			;
			prevy = playerY;
			playerY += Gdx.graphics.getDeltaTime() + speed; // gets time from last frame and adds speed

			// System.out.println("W pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			isMoving = true;
			;
			isMovingLeft = true;
			prevx = playerX;
			playerX -= Gdx.graphics.getDeltaTime() + speed;

			// System.out.println("A pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			isMoving = true;
			;
			prevy = playerY;
			playerY -= Gdx.graphics.getDeltaTime() + speed;

			// System.out.println("S pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			isMoving = true;
			;
			isMovingLeft = false;
			prevx = playerX;
			playerX += Gdx.graphics.getDeltaTime() + speed;

			// System.out.println("D pressed");
		}
	}
	
	
	//draws the player movement depending on direction
	public void playerAnimation(TextureRegion currentFrame, TextureRegion currentFrameLeft, Main game) {
		
		if (!isMoving && !isMovingLeft) {
			game.batch.draw(playerIdle, playerX, playerY);

		} else if (!isMoving && isMovingLeft) {
			game.batch.draw(playerIdleFlipped, playerX, playerY);

		} else if (isMovingLeft && isMoving) {
			game.batch.draw(currentFrame, playerX, playerY);
		} else if (!isMovingLeft && isMoving) {
			game.batch.draw(currentFrameLeft, playerX, playerY);
		}
		
	}
	
	//the worlds border
	public void playerBorder() {
		if (playerX < 20 ||playerX > 1000|| playerY > 680 || playerY < 20) {
			playerX = prevx;
			playerY = prevy;
		}
	}

	public void dispose() {

	}
}
