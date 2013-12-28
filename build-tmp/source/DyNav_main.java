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
	if (currentScreen == 3) {
		servScreen();
	}
	// print(frameRate + "\n");
}

public void mouseReleased()
{
	petalPressed = 0;
}

public void delay(int delay)
{
  int time = millis();
  while(millis() - time <= delay);
}
//is it on or not
int activated =0;
//create images
PImage mainMenu;
PImage search;
PImage services;
PImage navigation;
PImage zoom;
PImage zoomPlus;
PImage zoomMinus;
PImage mapUp;
PImage mapDown;
PImage greenCircle;

// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 60;

//zoom and map display opens
int zoomOpen = 0;
int mapOpen = 0;

// varibles for postions
int menuX1 = menuX - 180;
int menuX2 = menuX - 165;
int menuX3 = menuX - 130;
int menuX4 = menuX - 70;
int menuX5 = menuX - 0;
int menuY1 = menuY - 0;
int menuY2 = menuY - 70;
int menuY3 = menuY - 130;
int menuY4 = menuY - 165;
int menuY5 = menuY - 180;
//Petal navigation for map screen
public void petalNav()
{
	//load icons
	mainMenu = loadImage("mainMenu.png");
	search = loadImage("search.png");
	services = loadImage("services.png");
	greenCircle = loadImage("greenCircle.png");

	petalNavControl();

}

// controls everything
public void petalNavControl(){
	// open or closed
	if (activated == 0) {
		petalNavDeactivated(); //open
	}
	if (activated == 1) {
		petalNavActivated(); //closed
	}

	// pressed
	if (mousePressed == true && (mouseX>menuX && mouseX<menuX+menuXY) && (mouseY>menuY && mouseY<menuY+menuXY)) //menuX-(menuXY/2)<mouseX && mouseX<menuX+(menuXY/2) && menuY-(menuXY/2)<mouseY && mouseY<menuY+(menuXY/2)) 
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

	//control button actions
	if (activated == 1) {
		//search
		if (mousePressed == true && (mouseX>menuX1 && mouseX<menuX1+menuXY) && (mouseY>menuY1 && mouseY<menuY1+menuXY)) {
			currentScreen = 0;
		}
		//navigation
		if (mousePressed == true && (mouseX>menuX2 && mouseX<menuX2+menuXY) && (mouseY>menuY2 && mouseY<menuY2+menuXY)) {
			currentScreen = 2;
		}
		//services
		if (mousePressed == true && (mouseX>menuX3 && mouseX<menuX3+menuXY) && (mouseY>menuY3 && mouseY<menuY3+menuXY)) {
			currentScreen = 3;
		}
		//zoom
		if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && (mouseY>menuY4 && mouseY<menuY4+menuXY)) {
			zoomOpen = 1;
			mapOpen = 0;
		}
		//map display
		if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && (mouseY>menuY5 && mouseY<menuY5+menuXY)) {
			mapOpen = 1;
			zoomOpen = 0;
		}
	}
}

public void petalNavActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	image(mainMenu, menuX, menuY);

	// create the mini menu buttons
	// search
	image(search, menuX1, menuY1);
	// navigation
	image(greenCircle, menuX2, menuY2);
	// services
	image(services, menuX3, menuY3);
	//zoom
	if (zoomOpen == 1) {
		image(greenCircle, menuX4, menuY4+(menuXY/2));
		image(greenCircle, menuX4, menuY4-(menuXY/2));
	}
	else {
		image(greenCircle, menuX4, menuY4);
	}
	//map display
	if (mapOpen == 1) {
		image(greenCircle, menuX5, menuY5+(menuXY/2));
		image(greenCircle, menuX5, menuY5-(menuXY/2));
	}
	else {
		image(greenCircle, menuX5, menuY5);
	}


}

public void petalNavDeactivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	zoomOpen = 0;
	mapOpen = 0;
	image(mainMenu, menuX, menuY);
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
// image pos
int mapX, mapY;
// create map
PImage map;


// mouseposition offset to image
int offX, offY;

//zoomLevel level
float zoomLevel = 1.0f;

int petalPressed = 0;
public void mapScreen()
{
	background(0);

	// cp5.addSlider("zoomLevel")
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

}

public void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY, width*zoomLevel, height*zoomLevel);

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
public void servScreen()
{
	print("welcome");
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
