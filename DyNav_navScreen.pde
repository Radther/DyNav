
int rows;
int move = 0;
int moveOff = 0;
int navPressed = 0;
int scriptRun = 0;
PImage back;
void navScreen()
{
	int rows = (height/80) + 1;
	back = loadImage("back.png");


	// rows = 16;
	// print(rows);
	navScreenControl();
	navScreenRender();	
	if (scriptRun == 0) {

		scriptRun = 1;
	}
}
void navScreenControl()
{
	if (mousePressed) {
		if (navPressed == 0) {
			moveOff = -mouseY;
			navPressed = 1;
		}
		move = mouseY+moveOff;
	}
	if (mousePressed && mouseX>700 && mouseX<780 && mouseY<80) {
		mail1 = "DIRECTIONS";
		mail2 = "1. From the entrence to left. Walk until you see the stairs. 2. Walk past the stairs and turn left. You will see starbucks. 3. ECG-15 will be on the right next to the starbucks.";
		mail();
	}
	if (mousePressed && mouseX<120 && mouseY<80) {
		currentScreen = 1;
	}
}

void navScreenRender()
{
	background(255);

 	//lines
 	stroke(0);
 	strokeWeight(1);
 	line(0, 80*2+move, width, 80*2+move);
 	textAlign(LEFT, CENTER);
 	textSize(24);
 	fill(0);
 	text(nav1, 40, 80*1+40+move);
 	line(0, 80*3+move, width, 80*3+move);
 	text(nav2, 40, 80*2+40+move);
 	line(0, 80*4+move, width, 80*4+move);
 	text(nav3, 40, 80*3+40+move);
 	line(0, 80*5+move, width, 80*5+move);
 	line(0, 80*6+move, width, 80*6+move);
 	line(0, 80*7+move, width, 80*7+move);
 	line(0, 80*8+move, width, 80*8+move);
 	line(0, 80*9+move, width, 80*9+move);
 	line(0, 80*10+move, width, 80*10+move);
 	line(0, 80*11+move, width, 80*11+move);
 	line(0, 80*12+move, width, 80*12+move);
 	line(0, 80*13+move, width, 80*13+move);
 	line(0, 80*14+move, width, 80*14+move);
 	line(0, 80*15+move, width, 80*15+move);
 	line(0, 80*16+move, width, 80*16+move);

 	//other stuff
 	noStroke();
 	fill(0, 152, 116);
 	rect(0, 0, width, 80);
 	image(back, 0, 0);

}