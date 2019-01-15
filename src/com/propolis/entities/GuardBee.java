package com.propolis.entities;

import org.newdawn.slick.SlickException;

import com.propolis.game.Bee;

public class GuardBee extends BaseEntity {

	public GuardBee(float x, float y, float width, float height, float mass,
			String img) throws SlickException {
		super(x, y, width, height, mass, img);
		// TODO Auto-generated constructor stub
		setImage("res/guard_bee.png");
		//body.setMoveable(false);
	}
	
	public void hit(Bee player) throws SlickException{

	}
}
