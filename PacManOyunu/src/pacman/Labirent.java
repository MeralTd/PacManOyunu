package pacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Labirent {

	public int genislik;
	public int yukseklik;
	
	public LabirentDuvarý[][] duvarlar;
	public List<Yem> yemler;	
	public List<Dusman> dusmanlar;
	
	public Labirent(String yol) {
		yemler = new ArrayList<>();
		dusmanlar = new ArrayList<>();
		try {
			BufferedImage oyunAlani=ImageIO.read(getClass().getResource(yol));
			this.genislik=oyunAlani.getWidth();
			this.yukseklik=oyunAlani.getHeight();
			int[] piksel= new int[genislik*yukseklik];
			duvarlar=new  LabirentDuvarý[genislik][yukseklik];
			oyunAlani.getRGB(0, 0, genislik, yukseklik, piksel, 0,genislik);
			
			for(int xx=0; xx<genislik; xx++) {
				for(int yy=0; yy<yukseklik; yy++){
					int val=piksel[xx+(yy*genislik)];
					if(val == 0xFF000000) {
						duvarlar[xx][yy]=new LabirentDuvarý(xx*32,yy*32);
					}
					else if(val==0xFF0000FF) {
						Oyun.oyuncu.x = xx*32;
						Oyun.oyuncu.y = yy*32;
					}
					else if(val==0xFFFF0000) {
						dusmanlar.add(new Dusman(xx*32 ,yy*32));
						
					}
					else {
						yemler.add(new Yem(xx*32,yy*32));
					}
				}
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	public void Tikla() {
		for(int i=0; i<dusmanlar.size(); i++) {
			dusmanlar.get(i).Tikla();
		}
	}
	
	public void olustur(Graphics g) {
		for(int x=0; x<genislik; x++) {
			  for(int y=0; y<yukseklik; y++) {
				  if(duvarlar[x][y] != null)
					  duvarlar[x][y].olustur(g);
			  }
		}
		
		for(int i=0; i<yemler.size(); i++){
			yemler.get(i).olustur(g);
		}
		
		for(int i=0; i<dusmanlar.size(); i++) {
			dusmanlar.get(i).olustur(g);
		}
	}
	
}
