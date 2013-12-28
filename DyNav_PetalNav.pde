//is it on or not
int activated =0;
//create images
PImage mainMenu;
PImage search;
PImage services;
PImage navigation;
PImage zoom;
PImage zoomPlus;
PImage zoomMinus;
PImage mapUp;
PImage mapDown;
PImage greenCircle;

// menu X and Y coords
int menuX = 420;
int menuY = 400;
// menu diameter
int menuXY = 60;

//zoom and map display opens
int zoomOpen = 0;
int mapOpen = 0;

//animation for petal
int petalAnim = 0;

// varibles for postions
int menuX1 = menuX - 180;
int menuX2 = menuX - 165;
int menuX3 = menuX - 130;
int menuX4 = menuX - 70;
int menuX5 = menuX - 0;
int menuY1 = menuY - 0;
int menuY2 = menuY - 70;
int menuY3 = menuY - 130;
int menuY4 = menuY - 165;
int menuY5 = menuY - 180;
//Petal navigation for map screen
void petalNav()
{
	//load icons
	mainMenu = loadImage("mainMenu.png");
	search = loadImage("search.png");
	services = loadImage("services.png");
	greenCircle = loadImage("greenCircle.png");

	petalNavControl();

}

// controls everything
void petalNavControl(){
	// open or closed
	if (activated == 0) {
		petalNavDeactivated(); //open
	}
	if (activated == 1) {
		petalNavActivated(); //closed
	}

	// pressed
	if (mousePressed == true && (mouseX>menuX && mouseX<menuX+menuXY) && 
		(mouseY>menuY && mouseY<menuY+menuXY))
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

	//control button actions
	if (activated == 1) {
		//search
		if (mousePressed == true && (mouseX>menuX1 && mouseX<menuX1+menuXY) && 
		(mouseY>menuY1 && mouseY<menuY1+menuXY)) {
			currentScreen = 0;
		}
		//navigation
		if (mousePressed == true && (mouseX>menuX2 && mouseX<menuX2+menuXY) && 
		(mouseY>menuY2 && mouseY<menuY2+menuXY)) {
			currentScreen = 2;
		}
		//services
		if (mousePressed == true && (mouseX>menuX3 && mouseX<menuX3+menuXY) && 
		(mouseY>menuY3 && mouseY<menuY3+menuXY)) {
			currentScreen = 3;
		}
		//zoom
		if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
		(mouseY>menuY4 && mouseY<menuY4+menuXY) && zoomOpen!=1) {
			zoomOpen = 1;
			mapOpen = 0;
			delay(500);
		}
		//map display
		if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
		(mouseY>menuY5 && mouseY<menuY5+menuXY) && mapOpen!=1) {
			mapOpen = 1;
			zoomOpen = 0;
			delay(500);
		}
		if (zoomOpen == 1) {
			if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
			(mouseY>menuY4-(menuXY/2) && mouseY<menuY4+(menuXY/2))) {
				zoomLevel = zoomLevel + .1;
			}
			if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
			(mouseY>menuY4+(menuXY/2) && mouseY<menuY4+menuXY+(menuXY/2))) {
				zoomLevel = zoomLevel - .1;
			}
		}
		if (mapOpen == 1) {
			if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
			(mouseY>menuY5-(menuXY/2) && mouseY<menuY5+(menuXY/2))) {
				if (mapLevel == 2) {
					mapLevel = 1;
					delay(500);
				}
				else {
					mapLevel = mapLevel + 1;
					delay(500);
				}
			}
			if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
			(mouseY>menuY5+(menuXY/2) && mouseY<menuY5+menuXY+(menuXY/2))) {
				if (mapLevel == 1) {
					mapLevel = 2;
					delay(500);
				}
				else {
					mapLevel = mapLevel - 1;
					delay(500);
				}

			}
		}

	}
}

void petalNavActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	image(mainMenu, menuX, menuY);

	// create the mini menu buttons
	// search
	image(search, menuX1, menuY1);
	// navigation
	image(greenCircle, menuX2, menuY2);
	// services
	image(services, menuX3, menuY3);
	//zoom
	if (zoomOpen == 1) {
		image(greenCircle, menuX4, menuY4+(menuXY/2));
		image(greenCircle, menuX4, menuY4-(menuXY/2));
	}
	else {
		image(greenCircle, menuX4, menuY4);
	}
	//map display
	if (mapOpen == 1) {
		image(greenCircle, menuX5, menuY5+(menuXY/2));
		image(greenCircle, menuX5, menuY5-(menuXY/2));
	}
	else {
		image(greenCircle, menuX5, menuY5);
	}


}

void petalNavDeactivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	zoomOpen = 0;
	mapOpen = 0;
	image(mainMenu, menuX, menuY);
}