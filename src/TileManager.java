import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashMap;

public class TileManager extends Tile{
    GamePanel gp;
    protected Tile[] tile;
    protected int[][] mapTileNUM;
    private HashMap<Integer, String> filepathMap = new HashMap<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[24];
        mapTileNUM = new int[gp.maxCol][gp.maxRow];
        getTileImg();
        loadMap("mapfrfrsmol.csv");
    }
    private void initFilepathMap(){
        filepathMap.put(0, "/tiles/hollowTurn1.png");
        filepathMap.put(1, "/tiles/hollowTurn2.png");
        filepathMap.put(2, "/tiles/hollowTurn3.png");
        filepathMap.put(3, "/tiles/hollowTurn4.png");
        filepathMap.put(4, "/tiles/parking4.png");
        filepathMap.put(5, "/tiles/parking5.png");
        filepathMap.put(6, "/tiles/parking6.png");
        filepathMap.put(7, "/tiles/parking7.png");
        filepathMap.put(8, "/tiles/roadsideD.png");
        filepathMap.put(9, "/tiles/roadsideU.png");
        filepathMap.put(10, "/tiles/roadTurn1.png");
        filepathMap.put(11, "/tiles/roadTurn2.png");
        filepathMap.put(12, "/tiles/roadTurn3.png");
        filepathMap.put(13, "/tiles/roadTurn4.png");
        filepathMap.put(14, "/tiles/roadTurn11.png");
        filepathMap.put(15, "/tiles/roadTurn41.png");
        filepathMap.put(16, "/tiles/roadupL.png");
        filepathMap.put(17, "/tiles/roadupL2.png");
        filepathMap.put(18, "/tiles/roadupR.png");
        filepathMap.put(19, "/tiles/roadupR2.png");
        filepathMap.put(20, "/lights/bulb_red_left.png");
        filepathMap.put(21, "/lights/bulb_red_right.png");
        filepathMap.put(22, "/lights/bulb_green_left.png");
        filepathMap.put(23, "/lights/bulb_green_right.png");
    } // INICJALIZACJA MAPY FILEPATH DO ZDJEC
    private void getTileImg(){
        initFilepathMap();
        try {
            for (int i = 0; i < tile.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(filepathMap.get(i)));
                if (i == 4 || i == 5) {
                    setParkingspot_ture(tile[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // POBRANIE ZDJEC Z PLIKU

    private void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hollowTurn1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hollowTurn2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hollowTurn3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hollowTurn4.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/parking4.png"));
            setParkingspot_ture(tile[4]);
            //tile[4].parkingspot = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/parking5.png"));
            setParkingspot_ture(tile[5]);
            //tile[5].sparkingspot = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/parking6.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/parking7.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadsideD.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadsideU.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn1.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn2.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn3.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn4.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn11.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadTurn41.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadupL.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadupL2.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadupR.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roadupR2.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_left.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_right.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_left.png"));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // STARE NIE ITERACYJNE DODAWANIE PLIKU

    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while (col < gp.maxCol) {
                    String[] numbers = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNUM[col][row] = num;
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
    } // LADOWANIE MAPY
}
