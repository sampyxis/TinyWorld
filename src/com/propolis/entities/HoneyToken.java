package com.propolis.entities;

import org.newdawn.slick.SlickException;

import com.propolis.game.Bee;

public class HoneyToken extends BaseEntity {

	public HoneyToken(float x, float y, float width, float height, float mass,
			String img) throws SlickException {
		super(x, y, width, height, mass, img);
		// TODO Auto-generated constructor stub
		
		setImage("res/honey_token.png");
	}
	
	public void hit(Bee player) throws SlickException{
		playSound();
		player.honey_tokens ++;
	}

}
