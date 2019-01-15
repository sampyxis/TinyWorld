package com.propolis.enemies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.propolis.game.Bee;
import com.propolis.game.InGameState;

public class QueenBee extends BaseEnemy {
	private SpriteSheet fly; //
	private SpriteSheet attack;
	private SpriteSheet resting;
	private boolean atRest = true;
	private boolean attacking  = false;
	private boolean flying = false;
	
	private int frame;
	private int frameTimer = 0;
	private int frameInterval = 300;
	

	public QueenBee(float x, float y, float mass, float size)
			throws SlickException {
		super(x, y, mass, size);
		fly = new SpriteSheet("res/queen_anim.png", 80,50);
		//attack = new SpriteSheet("res/queen_jumping.png", 80,50);
		resting = new SpriteSheet("res/queen_anim.png", 80,50);
	}
	
	@Override
	public void render(Graphics g){
	SpriteSheet sheet = fly;
	int sx = 0;
	int sy =0 ;
	
	if(atRest){
		sheet = resting;
		sx = 0;
		sy = 0;
	}
	// Just doing so I see something
		int f = (frame %6) + 1;
		sx = f % 3;
		sy = f/3;
	
	Image image = sheet.getSprite(sx,sy);
	// Start flipping him because he's opposite on the sprite sheet
	image = image.getFlippedCopy(true, false);
	
	if(facingRight()){
		image = image.getFlippedCopy(true, false);
	}
	
	image.drawCentered(getX(), getY());
	}

	
	public void preUpdate(int delta){
		super.preUpdate(delta);
		
		frameTimer -= delta;
		while(frameTimer < 0 ){
			frame++;
			frameTimer += frameInterval;
		}
	}
	
	
	public void update(int delta){
		super.update(delta);
	}
	
	public void gotHit(Bee player){
		System.out.println("Hit the Bee");
		// Put the message up
		InGameState.setPaused(true);
		String message = InGameState.lvMessages.showMessage();
		//InGameState.lvMessages.showMessage();
		try {
			InGameState.showMessage(message);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
