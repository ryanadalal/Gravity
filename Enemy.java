import java.awt.*;
import java.util.*;

public class Enemy extends Thing{

  private boolean onPlatform;
  private boolean blockedTop;
  private boolean blockedLeft;
  private boolean blockedRight;

  private double velocityX;
  private double velocityY;

  private static final double SPEED_X = 5;

  private static final double MAX_SPEED_Y = 12;
  private static final double DECREASE_SPEED_Y = 0.2;

  public Enemy(int x, int y, int width, int height){
    super(x, y, width, height);
    this.onPlatform = false;
    this.blockedTop = false;
    this.blockedLeft = false;
    this.blockedRight = false;
    this.velocityX = 0;
    this.velocityY = 0;
  }
  public void act(Dictionary d){
    move();
    this.x += this.velocityX;
    this.y += this.velocityY;
  }
  public void move(){
    if(!this.onPlatform){
      this.velocityY += (Math.abs(this.velocityY) < Math.abs(Enemy.MAX_SPEED_Y)) ? Enemy.DECREASE_SPEED_Y : 0;
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
          }
          else if(this.getMinX() < platform.getMaxX() && this.getMinX() > platform.getMinX()){
            this.blockedLeft = true;
            this.blockedRight = false;
          }
        }
        this.leavePlat(platform);
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
    if(this.blockedLeft){
      this.velocityX = this.SPEED_X;
    }
    else if(this.blockedRight){
      this.velocityX = -this.SPEED_X;
    }
  }
  public void leavePlat(Platform plat){
    if(this.onPlatform){
      this.x -= this.getMaxY() - plat.getMinY() - 1;
    }
    else if(this.blockedTop){
      while(this.getMinY() < plat.getMaxY()){
        this.x += 1;
      }
    }
    else if(this.blockedRight){
      while(this.getMaxX() > plat.getMinX()){
        this.x -= 1;
      }
    }
    else if(this.blockedLeft){
      while(this.getMinX() < plat.getMaxX()){
        this.x += 1;
      }
    }
  }
  public void draw(Graphics g){
    g.setColor(Color.red);
    g.fillRect(this.x, this.y, this.width, this.height);
  }
}
