package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_OpenedChest extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_OpenedChest(GamePanel gp) {
		
		this.gp = gp;
		
		name = "chestOpened";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/openchest1.png/"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
