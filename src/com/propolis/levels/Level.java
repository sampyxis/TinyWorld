package com.propolis.levels;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.propolis.base.Actor;
import com.propolis.base.Entity;
import com.propolis.base.TileEnvironment;
import com.propolis.entities.Flower;
import com.propolis.entities.NewCrate;
import com.propolis.entities.NewFlower;
import com.propolis.game.Bee;
import com.propolis.game.TileSet;

/*
 * Individual levels - holds the tile set 
 * any text for the game, etc.
 */
public class Level {
	private TileSet tileSet;
	private MapLoader loader;
	private TileEnvironment tileEnv;
	private Image levelBackground;
	private int nextLevel = 0;
	private int levelNum = 0;
	private Sound levelSong;
	
	public Level(int levelNum) throws SlickException{

		// load the level number passed
		tileSet = new TileSet("res/tiles.xml");
		loader  = new MapLoader(tileSet);
		//levelNum++;
		tileEnv = loader.load("res/level_" + levelNum + ".txt");
		//levelNum--;
		tileEnv.setImageSize(32, 32);
		tileEnv.init();
		
		this.levelNum = levelNum;
		
		// get the next level
		nextLevel = tileSet.getNextLevel();
		
		// Level one song
	    levelSong = new Sound("res/song_" + levelNum + ".wav");
	    if( levelNum == 1){
	    	levelSong.loop(.98f, .3f);
	    } 	else {
	    	levelSong.loop(.98f, 1f);
	    }
	    
		// Load background
	    //levelNum +=2;
		levelBackground = new Image("res/levelBackground_" + levelNum + ".png");
		//levelNum -=2;
		// Don't need this part anymore - taken care of in the map loader class
		// Need a way to load the player location 
		// as well as any extra things like crates, pickups etc.
		// for now - one big switch case
//		switch(levelNum){
//		case 1:
//			// adding the crates here - but not doing that now
//		    tileEnv.addEntity(new NewCrate(300,100, 16,16,.001f, ""));
//		    tileEnv.addEntity(new NewCrate(550,40, 16,16,1,""));
//		    tileEnv.addEntity(new NewCrate(555,-10, 16,16,1,""));
//		    tileEnv.addEntity(new NewCrate(545,100, 32,32,5, ""));
//		    
//		    // Add flower
//		    tileEnv.addEntity(new Flower(200,250, 16,16,1));
//		    tileEnv.addEntity(new NewFlower(300,200, 16,16,1, ""));
//		    tileEnv.addEntity(new NewFlower(200,200, 16,16,1, ""));
//		    
//		    break;
//		}
	}
	
	public void turnOffLevelSound(){
		ArrayList<Actor> levelActors = new ArrayList<Actor>();
		levelActors = loader.getLevelActors();
		if(levelActors.size() > 0){
			for(Actor a: levelActors){
				a.stopSound();
			}
		}
	}
	
	public Sound getLevelSound(){
		return levelSong;
	}
	public void levelAddEntity(Entity entity){
		tileEnv.addEntity(entity);
	}
	
	public TileEnvironment getLevelEnv(){
		return tileEnv;
	}
	
	public MapLoader getLevelMapLoader(){
		return loader;
	}
	
	public TileSet getLevelTileSet(){
		return tileSet;
	}
	
	public Image getLevelBackground(){
		return levelBackground;
	}
	
	public int getNextLevel(){
		return levelNum + 1;//nextLevel;
	}
	
	public int getLevelNum() {
		return levelNum;
	}

}
