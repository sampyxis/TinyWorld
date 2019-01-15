package com.propolis.enemies;

import net.phys2d.math.Vector2f;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.propolis.game.Bee;
import com.propolis.game.InGameState;

public class ZombieBee extends BaseEnemy {

	private SpriteSheet attack;
	private SpriteSheet dead;
	private SpriteSheet walking;
	private boolean attacking  = false;
	
	private int frame;
	private int frameTimer = 0;
	private int frameInterval = 200;
	private Image image;
	
	

	//private float moveForce;

	public ZombieBee(float x, float y, float mass, float size)
			throws SlickException {
		super(x, y, mass, size);

		attack = new SpriteSheet("res/beetle.png", 66,33);
		dead = new SpriteSheet("res/beetle_dead.png", 66,33);
		walking = new SpriteSheet("res/Z-bee walking.png", 45,68 );
		//attack = new SpriteSheet("res/Beetle am.png", 64,22);
		// These guys are fast!
		hp=100;
		moveForce = 250f;
		deadSound = new Sound("res/dead_beetle.wav");
		aliveSound = new Sound("res/BeeOrWaspNoise.wav");

	}
	
	@Override
	public void render(Graphics g){
		SpriteSheet sheet;
		if (!alive){
			sheet = dead;
		} else {
			sheet = walking;
		}
	int sx = 0;
	int sy =0 ;
	// Just doing so I see something
		int f =  (frame %3) ;
		sx = f % 3;
		sy = f/3;	
	
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
	
	
	public void update(int delta){
		super.update(delta);
		// Check for aliviness
		if(hp<=0){
			alive=false;
			moveForce=0;
			if(playedDeadSound = false){
				deadSound.play();
				playedDeadSound = true;
			}
		}
		
		if(alive){
			if(!aliveSound.playing()){
				//aliveSound.playAt(1f,.5f,getX(), getY(), 0);
			}
		}
		// Move towards the player -
		
		// only can move left or right - so we'll do that
		// If we're signifigantly above or below the player - it doens't matter - and just choose random
		//if( InGameState.player.getY() < ){ - do this later
			if( InGameState.player.getX() > this.getX()) {
				this.applyForce(moveForce, 0);
			} else {
				this.applyForce(-moveForce, 0);
			}
		//}
		
		checkCollissions();
		// If I've been hit - apply the force until I'm not hit
	
		if(amHit){
			// cool down
			hitTimer += delta;
			if(hitTimer <150){
				hp=hp-1; // Way too much
				if(facingRight){
					//applyForce(-moveForce*20, 1000);
					body.addForce(new Vector2f(-moveForce*200,100));
					System.out.println("Hit, left? " + hp);
				} else {
					//applyForce(moveForce*20, 1000);
					body.addForce(new Vector2f(moveForce*200,100));
					System.out.println("Hit, right? " + hp);
				}	
			} else {
			hitTimer = 0;
			amHit=false;
			}
		}
			
	}
	
	public void gotHit(Bee player){
		amHit=true;
		if(alive){
			gotHitSound.play();
		}
		
	}
	
	public void stopSound(){
		deadSound.stop();
		aliveSound.stop();
	}

}
