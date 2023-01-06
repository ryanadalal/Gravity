

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

  private Dimension size;
  
  public Frame(String title, int width, int height){
    this.setTitle(title);
    this.size = new Dimension(width, height);
    this.setSize(size);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.show();
  }
}
