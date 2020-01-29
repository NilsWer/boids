package nils.main;

import java.awt.Color;
import java.awt.Graphics;

public class Obstical {
	int x, y, width, height;
	int xMitte, yMitte; 
	Color color = Color.BLUE;
	
	public Obstical(int x, int y, int w, int h) {
		this.x = x;
		this.y = y; 
		this.width = w;
		this.height = h;
		xMitte = x + width/2;
		yMitte = y + height/2;
	}

	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
	}
}
