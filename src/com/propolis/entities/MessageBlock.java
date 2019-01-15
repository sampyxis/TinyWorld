package com.propolis.entities;

import org.newdawn.slick.SlickException;

public class MessageBlock extends BaseEntity {


	
	public MessageBlock(float x, float y, float width, float height,
			float mass, String img) throws SlickException {
		super(x, y, width, height, mass, img);
		// TODO Auto-generated constructor stub
		
		setImage("res/message_token.png");
		body.setMoveable(false);
	}
	

}
