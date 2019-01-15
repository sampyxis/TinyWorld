package com.propolis.levels;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import com.propolis.base.Actor;
import com.propolis.base.Tile;
import com.propolis.base.TileEnvironment;
import com.propolis.enemies.BeeMessanger;
import com.propolis.enemies.Beetle;
import com.propolis.enemies.GBeetle;
import com.propolis.enemies.NPCBee;
import com.propolis.enemies.QueenBee;
import com.propolis.enemies.YBeetle;
import com.propolis.enemies.ZombieBee;
import com.propolis.enemies.ants;
import com.propolis.entities.GuardBee;
import com.propolis.entities.HoneyPot;
import com.propolis.entities.HoneyToken;
import com.propolis.entities.LevelChange;
import com.propolis.entities.MessageBlock;
import com.propolis.entities.Resin;
import com.propolis.entities.RoyalJelly;
import com.propolis.game.TileSet;

public class MapLoader {
	private TileSet set;
    private int playerX;
    private int playerY;
    private ArrayList<Actor> levelActors = new ArrayList<Actor>();
	
	public MapLoader(TileSet set){
		
		this.set = set;
	}

	public TileEnvironment load(String ref) throws SlickException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(ref)));
		int width = 0;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			while(reader.ready()){
				String line = reader.readLine();
				if(line == null){
					break;
				}
				
				width = Math.max(line.length(), width);
				lines.add(line);
			}
		} catch (IOException e){
			throw new SlickException("Failed to load map: " + ref, e);
		}
		
		int height = lines.size();
		TileEnvironment env = new TileEnvironment(width, height);
		boolean foundSpecial = false;
		System.out.println("Height: " + height + " Width: " + width);
		for(int x = 0; x<width; x++){
			for(int y = 0;y<height;y++){
				char c = lines.get(y).charAt(x);
				System.out.println("ch: " + c + " " + x + " " + y + " ");
				 
				// Here we want to pull out our special characters for processing
				// We do not want to make them a tile - we're just using them in the load
				// So, we trigger foundSpecial if we find a special character - it does not add
				// a tile - but does add the character
				switch(c){
					case('p'):
						playerX = x;
						playerY = y;
						foundSpecial = true;
						break;
					case('h'):
						env.addEntity(new HoneyPot(x * 32,y * 32, 32,32,1, ""));
						foundSpecial = true;
						break;
					
					case('*'):
						env.addEntity(new LevelChange(x * 32,y * 32, 32,32,1, ""));
						foundSpecial = true;
						break;
					case('r'):
						env.addEntity(new RoyalJelly(x * 32,y * 32, 32,32,1, ""));
						foundSpecial = true;
						break;
					case('t'):
						env.addEntity(new HoneyToken(x*32, y*32, 32,32,1,""));
						foundSpecial = true;
						break;
					case('u'):
						env.addEntity(new MessageBlock(x*32, y*32, 32, 32,1,""));
						foundSpecial=true;
						break;
					case('1'):// Queen Bee
						//player = new Bee(level.getLevelMapLoader().getPlayerStartX()*32,level.getLevelMapLoader().getPlayerStartY()*32,1f,24);
					    env.addEntity(new QueenBee(x*32, y*32, 1f, 24));
						foundSpecial=true;
						break;
					case ('2'): // Beetle
						Beetle beetle;
						beetle = new Beetle(x*32, y*32, 1f, 24);
						env.addEntity(beetle);
						foundSpecial = true;
						// Register Sounds
						levelActors.add(beetle);
						break;
					case ('3'):
						env.addEntity(new BeeMessanger(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case ('4'):
						env.addEntity(new NPCBee(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case ('5'):
						env.addEntity(new ZombieBee(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case ('6'):
						env.addEntity(new GuardBee(x*32, y*32, 50,50,1,"") );
						foundSpecial = true;
						break;
					case ('7'):
						env.addEntity(new YBeetle(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case ('8'):
						env.addEntity(new GBeetle(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case ('9'):
						env.addEntity(new ants(x*32, y*32, 1f, 24) );
						foundSpecial = true;
						break;
					case('R'):
						env.addEntity(new Resin(x*32, y*32, 32,32,1,""));
						foundSpecial = true;
						break;
				}
				
				
				
				
				Tile tile = set.getTile(c);
				if(tile != null && !foundSpecial){
					env.setTile(x,y, tile);
				}
				foundSpecial = false;
			}
		}
		return env;
	}
	
	public int getPlayerStartX(){
		return playerX;
	}
	
	public int getPlayerStartY(){
		return playerY;
	}
	
	public ArrayList<Actor> getLevelActors(){
		return levelActors;
	}
}
