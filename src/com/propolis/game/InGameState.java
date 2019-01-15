package com.propolis.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import com.propolis.base.Actor;
import com.propolis.base.Environment;
import com.propolis.base.TileEnvironment;
import com.propolis.entities.NewCrate;
import com.propolis.graphics.Images;
import com.propolis.graphics.Resources;
import com.propolis.levels.Level;
import com.propolis.levels.LevelManager;
import com.propolis.levels.LevelMessages;

public class InGameState  extends BasicGameState {
	
	// Basic state-based game class
	/* unique id given to the state */
	private static final int ID = 1;
	public static Actor player;
	private float xoffset;
	private float yoffset;
	private static Image background;
	private int totalDelta;
	private int controlInterval = 50;
	private boolean showBounds = false;
	public int  width = 600;
	public int height = 400;
	public static Image messageImage = null;
	public static boolean showMessage = false;
	public static String subMessage;
	private static Graphics globalGraphics;
	private static boolean paused = false;
	
	
	// Level manager and level
	public static LevelManager levelManager;
	public static Level level;
	
	// Having physics - need an environment
	private static Environment env;
	
	public static LevelMessages lvMessages;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		//background = new Image("res/BeeHiveCube.png");
		//restart();
		//
		
		lvMessages = new LevelMessages();
		levelManager = new LevelManager();
		loadLevel(1);
	}
	
	public static void loadLevel(int levelNum) throws SlickException{
		// Pause the game
		paused = true;
		// Show level loading screen
		Image loadingImage = new Image("res/Loading Screen.png");
		Graphics g = new Graphics();
		g.resetTransform();
		loadingImage.draw();
		// Turn the last level's music off
		if(level !=null){
			if(level.getLevelNum()>0 && level.getLevelSound().playing()){
				level.getLevelSound().stop();
			}
			level.turnOffLevelSound();
		}
		// doesn't work yet.
		levelManager.loadLevel(levelNum);
		level = levelManager.getCurrLevel();
		background = level.getLevelBackground();
		// May move player creation to somewhere else
		//player = new Bee(100,150,1f,24);
		player = new Bee(level.getLevelMapLoader().getPlayerStartX()*32,level.getLevelMapLoader().getPlayerStartY()*32,1f,24);
		level.levelAddEntity(player);
		player.setLevel(level);
		// Now the enviroment is set up - make it this env
		env = level.getLevelEnv();
		paused = false;
	}
	


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		globalGraphics = g;

	    float width = container.getWidth();
        float height = container.getHeight();
        float backPar = 3f;
        float bx = ((-xoffset * backPar) % width) / -width;
        float by = ((-yoffset * backPar) % height) / -height;
        //g.setFont(Images.getFont());
        background.bind();
        
        g.setColor(Color.white);
        GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(bx,by);
                GL11.glVertex2f(0,0);
                GL11.glTexCoord2f(bx+3,by);
                GL11.glVertex2f(width,0);
                GL11.glTexCoord2f(bx+3,by+3);
                GL11.glVertex2f(width,height);
                GL11.glTexCoord2f(bx,by+3);
                GL11.glVertex2f(0,height);
        GL11.glEnd();
        
        
        // Moving the draw here works for most of the backgrounds
        background.draw(0,0);
        g.translate(-(int) xoffset, -(int) yoffset);
        // Having the draw here works for the bee hive background
        //background.draw(0,0);
        
        env.render(g);
        
        if (showBounds) {
                env.renderBounds(g);
        }
        
        g.translate((int) xoffset, (int) yoffset);
        drawString(g,"Propolis", 0);
        //drawString(g,"Cursors - Move   Space - Jump   B - Show Bounds   R - Restart", 380);
        
        //if(showMessage && messageImage != null){
        if(showMessage){
        	g.setColor(new Color(0,0,0,0.8f));
        	g.fillRect(50,10,480,250);
        	g.drawImage(messageImage, 0,-40, Color.black);
        	g.setColor(Color.white);
        	g.drawString(subMessage, 100, 20);
        	
        }
		
	}

    private void drawString(Graphics g, String str, int y) {
        int x = (width - g.getFont().getWidth(str)) / 2;
        
        g.setColor(Color.black);
        g.drawString(str, x+1,y+1);
        g.setColor(Color.white);
        g.drawString(str, x,y);
        
}
    
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
        Input input = container.getInput();

        
        // If a message is showing - remove it - and un-pause the game
        if(input.isKeyDown(Input.KEY_ESCAPE)){
        	paused = false;
        	showMessage = false;
        }
        // Menu
        if(input.isKeyDown(Input.KEY_M)){
        	game.enterState(2);//2 menu
        }
        
        if(paused == true){
        	return;
        }
        // restart and bounds toggling
        if (input.isKeyPressed(Input.KEY_R)) {
                //restart();
        		loadLevel(level.getLevelNum());
                return;
        }
        if (input.isKeyPressed(Input.KEY_B)) {
                showBounds = !showBounds;
                //showMessage("Hey", Color.black);
        }

        // the forces applied for different actions. The move force is applied over and
        // over so is reasonably small. The jump force is a one shot deal and so is reasonably
        // big
        float moveForce = 100;
        float jumpForce = 20000;
        
        totalDelta += delta;
        
        
        // setup the player's moving flag, this control the animation
        player.setMoving(false);
        if (input.isKeyDown(Input.KEY_LEFT)) {
                player.setMoving(true);
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
                player.setMoving(true);
        }
        
        if(input.isKeyDown(Input.KEY_Z)){
        	// Messages
        	showMessage("hey there");
        	//showMessage = !showMessage;
        }
        
        if(input.isKeyDown(Input.KEY_F)){
        	player.fire(level.getLevelEnv());
        }
        // only check controls at set interval. If we don't do this different
        // frame rates will effect how the controls are interpreted
        if (totalDelta > controlInterval) {
                controlInterval -= totalDelta;
                

                
                
                // Need an up key
                float moveForceCopy= 0f;
                if (input.isKeyDown(Input.KEY_LEFT)) {
                        player.applyForce(-moveForce, 0);
                        moveForceCopy  = -moveForce;
                }
                if (input.isKeyDown(Input.KEY_RIGHT)) {
                        player.applyForce(moveForce, 0);
                        moveForceCopy = moveForce;
                }
                if (true) {
                        if ((input.isKeyPressed(Input.KEY_SPACE))) {
                                if (player.facingRight()) { // they were 0's =- not moveForceCopy
                                        player.applyForce(moveForceCopy, -jumpForce);
                                } else {
                                        player.applyForce(moveForceCopy, -jumpForce);
                                }
                        }
                }
                
                
                if (!input.isKeyDown(Input.KEY_LCONTROL)) {
                        if (player.jumping()) {
                                player.setVelocity(player.getVelX(), player.getVelY() * 0.95f);
                        }
                }
                
                // flying
                if(input.isKeyDown(Input.KEY_UP)){
                	// have to jump to start flying
//                	if(player.jumping() || player.flying()) {
//                		player.setFlying(true);
//                	}
                	player.setFlying(true);
                }
                
                //??
                if(input.isKeyPressed(Input.KEY_UP)){
                	
                }
        }
        
        // update the environemnt and hence the physics world
        env.update(delta);
        
        // calculate screen position clamping to the bounds of the level
        xoffset = player.getX() - width / 2;//400;
        yoffset = player.getY() - height / 2;//300;
        
        Rectangle bounds = env.getBounds();
        if (xoffset < bounds.getX()) {
                xoffset = bounds.getX();
        }
        if (yoffset < bounds.getY()) {
                yoffset = bounds.getY();
        }
        
        if (xoffset > (bounds.getX() + bounds.getWidth()) - width) {
                xoffset = (bounds.getX() + bounds.getWidth()) - width;
        }
        if (yoffset > (bounds.getY() + bounds.getHeight()) - height) {
                yoffset = (bounds.getY() + bounds.getHeight()) - height;
        }
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	// put collision here for now -but later move it
	public boolean entityCollisionWith() throws SlickException{
		return false;
	}
	
	public static void showMessage(String message) throws SlickException{
		messageImage = new Image(100, 60);
		//Graphics2D g = (Graphics2D) messageImage.getGraphics();
		
		//g.setFont(Images.ttFont);
		//g.setColor(col);
		globalGraphics.drawString(message, 150, 10);
		subMessage = message;
		showMessage = true;
		//showMessage = !showMessage;
		//showMessage = !showMessage;
		
	}

	public static void setPaused(boolean pause){
		paused = pause;
	}
}
