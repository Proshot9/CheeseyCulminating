package io.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Animator  {
	
	private static final int col = 3, row = 1;
	
    SpriteBatch batch;
    Texture image;
    Animation<TextureRegion> walkAnimation; 
	Texture walkSheet;
    
	float time;
    
    

    public void create() {
        batch = new SpriteBatch();
        walkSheet = new Texture("spritesheet.png");
        
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
        		walkSheet.getWidth() / col,
        		walkSheet.getHeight() / row);
        
        TextureRegion[] walkFrames = new TextureRegion[col * row];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		
		walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);
		
		time =0;
    }

    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        
        time += Gdx.graphics.getDeltaTime(); 
        TextureRegion currentFrame = walkAnimation.getKeyFrame(time, true);
        
        
        
        batch.begin();
        batch.draw(currentFrame, 50, 50); 
        batch.end();
    	  
       }
    

    public void dispose() {
        batch.dispose();
        walkSheet.dispose();
    }
    
}

