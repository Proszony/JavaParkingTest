import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetLight {
    GamePanel gp;
    public int[][] ParkingSpots;
    public int Light_x,Light_y;
    public LightTile[] LightTiles;
    public GetLight(GamePanel gp){
        this.gp = gp;
        LightTiles = new LightTile[4];
        ParkingSpots = new int[258][2];
        GetLightIMG();
        LoadParking("mapfrfr.csv");

    }
    public void LoadParking(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            int i = 0;
            while (col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while (col < gp.maxCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    if(num == 4 || num == 5){
                        ParkingSpots[i][0] = col;
                        ParkingSpots[i][1] = row;
                        i++;
                    }
                    col++;
                }
                if (col == gp.maxCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }
    public void GetLightIMG(){
        try {
            LightTiles[0] = new LightTile();
            LightTiles[0].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_left.png"));

            LightTiles[1] = new LightTile();
            LightTiles[1].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_right.png"));

            LightTiles[2] = new LightTile();
            LightTiles[2].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_left.png"));

            LightTiles[3] = new LightTile();
            LightTiles[3].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GetTile(Entity entity){
        if(entity.parking){

            for(int i = 0;i<ParkingSpots.length;i++){
                int col = ParkingSpots[i][0];
                int row = ParkingSpots[i][1];
                int tileX = col*48;
                int tileY = row*48;
                if(Math.abs(entity.x - tileX) < 48 && Math.abs(entity.y - tileY) < 48){
                    Light_y = tileY;
                    Light_x = tileX;
                }
            }
        }
    }

    public void drawLight(Graphics2D g2) {
        if(gp.player.parking){
            int tilenum = gp.TileM.mapTileNUM[Light_x/48][Light_y/48];
            if(tilenum == 4){
                g2.drawImage(LightTiles[3].image, Light_x, Light_y, gp.tileSize, gp.tileSize, null);
            }
            if(tilenum == 5){
                g2.drawImage(LightTiles[2].image, Light_x, Light_y, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}