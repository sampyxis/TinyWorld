package com.propolis.entities;

import org.newdawn.slick.SlickException;

public class NewFlower extends BaseEntity {

	public NewFlower(float x, float y, float width, float height, float mass, String img) throws SlickException {
		super(x, y, width, height, mass, img);
		setName("New Flower");
		this.body.setMoveable(false);
		// TODO Auto-generated constructor stub
		
		setImage("res/flower.png");
	}

}
