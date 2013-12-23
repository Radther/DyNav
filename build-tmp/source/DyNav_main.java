import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DyNav_main extends PApplet {

// image pos
int mapX, mapY;
// create map
PImage map;
// mouseposition offset to image
int offX, offY;
// screen
int navScreen = 0;

// regular old setup
public void setup()
{
	size(800,1280);
	map = loadImage("map.jpg");
	mapCenter();
	background(0);
}

public void draw()
{
	if (navScreen == 0)
	{
		startScreen();
	}
	if (navScreen == 1) 
	{
		mapScreen();
	}
}

public void mapCenter()
{
	// center the map
	mapX = (width-map.width)/2;
	mapY = (height-map.height)/2;
}
public void mapScreen()
{
	mapCenter();
	// move map
	background(0);
	if(mousePressed)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}

	// update pos
	image(map, mapX, mapY);

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

}
public void mousePressed()
{

}
public void startScreen()
{
	background(0, 152, 116);

	startScreenControl();
}

public void startScreenControl()
{
	if (mousePressed == true) {
		navScreen =1;
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DyNav_main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
