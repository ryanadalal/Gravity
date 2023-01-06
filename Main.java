import java.util.*;

public class Main {

  public static Frame frame;
  public static Player player;
  public static Platform platform;

  public static void main(String[] args){
    frame = new Frame("Gravity", Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    player = new Player(100, 100, 25, 25);
    platform = new Platform(0, Integer.parseInt(args[1]) - 60, Integer.parseInt(args[0]), 30);
  }
}
