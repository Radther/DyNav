//import controlP5 for sliders and whatnot
import controlP5.*;
ControlP5 cp5;

// image pos
int mapX, mapY;
// create map
PImage map;
// mouseposition offset to image
int offX, offY;
// screen
int navScreen = 0;

// regular old setup
void setup()
{
	size(800,980);
	background(0);
	cp5 = new ControlP5(this);
}

void draw()
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
		petalNav();
	}
	print(frameRate + "\n");
}

void mouseReleased()
{
	petalPressed = 0;
}