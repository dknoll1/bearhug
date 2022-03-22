//GameLoopSynchronized.java
import acm.program.*;
import acm.graphics.*;
import java.awt.*;
public class GameLoopSynchronized extends GraphicsProgram
{
   final int WAIT = 1000;
   UFO3 u1;
   
   public static void main(String[] args)
   {
      GameLoopSynchronized ufos = new GameLoopSynchronized();
      ufos.start();
   }
   public void init( )
   {
      u1 = new UFO3();
      add(u1, 10, 100);
   } //init
   public void run( )
   {
      while(true)
      {
      pause(WAIT);
      u1.move(111, 0);
      } //game loop
   } //run
}