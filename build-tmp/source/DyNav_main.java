import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DyNav_main extends PApplet {

//import controlP5 for sliders and whatnot

ControlP5 cp5;

// image pos
int mapX, mapY;
// create map
PImage map;
// mouseposition offset to image
int offX, offY;
// current screen
int currentScreen = 0;

// regular old setup
public void setup()
{
	size(800,980);
	background(0);
	cp5 = new ControlP5(this);
}

public void draw()
{
	//startscreen
	if (currentScreen == 0)
	{
		startScreen();
	}
	//mapscreen
	if (currentScreen == 1) 
	{
		loadMap();
		mapScreen();
		petalNav();
	}
	// navigation screen
	if (currentScreen == 2) {
		navScreen();
	}
	// print(frameRate + "\n");
}

public void mouseReleased()
{
	petalPressed = 0;
}
// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 60;
int activated =0;

//Petal navigation for map screen
public void petalNav()
{
	if (activated == 0) {
		petalNavDeactivated();
	}
	if (activated == 1) {
		petalNavActivated();
	}

}

public void petalNavActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
	// create the mini menu buttons
	ellipse(menuX-180, menuY-0, menuXY, menuXY);
	ellipse(menuX-155, menuY-90, menuXY, menuXY);
	ellipse(menuX-90, menuY-155, menuXY, menuXY);
	ellipse(menuX-0, menuY-180, menuXY, menuXY);
}

public void petalNavDeactivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
}
public void loadMap()
{
	if (mapNo == 1) {
		//load map 1
		map = loadImage("map1.jpg");
	}
	if (mapNo == 2) {
		// load map 2
		map = loadImage("map2.gif");
	}

}
public void mapCenter()
{
	// center the map
	mapX = (width-map.width)/2;
	mapY = (height-map.height)/2;
}
//map number
int mapNo = 1;

//zoom level
float zoom = 1.0f;

int petalPressed = 0;
public void mapScreen()
{
	// move map
	background(0);

	// cp5.addSlider("zoom")
 //    	.setPosition(100,50)
 //    	.setRange(.5,5)
 //    	;

	mapScreenControl();

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

	mapScreenRender();

}

public void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>80 && mouseY<height-80)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	} 

	// activate menu button
	if (mousePressed == true && menuX-(menuXY/2)<mouseX && mouseX<menuX+(menuXY/2) && menuY-(menuXY/2)<mouseY && mouseY<menuY+(menuXY/2)) 
	{
		if (petalPressed == 0) 
		{
			if (activated == 0) {
				petalPressed = 1;
			 	activated = 1;
			 }
			 else {
			 	petalPressed = 1;
			 	activated = 0;
			 	
			 }
			 
		}

	}

}

public void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY, width*zoom, height*zoom);

}
public void mousePressed()
{

}
int rows;
 public void navScreen()
 {
 	background(255);
 	noStroke();
 	fill(0, 152, 116);
 	rect(0, 0, width, 80);

	int rows = (height/80) + 1;
	// print(rows);
	
}

public void nav_screenRender()
{
		for (int i = 2; i<rows; i = i + 1) {
		stroke(0);
		strokeWeight(1);
		strokeCap(ROUND);
		line(0, i*80, width-0, i*80);

	}

 }
public void startScreen()
{

	background(0, 152, 116);
	startScreenControl();
}

public void startScreenControl()
{
	if (mousePressed == true) {
		currentScreen =1;
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
