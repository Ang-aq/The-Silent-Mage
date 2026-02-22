package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.OBJ_key;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	private BufferedImage titleBackgroundImage; 
	public int commandNum = 0;

	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
	}
	
	public void ShowMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.playState) {
			
		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
	}
	
	public void drawTitleScreen() {
	    
		// Draw the background image
		try {
			titleBackgroundImage = ImageIO.read(getClass().getResourceAsStream("/player/titlescreen.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		g2.drawImage(titleBackgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

	    // Set game title
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
	    String text = "TITLE HERE";
	    int x = getXforCenteredText(text);
	    int y = gp.tileSize * 3;

	    // Text Shadow
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);

	    // Title text color
	    g2.setColor(Color.black);
	    g2.drawString(text, x, y);
	    
	    // Menu Options
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 49F));
	    
	    text = "New Game";
	    x = getXforCenteredText (text);
	    y += gp.tileSize*4;
	    g2.drawString(text, x, y);
	    if(commandNum == 0) {
	    	g2.drawString(">", x-gp.tileSize, y);
	    	
	    }
	    
	    text = "Load Game";
	    x = getXforCenteredText (text);
	    y += gp.tileSize;
	    g2.drawString(text, x, y);
	    if(commandNum == 1) {
	    	g2.drawString(">", x-gp.tileSize, y);
	    	
	    }
	    
	    text = "Quit Game";
	    x = getXforCenteredText (text);
	    y += gp.tileSize;
	    g2.drawString(text, x, y);
	    if(commandNum == 2) {
	    	g2.drawString(">", x-gp.tileSize, y);
	    	
	    }
	    
	}

	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		// Dialogue Window
		int x = gp.tileSize*2;
		int y = gp.tileSize*6+40;
		int width = gp.screenWidth - (gp.tileSize*4); 
		int height = gp.tileSize*3;
		
		drawSubWindow(x ,y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 255); // final value will adjust the opacity.
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
