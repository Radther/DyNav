void draw()
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
}