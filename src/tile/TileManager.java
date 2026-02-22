package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
	GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        
    	this.gp = gp;
        
    	tile = new Tile[55];  // can add more later
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/fblamap.txt");  // Replace with map file path.
    }

    public void getTileImage() {
       
            setup(0, "1", false);
            setup(1, "1", false);
            setup(2, "1", false);
            setup(3, "0", false);
            setup(4, "0", false);
            setup(5, "0", false);
            setup(6, "0", false);
            setup(7, "0", false);
            setup(8, "0", false);
            setup(9, "0", false);
            setup(10, "0", false);
            setup(11, "0", false);
            setup(12, "0", false);
            setup(13, "0", false);
            setup(14, "0", false);
            setup(15, "0", false);
            setup(16, "0", false);
            setup(17, "0", false);
            setup(18, "0", false);
            setup(19, "0", false);
            setup(20, "0", false);
            setup(21, "0", false);
            setup(22, "0", false);
            setup(23, "0", false);
            setup(24, "0", false);
            setup(25, "0", false);
            setup(26, "0", false);
            setup(27, "0", false);
            setup(28, "0", false);
            setup(29, "0", false);
            setup(30, "0", false);
            setup(31, "0", false);
            setup(32, "0", false);
            setup(33, "0", false);
            setup(34, "0", false);
        
            //set tiles here: (Tile index, image name, collision)

    }
    
    public void setup(int index, String imageName, boolean collision) {
    	
    	UtilityTool uTool = new UtilityTool();
    	
    	try {
    		tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    public void loadMap(String filePath) {
    	
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
            	String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    
                	String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        
        } catch (Exception e) {
        	
        }
    }
    
    

    public void draw(Graphics2D g2) {
        
    	int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
        	int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
