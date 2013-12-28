int textPressed = 0;
void startScreen()
{
	startScreenControl();
	startScreenRender();

}

void startScreenControl()
{
	if (mousePressed == true && mouseX>350 && mouseX<450 && 
	mouseY>height/2+40 && mouseY<height/2+100) {
		currentScreen =1;
	}
	if (mousePressed == true && mouseX>100 && mouseX<700 && 
	mouseY>(height/2)-40 && mouseY<height/2) {
		textPressed = 1;
	}
}

void startScreenRender()
{

	background(0, 152, 116);
	//render text box
	rectMode(CORNER);
	stroke(177, 99, 163);
	strokeWeight(5);
	fill(0, 152, 116);
	rect(100, (height/2)-40, 600, 40,0,0,20,0);
	noStroke();
	rect(98, (height/2)-50, 605, 30);
	//text
	fill(50);
	textSize(25);
	if (textPressed == 1) {
		fill(30);
		text("ECG-15 |", (width/2)-280, (height/2)-15);	
	}
	else {
		text("What are you looking for?_", (width/2)-280, (height/2)-15);
	}
	// go button
	fill(177, 99, 163);
	rect(350, height/2+40, 100, 60,15,15,15,15);
}