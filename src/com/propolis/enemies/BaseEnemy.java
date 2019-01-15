package com.propolis.enemies;

import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.propolis.base.Actor;
import com.propolis.entities.NewFlower;
import com.propolis.game.Bee;

public class BaseEnemy extends Actor {

	protected float moveForce = 100;
	protected boolean amHit=false;
	protected float hitTimer = 0f;
	protected Sound gotHitSound;
	protected Sound deadSound;
	protected Sound aliveSound;
	protected boolean playedDeadSound = false;
	
	public BaseEnemy(float x, float y, float mass, float size) throws SlickException {
		super(x, y, mass, size);
		gotHitSound = new Sound("res/got_hit.wav");
		
	}

	@Override
	public void render(Graphics g) {
		
	}
	
	// Need to make these generic
	public void checkCollissions()  {
		World world = this.getWorld();
		if(world==null){
			return;
		}
		
        CollisionEvent[] events = world.getContacts(body);
        
        for (int i=0;i<events.length;i++) {
        	if(events[i].getBodyA().getUserData() != null){
        		Object o =  events[i].getBodyA().getUserData();
        	//System.out.println("B: " + o.getClass().toString());
        	//System.out.println("A: " + events[i].getBodyA().getClass().getName().toString());
        	if(events[i].getBodyB().getUserData() != null){
        		Object a = events[i].getBodyB().getUserData();
        		//System.out.println("A: " +a.getClass().toString());
        	}
        	}
        	
        	// Ok - now do some work on the collisions
        	// Player is always getBodyB
        	Body colBody = events[i].getBodyB();
        	if(colBody != null){
        		// We've collided
        		Object obj = colBody.getUserData();
        		if(obj != null){
        			
        			// Player
        			if ( obj.getClass() == com.propolis.game.Bee.class){
        				System.out.println("Beetle Hit the Player");
        				Bee bee = (Bee) obj;
        				bee.gotHit(this);
        				
        			}
        		}
        	}
        }
	}
	

}
