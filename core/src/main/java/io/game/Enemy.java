package io.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Enemy {
	
	SpriteBatch batch;
	public Texture maggot;
	
	
	public int enemyX;
	public int enemyY;
	
	public int prevEnemyX;
	public int prevEnemyY;
	
	
	public void enemyMove() {
		if (enemyX > prevEnemyX + 10) {
			enemyX+=1;
		}
		else if (enemyY > prevEnemyY +10) {
			enemyY-=1;
		}
	}
	
	public void show() {
		maggot = new Texture("Maggot.png");
		batch = new SpriteBatch();
		
		
	
	}

	public void render() {
		batch.begin();
		batch.draw(moveLeft, enemyX, enemyY);
		batch.end();
		
	}

	public void dispose() {
		maggot.dispose();
		batch.dispose();
	}
}
