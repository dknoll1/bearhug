/*
*  Daniel Knoll
*  Date: October 4, 2021
*  Description: Screensaver animation with 4 graphics classes :) 
*  Concepts taken from lectures, mostly randombox
*/
import java.awt.Color;
import acm.graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import acm.program.GraphicsProgram;
import java.util.Random;



public class Screensaver extends GraphicsProgram implements ActionListener, KeyListener
{ //from testing window sizes i've found things smaller than 200 for x/y is broken because of
   //not converting everything into decimal numbers perhaps
   final int WINDOW_X = 800;
   final int WINDOW_Y = 600;
   final double BOX_SIZE = (int) ((WINDOW_X + WINDOW_Y) / 2)*.1;
   final double INC_SIZE = (int) BOX_SIZE*.1; // originally tested with *.1
   int x = 0, y = 0, velX = 0, velY = 0;
   public static void main(String[] args)
   {
      Screensaver program = new Screensaver();
      program.start();
   }
   public void init()
   {
      setTitle("Screensaver");
      setSize(WINDOW_X, WINDOW_Y);
      GRect windowBorder = new GRect(0,0,WINDOW_X,WINDOW_Y);
      add(windowBorder);
      setBackground(Color.RED);
      windowBorder.setColor(Color.BLACK);
      windowBorder.setFillColor(Color.BLACK);
      windowBorder.setFilled(true);
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
   }
   public void run()
   {
      Random genRnd = new Random();
      GPoint tempXY = new GPoint();
      double xRand = 0;
      double yRand = 0;
      //making box
      GRect box = new GRect(WINDOW_X/2-BOX_SIZE/2,WINDOW_Y/2-BOX_SIZE/2,BOX_SIZE,BOX_SIZE);
      add(box);
      box.setColor(Color.RED);
      box.setFillColor(Color.RED);
      box.setFilled(true);
      //make circle
      GOval circle = new GOval(WINDOW_X/2-BOX_SIZE/2,WINDOW_Y/2-BOX_SIZE/2,BOX_SIZE,BOX_SIZE);
      add(circle);
      circle.setColor(Color.WHITE);
      circle.setFillColor(Color.WHITE);
      circle.setFilled(true);
      //making triangle mess
      GPoint[] maketriangle = new GPoint[3];
      maketriangle[0] = box.getLocation();
      maketriangle[1] = box.getLocation();
      maketriangle[2] = box.getLocation();
      maketriangle[0].setLocation(BOX_SIZE/2, 0);
      maketriangle[1].setLocation(BOX_SIZE, BOX_SIZE);
      maketriangle[2].setLocation(0, BOX_SIZE);
      GPolygon triangle = new GPolygon(maketriangle);
      add(triangle);
      triangle.setColor(Color.YELLOW);
      triangle.setFillColor(Color.YELLOW);
      triangle.setFilled(true);
      triangle.setLocation(WINDOW_X/2 - BOX_SIZE/2, WINDOW_Y/2 - BOX_SIZE/2);
      //System.out.println(triangle.getX() + ", " + triangle.getY()+ "");
      //make line
      GLine line = new GLine(box.getX(), box.getY(), box.getX()+BOX_SIZE, box.getY()+BOX_SIZE);
      add(line);
      line.setColor(Color.BLUE);
      pause(2500);
      //move things around randomly but within the window
      while(true)
      {
         bouncytriangle(tempXY, genRnd, triangle, xRand, yRand);
         bouncyline(tempXY, genRnd, line, xRand, yRand);
         bouncycircle(tempXY, genRnd, circle, xRand, yRand);
         
      }
   }
   public void keyTyped(KeyEvent e)
   {
   
   }
   public void keyReleased(KeyEvent e)
   {
   
   }
   public void bouncycircle(GPoint temp, Random gen, GOval circle, double xRand, double yRand)
   {
      xRand = gen.nextInt() % INC_SIZE;
      yRand = gen.nextInt() % INC_SIZE;
      temp = circle.getLocation();
      while(temp.getX() + xRand < 0 || temp.getX() + xRand > WINDOW_X - BOX_SIZE)
      {         
         xRand = gen.nextInt() % INC_SIZE;
      }
      while(temp.getY() + yRand < 0 || temp.getY() + yRand > WINDOW_Y - BOX_SIZE)
      {
         yRand = gen.nextInt() % INC_SIZE;
      } 
      for(int i = 0; i != xRand; )
      {
         pause(1);
         if(xRand > 0)
         {
            circle.move(1, 0);
            i++;
         }
         else
         {
            circle.move(-1,0);
            i--;
         }
      }
      for(int i = 0; i != yRand; )
      {
         pause(1);
         if(yRand > 0)
         {
            circle.move(0, 1);
            i++;
         }
         else
         {
            circle.move(0,-1);
            i--;
         }
      }
   }
   public void bouncytriangle(GPoint temp, Random gen, GPolygon tri, double xRand, double yRand)
   {
      xRand = gen.nextInt() % INC_SIZE;
      yRand = gen.nextInt() % INC_SIZE;
      temp = tri.getLocation();
      while(temp.getX() + xRand < 0 || temp.getX() + xRand > WINDOW_X - BOX_SIZE)
      {         
         xRand = gen.nextInt() % INC_SIZE;
      }
      while(temp.getY() + yRand < 0 || temp.getY() + yRand > WINDOW_Y - BOX_SIZE)
      {
         yRand = gen.nextInt() % INC_SIZE;
      } 
      for(int i = 0; i != xRand; )
      {
         pause(1);
         if(xRand > 0)
         {
            tri.move(1, 0);
            i++;
         }
         else
         {
            tri.move(-1,0);
            i--;
         }
      }
      for(int i = 0; i != yRand; )
      {
         pause(1);
         if(yRand > 0)
         {
            tri.move(0, 1);
            i++;
         }
         else
         {
            tri.move(0,-1);
            i--;
         }
      }
   }
   public void bouncyline(GPoint temp, Random gen, GLine line, double xRand, double yRand)
   {
      xRand = gen.nextInt() % INC_SIZE;
      yRand = gen.nextInt() % INC_SIZE;
      temp = line.getLocation();
      while(temp.getX() + xRand < 0 || temp.getX() + xRand > WINDOW_X - BOX_SIZE)
      {         
         xRand = gen.nextInt() % INC_SIZE;
      }
      while(temp.getY() + yRand < 0 || temp.getY() + yRand > WINDOW_Y - BOX_SIZE)
      {
         yRand = gen.nextInt() % INC_SIZE;
      } 
      for(int i = 0; i != xRand; )
      {
         pause(1);
         if(xRand > 0)
         {
            line.move(1, 0);
            i++;
         }
         else
         {
            line.move(-1,0);
            i--;
         }
      }
      for(int i = 0; i != yRand; )
      {
         pause(1);
         if(yRand > 0)
         {
            line.move(0, 1);
            i++;
         }
         else
         {
            line.move(0,-1);
            i--;
         }
      }
   }
   public void actionPerformed(ActionEvent e)
   {
      x = x + velX;
      y = y + velY;
   }
   public void keyPressed(KeyEvent e)
   {
      int c = e.getKeyCode();
      
      if (c == KeyEvent.VK_LEFT)
      {
         velX = -1;
         velY = 0;
      }
      if (c == KeyEvent.VK_UP)
      {
         velX = 0;
         velY = -1;
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velX = 1;
         velY = 0;
      }
      if (c == KeyEvent.VK_DOWN)
      {
         velX = 0;
         velY = 1;
      }
   }
}