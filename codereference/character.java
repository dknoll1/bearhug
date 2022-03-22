import java.util.Random;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.Color;

public class character extends GraphicsProgram
{
   public GImage drawChar()
   {
      GImage girl = new GImage("girlFront.png");
      return(girl);
   }
   
   public void moveChar(GImage character, int x, int y)
   {
      character.move(character.getX() + x, character.getY() + y);
   }
}