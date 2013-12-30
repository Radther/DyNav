# Documentation

## DyNav_main
### setup
`void setup()
{
	size(800,980);
	background(0);
	cp5 = new ControlP5(this);
}`
*sets up the code*

### draw
`void draw()
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
}`
*This draws the basic items and will call the other screens, this is a very important part of code as it is what loads the screens*

### mouseReleased
`void mouseReleased()
{
	petalPressed = 0;
	offset = 0;
}`
*This checks to see if the mouse has been released and then sets the variables back to zero*

### delay
`void delay(int delay)
{
  int time = millis();
  while(millis() - time <= delay);
}`
*This allows the program to have a delay, currently this isn’t being used*

## DyNav_mapScreen
### mapScreen
`void mapScreen()
{
	// move map
	background(0);

	// cp5.addSlider("zoom")
 //    	.setPosition(100,50)
 //    	.setRange(.5,5)
 //    	;

	mapScreenControl();

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

	mapScreenRender();

}`
*This is the function that controls the map screen, there are lots of elements to the map screen and this function is the overarching thing*

### mapScreenControl
`void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>80 && mouseY<height-80)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	} 

	// activate menu button
	if (mousePressed == true && menuX-(menuXY/2)<mouseX && mouseX<menuX+(menuXY/2) && menuY-(menuXY/2)<mouseY && mouseY<menuY+(menuXY/2)) 
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

}`
*This function is used to control the map screen. It contains all the buttons and button states e.g. weather the petal is open or not.*

### mapScreenRender
`void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY, width*zoom, height*zoom);

}`
*This function is used to render the map, this was also originally going to render the petal before the petal was moved to its own section as it became more complex with potential animation and whatnot*


