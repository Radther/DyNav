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
int currentScreen = 1;

// regular old setup
public void setup()
{
	size(800,1280);
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
	// print(offset);
}

public void mouseReleased()
{
	petalPressed = 0;
	offset = 0;
	navPressed = 0;
	move = 0;
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
PImage mapLayer;
PImage mapUp;
PImage mapDown;
PImage greenCircle;

// menu X and Y coords
int menuX = 420;
int menuY = 400;
// menu diameter
int menuXY = 100;

//zoom and map display opens
int zoomOpen = 0;
int zoomReady = 0;
int mapOpen = 0;
int mapReady = 0;

//animation for petal
int petalAnim = 0;

// varibles for postions
int menuX1 = menuX - 250;
int menuX2 = menuX - 230;
int menuX3 = menuX - 175;
int menuX4 = menuX - 95;
int menuX5 = menuX - 0;
int menuY1 = menuY - 0;
int menuY2 = menuY - 95;
int menuY3 = menuY - 175;
int menuY4 = menuY - 230;
int menuY5 = menuY - 250;
//Petal navigation for map screen
public void petalNav()
{
	//load icons
	mainMenu = loadImage("mainMenu.png");
	search = loadImage("search.png");
	services = loadImage("services.png");
	greenCircle = loadImage("greenCircle.png");
	mapLayer = loadImage("mapLayer.png");
	mapUp = loadImage("mapUp.png");
	mapDown = loadImage("mapDown.png");

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
	if (mousePressed == true && (mouseX>menuX && mouseX<menuX+menuXY) && 
		(mouseY>menuY && mouseY<menuY+menuXY))
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
		if (mousePressed == true && (mouseX>menuX1 && mouseX<menuX1+menuXY) && 
		(mouseY>menuY1 && mouseY<menuY1+menuXY)) {
			currentScreen = 0;
		}
		//navigation
		if (mousePressed == true && (mouseX>menuX2 && mouseX<menuX2+menuXY) && 
		(mouseY>menuY2 && mouseY<menuY2+menuXY)) {
			currentScreen = 2;
		}
		//services
		if (mousePressed == true && (mouseX>menuX3 && mouseX<menuX3+menuXY) && 
		(mouseY>menuY3 && mouseY<menuY3+menuXY)) {
			currentScreen = 3;
		}
		//zoom
		if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
		(mouseY>menuY4 && mouseY<menuY4+menuXY) && zoomOpen!=1) {
			zoomOpen = 1;
			mapReady = 0;
			mapOpen = 0;
			delay(500);
		}
		//map display
		if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
		(mouseY>menuY5 && mouseY<menuY5+menuXY) && mapOpen!=1) {
			mapOpen = 1;
			zoomOpen = 0;
			zoomReady = 0;
			delay(500);
		}
		if (zoomReady == 1) {
			if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
			(mouseY>menuY4-(menuXY/2) && mouseY<menuY4+(menuXY/2))) {
				zoomLevel = zoomLevel + .1f;
				delay(50);
			}
			if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
			(mouseY>menuY4+(menuXY/2) && mouseY<menuY4+menuXY+(menuXY/2))) {
				zoomLevel = zoomLevel - .1f;
				delay(50);
			}
		}
		if (mapReady == 1) {
			if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
			(mouseY>menuY5-(menuXY/2) && mouseY<menuY5+(menuXY/2))) {
				if (mapLevel == 3) {
					mapLevel = 1;
					delay(50);
				}
				else {
					mapLevel = mapLevel + 1;
					delay(50);
				}
			}
			if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
			(mouseY>menuY5+(menuXY/2) && mouseY<menuY5+menuXY+(menuXY/2))) {
				if (mapLevel == 1) {
					mapLevel = 3;
					delay(50);
				}
				else {
					mapLevel = mapLevel - 1;
					delay(50);
				}

			}
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
		image(greenCircle, menuX4+25, menuY4+(menuXY/2));
		image(greenCircle, menuX4-25, menuY4-(menuXY/2));
		zoomReady = 1;
	}
	else {
		image(greenCircle, menuX4, menuY4);
	}
	//map display
	if (mapOpen == 1) {
		image(mapDown, menuX5, menuY5+(menuXY/2));
		image(mapUp, menuX5, menuY5-(menuXY/2));
		mapReady = 1;
	}
	else {
		image(mapLayer, menuX5, menuY5);
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

public void mapCenter()
{
	// center the map
	mapX = (width-map.width)/2;
	mapY = (height-map.height)/2;
}
//map number
int mapLevel = 1;
// image pos
float mapX, mapY;
// create map
PImage map;
//where touched
int touchedX,touchedY;

// mouseposition offset to image
float offX, offY;
int offset = 0;

//zoomLevel level
float zoomLevel = 1.0f;

int petalPressed = 0;
public void mapScreen()
{
	mapScreenControl();
	mapScreenRender();

}

public void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>80 && mouseY<height-80)
	{
		if (offset == 0) {
			offX = mouseX-mapX;
			offY = mouseY-mapY;	
			offset = 1;
		}
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}  

	// activate menu button

}

public void mapScreenRender()
{
	background(0);
	// update pos
	image(map, mapX, mapY, map.width*zoomLevel, map.height*zoomLevel);

}

public void loadMap()
{
	if (mapLevel == 1) {
		//load map 1
		map = loadImage("map1.png");
	}
	if (mapLevel == 2) {
		// load map 2
		map = loadImage("map2.png");
	}
	if (mapLevel == 3) {
		map = loadImage("map3.png");
	}

}
public void mousePressed()
{

}
int rows;
int move = 0;
int moveOff = 0;
int navPressed = 0;
int scriptRun = 0;
PImage back;
public void navScreen()
{
	int rows = (height/80) + 1;
	back = loadImage("back.png");


	// rows = 16;
	// print(rows);
	navScreenControl();
	navScreenRender();	
	if (scriptRun == 0) {

		scriptRun = 1;
	}
}
public void navScreenControl()
{
	if (mousePressed) {
		if (navPressed == 0) {
			moveOff = -mouseY;
			navPressed = 1;
		}
		move = mouseY+moveOff;
	}
	if (mousePressed && mouseX>700 && mouseX<780 && mouseY<80) {
		open("/Users/Tom/Documents/pi.py");		
	}
}

public void navScreenRender()
{
	background(255);

 	//lines
 	stroke(0);
 	strokeWeight(1);
 	line(0, 80*2+move, width, 80*2+move);
 	textAlign(LEFT, CENTER);
 	textSize(24);
 	fill(0);
 	text("1. From the entrence to left. Walk until you see the stairs.", 40, 80*1+40+move);
 	line(0, 80*3+move, width, 80*3+move);
 	text("2. Walk past the stairs and turn left. You will see starbucks.", 40, 80*2+40+move);
 	line(0, 80*4+move, width, 80*4+move);
 	text("3. ECG-15 will be on the right next to the starbucks", 40, 80*3+40+move);
 	line(0, 80*5+move, width, 80*5+move);
 	line(0, 80*6+move, width, 80*6+move);
 	line(0, 80*7+move, width, 80*7+move);
 	line(0, 80*8+move, width, 80*8+move);
 	line(0, 80*9+move, width, 80*9+move);
 	line(0, 80*10+move, width, 80*10+move);
 	line(0, 80*11+move, width, 80*11+move);
 	line(0, 80*12+move, width, 80*12+move);
 	line(0, 80*13+move, width, 80*13+move);
 	line(0, 80*14+move, width, 80*14+move);
 	line(0, 80*15+move, width, 80*15+move);
 	line(0, 80*16+move, width, 80*16+move);

 	//other stuff
 	noStroke();
 	fill(0, 152, 116);
 	rect(0, 0, width, 80);
 	image(back, 0, 0);

}
public void servScreen()
{
	print("welcome");
}
int textPressed = 0;
public void startScreen()
{
	startScreenControl();
	startScreenRender();

}

public void startScreenControl()
{
	if (mousePressed == true && mouseX>350 && mouseX<450 && 
	mouseY>height/2+40 && mouseY<height/2+100) {
		currentScreen =1;
	}
	if (mousePressed == true && mouseX>100 && mouseX<700 && 
	mouseY>(height/2)-40 && mouseY<height/2) {
		textPressed = 1;
	}
}

public void startScreenRender()
{

	background(0, 152, 116);
	//render text box
	rectMode(CORNER);
	stroke(177, 99, 163);
	strokeWeight(5);
	fill(0, 152, 116);
	rect(100, (height/2)-40, 600, 40,0,0,20,0);
	noStroke();
	rect(98, (height/2)-50, 605, 30);
	//text
	fill(50);
	textSize(25);
	if (textPressed == 1) {
		fill(30);
		text("ECG-15 |", (width/2)-280, (height/2)-15);	
	}
	else {
		text("What are you looking for?_", (width/2)-280, (height/2)-15);
	}
	// go button
	fill(177, 99, 163);
	rect(350, height/2+40, 100, 60,15,15,15,15);
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
