import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import javax.mail.*; 
import java.util.*; 
import javax.mail.internet.*; 
import controlP5.*; 
import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication; 

import com.sun.mail.util.*; 
import javax.mail.util.*; 
import javax.mail.event.*; 
import com.sun.mail.pop3.*; 
import com.sun.mail.imap.protocol.*; 
import javax.mail.search.*; 
import com.sun.activation.registries.*; 
import com.sun.mail.handlers.*; 
import myjava.awt.datatransfer.*; 
import javax.activation.*; 
import com.sun.mail.dsn.*; 
import org.apache.harmony.misc.*; 
import javax.mail.internet.*; 
import org.apache.harmony.awt.internal.nls.*; 
import com.sun.mail.smtp.*; 
import com.sun.mail.imap.*; 
import org.apache.harmony.awt.datatransfer.*; 
import javax.mail.*; 
import com.sun.mail.iap.*; 
import org.apache.harmony.awt.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DyNav_main extends PApplet {

//import controlP5 for sliders and whatnot





ControlP5 cp5;

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
public void setup()
{
	size(800,1280);
	background(0);
	cp5 = new ControlP5(this);
	language();
}

public void draw()
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

}

public void mouseReleased()
{
	petalPressed = 0;
	offset = 0;
	navPressed = 0;
	move = 0;
	dragged = 0;
}

public void delay(int delay)
{
  int time = millis();
  while(millis() - time <= delay);
}

public void language()
{
	if (language == 0) {
		looking = "What are you looking for?";
		nav1 = "1. From the entrence to left. Walk until you see the stairs";
		nav2 = "2. Walk past the stairs and turn left. You will see starbucks";
		nav3 = "3. ECG-15 will be on the right next to the starbucks";
	}
	else if (language == 1) {
		looking = "Czego szukasz?";
		nav1 = "Z Entrence do lewej. Id\u017a a\u017c zobaczysz po schodach";
		nav2 = "Przej\u015b\u0107 obok schod\u00f3w i skr\u0119ci\u0107 w lewo. Zobaczysz Starbucks";
		nav3 = "EKG-15 b\u0119dzie po prawej stronie, obok Starbucks";
	}

}
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
PImage mapLayer;
PImage mapUp;
PImage mapDown;
PImage greenCircle;

// menu X and Y coords
int menuX = 680;
int menuY = 500;
// menu diameter
int menuXY = 100;

//zoom and map display opens
int zoomOpen = 0;
int zoomReady = 0;
int mapOpen = 0;
int mapReady = 0;

//animation for petal
int petalAnim = 0;

int dragged = 0;

// varibles for postions
int menuX1 = menuX - 250;
int menuX2 = menuX - 230;
int menuX3 = menuX - 175;
int menuX4 = menuX - 95;
int menuX5 = menuX - 0;
int menuY1 = menuY - 0;
int menuY2 = menuY - 95;
int menuY3 = menuY - 175;
int menuY4 = menuY - 230;
int menuY5 = menuY - 250;
//Petal navigation for map screen
public void petalNav()
{
	//load icons
	mainMenu = loadImage("mainMenu.png");
	search = loadImage("search.png");
	services = loadImage("services.png");
	greenCircle = loadImage("greenCircle.png");
	mapLayer = loadImage("mapLayer.png");
	mapUp = loadImage("mapUp.png");
	mapDown = loadImage("mapDown.png");
	zoom = loadImage("zoom.png");
	zoomPlus = loadImage("zoomPlus.png");
	zoomMinus = loadImage("zoomMinus.png");

	petalNavControl();

}

// controls everything
public void petalNavControl(){
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

	if (dragged == 0) {
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
				mapReady = 0;
				mapOpen = 0;
				delay(500);
			}
			//map display
			if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
			(mouseY>menuY5 && mouseY<menuY5+menuXY) && mapOpen!=1) {
				mapOpen = 1;
				zoomOpen = 0;
				zoomReady = 0;
				delay(500);
			}
			if (zoomReady == 1) {
				if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
				(mouseY>menuY4-(menuXY/2) && mouseY<menuY4+(menuXY/2))) {
					zoomLevel = zoomLevel + .1f;
					delay(50);
				}
				if (mousePressed == true && (mouseX>menuX4 && mouseX<menuX4+menuXY) && 
				(mouseY>menuY4+(menuXY/2) && mouseY<menuY4+menuXY+(menuXY/2))) {
					zoomLevel = zoomLevel - .1f;
					delay(50);
				}
			}
			if (mapReady == 1) {
				if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
				(mouseY>menuY5-(menuXY/2) && mouseY<menuY5+(menuXY/2))) {
					if (mapLevel == 3) {
						mapLevel = 1;
						delay(50);
					}
					else {
						mapLevel = mapLevel + 1;
						delay(50);
					}
				}
				if (mousePressed == true && (mouseX>menuX5 && mouseX<menuX5+menuXY) && 
				(mouseY>menuY5+(menuXY/2) && mouseY<menuY5+menuXY+(menuXY/2))) {
					if (mapLevel == 1) {
						mapLevel = 3;
						delay(50);
					}
					else {
						mapLevel = mapLevel - 1;
						delay(50);
					}

				}
			}
		}
	}
}

public void petalNavActivated()
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
		image(zoomMinus, menuX4+25, menuY4+(menuXY/2));
		image(zoomPlus, menuX4-25, menuY4-(menuXY/2));
		zoomReady = 1;
	}
	else {
		image(zoom, menuX4, menuY4);
	}
	//map display
	if (mapOpen == 1) {
		image(mapDown, menuX5, menuY5+(menuXY/2));
		image(mapUp, menuX5, menuY5-(menuXY/2));
		mapReady = 1;
	}
	else {
		image(mapLayer, menuX5, menuY5);
	}


}

public void petalNavDeactivated()
{
	// create the main menu button
	fill(0, 152, 116);
	noStroke();
	zoomOpen = 0;
	mapOpen = 0;
	image(mainMenu, menuX, menuY);
}

public void mail()
{

  // Function to check mail
  // checkMail();
  
  // Function to send mail
  sendMail();
  
  // noLoop();
}

public void sendMail() {
  // Create a session
  String host="smtp.gmail.com";
  Properties props=new Properties();

  // SMTP Session
  props.put("mail.transport.protocol", "smtp");
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.port", "25");
  props.put("mail.smtp.auth", "true");
  // We need TTLS, which gmail requires
  props.put("mail.smtp.starttls.enable","true");

  // Create a session
  Session session = Session.getDefaultInstance(props, new Auth());

  try
  {
    // Make a new message
    MimeMessage message = new MimeMessage(session);

    // Who is this message from
    message.setFrom(new InternetAddress("tomquil13@gmail.com", "Tom"));

    // Who is this message to (we could do fancier things like make a list or add CC's)
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("u3MHJadTLtG46zAyrxt538p3rpvJKy@api.pushover.net", false));

    // Subject and body
    message.setSubject(mail1);
    message.setText(mail2);

    // We can do more here, set the date, the headers, etc.
    Transport.send(message);
    // println("Mail sent!");
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }

}




public class Auth extends Authenticator {

  public Auth() {
    super();
  }

  public PasswordAuthentication getPasswordAuthentication() {
    String username, password;
    username = "********************";
    password = "*********";
    // System.out.println("authenticating. . ");
    return new PasswordAuthentication(username, password);
  }
}
public void mapCenter()
{
	// center the map
	mapX = (width-map.width)/2;
	mapY = (height-map.height)/2;
}
//map number
int mapLevel = 1;
// image pos
float mapX, mapY;
// create map
PImage map;
//where touched
int touchedX,touchedY;

String floorLevel = "GroundFloor";

// mouseposition offset to image
float offX, offY;
int offset = 0;

//zoomLevel level
float zoomLevel = 2.0f;

int petalPressed = 0;
public void mapScreen()
{
	mapScreenControl();
	mapScreenRender();

}

public void mapScreenControl()
{
	//get new pos
	if (mousePressed && mouseY>120 && mouseY<height-120)
	{
		if (offset == 0) {
			offX = mouseX-mapX;
			offY = mouseY-mapY;	
			offset = 1;
			dragged = 1;
		}
	 	mapX = mouseX-offX;
	 	mapY = mouseY-offY;
	}  

	// activate menu button

}

public void mapScreenRender()
{
	background(0);
	// update pos
	image(map, mapX, mapY, map.width*zoomLevel, map.height*zoomLevel);
	fill(140);
	rectMode(CENTER);
	rect(width/2, 60, 200, 60,20,20,20,20);
	fill(255);
	textSize(30);
	textAlign(CENTER,CENTER);
	text(floorLevel, width/2, 60);

}

public void loadMap()
{
	if (arrow == 0) {
		if (mapLevel == 1) {
			//load map 1
			map = loadImage("map1.png");
			floorLevel = "Ground Floor";
		}
		if (mapLevel == 2) {
			// load map 2
			map = loadImage("map2.png");
			floorLevel = "First Floor";
		}
		if (mapLevel == 3) {
			map = loadImage("map3.png");
			floorLevel = "Second Floor";
		}
	}
	else if (arrow == 1) {
		if (mapLevel == 1) {
			//load map 1
			map = loadImage("map1arrow.png");
			floorLevel = "Ground Floor";
		}
		if (mapLevel == 2) {
			// load map 2
			map = loadImage("map2arrow.png");
			floorLevel = "First Floor";
		}
		if (mapLevel == 3) {
			map = loadImage("map3arrow.png");
			floorLevel = "Second Floor";
		}
	}
}
public void mousePressed()
{

}

int rows;
int move = 0;
int moveOff = 0;
int navPressed = 0;
int scriptRun = 0;
PImage back;
public void navScreen()
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
public void navScreenControl()
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

public void navScreenRender()
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
PImage coffee;
PImage toiletserv;
PImage computer;
PImage sigma;
public void servScreen()
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

public void servScreenControl()
{
	if (mousePressed && mouseX<120 && mouseY<100) {
		currentScreen = 1;
	}
	if (mousePressed && mouseX>100 && mouseX<400 && mouseY>120) {
		currentScreen = 4;
	}
}

public void servScreenRender()
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
int textPressed = 0;
int languageOpen = 0;
public void startScreen()
{
	startScreenControl();
	startScreenRender();

}

public void startScreenControl()
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

public void startScreenRender()
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


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DyNav_main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
