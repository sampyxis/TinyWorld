package com.propolis.base;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/*
 * Thanks to Keving Glass
 * 
 * A single tile
 */
public class Tile {
	private Shape shape;
	private Image image;

	public Tile(Image image, Shape shape){
		this.shape = shape;
		this.image = image;
	}
	
	public Image getImage(){
		return image;		
	}
	
	public Shape getShape(){
		return shape;
	}
}
