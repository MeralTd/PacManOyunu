package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class LabirentDuvar� extends Rectangle {

	public LabirentDuvar�(int x,int y) {
		setBounds(x,y,32,32);
	}
	
	public void olustur(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
