import java.util.*;
import java.io.*;

public class Main {

  public static Frame frame;
  public static Player player;
  public static ArrayList<Platform> platforms;
  public static ArrayList<Enemy> enemies;

  public static void main(String[] args) throws IOException{
    //int map_size = Integer.parseInt(args[0]);
    
    platforms = new ArrayList<Platform>();
    enemies = new ArrayList<Enemy>();

    File file = new File("./" + args[0]);
    BufferedReader reader = new BufferedReader(new FileReader(args[0]));
    String ln;

    int screen_size = Integer.parseInt(args[1]);
    int map_size = 0;
    while ((ln = reader.readLine()) != null){
      StringTokenizer st = new StringTokenizer(ln);
      String key = st.nextToken();
      if(key.equalsIgnoreCase("size")){
        st = new StringTokenizer(reader.readLine());
        map_size = Integer.parseInt(st.nextToken());
      }
      else if(key.equalsIgnoreCase("player")){
        st = new StringTokenizer(reader.readLine());
        Main.player = new Player(
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size), 
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size));
      }
      else if(key.equalsIgnoreCase("platform")){
        st = new StringTokenizer(reader.readLine());
        Main.platforms.add(new Platform(
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size)));
      }
      else if(key.equalsIgnoreCase("enemy")){
        st = new StringTokenizer(reader.readLine());
        Main.enemies.add(new Enemy(
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size),
          (int)(Integer.parseInt(st.nextToken()) * screen_size / map_size)));
      }
    }
    frame = new Frame("Gravity", screen_size + screen_size / map_size);
  }
}
