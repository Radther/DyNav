int textPressed = 0;
int languageOpen = 0;
void startScreen()
{
	startScreenControl();
	startScreenRender();

}

void startScreenControl()
{
	if (mousePressed == true && mouseX>350 && mouseX<450 && 
	mouseY>height/2+40 && mouseY<height/2+100 && languageOpen == 0) {
		if (textPressed == 1) {
			currentScreen = 4;
		}else {
			currentScreen = 1;
		}
	}
	if (mousePressed == true && mouseX>100 && mouseX<700 && 
	mouseY>(height/2)-40 && mouseY<height/2 && languageOpen == 0) {
		textPressed = 1;
	}
	if (languageOpen == 1) {
		if (mousePressed && mouseX>150 && mouseX<650 && mouseY>height/2-60 && mouseY<height/2+40) {
			language = 0;
			languageOpen = 0;
		}
		if (mousePressed && mouseX>150 && mouseX<650 && mouseY>height/2+40 && mouseY<height/2+140) {
			language = 1;
			languageOpen = 0;
		}
	}
	if (mousePressed && mouseX>150 && mouseX<650 && mouseY<height/2-80 && mouseY>height/2-160) {
		languageOpen = 1;
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
	textAlign(LEFT,BOTTOM);
	if (textPressed == 1) {
		fill(30);
		text("ECG-15 |", (width/2)-280, (height/2)-15);	
	}
	else {
		text(looking, (width/2)-280, (height/2)-15);
	}
	// go button
	fill(177, 99, 163);
	rect(350, height/2+40, 100, 60,15,15,15,15);
	if (languageOpen == 1) {
		rectMode(CENTER);
		fill(20);
		rect(width/2, height/2, 300, 400);
		textSize(60);
		textAlign(CENTER,CENTER);
		fill(255);
		text("Language", width/2, height/2-160);
		textSize(60);
		text("english", width/2, height/2-40);
		text("polski", width/2, height/2+80);
	}
	if (languageOpen == 0) {
		textSize(40);
		textAlign(CENTER,CENTER);
		fill(0);
		text("language", width/2, height/2-150);
	}

}


