package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	
    public boolean upPressed, downPressed, leftPressed, rightPressed, zPressed;
    
    boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // Title Screen
        if (gp.gameState == gp.titleState) {
        	
        	if (code == KeyEvent.VK_UP) {
        		if(gp.ui.commandNum != 0) {
                	gp.ui.commandNum--;
            	}
        		else {
        			// play sound effect that signals they can't go any further
        		}
        	}
            if (code == KeyEvent.VK_DOWN) {
            	if(gp.ui.commandNum != 2) {
                	gp.ui.commandNum++;
            	}
        		else {
        			// play sound effect that signals they can't go any further
        		}
           	}
            if(code == KeyEvent.VK_Z) {
            	if(gp.ui.commandNum == 0) {
            		gp.gameState = gp.playState;
            		// add play music code here
            		// gp,playMusic();
            	}
            	
            	if(gp.ui.commandNum == 1) {
            		// select load file (add later)
            	}
            	
            	if(gp.ui.commandNum == 2){
            		System.exit(0);
            	}
            }
        }
        
        // Play State
        if(gp.gameState == gp.playState) {
        
        	if (code == KeyEvent.VK_UP) {
            upPressed = true;
        	}
        	
        	if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        	}
        	
        	if (code == KeyEvent.VK_LEFT) {
        		leftPressed = true;
        	}
        
        	if (code == KeyEvent.VK_RIGHT) {
        		rightPressed = true;
        	}
        
        	if (code == KeyEvent.VK_P) {
        		gp.gameState = gp.pauseState;
        	}
        
        	//debugging
        	if (code == KeyEvent.VK_T) {
        		if(checkDrawTime == false) {
        			checkDrawTime = true;
        		}
        		else if (checkDrawTime == true) {
        			checkDrawTime = false;
        		}
        	}
        	
        	
        	if (code == KeyEvent.VK_Z) {
        		 zPressed = true;
        	}
        }
        
        // pause
        
        else if(gp.gameState == gp.pauseState) {
        	if (code == KeyEvent.VK_P) {
        		gp.gameState = gp.playState;
        	}
        }
        
        // Dialogue
        else if (gp.gameState == gp.dialogueState) {
        	if(code == KeyEvent.VK_Z) {
        		gp.gameState = gp.playState;
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        
     }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}