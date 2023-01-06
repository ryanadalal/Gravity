import java.util.*;

public class Main {

  public static Frame frame;
  public static Player player;
  public static ArrayList<Platform> platforms;

  public static void main(String[] args){
    frame = new Frame("Gravity", Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    player = new Player(100, 100, 25, 25);
    platforms = new ArrayList<Platform>();
    platforms.add(new Platform(0, Integer.parseInt(args[1]) - 60, Integer.parseInt(args[0]), 30));
    platforms.add(new Platform(0, 500, Integer.parseInt(args[0]), 30));
  }
}
