package com.propolis.base;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

import org.newdawn.slick.Graphics;

public interface Entity {
	
	public void setUserData(Entity entity);
	
	public void setWorld(World world);
	
	public Body getBody();
	
	public void preUpdate(int delta);
	
	public void update(int delta);
	
	public void render(Graphics g);

}
