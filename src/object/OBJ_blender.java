package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_blender extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_blender(GamePanel gp) {
		
		this.gp = gp;
		
		name = "blenderOff";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/IMAGEHERE.png/"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
