import java.util.*;
import java.io.*;

public class Main {

  public static Frame frame;
  public static Player player;
  public static ArrayList<Platform> platforms;
  private static final int PADDING = 40;

  public static void main(String[] args) throws IOException{
    int map_size = Integer.parseInt(args[0]);
    
    platforms = new ArrayList<Platform>();
    
    File file = new File("./level.txt");
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String ln;
    ArrayList<String[]> map = new ArrayList<String[]>();
    while ((ln = reader.readLine()) != null){
      map.add(ln.split(""));
    }
    int multipleX = (int) (map_size / map.get(0).length);
    int multipleY = (int) (map_size / map.size());
    for(int i = 0; i < map.size(); i ++){
      String[] line = map.get(i);
      for(int j = 0; j < map.get(0).length; j ++){
        String spot = line[j];
        if(spot.equalsIgnoreCase("S")){
          player = new Player(j * multipleX, i * multipleY, multipleX, multipleY);
        }
        else if(spot.equalsIgnoreCase("P")){
          platforms.add(new Platform(j * multipleX, i * multipleY, multipleX, multipleY));
        }
      }
    }
    frame = new Frame("Gravity", map_size + multipleX, map_size + multipleY * 2);
  }
}
