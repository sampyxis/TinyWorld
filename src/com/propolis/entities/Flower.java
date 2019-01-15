package com.propolis.entities;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.propolis.base.AbstractEntity;

public class Flower extends AbstractEntity {
	private Image image;
	private float width;
	private float height;
	private World world;

	public Flower(float x, float y, float width, float height, float mass) throws SlickException {
		this.width = width;
		this.height = height;
		image = new Image("res/flower.png");
		body = new Body(new Box(width, height), mass);
		body.setPosition(x, y);
		body.setFriction(0.1f);
		body.setMoveable(false);
		setName("Flower");
		body.setUserData(this);
		
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
		//g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
        image.draw(-width/2,-height/2,width,height);
        //g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
        g.translate(-getX(), -getY());
		
	}

}
