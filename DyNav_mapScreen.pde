//map number
int mapNo = 1;
// image pos
int mapX, mapY;
// create map
PImage map;


// mouseposition offset to image
int offX, offY;

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

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

	mapScreenRender();

}

void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>80 && mouseY<height-80)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}  

	// activate menu button

}

void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY, width*zoomLevel, height*zoomLevel);

}