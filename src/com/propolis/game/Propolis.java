package com.propolis.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Propolis extends StateBasedGame {
	private GameContainer container;
	public static final int PLAY = 1;
	public static final int MENU = 2;
	private boolean vsync = true;
	public static int  width = 600;
	public static int height = 400;

	public Propolis() {
		super("Propolis");
		// TODO Auto-generated constructor stub
		addState(new InGameState());
		addState(new Menu());

		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.container = container;
		
		container.setTargetFrameRate(100);
		container.setVSync(vsync);
		container.setShowFPS(false);
		
		// Start on the menu
		this.enterState(MENU);
	}
	
	public void keyPressed(int key, char c){
		super.keyPressed(key, c);
		
		if (key==Input.KEY_F7){
			vsync =!vsync;
			container.setVSync(vsync);
		}
	}
	
	
    public static void main(String[] argv) throws SlickException {
        AppGameContainer container = new AppGameContainer(new Propolis(), width, height, false);
        container.start();
}

}
