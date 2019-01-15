package com.propolis.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.IOException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

public class Images {

	public static Font FONT;
	public static BufferedImageFilter LOGO;
	public static BufferedImage BG;
	public static BufferedImage INTRO;
	public static UnicodeFont ttFont;
	
	public static void init() throws IOException {
//		LOGO = ImageIO.read(Resources.getResource("logo.png"));
//		INTRO = ImageIO.read(Resources.getResource("intro.png"));
//		BG = ImageIO.read(Resources.getResource("background.png"));
//		try {
//			FONT = Font.createFont(Font.TRUETYPE_FONT, Resources.getResource("font.ttf"));
//			FONT = FONT.deriveFont(30.0f);
//		} catch (FontFormatException e) {
//			IOException x = new IOException();
//			x.initCause(e);
//			throw x;
//		}
		
	}
	
	public static UnicodeFont getFont() throws SlickException{
		UnicodeFont uFont = new UnicodeFont("res/font.ttf", 24, true, false);
		return uFont;
	}
}
