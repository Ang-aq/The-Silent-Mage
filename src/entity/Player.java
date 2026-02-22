 package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8, 16, 32, 32);
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
		direction = "down";
	}
	
	public void setDefaultValues() {
		
		worldX = (int) (gp.tileSize * 19.5);
		worldY = gp.tileSize * 10;
		speed = 8;
		
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/McFacingBackWalking1");
		up2 = setup("/player/McFacingBackWalking2");
		down1 = setup("/player/McFacingFrontWalking1");
		down2 = setup("/player/McFacingFrontWalking2");
		left1 = setup("/player/McFacingLeft");
		left2 = setup("/player/McFacingLeftWalking");
		right1 = setup("/player/McFacingRight");
		right2 = setup("/player/McFacingRightWalking");
	}
	
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true 
			|| keyH.leftPressed == true || keyH.rightPressed == true ) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			
			interactNPC(npcIndex);
			
			// if collision false, player can move.
			if(collisionOn == false) {
				
				switch(direction) {
				
				case"up": worldY -= speed; break;
				case"down": worldY += speed; break;
				case"left": worldX -= speed; break;
				case"right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter>11) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			
		}
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.zPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.zPressed = false;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
			
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
			
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2; 
			}
			break;
			
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}
}
