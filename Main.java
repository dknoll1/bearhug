/*	Daniel Knoll
 *	SDEV218 Final
 *	Nov 17, 21
 *	game that involves teddy bears and not dying / hugging them to get points
 */

import java.util.Random;
import java.lang.Math;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.util.SoundClip;

import java.awt.Color;
import java.awt.event.*;

public class Main extends GraphicsProgram
{
   // some stuff that is kind of always around
   GLabel   keyLabel;
   GLabel   centerLabel;
   GOval    hugCirc;
   
   final int   WINDOW_X   =  800;
   final int   WINDOW_Y   =  600;
   final int   HIT_BOX    =  25;
   final int   WAIT       =  50;
   final int   MV_AMT     =  HIT_BOX;
   
   int         score, highScore, xMove, yMove, ballX, ballY, i, j, difficulty, bearCount, hugCircDel;
   
   Heroine     girl;
   Bear        bear;
   Bear[]      bears;
   
   
   SoundClip p = new SoundClip("audio/chew.wav");

   public static void main(String[] args)
   {
      Main game = new Main();
      game.start();
   }
   public void init()
   {
      //window definitions
      setTitle("RPG");
      setSize(WINDOW_X+HIT_BOX, WINDOW_Y+HIT_BOX*4);
      GRect windowBorder = new GRect(0,0,WINDOW_X,WINDOW_Y);
      add(windowBorder);
      
      //setting some integers to zero and creating some labels to inform the user
      xMove = yMove = score = highScore = 0;
      addKeyListeners();
      keyLabel = new GLabel("Spacebar: Hug bears | Highscore: " + highScore + " | Score: " + score);
      keyLabel.setFont("*-BOLD-25");
      centerLabel = new GLabel("Level 1!");
      centerLabel.setFont("*-BOLD-25");
      add(centerLabel, WINDOW_X/2 - HIT_BOX * 2, WINDOW_Y/2);
      add(keyLabel, 0, WINDOW_Y + HIT_BOX);
      pause(WAIT * 27);
      centerLabel.setVisible(false);
      
      //instantiating some custom classes for hero and enemies
      girl = new Heroine();
      add(girl, 500, 400);
      girl.setLocation(500,400);
      
      bearCount = 0;
      bears = new Bear[3];
      for (int i = 0; i < 3; i++)
      {
         bears[i] = new Bear();
         add(bears[i], i * 100, 50);
         bearCount++;
      }
      
      // the attack is a little circle that is mostly hidden during the game loop
      hugCirc  =  new GOval(50, 50);
      add(hugCirc, 400,400);
      hugCirc.setVisible(false);
      
      difficulty = 1;
   }
   public void run()
   {
      //play a music 1 time
      SoundClip x = new SoundClip("audio/medium.wav");
      x.setVolume(1);
      x.play();

      //the main game loop is below
      while(!girl.gameOver)
      {
         //girl turn to move
         girl.showCharacter(xMove, yMove);
         girl.girl.move(xMove, yMove);
         
         //'hold' movement direction for attack parameter
         if (xMove != 0)
         {
            ballX = xMove;
            ballY = 0;
         }
         else if (yMove != 0)
         {
            ballY = yMove;
            ballX = 0;
         }
 
         //bear turn to move
         for (int i = 0; i < 3; i++)
         {
            bears[i].bouncyBear(difficulty);
         }
         xMove = yMove = 0;
         
         //check if anyone getting hurt
         for (int i = 0;i < 3; i++)
         {
            if (collides(girl, bears[i]))
            {
               pause(WAIT);
               girl.loseLife();
               score -= 10;
            }
         }
         
         //check if level progresses
         if (bearCount == 0)
         {
            x.setVolume(1);
            x.play();      
            setLevel();
         }

         //game loop pause
         pause(WAIT);
         
         //remove attack from visibility but not so quick that it isn't ever seen
         if (hugCirc.isVisible())
         {
            hugCircDel++;
            if (hugCircDel >= 3)
            {
               hugCirc.setVisible(false);
            }
         }
         keyLabel.setLabel("Spacebar: Hug bears | Highscore: " + highScore + " | Score: " + score);
      } // end game loop
      
      // highscore things for if this project was needing a menu/try again feature
      if (score > highScore)
      {
         highScore = score;
      }
      keyLabel.setLabel("Spacebar: Hug bears | Highscore: " + highScore + " | Score: " + score);
   }
   public void setLevel()
   {     
      //set us up the level each time the bears are cleared       
      score += 100;
      difficulty++;
      hugCirc.setVisible(false);
      centerLabel.setLabel("Level " + difficulty);
      centerLabel.setVisible(true);
      keyLabel.setLabel("Nice! Level " + difficulty + " | Highscore: " + highScore + " | Score: " + score);
      
      for (i = 0; i < 3; i++)
      {
         bears[i].setLocation(i * 100, 50);
         bearCount++;
      }
      girl.girl.setLocation(0, 0);
      
      // some magic number to define how long you must wait for the level to reset / progress
      pause(WAIT * 27);
      centerLabel.setVisible(false);
   }
   
   public void keyPressed(KeyEvent k)
   {  /*
         Keyboard event handlers for arrow keys/spacebar attack
      */
      int key = k.getKeyCode();
      
      if (key == KeyEvent.VK_LEFT)
      {
         xMove = -MV_AMT;
      }
      if (key == KeyEvent.VK_UP)
      {
         yMove = -MV_AMT;
      }
      if (key == KeyEvent.VK_RIGHT)
      {
         xMove = MV_AMT;
      }
      if (key == KeyEvent.VK_DOWN)
      {
         yMove = MV_AMT;
      }
      if (key == KeyEvent.VK_SPACE)
      {
         throwBall(ballX, ballY);
      }
   }
   
   public boolean collides(GObject girl, GObject x)
   {  //basically taken from the free java book, determines when there's collision 
      GRectangle girlBox = girl.getBounds();
      GRectangle bearBox = x.getBounds();
      if (girlBox.intersects(bearBox) == true) return true;
      else return false;
   }
   
   public void throwBall(int x, int y)
   {  //the point 500,400 is 0,0 for the heroine because that's where she starts out
      //this was a major confusion early on in making this function
      // but basically just play a little sound and throw a little ball at enemies
      p.setVolume(1);
      p.play();
      hugCirc.setLocation(girl.girl.getX() + 500, girl.girl.getY() + 400);
      hugCirc.setVisible(true);
      for (i = 0; i < 3; i++)
      {
         hugCirc.move(x + (x/MV_AMT * MV_AMT), y + (y/MV_AMT * MV_AMT));
         for (j = 0; j < 3; j++)
         {
            if (collides(hugCirc, bears[j]))
            {
               bearCount--;
               bears[j].setLocation(9999,9999);
            }
         }
         pause(11);
         hugCircDel = 0;
      }
   }
}