package tile;

import java.awt.Graphics2D;
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
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/map01.txt");
	}

	public void getTileImage() {

		setup(0, "grass", false);

		setup(1, "dirt", false);

		setup(2, "sand", false);
		setup(3, "sand_bottom_left_inside", false);
		setup(4, "sand_bottom_left_outside", false);
		setup(5, "sand_bottom_right_inside", false);
		setup(6, "sand_bottom_right_outside", false);
		setup(7, "sand_top", false);
		setup(8, "sand_bottom", false);
		setup(9, "sand_left", false);
		setup(10, "sand_right", false);
		setup(11, "sand_top_left_outside", false);
		setup(12, "sand_top_right_inside", false);
		setup(13, "sand_top_right_outside", false);
		setup(14, "sand_top_left_inside", false);
		
		setup(15, "tree", true);
		
		setup(16, "wall", true);
		
		setup(17, "water", true);
		setup(18, "water1", true);
		setup(19, "water_bottom_left_corner_inside", true);
		setup(20, "water_bottom_left_corner_outside", true);
		setup(21, "water_bottom_right_corner_inside", true);
		setup(22, "water_bottom_right_corner_outside", true);
		setup(23, "water_bottom", true);
		setup(24, "water_left", true);
		setup(25, "water_right", true);
		setup(26, "water_top", true);
		setup(27, "water_top_left_corner_inside", true);
		setup(28, "water_top_left_corner_outside", true);
		setup(29, "water_top_right_corner_inside", true);
		setup(30, "water_top_right_corner_outside", true);
		
		

		
		

	}

	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResource("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader((is)));

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
			e.printStackTrace();
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

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
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
