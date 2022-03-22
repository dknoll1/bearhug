/*	Daniel Knoll
 *	SDEV218 Final
 *	Nov 17, 21
 *	Class for enemies in bearhug (Main.java)
 */

import acm.graphics.*;
import java.awt.*;
import java.util.Random;

public class Bear extends GCompound
{
   final int   WINDOW_X   =  800;
   final int   WINDOW_Y   =  600;
   final int   HIT_BOX    =  25;
   final int   WAIT       =  50;
   final int   MV_AMT     =  HIT_BOX;

   //Stuff needed for the movement method
   public GImage  bear;
   Random         gen;
   double         xRand, yRand;
   GPoint         temp;
   
   public Bear()
   {
      // constructor to make bears
		bear = new GImage("images/tinyBear.png", 0, 0);
		add(bear, 0, 0);
      gen = new Random();
   }

   public void bouncyBear(int difficulty)
   {  /*
         This method describes the bears movement which could have been done in a more
         linear way, but I liked the randomness and unpredictability from this. 
         I think with multithreading this could have been of better quality, but I wanted
         the game loop to refresh enough to keep control of the hero  
       */
      xRand = gen.nextInt(MV_AMT * 2) - MV_AMT;
      yRand = gen.nextInt(MV_AMT * 2) - MV_AMT;
      temp = this.bear.getLocation();
      while(temp.getX() + xRand < 0 || temp.getX() + xRand > WINDOW_X - HIT_BOX)
      {         
         xRand = gen.nextInt(MV_AMT * 2) - MV_AMT;
      }
      while(temp.getY() + yRand < 0 || temp.getY() + yRand > WINDOW_Y - (HIT_BOX * 4))
      {
         yRand = gen.nextInt(MV_AMT * 2) - MV_AMT;
      } 
      
      this.bear.move(xRand, yRand);
   }
}