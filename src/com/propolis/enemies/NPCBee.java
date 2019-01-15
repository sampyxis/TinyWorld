package com.propolis.enemies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.propolis.game.Bee;
import com.propolis.game.InGameState;

public class NPCBee extends BaseEnemy {
	private SpriteSheet resting;
	private int frame;
	private int frameTimer = 0;
	private int frameInterval = 200;
	private Image image;
	
	public NPCBee(float x, float y, float mass, float size)
	throws SlickException {
super(x, y, mass, size);

	resting = new SpriteSheet("res/talk_to_bee.png", 50,50);
	//attack = new SpriteSheet("res/Beetle am.png", 64,22);
	hp=100;
	moveForce = 100f;
	}
	
	@Override	
	public void render(Graphics g){
	int sx = 0;
	int sy =0 ;
	// Just doing so I see something
	int f = (frame %6) + 1;
	sx = f % 3;
	sy = f/3;	
	
	SpriteSheet sheet = resting;
	image = sheet.getSprite(sx,sy);
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
	
	
	
	
	public void gotHit(Bee player){
	System.out.println("Hit the Bee");

	return;
	}
	
}
