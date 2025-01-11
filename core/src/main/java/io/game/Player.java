package io.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player {
	private int Health;
	

	public float speed = 2f; //controls the players movement speed
	public float playerX; //the players x coordinate on the 2d scene
	public float playerY; //the players y coordinate on the 2d scene
	
	//creates a new variable for where the player was before it was moved // Used for collisions
	public float prevx ;
	public float prevy ;

	public Player(int Health) {
		this.Health = Health;
	}

	public int getHealth() {
		return Health;
	}

	//Controls the players movement with the keys "W", "A", "S", "D"
	public void playerMovement() {
		System.out.println(prevx + " " + prevy + " " + playerX+ " " + playerY);
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			prevy = playerY;
			playerY+= Gdx.graphics.getDeltaTime() + speed;   //gets time from last frame and adds speed

			//System.out.println("W pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			prevx = playerX;
			playerX-= Gdx.graphics.getDeltaTime() + speed; 

			//System.out.println("A pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			prevy = playerY;
			playerY-= Gdx.graphics.getDeltaTime() + speed; 
			
			
			//System.out.println("S pressed");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			prevx = playerX;
			playerX+= Gdx.graphics.getDeltaTime() + speed; 
			
			
			//System.out.println("D pressed");
		}
	}
	

	
	public void dispose() {

	}
}
