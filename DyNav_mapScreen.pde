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
float zoomLevel = 1.0;

int petalPressed = 0;
void mapScreen()
{
	background(0);

	// cp5.addSlider("zoomLevel")
 //    	.setPosition(100,50)
 //    	.setRange(.5,5)
 //    	;

	mapScreenControl();

	//where pressed
	if (mousePressed) {
		touchedX = mouseX;
		touchedY = mouseY;
	}
	// print(mouseX + "\n");
	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	
	// offX = 500;
	// offY = 500;
	mapScreenRender();

}

void mapScreenControl()
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

void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY, map.width*zoomLevel, map.height*zoomLevel);

}
