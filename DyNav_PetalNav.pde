// menu X and Y coords
int menuX = 500;
int menuY = 500;
// menu diameter
int menuXY = 60;
int activated =0;

//Petal navigation for map screen
void petalNav()
{
	if (activated == 0) {
		petalNavDeActivated();
	}
	if (activated == 1) {
		petalNavActivated();
	}

}

void petalNavActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
	// create the mini menu buttons
	ellipse(menuX-180, menuY-0, menuXY, menuXY);
	ellipse(menuX-155, menuY-90, menuXY, menuXY);
	ellipse(menuX-90, menuY-155, menuXY, menuXY);
	ellipse(menuX-0, menuY-180, menuXY, menuXY);
}

void petalNavDeActivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	ellipse(menuX, menuY, menuXY, menuXY);
}