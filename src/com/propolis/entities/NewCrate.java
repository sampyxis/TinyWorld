package com.propolis.entities;

import org.newdawn.slick.SlickException;

public class NewCrate extends BaseEntity {

	public NewCrate(float x, float y, float width, float height, float mass,
			String img) throws SlickException {
		super(x, y, width, height, mass, img);
		setName("Crate");
		// TODO Auto-generated constructor stub
		
		setImage("res/crate.png");
	}

}
