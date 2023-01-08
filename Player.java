import java.awt.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class Player extends Thing{

  private boolean onPlatform;
  private boolean blockedTop;
  private boolean blockedLeft;
  private boolean blockedRight;

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
    this.blockedTop = false;
    this.blockedLeft = false;
    this.blockedRight = false;
    this.velocityX = 0;
    this.velocityY = 0;
  }
  public void act(Dictionary d){
    if((Boolean) d.get("w"))
      this.dirY = Player.UP;
    else if((Boolean) d.get("s"))
      this.dirY = Player.DOWN;
    else
      this.dirY = Player.FLAT;
    if((Boolean) d.get("a"))
      this.dirX = Player.LEFT;
    else if((Boolean) d.get("d"))
      this.dirX = Player.RIGHT;
    else 
      this.dirX = Player.STILL;
    move();
    this.x += this.velocityX;
    this.y += this.velocityY;
  }
  public void move(){
    if(!this.onPlatform){
      this.velocityY += (Math.abs(this.velocityY) < Math.abs(Player.MAX_SPEED_Y)) ? Player.DECREASE_SPEED_Y : 0;
    }
    

    int countY = 0, countX = 0;
    for(Platform platform : Main.platforms){
      double intersectionX = Math.min(this.getMaxX(), platform.getMaxX()) - Math.max(this.getMinX(), platform.getMinX());
      double intersectionY = Math.min(this.getMaxY(), platform.getMaxY()) - Math.max(this.getMinY(), platform.getMinY());
      if(intersectionY >= 0 && intersectionX >= 0){
        //add a line to only check horizontal or vertical based on which intersection is smaller
        if(intersectionY < intersectionX){
          if(this.getMaxY() > platform.getMinY() && this.getMaxY() < platform.getMaxY()){
            this.onPlatform = true;
            this.blockedTop = false;
            this.velocityY = this.velocityY > 0 ? 0 : this.velocityY;
          }
          else if(this.getMinY() < platform.getMaxY() && this.getMinY() > platform.getMinY()){
            this.blockedTop = true;
            this.onPlatform = false;
            this.velocityY = this.velocityY < 0 ? 0 : this.velocityY;
          }
        }
        else{
          if(this.getMaxX() > platform.getMinX() && this.getMaxX() < platform.getMaxX()){
            this.blockedRight = true;
            this.blockedLeft = false;
            this.velocityX = this.velocityX > 0 ? 0 : this.velocityX;
          }
          else if(this.getMinX() < platform.getMaxX() && this.getMinX() > platform.getMinX()){
            this.blockedLeft = true;
            this.blockedRight = false;
            this.velocityX = this.velocityX < 0 ? 0 : this.velocityX;
          }
        }
      }
      else{
        countX ++;
        countY ++;
      }
    }
    if(countX == Main.platforms.size()){
      this.blockedRight = false;
      this.blockedLeft = false;
    }
    if(countY == Main.platforms.size()){
      this.blockedTop = false;
      this.onPlatform = false;
    }

    if(this.dirY == Player.UP){
      if(this.onPlatform){
        this.velocityY = Player.JUMP_Y;
        this.onPlatform = false;
      }
    }

    if(this.dirX == Player.RIGHT && !this.blockedRight){
      if(this.velocityX <= Player.MAX_SPEED_X)
        this.velocityX += Player.INCREASE_SPEED_X;
    }
    else if(this.dirX == Player.LEFT && !this.blockedLeft){
      if(this.velocityX >= -Player.MAX_SPEED_X)
        this.velocityX -= Player.INCREASE_SPEED_X;
    }
    else if(this.dirX == Player.STILL){
      this.velocityX += ((this.velocityX > 0) ? Player.DECREASE_SPEED_X : -Player.DECREASE_SPEED_X);
      if(Math.abs(this.velocityX) <= Math.abs(Player.DECREASE_SPEED_X)){
        this.velocityX = 0;
      }
    }
  }
  public void draw(Graphics g){
    g.setColor(Color.gray);
    g.fillRect(this.x, this.y, this.width, this.height);
  }
}
