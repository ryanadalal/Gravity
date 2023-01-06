import javax.swing.*;
import java.awt.*;
import java.util.*;

public abstract class Thing extends Rectangle{
  
  

  public Thing(int x, int y, int width, int height){
    this.setRect(new Rectangle(x, y, width, height));
  }
  
  public abstract void act(Dictionary d);
  public abstract void move();
  public abstract void draw(Graphics g);
}
