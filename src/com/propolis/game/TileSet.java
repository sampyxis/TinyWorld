package com.propolis.game;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

import com.propolis.base.Tile;
import com.propolis.base.TileShapes;

public class TileSet {
    private HashMap<Character, Tile> entries = new HashMap<Character, Tile>();
    private int nextLevel = 0;
    
    /**
     * Create a new tileset by loading an XML file
     * 
     * @param ref The reference to the XML file to load
     * @throws SlickException Indicates a failure to locate or parse the XML
     */
    public TileSet(String ref) throws SlickException {
            XMLParser parser = new XMLParser();
            XMLElement root = parser.parse(ref);
            
            XMLElementList list = root.getChildrenByName("tile");
            for (int i=0;i<list.size();i++) {
                    XMLElement element = list.get(i);
                    char id = element.getAttribute("id").charAt(0);
                    String image = element.getAttribute("image");
                    Shape shape = TileShapes.getShapeByName(element.getAttribute("shape"));
                    
                    entries.put(id, new Tile(new Image(image), shape));
                    
                    if (id == '*') {//next level num
                    	nextLevel  = Integer.parseInt(element.getAttribute("nextLevel"));
                    }
                   
            }
    }
    
    /**
     * Get a simple tile definition based on a given character
     * 
     * @param c The character identifing the tile
     * @return The tile definition
     */
    public Tile getTile(char c) {
            return entries.get(c);
    }
    
    
    
    public int getNextLevel(){
    	return nextLevel;
    }
}
