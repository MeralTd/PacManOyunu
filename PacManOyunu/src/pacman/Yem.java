package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Yem extends Rectangle {

	public Yem(int x ,int y) {
		setBounds(x+10,y+8,8,8);
	}
	
	public void olustur(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,width,height);

	}
}
