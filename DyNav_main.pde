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
	background(0);
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
	}
	print(frameRate + "\n");
}
