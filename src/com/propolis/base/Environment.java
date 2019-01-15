package com.propolis.base;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

// Took this from Kevin Glass - this is the environment which entities can exist

public interface Environment {

	// Render the environment
	public void render(Graphics g);
	
	// Render the bounds of all the entities
	public void renderBounds(Graphics g);
	
	// Update the environment
	public void update(int delta);
	
	// Get the environmental limits
	public Rectangle getBounds();
}
