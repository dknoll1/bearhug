import acm.graphics.*;
import java.awt.*;

public class UFO extends GCompound
{
   private GOval body, bubble, alien;
   
   public UFO( )
   {
      body = new GOval(50, 25);
      body.setFilled(true);
      body.setColor(Color.RED);
      bubble = new GOval(25, 25);
      alien = new GOval(10, 10);
      alien.setFilled(true);
      alien.setColor(Color.GREEN);
      add(bubble, 13, 0);
      add(alien, 20, 5);
      add(body, 0, 13);
   } //UFO
}