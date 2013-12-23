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
	background(0);
}

public void draw()
{
	//startscreen
	if (navScreen == 0)
	{
		startScreen();
	}
	//mapscreen
	if (navScreen == 1) 
	{
		loadMap();
		mapScreen();
	}
	print(frameRate + "\n");
}
public void loadMap()
{
	// load map
	map = loadImage("map.jpg");
}
public void mapCenter()
{
	// center the map
	mapX = (width-map.width)/2;
	mapY = (height-map.height)/2;
}
// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 300;

// map in use
int mapDraged = 0;

public void mapScreen()
{
	// move map
	background(0);

	mapScreenControl();

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

	//map in use no longer
	mapDraged = 0;
	//button in use no long

	mapScreenRender();
}

public void mapScreenControl()
{
	//get new pos
	if (mousePressed)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	 	mapDraged = 1;
	}
	
	// activate manu button
	if (mousePressed == true && menuX-(menuXY/2)<mouseX && mouseX<menuX+(menuXY/2) && menuY-(menuXY/2)<mouseY && mouseY<menuY+(menuXY/2) && mapDraged == 0) 
	{
		menuXY = 200;
	}


}

public void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY);
	fill(177, 99, 163);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
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
