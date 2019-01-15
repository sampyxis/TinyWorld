 package com.propolis.entities;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.propolis.base.AbstractEntity;
import com.propolis.game.Bee;

/*
 * Base entity - box, pickup, crate,etc.
 */
public class BaseEntity extends AbstractEntity {
	private Image image;
	private float width;
	private float height;
	private World world;
    private Sound pickupSound;
    private String soundLocation = "";
    
	public BaseEntity(float x, float y, float width, float height, float mass, String img) throws SlickException {
		this.width = width;
		this.height = height;
		if(img.contains("")){
			img = "res/blank.png";
		}
		image = new Image(img);
		body = new Body(new Box(width, height), mass); 
		body.setPosition(x, y);
		body.setFriction(0.1f);
		
		setName("Base Entity");
		body.setUserData(this);
		
		// Default pickup sounds
		soundLocation = "res/ItemPickup.wav";
	}
	
	public void setImage(String img) throws SlickException{
		Image newImage = new Image(img);
		image = newImage;
	}
	
	@Override
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}

	@Override
	public void preUpdate(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
        image.draw(-width/2,-height/2,width,height);
        g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
        g.translate(-getX(), -getY());
		
	}
	
	public void playSound() throws SlickException{
		pickupSound = new Sound(soundLocation);
		pickupSound.play();
	}
	
	public void hit(Bee player) throws SlickException{
		// When the player hits the entity - call this and take care of things
		
	}
}
