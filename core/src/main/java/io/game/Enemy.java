package io.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Enemy {
	
	SpriteBatch batch;
	Texture maggotWalk = new Texture("MaggotWalk.png");
	
	
	private static final int col = 4, row=4;
	
	private float enemyX;
	private float enemyY;
	
	public int startX;
	public int startY;
	
	private float time = 0;
	
	public Enemy(int enemyX, int enemyY){
		this.enemyX = enemyX;
		this.enemyY = enemyY;
	}
	
	
	
	
	
	public Animation[] maggotAnimation(Texture image) {
		
		TextureRegion[][] maggotMoveLeft = TextureRegion.split(image, image.getWidth()/col ,image.getHeight()/row);

		
		TextureRegion[] maggotFramesLeft = new TextureRegion[row];
		Animation[] anim = new Animation[col];
		
		
		
		int index = 0;
		for (int i=0;i<row;i++) {
			for (int j = 0; j < col; j++) {

				maggotFramesLeft[index++] = maggotMoveLeft[i][j];

			}
			anim[i] = new Animation(0.2f,maggotFramesLeft);
		}
	
		System.out.println(maggotFramesLeft);
		return anim;
		
	}
	
	
	boolean moveLeft = false;
	public void enemyMove(Main game) {
		
	}
	
	public void spawnEnemy(Main game) {
		
		if (!moveLeft) {
			enemyX+=0.2;
			time += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrameLeft = maggotAnimation(maggotWalk).getKeyFrame(time, true);
			game.batch.draw(currentFrameLeft, enemyX, enemyX);
			if (enemyX>=500) {
				moveLeft = true;
			}
		}
		if (moveLeft) {
			enemyX-=0.2;
			time += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrameLeft = maggotLeftAni.getKeyFrame(time, true);
			game.batch.draw(currentFrameLeft, enemyX, enemyX);
			if(enemyX<=300) {
				moveLeft = false;
			}
		}
		
		
	}
}
