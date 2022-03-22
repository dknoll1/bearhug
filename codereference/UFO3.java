//UFO3.java
import acm.graphics.*;
import java.awt.*;
public class UFO3 extends GCompound
{
   private GImage girl;
   
   public UFO3()
   {
      girl = new GImage("purpFront.png", 50, 25);
      add(girl, 10, 5);
   }
}