package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Oyuncu extends Rectangle  {
	
	public boolean sag,sol,yukari,asagi;
	private int hýz=4;
	
	private int time =0,targetTime=10;
	public int imageIndex =0;
	private int lastDir=1;
	
	public Oyuncu(int x,int y) {
		setBounds(x,y,32,32);
		
	}
	
	
	
	
	public void Tikla() {
		
		if(sag && hareket(x+hýz,y)) {
			x+=hýz;
			lastDir=1;
		}
		if(sol && hareket(x-hýz,y))
		{
			x-=hýz;
			lastDir=-1;
		}
			
		if(yukari && hareket(x, y-hýz))
		{
			y-=hýz;
			
		}
			
		if(asagi && hareket(x, y+hýz))
		{
			y+=hýz;
			
		}
			
		
		Labirent labirent = Oyun.labirent;
		for(int i=0; i<labirent.yemler.size(); i++) {
			if(this.intersects(labirent.yemler.get(i))) {
				
				Oyun.labirent.yemler.remove(i);
				Oyun.score++;
				break;
			}
		}
		
		
		if(labirent.yemler.size() == 0) {
			
			int sonuc =JOptionPane.showConfirmDialog(null, "Tebrikler Level 1'i Tamamladýnýz Level 2'ye Geçmek Ýçin Evet Tuþuna Basýnýz. ","secim",JOptionPane.YES_NO_OPTION,3);
			if(sonuc==JOptionPane.YES_OPTION) {
				Oyun.score=-1;
				Oyun.oyuncu = new Oyuncu(64,64);
				Oyun.labirent = new Labirent("/map/map2.png");
				
			
			}
			
			else
				System.exit(1);		
						
			
		}
		
		for(int i = 0; i < Oyun.labirent.dusmanlar.size(); i++) {
			Dusman en = Oyun.labirent.dusmanlar.get(i);
			if(en.intersects(this)) {
				int sonuc =JOptionPane.showConfirmDialog(null, "Level 1'i Geçemediniz. Tekrar Oynamak Ýster misiniz ?","secim",JOptionPane.YES_NO_OPTION,3);
				if(sonuc==JOptionPane.YES_OPTION) {
					Oyun.can--;
					Oyun.score=-1;
					Oyun.oyuncu = new Oyuncu(64,64);
					Oyun.labirent = new Labirent("/map/map2.png");	
					if(Oyun.can==0)
					{
						System.exit(1);
					}
					

				}
				else
					System.exit(1);
			}
		}	
			time++;
			if(time==targetTime) {
				time=0;
				imageIndex++;
			}
			
			
		
	}
	
	private boolean hareket(int nextx, int nexty) {
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
		if(lastDir==1)
			g.drawImage(Karakterler.oyuncu[imageIndex%2],x,y,width,height,null);
		else
			g.drawImage(Karakterler.oyuncu[imageIndex%2],x+32,y,-width,height,null);

			
		
	}

	
}

