// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 60;
int activated =0;
//create images
PImage mainMenu;
PImage search;
PImage services;
PImage navigation;
PImage zoom;
PImage zoomPlus;
PImage zoomMinus;

//Petal navigation for map screen
void petalNav()
{
	//load icons
	mainMenu = loadImage("mainMenu.png");
	search = loadImage("search.png");
	services = loadImage("services.png");
	// open or closed
	if (activated == 0) {
		petalNavDeactivated(); //open
	}
	if (activated == 1) {
		petalNavActivated(); //closed
	}


}

void petalNavActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	image(mainMenu, menuX, menuY);
	// create the mini menu buttons
	image(search, menuX-180, menuY-0);
	image(services, menuX-155, menuY-90);
	ellipse(menuX-90, menuY-155, menuXY, menuXY);
	ellipse(menuX-0, menuY-180, menuXY, menuXY);
}

void petalNavDeactivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	image(mainMenu, menuX, menuY);
}