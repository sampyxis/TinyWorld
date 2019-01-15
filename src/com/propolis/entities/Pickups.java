package com.propolis.entities;

import org.newdawn.slick.SlickException;

/*
 * Pickups are a special kind of BaseEntity
 * They can't move - no physics
 */
public class Pickups extends BaseEntity {

	public Pickups(float x, float y, float width, float height, float mass,String img) throws SlickException {
		super(x, y, width, height, mass, img);
		
		this.body.setMoveable(false);
	}

}
