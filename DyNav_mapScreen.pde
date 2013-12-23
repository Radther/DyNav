// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 300;

void mapScreen()
{
	// move map
	background(0);

	mapScreenControl();

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	

	mapScreenRender();
}

void mapScreenControl()
{
	//get new pos
	if (mousePressed)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}

	// activate manu button
	if (mousePressed == true && menuX-(menuXY/2)<mouseX && mouseX<menuX+(menuXY/2) && menuY-(menuXY/2)<mouseY && mouseY<menuY+(menuXY/2)) 
	{
		menuXY = 200;
	}
}

void mapScreenRender()
{
	// update pos
	image(map, mapX, mapY);
	fill(177, 99, 163);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
}