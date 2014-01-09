//import controlP5 for sliders and whatnot
import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

// current screen
int currentScreen = 0;
int arrow = 0;

//mail
String mail1 = "hey";
String mail2 = "hey";

//language
int language = 0;
String looking = "";
String nav1 = "";
String nav2 = "";
String nav3 = "";

// regular old setup
void setup()
{
	size(800,1280);
	background(0);
	language();
}

void draw()
{
	// language();
	//startscreen
	if (currentScreen == 0)
	{
		startScreen();
	}
	//mapscreen
	if (currentScreen == 1) 
	{
		arrow = 0;
		loadMap();
		petalNav();
		mapScreen();
		petalNav();
	}
	// navigation screen
	if (currentScreen == 2) {
		navScreen();
	}
	if (currentScreen == 3) {
		servScreen();
	}
	if (currentScreen == 4) {
		arrow = 1;
		loadMap();
		petalNav();
		mapScreen();
		petalNav();
	}
	// print(frameRate + "\n");
	// print(offset);
	if (mousePressed) {
		respond();	
	}
	

}

void mouseReleased()
{
	petalPressed = 0;
	offset = 0;
	navPressed = 0;
	move = 0;
	dragged = 0;
}

void delay(int delay)
{
  int time = millis();
  while(millis() - time <= delay);
}

void language()
{
	if (language == 0) {
		looking = "What are you looking for?";
		nav1 = "1. From the entrence to left. Walk until you see the stairs";
		nav2 = "2. Walk past the stairs and turn left. You will see starbucks";
		nav3 = "3. ECG-15 will be on the right next to the starbucks";
	}
	else if (language == 1) {
		looking = "Czego szukasz?";
		nav1 = "Z Entrence do lewej. Idź aż zobaczysz po schodach";
		nav2 = "Przejść obok schodów i skręcić w lewo. Zobaczysz Starbucks";
		nav3 = "EKG-15 będzie po prawej stronie, obok Starbucks";
	}

}

void respond()
{
	stroke(51, 181, 229, 200);
	fill(0, 0);
	strokeWeight(5);
	ellipseMode(CENTER);
	ellipse(mouseX, mouseY, 150, 150);
}