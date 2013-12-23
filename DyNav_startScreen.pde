void startScreen()
{
	background(0, 152, 116);

	startScreenControl();
}

void startScreenControl()
{
	if (mousePressed == true) {
		navScreen =1;
	}
}