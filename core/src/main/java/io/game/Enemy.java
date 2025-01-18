package io.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {

	SpriteBatch batch;
	Texture maggotLeft = new Texture("MaggotWalkLeft.png");
	Texture maggotRight = new Texture("MaggotWalkRight.png");

	private static final int col = 4, row = 1;

	private float enemyX;
	private float enemyY;

	private float startX = enemyX;
	private float startY = enemyY;

	private float time = 0;
	
	

	public Enemy(int enemyX, int enemyY) {
		this.enemyX = enemyX;
		this.enemyY = enemyY;
		startX = enemyX;
		startY = enemyY;
	}

	public TextureRegion[] maggotAnimationLeft(Texture image) {

		TextureRegion[][] maggotMoveLeft = TextureRegion.split(image, image.getWidth() / col, image.getHeight() / row);

		TextureRegion[] maggotFramesLeft = new TextureRegion[row * col];

		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				maggotFramesLeft[index++] = maggotMoveLeft[i][j];

			}
		}

		return maggotFramesLeft;

	}

	public TextureRegion[] maggotAnimationRight(Texture image) {

		TextureRegion[][] maggotMoveRight = TextureRegion.split(image, image.getWidth() / col, image.getHeight() / row);

		TextureRegion[] maggotFramesRight = new TextureRegion[row * col];

		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				maggotFramesRight[index++] = maggotMoveRight[i][j];

			}
		}

		return maggotFramesRight;

	}

	Animation<TextureRegion> maggotRightAni = new Animation<TextureRegion>(0.2f, maggotAnimationLeft(maggotLeft));
	Animation<TextureRegion> maggotLeftAni = new Animation<TextureRegion>(0.2f, maggotAnimationRight(maggotRight));
	
	Random random = new Random();
	boolean moveLeft = random.nextBoolean();
	public void spawnEnemy(Main game) {
		time += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrameLeft = maggotLeftAni.getKeyFrame(time, true);
		TextureRegion currentFrameRight = maggotRightAni.getKeyFrame(time, true);

		//System.out.println(moveLeft + " " + enemyX + " " + (startX - 100));

		if (!moveLeft) {
			enemyX += 0.2;
			
			game.batch.draw(currentFrameLeft, enemyX, enemyY);
			if (enemyX >= (startX + 100)) {
				moveLeft = true;
			}

		}
		if (moveLeft) {
			enemyX -= 0.2;
			game.batch.draw(currentFrameRight, enemyX, enemyY);
			if (enemyX <= (startX - 100)) {
				moveLeft = false;
			}

		}

	}
}
