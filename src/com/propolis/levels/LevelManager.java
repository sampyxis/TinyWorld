package com.propolis.levels;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.propolis.base.Environment;

/*
 * Level manager
 * Loads each level
 * Has a function to return the environment for each level loaded
 * also returns background as image
 */
public class LevelManager {
	
	public Image background;
	private Environment env;
	public Level currLevel;
	public ArrayList<Level> levels = new ArrayList<Level>();
	
	public LevelManager(){
		
	}
	
	public Environment getLevelEnvironment(Level currLevel){
		
		return currLevel.getLevelEnv();
	}
	
	public void loadLevel(int levelNum) throws SlickException{
		currLevel = new Level(levelNum);
		levels.add(currLevel);
	}
	
	public Level getCurrLevel(){
		return currLevel;
	}

}
