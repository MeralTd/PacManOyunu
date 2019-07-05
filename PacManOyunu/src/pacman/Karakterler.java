package pacman;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Karakterler {

	public static BufferedImage[] oyuncu;
	public BufferedImage pacman;
	public static BufferedImage dusman;
	public Karakterler() {
		try {
			pacman =ImageIO.read(getClass().getResource("/pacman/pacman.png"));
			dusman =ImageIO.read(getClass().getResource("/pacman/pacman.png"));

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		oyuncu = new BufferedImage[3];
		oyuncu[0]=resimiAl(0,0);
		oyuncu[1]=resimiAl(16,0);
		oyuncu[2]=resimiAl(0,16);
	}
	
	public BufferedImage resimiAl(int xx,int yy) {
		return pacman.getSubimage(xx, yy, 16, 16);
		 
	}

}
