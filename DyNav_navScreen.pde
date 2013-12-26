int rows;
 void navScreen()
 {
 	background(255);
 	noStroke();
 	fill(0, 152, 116);
 	rect(0, 0, width, 80);

	int rows = (height/80) + 1;
	// print(rows);
	
}

void nav_screenRender()
{
		for (int i = 2; i<rows; i = i + 1) {
		stroke(0);
		strokeWeight(1);
		strokeCap(ROUND);
		line(0, i*80, width-0, i*80);

	}

 }