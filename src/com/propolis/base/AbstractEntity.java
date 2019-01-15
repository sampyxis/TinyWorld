package com.propolis.base;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;

/*
 * from Kevin Glass
 * 
 * The common parts of entities
 */
public abstract class AbstractEntity implements Entity{
	
	protected Body body;
	private String name = "Base";
	protected int hp=100;
	protected boolean alive=true;
	
	//Sets the velocity
	public void setVelocity(float x, float y){
		Vector2f vec = new Vector2f(body.getVelocity());
		vec.scale(-1);
		body.adjustVelocity(vec);
		body.adjustVelocity(new Vector2f(x,y));
	}
	
	public void setUserData(Entity entity){
		body.setUserData(this);
	}
	
	public void setX(float x){
		body.setPosition(x, getY());
	}

	public void setY(float y){
		body.setPosition(getX(), y);
	}
	
	public void setPosition(float x, float y){
		body.setPosition(x,y);
	}
	
	public float getX(){
		return body.getPosition().getX();
	}
	
	public float getY(){
		return body.getPosition().getY();
	}
	
	public float getVelX(){
		return body.getVelocity().getX();
	}
	
	public float getVelY(){
		return body.getVelocity().getY();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
