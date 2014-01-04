PImage coffee;
PImage toiletserv;
PImage computer;
PImage sigma;
void servScreen()
{	
	coffee = loadImage("coffee.png");
	toiletserv = loadImage("toiletserv.png");
	computer = loadImage("computer.png");
	sigma = loadImage("sigma.png");
	back = loadImage("back.png");

	servScreenRender();
	delay(40);
	servScreenControl();



}

void servScreenControl()
{
	if (mousePressed && mouseX<120 && mouseY<100) {
		currentScreen = 1;
	}
	if (mousePressed && mouseX>100 && mouseX<400 && mouseY>120) {
		currentScreen = 4;
	}
}

void servScreenRender()
{
	background(255);

	fill(0);
	textSize(40);
	image(coffee, 80, 100);
	textAlign(LEFT,CENTER);
	text("Food and drink", 280, 200);
	image(toiletserv, 80, 320);
	textAlign(LEFT,CENTER);
	text("Toilets", 280, 420);
	image(computer, 80, 540);
	textAlign(LEFT,CENTER);
	text("Computers", 280, 640);
	image(sigma, 80, 760);
	textAlign(LEFT,CENTER);
	text("Sigma", 280, 860);

	noStroke();
 	fill(0, 152, 116);
 	rect(0, 0, 800, 80);
 	rect(0, 0, 700, 200);
 	image(back, 0, 0);
}