package com.propolis.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class Menu extends BasicGameState {
	private static final int ID = 2;
	private Image background;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background = new Image("res/BeeMenu.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Color lColor = Color.lightGray;
		g.setBackground(lColor);
		
		g.resetTransform();
		background.draw();
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
        Input input = container.getInput();
        
        // restart and bounds toggling
        if (input.isKeyPressed(Input.KEY_SPACE)) {
                game.enterState(1); // 1 is play
        }
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
