package com.propolis.base;

import org.newdawn.slick.Graphics;

import com.propolis.levels.Level;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;


/*
 * From Kevin Glass
 * 
 * Actor handles the platformer
 */
public abstract class Actor extends AbstractEntity{
	
	protected static final int MAX_JUMP_VEL = 50;
	protected World world;
	protected Level level;
	
	protected boolean onGround = false;
	protected int offGroundTimer = 0;
	protected boolean jumped=false;
	protected boolean facingRight = true;
	protected boolean moving = false;
	protected boolean falling = false;
	protected boolean flying = false;
	protected float moveForce = 100f;
	

	
	//size of bounds
	private float size;
	private float velx;
	
	public Actor(float x, float y, float mass, float size){
		this.size = size;
		
		body = new Body(new Box(size, size), mass);
		body.setUserData(this);
		body.setRestitution(0);
		body.setFriction(0f);
		body.setMaxVelocity(20,50);
		
		body.setUserData(this);
		
		// ?? Do we want this to be rotable?  Maybe
		body.setRotatable(false);
		setPosition(x,y);
		
		setName("Actor");
		
	}
	
	public void stopSound(){
		
	}
	public void setLevel(Level level){
		this.level  = level;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public World getWorld(){
		if(world == null){
			return null;
		} else {
			return world;
		}
	}
	
	public void applyForce(float x, float y){
		body.addForce(new Vector2f(x,y));
		
		// jumping
		if(y < 0){
			jumped = true;
		}
		
		// if changed direction - stop the x velocity
		if( x > 0 ){
			if(!facingRight){
				setVelocity(0, getVelY());
			}
			facingRight = true;
		}
		if( x < 0 ){
			if(facingRight){
				setVelocity(0, getVelY());
			}
			facingRight = false;
		}
	}
	
	// are we moving
	public void setMoving(boolean moving){
		this.moving = moving;
	}

	public Body getBody(){
		return body;
	}
	
	public boolean facingRight(){
		return facingRight;
	}
	
	public void setFlying(boolean fly){
		flying = fly;
	}
	
	public boolean falling(){
		return falling;
	}
	
	public boolean flying(){
		return flying;
	}
	
	public boolean moving(){
		return moving;
	}
	
	public boolean jumping(){
		return jumped;
	}
	
	public void preUpdate(int delta){
		if(!moving){
			setVelocity(0, getVelY());
		}
		falling = (getVelY() > 0) && (!onGround());
		velx = getVelX();
	}
	
	public void update(int delta){
		// Check for aliviness
		if(hp<=0){
			alive=false;
			moveForce=0;
		}
		
		boolean on = onGroundImpl(body);
		if(!on){
			offGroundTimer += delta;
			if(offGroundTimer >100){
				onGround = false;
			}
		} else {
			offGroundTimer = 0;
			onGround = true;
			
			// on ground - not flying
			flying = false;
		}
		
		// if we're getting pushed back from a collission = kill velocity
		if((getVelX() > 0) && (!facingRight)){
			velx = 0;
		}
		if((getVelX() < 0 ) && (facingRight)){
			velx = 0;
		}
		
		setVelocity(velx, getVelY());
		body.setGravityEffected(!on);
		
		// clamp y
		if( getVelY() < - MAX_JUMP_VEL){
			setVelocity(getVelX(), -MAX_JUMP_VEL);
		}
		
		// handle jumping as opposed to be moving up
		if((!jumped) && (getVelY() <0)){
			setVelocity(getVelX(), getVelY() * 0.95f);
		}
		
		if(jumped){
			if(getVelY() >= 0){
				jumped = false;
			}
		}
		
		if(flying){
			//System.out.println("Flying...");
			setVelocity(getVelX(), getVelY() * 1.2f);
			flying = false;
		}
	}
	
	public abstract void render(Graphics g);
	
	public boolean onGround(){
		return onGround;
	}
	
	protected boolean onGroundImpl(Body body){
		if(world == null){
			return false;
		}
		   
        // loop through the collision events that have occured in the
        // world
        CollisionEvent[] events = world.getContacts(body);
        
        for (int i=0;i<events.length;i++) {
        	// ok - deal with the collission
        	
                // if the point of collision was below the centre of the actor
                // i.e. near the feet
                if (events[i].getPoint().getY() > getY()+(size/3)) {
                        // check the normal to work out which body we care about
                        // if the right body is involved and a collision has happened
                        // below it then we're on the ground
                        if (events[i].getNormal().getY() < -0.5) {
                                if (events[i].getBodyB() == body) {
                                        //System.out.println(events[i].getPoint()+","+events[i].getNormal());
                                        return true;
                                }
                        }
                        if (events[i].getNormal().getY() > 0.5) {
                                if (events[i].getBodyA() == body) {
                                       //System.out.println(events[i].getPoint()+","+events[i].getNormal() +" "+this.getClass().getName());
                                        return true;
                                }
                        }
                }
        }
        
        return false;
	}

	public void fire(TileEnvironment levelEnv) {
		// TODO Auto-generated method stub
		
	}
	
}
