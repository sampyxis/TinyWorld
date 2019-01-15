package com.propolis.game;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.propolis.base.AbstractEntity;

/*
 * Really just a place holder - will use
 * pickups - enemies - maybe dropping bmombs
 * Need to make a generic one too
 */
public class Crate extends AbstractEntity {
	private Image image;
	private float width;
	private float height;
	private World world;

	public Crate(float x, float y, float width, float height, float mass) throws SlickException {
		this.width = width;
		this.height = height;
		image = new Image("res/crate.png");
		body = new Body(new Box(width, height), mass);
		body.setPosition(x, y);
		body.setFriction(0.1f);
		setName("Crate");
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
		g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
        image.draw(-width/2,-height/2,width,height);
        g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
        g.translate(-getX(), -getY());
		
	}

}
