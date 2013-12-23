void mapScreen()
{
	// move map
	background(0);
	if(mousePressed)
	{ 
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}

	// update pos
	image(map, mapX, mapY);

	// get the offset
	offX = mouseX-mapX;
	offY = mouseY-mapY;	
}
