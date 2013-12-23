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
	size(800,1280);
	map = loadImage("map.jpg");
	mapCenter();
	background(0);
}

void draw()
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
