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
float zoomLevel = 2.0;

int petalPressed = 0;
void mapScreen()
{
	mapScreenControl();
	mapScreenRender();

}

void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>120 && mouseY<height-120)
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

void mapScreenRender()
{
	background(0);
	// update pos
	image(map, mapX, mapY, map.width*zoomLevel, map.height*zoomLevel);

}

void loadMap()
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