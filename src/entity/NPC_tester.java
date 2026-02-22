package entity;

import java.util.Random;

import main.GamePanel;


public class NPC_tester extends Entity{
	
	public NPC_tester(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/tester");
		up2 = setup("/npc/tester2");
		down1 = setup("/npc/tester");
		down2 = setup("/npc/tester2");
		left1 = setup("/npc/tester2");
		left2 = setup("/npc/tester");
		right1 = setup("/npc/tester");
		right2 = setup("/npc/tester2");
	}
	
	public void setDialogue() {
		dialogues[0] = "I AM A TESTER NPC";
		dialogues[1] = "I AM A PLACEHOLDER";
		dialogues[2] = "SRAVYA PLS DRAW ME NOW NOW \nNOW NOW NOW NOW NOW"; // Insert a \n to go to next line
		dialogues[3] = "THANK YOU";

	}
	
	public void setAction() {
		
		actionLookCounter++;
		
		if(actionLookCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // picks a number from 1-100
			
			if (i <= 25) {
			direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLookCounter = 0;
		}
		
		
	}
	
	public void speak() {
		
		// Add character specific stuff here
		super.speak();
	}
}
