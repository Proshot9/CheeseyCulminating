package io.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player {
	private int Health;
	

	public float speed = 2f; //controls the players movement speed
	public float playerX = 0; //the players x coordinate on the 2d scene
	public float playerY = 0; //the players y coordinate on the 2d scene

	Player(int Health) {
		this.Health = Health;
	}

	public int getHealth() {
		return Health;
	}

	
	public void playerMovement() {
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			playerY+= Gdx.graphics.getDeltaTime() + speed; 

			System.out.println("W pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			playerX-= Gdx.graphics.getDeltaTime() + speed; 

			System.out.println("A pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			playerY-= Gdx.graphics.getDeltaTime() + speed; 
			System.out.println("S pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			playerX+= Gdx.graphics.getDeltaTime() + speed; 
			System.out.println("D pressed");
		}
	}

	public void dispose() {

	}
}
