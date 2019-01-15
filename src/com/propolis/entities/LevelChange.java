package com.propolis.entities;

import org.newdawn.slick.SlickException;

import com.propolis.game.Bee;
import com.propolis.game.InGameState;

public class LevelChange extends BaseEntity {

	public LevelChange(float x, float y, float width, float height, float mass,
			String img) throws SlickException {
		super(x, y, width, height, mass, img);
		// TODO Auto-generated constructor stub
		setImage("res/door.png");
	}
	
	public void hit(Bee player) throws SlickException{
		// need to transition levels
		InGameState.loadLevel(InGameState.levelManager.currLevel.getNextLevel());
	}

}
