package com.propolis.entities;

import org.newdawn.slick.SlickException;

import com.propolis.game.Bee;

public class Resin extends BaseEntity {

	public Resin(float x, float y, float width, float height, float mass,
			String img) throws SlickException {
		super(x, y, width, height, mass, img);
		// TODO Auto-generated constructor stub
		
		setImage("res/Resinbottle.png");
	}

	public void hit(Bee player) throws SlickException{
		playSound();
		player.resin ++;
	}
}
