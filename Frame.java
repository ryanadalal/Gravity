

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Frame extends JFrame implements ActionListener{

  private static Timer timer;
  private Dimension size;
  private KeyboardInput keyboardInput;
  
  public Frame(String title, int width, int height){
    this.setTitle(title);
    this.size = new Dimension(width, height);
    this.setSize(size);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new Pane(width, height));
    this.keyboardInput = new KeyboardInput();
    this.addKeyListener(keyboardInput);
    this.setVisible(true);
    timer = new Timer(10, this);
    timer.start();
  }


  public void actionPerformed(ActionEvent e){
    //System.out.println(this.keyboardInput.getKeys().get("w").getClass());
    Main.player.act(this.keyboardInput.getKeys());
    for (Platform platform : Main.platforms)
      platform.act(this.keyboardInput.getKeys());
    repaint();
  }
}

class Pane extends JPanel{

  private int width;
  private int height;

  public Pane(int width, int height){
    super(null, true);
    this.width = width;
    this.height = height;
  }
  protected void paintComponent(Graphics g){
    this.repaint();
    g.setColor(Color.white);
    g.fillRect(0, 0, this.width, this.height);
    Main.player.draw(g);
    for (Platform platform : Main.platforms)
      platform.draw(g);
  }
}

class KeyboardInput extends KeyAdapter{
  private Dictionary<String, Boolean> keys = new Hashtable<String, Boolean>();

  public KeyboardInput(){
    super();
    this.keys.put("w", false);
    this.keys.put("s", false);
    this.keys.put("a", false);
    this.keys.put("d", false);
  }


  public void keyPressed(KeyEvent e){
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_W)
      this.keys.put("w", true);
    else if(code == KeyEvent.VK_S)
      this.keys.put("s", true);
    if(code == KeyEvent.VK_A)
      this.keys.put("a", true);
    else if(code == KeyEvent.VK_D)
      this.keys.put("d", true);
    
  }

  public void keyReleased(KeyEvent e){
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_W)
      this.keys.put("w", false);
    else if(code == KeyEvent.VK_S)
      this.keys.put("s", false);
    if(code == KeyEvent.VK_A)
      this.keys.put("a", false);
    else if(code == KeyEvent.VK_D)
      this.keys.put("d", false);

  }

  public Dictionary getKeys(){
    return this.keys;
  }
}