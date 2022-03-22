/*	Daniel Knoll
 *	SDEV218 Final
 *	Nov 17, 21
 *	heroine character in main.java game
 */

import acm.graphics.*;
import java.awt.*;
import java.util.Random;

public class Heroine extends GCompound
{
   final int   WINDOW_X   =  800;
   final int   WINDOW_Y   =  600;
   final int   HIT_BOX    =  25;
   final int   WAIT       =  50;
   final int   MV_AMT     =  HIT_BOX;
   
   //stuff girl needs to determine gameloop condition and death whenabouts  
   public GImage  girl;
   GRectangle     girlBox;
   int            hp;
   int            lives;
   boolean        gameOver;       
   int            i;
   
   public Heroine()
   {  // constructor for the heroine 
		girl     =  new GImage("girlFront.png", 0, 0);
      hp       =  3;
      lives    =  3;
      gameOver =  false;
		add(girl);
   }
   
   public void showCharacter(int xMove, int yMove)
   {  /*
         This will show us the right sprite depending on where the user is moving
      */
      if (yMove < 0)
         this.girl.setImage("images/girlBack.png");
      if (yMove > 0)
         this.girl.setImage("images/girlFront.png");
      if (xMove < 0)
         this.girl.setImage("images/girlLeft.png");
      if (xMove > 0)
         this.girl.setImage("images/girlRight.png");
   }
   
   public void loseLife()
   {  // things to happen when the heroine loses life
      this.hp -= 1;
      this.girl.move(25,25);
      this.showCharacter(0, 1);
      if (this.hp < 0)
      {
         this.dies();
      }
   }
   
   public void dies()
   {  //need to reset game state to default after death
      this.girl.setImage("girlDead.png");
      this.lives        -= 1;
      if (this.lives <= 0)
      {
         gameOver = true;
         System.out.printf("Game over!");
      }
      
      
      pause(1111);
      System.out.printf("U Died!");
      this.hp           = 3;
      this.girl.setLocation(0, 0);
      this.showCharacter(0, 1);
      pause(1111);
   }
}