package pacman;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Dusman extends Rectangle{

	
	private int random = 0;
	private int state = random;
	private int right = 0, left = 1, up = 2, down = 3;
	private int dir = -1;
	
	public Random randomGen;

	private int time = 0;
	private int spd = 2;
	private int lastDir = -1; 

	public Dusman(int x ,int y) {
		randomGen= new Random();
		setBounds(x,y,32,32);
		dir = randomGen.nextInt(4);
	}
	
	public void Tikla() {
		if(state == random) {
			if(dir == right) {
				if(haraket(x+spd,y))
						x+=spd;
				else
					dir = randomGen.nextInt(4);
				
			}
			
			else if(dir == left) {
				if(haraket(x-spd,y))
						x-=spd;
				else
					dir = randomGen.nextInt(4);
			}
			
			else if(dir == up) {
				if(haraket(x,y-spd))
						y-=spd;
				else
					dir = randomGen.nextInt(4);
			}
			
			else if(dir == down) {
				if(haraket(x,y+spd))
						y+=spd;
				else
					dir = randomGen.nextInt(4);
			}
			
			time++;
			
		}
	}
	
	private boolean haraket(int nextx, int nexty) {
		Rectangle bounds =new Rectangle(nextx,nexty,width,height);
		Labirent labirent = Oyun.labirent;
		
		for(int xx=0; xx<labirent.duvarlar.length; xx++) {
			for(int yy=0; yy<labirent.duvarlar[0].length; yy++) {
				if(labirent.duvarlar[xx][yy] != null) {
					if(bounds.intersects(labirent.duvarlar[xx][yy])){
						return false;
					}
						
					
				}
			}
		}
		return true;
	}
	
	public void olustur(Graphics g) {
		g.drawImage(Karakterler.oyuncu[2],x,y,width,height,null);
	}
}
