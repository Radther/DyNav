//import controlP5 for sliders and whatnot
import controlP5.*;
ControlP5 cp5;

// current screen
int currentScreen = 0;

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

void mouseReleased()
{
	petalPressed = 0;
}