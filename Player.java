import java.awt.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class Player extends Thing{

  private boolean onPlatform;

  private double velocityX;
  private double velocityY;

  private int dirY;
  private int dirX;

  private static final int FLAT = 0;
  private static final int UP = 1;
  private static final int DOWN = 2;

  private static final int STILL = 3;
  private static final int RIGHT = 4;
  private static final int LEFT = 5;

  private static final double MAX_SPEED_X = 5;
  private static final double INCREASE_SPEED_X = 0.2;
  private static final double DECREASE_SPEED_X = -0.1;

  private static final double MAX_SPEED_Y = 12;
  private static final double JUMP_Y = -9;
  private static final double DECREASE_SPEED_Y = 0.2;

  public Player(int x, int y, int width, int height){
    super(x, y, width, height);
    this.onPlatform = false;
    this.velocityX = 0;
    this.velocityY = 0;
  }
  public void act(Dictionary d){
    if((Boolean) d.get("w"))
      this.dirY = this.UP;
    else if((Boolean) d.get("s"))
      this.dirY = this.DOWN;
    else
      this.dirY = this.FLAT;
    if((Boolean) d.get("a"))
      this.dirX = this.LEFT;
    else if((Boolean) d.get("d"))
      this.dirX = this.RIGHT;
    else 
      this.dirX = this.STILL;
    move();
    this.x += this.velocityX;
    this.y += this.velocityY;
  }
  public void move(){
    this.velocityY += (Math.abs(this.velocityY) < Math.abs(this.MAX_SPEED_Y)) ? this.DECREASE_SPEED_Y : 0;
    
    for(Platform platform : Main.platforms){
      if(this.getMaxY() > platform.getMinY()){
        this.velocityY = 0;
        this.onPlatform = true;
      }
    }

    if(this.dirY == this.UP){
      if(this.onPlatform){
        this.velocityY = this.JUMP_Y;
        this.onPlatform = false;
      }
    }

    if(this.dirX == this.RIGHT){
      if(this.velocityX <= this.MAX_SPEED_X)
        this.velocityX += this.INCREASE_SPEED_X;
    }
    else if(this.dirX == this.LEFT){
      if(this.velocityX >= -this.MAX_SPEED_X)
        this.velocityX -= this.INCREASE_SPEED_X;
    }
    else if(this.dirX == this.STILL){
      this.velocityX += ((this.velocityX > 0) ? this.DECREASE_SPEED_X : -this.DECREASE_SPEED_X);
      if(Math.abs(this.velocityX) <= Math.abs(this.DECREASE_SPEED_X)){
        this.velocityX = 0;
      }
    }
  }
  public void draw(Graphics g){
    g.setColor(Color.gray);
    g.fillRect(this.x, this.y, this.width, this.height);
  }
}
