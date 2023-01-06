import java.awt.*;
import java.util.*;

public class Platform extends Thing{
  public Platform(int x, int y, int width, int height){
    super(x, y, width, height);
  }
  public void act(Dictionary d){
    
  }
  public void move(){

  }
  public void draw(Graphics g){
    g.setColor(Color.black);
    g.fillRect(this.x, this.y, this.width, this.height);
  }
}
