package com.propolis.base;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;

/*
 * From Kevin Glass
 * Common parts of all enviroments - holds the physical world, allows
 * addition of entities and their physical representation
 */

public abstract class AbstractEnvironment implements Environment {
	
	protected World world = new World(new Vector2f(0,10), 20);
	
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	private int totalDelta;
	private int stepSize = 5;
	
	/*
	 * Adds an entity to the environment
	 */
	public void addEntity(Entity entity){
		if(entity.getBody() !=null){
			world.add(entity.getBody());
			entity.setUserData(entity);
		}
		
		entities.add(entity);
		entity.setWorld(world);
	}
	
	//Kill
	public void removeEntity(Entity entity){
		entities.remove(entity);
		world.remove(entity.getBody());
	}
	
	//update
	public void update(int delta){
		boolean first = true;
		
		totalDelta += delta;
		while( totalDelta > stepSize){
			world.step(stepSize * 0.01f);
			totalDelta -= stepSize;
			
			if(first) {
				first = false;
				for(int i=0; i<entities.size(); i++){
					entities.get(i).preUpdate(delta);
				}
				for(int i=0; i<entities.size();i++){
					entities.get(i).update(stepSize);
				}
			}
				
		}
	}
	

}
