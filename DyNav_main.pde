// image pos
int mapX, mapY;
// create map
PImage map;
// mouseposition offset to image
int offX, offY;

// regular old setup
void setup()
{
	size(800,1200);
	map = loadImage("map.jpg");
	mapCenter();
	background(0);
}