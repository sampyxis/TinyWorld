package com.propolis.game;

import java.awt.Color;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.propolis.base.Actor;
import com.propolis.base.TileEnvironment;
import com.propolis.enemies.BaseEnemy;
import com.propolis.enemies.BeeBullet;
import com.propolis.enemies.BeeMessanger;
import com.propolis.enemies.Beetle;
import com.propolis.enemies.QueenBee;
import com.propolis.entities.Flower;
import com.propolis.entities.HoneyPot;
import com.propolis.entities.HoneyToken;
import com.propolis.entities.LevelChange;
import com.propolis.entities.MessageBlock;
import com.propolis.entities.NewFlower;
import com.propolis.entities.Resin;
import com.propolis.entities.RoyalJelly;

public class Bee extends Actor{
	private SpriteSheet run; // fly
	private SpriteSheet jump; //
	private int frame;
	private int frameTimer = 0;
	private int frameInterval = 100;
	public int resin = 0;
	public int honey = 0;
	public int royal = 0;
	public int honey_tokens = 0;
	private boolean amHit = false;
	private float hitTimer=0f;
	
	private Sound gotHitSound;

	public Bee(float x, float y, float mass, float size) throws SlickException {
		super(x, y, mass, size);
		run = new SpriteSheet("res/bee_anim.png", 50,50);
		jump = new SpriteSheet("res/bee_jumping.png", 50,50);
		
		setName("Player");
		//body.setUserData("Player");
		gotHitSound = new Sound("res/got_hit.wav");
		hp=100;
	}
	
	public void render(Graphics g){
		SpriteSheet sheet = jump;
		int sx = 0;
		int sy =0 ;
		
		if(jumping()){
			sheet = jump;
			sx = 0;
			sy = 0;
		} else if(falling()){
			sheet = jump;
			sx = 2;
			sy = 0;
		} else if (moving() && onGround()){
			int f = (frame %6) + 1;
			sheet = run;
			sx = f % 3;
			sy = f/3;
		} else if (onGround() ){
			sheet = run;
			sx = 0;
			sy = 0;
		}
		
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
		
		checkCollissions();
		
		// If I've been hit - apply the force until I'm not hit
		if(amHit){
			// cool down
			hitTimer += delta;
			if(hitTimer <150){
				if(facingRight){
					//applyForce(-moveForce*20, 1000);
					body.addForce(new Vector2f(-moveForce*200,100));
					System.out.println("Hit, left?");
				} else {
					//applyForce(moveForce*20, 1000);
					body.addForce(new Vector2f(moveForce*200,100));
					System.out.println("Hit, right?");
				}	
			} else {
			hitTimer = 0;
			amHit=false;
			}
			


		}
	}
	@Override
	public void fire(TileEnvironment env) {
		if(facingRight){
			//env.addEntity(new BeeBullet(this.getX()*32, this.getY()*32, 1f, 24));
		}
	}
	public void gotHit(BaseEnemy hitter){
		// Who hit me?
		// Back up a few
			moving = true;
			amHit = true;
			gotHitSound.play();
	}
	
	// Need to make these generic
	public void checkCollissions()  {
		World world = this.getWorld();
		if(world==null){
			return;
		}
		
        CollisionEvent[] events = world.getContacts(body);
        
        for (int i=0;i<events.length;i++) {
        	if(events[i].getBodyB().getUserData() != null){
        		Object o =  events[i].getBodyB().getUserData();
        	//System.out.println("B: " + o.getClass().toString());
        	//System.out.println("A: " + events[i].getBodyA().getClass().getName().toString());
        	if(events[i].getBodyA().getUserData() != null){
        		Object a = events[i].getBodyA().getUserData();
        		//System.out.println("A: " +a.getClass().toString());
        	}
        	}
        	
        	// Ok - now do some work on the collisions
        	// Player is always getBodyB
        	Body colBody = events[i].getBodyA();
        	if(colBody != null){
        		// We've collided
        		Object obj = colBody.getUserData();
        		if(obj != null){
        			
        			// FLower
        			if ( obj.getClass() == com.propolis.entities.NewFlower.class){
        				System.out.println("Hit a flower");
        				NewFlower flow = (NewFlower) obj;
        				try {
							flow.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(flow);
        			}
        			// Level Change
        			if ( obj.getClass() == com.propolis.entities.LevelChange.class){
        				System.out.println("Hit a LevelChange");
        				LevelChange flow = (LevelChange) obj;
        				try {
							flow.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(flow);
        				return;
        				// Change levels
        				
        			}
        			
        			// Honey Pot
        			if ( obj.getClass() == com.propolis.entities.HoneyPot.class){
        				System.out.println("Hit a HoneyPot");
        				HoneyPot pot = (HoneyPot) obj;
        				try {
							pot.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(pot);
        			}
        			// Resin
        			if ( obj.getClass() == com.propolis.entities.Resin.class){
        				System.out.println("Hit Resin");
        				Resin resin = (Resin) obj;
        				try {
							resin.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(resin);
        			}
        			
        			// Royal Jelly Pot
        			if ( obj.getClass() == com.propolis.entities.RoyalJelly.class){
        				System.out.println("Hit a RoyalJelly ");
        				RoyalJelly jelly = (RoyalJelly) obj;
        				try {
							jelly.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(jelly);
        			}
        			// Honey Token
        			if ( obj.getClass() == com.propolis.entities.HoneyToken.class){
        				System.out.println("Hit a Honey Token");
        				HoneyToken honeyToken = (HoneyToken) obj;
        				try {
							honeyToken.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(honeyToken);
        			}
        			
        			// Message Bloc
        			if ( obj.getClass() == com.propolis.entities.MessageBlock.class) {
        				System.out.println("Hit a Message Block");
        				MessageBlock messageBlock = (MessageBlock) obj;
        				// Put the message up
        				InGameState.setPaused(true);
        				String message = InGameState.lvMessages.showMessage();
        				//InGameState.lvMessages.showMessage();
        				try {
							InGameState.showMessage(message);
							messageBlock.hit(this);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				getLevel().getLevelEnv().removeEntity(messageBlock);
        				return;
        			}
        			
        			// Message Bee
        			if ( obj.getClass() == com.propolis.enemies.BeeMessanger.class){
        				System.out.println("Hit a BeeMessanger");
        				BeeMessanger beeMessanger = (BeeMessanger) obj;
        				beeMessanger.gotHit(this);
        				getLevel().getLevelEnv().removeEntity(beeMessanger);
        				return;
        			}
        			// QueenBee
        			if ( obj.getClass() == com.propolis.enemies.QueenBee.class){
        				System.out.println("Hit a BeeMessanger");
        				QueenBee queenBee = (QueenBee) obj;
        				queenBee.gotHit(this);
        				getLevel().getLevelEnv().removeEntity(queenBee);
        				
        				return;
        			}
        			
        			// Beetle
        			if ( obj.getClass() == com.propolis.enemies.Beetle.class){
        				System.out.println("Hit a Beetle");
        				Beetle beetle = (Beetle) obj;
        				beetle.gotHit(this);
        			}
        			// Ant - they don't hurt us
        			if ( obj.getClass() == com.propolis.enemies.ants.class){
        				System.out.println("Hit a Beetle");
//        				Beetle beetle = (Beetle) obj;
//        				beetle.gotHit(this);
        				return;
        			}
        			
        		}
        	}
        	        	
        }
		
		
	}
	
	

}
