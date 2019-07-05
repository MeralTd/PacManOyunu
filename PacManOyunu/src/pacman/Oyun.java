package pacman;
import java.awt.Canvas;
import java.awt.Color;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;

public class Oyun extends Canvas implements Runnable,KeyListener {

	private boolean baslangic=false;
	public static final int GENISLIK=640,YUKSEKLIK=480;
	public static final String BASLIK="PACMAN";
	private Thread thread;
	
	public static Oyuncu oyuncu;
	public static Labirent labirent;
	public static float score=-1;
	public static int can=3;

	
	public Oyun() {
		Dimension boyut =new Dimension(Oyun.GENISLIK,Oyun.YUKSEKLIK);
		setPreferredSize(boyut);
		setMinimumSize(boyut);
		setMaximumSize(boyut);	
		addKeyListener(this);
		oyuncu=new Oyuncu(Oyun.GENISLIK/4,Oyun.YUKSEKLIK/4);
		labirent=new Labirent("/map/map.png");
		
		new Karakterler();
		
	}
	
	public synchronized void Basla() {
		if(baslangic) return;
		baslangic=true;
		thread=new Thread(this);
		thread.start();
		
	}
	
	public synchronized void Dur() {
		if(!baslangic) return;
		baslangic=false;
		try {
			thread.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void Tikla() {
		oyuncu.Tikla();
		labirent.Tikla();
	}
	private void olustur() {
		BufferStrategy bs= getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;			
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,Oyun.GENISLIK,Oyun.YUKSEKLIK);
		oyuncu.olustur(g);
		labirent.olustur(g);
		g.setColor(Color.white);
		g.drawString("SCORE :"+(int)score, 10, 20);
		g.drawString("                                CANLARIN :" +(int)can,10, 20);
		g.setFont(new Font(Font.DIALOG,Font.BOLD,19));
		g.dispose();
		bs.show();

	}
	@Override
	public void run() {
		requestFocus();
		double timer =System.currentTimeMillis();
		long lastTime=System.nanoTime();
		double targetTick=60.0;
		double delta=0;
		double ns=1000000000/targetTick;
		
		
		while(baslangic) {
			long now =System.nanoTime();
			delta+= (now-lastTime)/ns;
			lastTime = now;
			
			while(delta>=1) {
				Tikla();
				olustur();
				delta--;
			}
			
		}
		Dur();
	}
	
	public static void main(String[] args) {
		Oyun oyun =new Oyun();
		JFrame frame =new JFrame();
		frame.setTitle(Oyun.BASLIK);
		frame.add(oyun);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		oyun.Basla();
		
		try {
		    File yourFile;
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(new File("pacman_beginning.wav"));
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e){
			
		}
		   
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			oyuncu.sag=true;
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
			oyuncu.sol=true;
		if(e.getKeyCode()== KeyEvent.VK_UP)
			oyuncu.yukari=true;
		if(e.getKeyCode()== KeyEvent.VK_DOWN)
			oyuncu.asagi=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			oyuncu.sag=false;
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
			oyuncu.sol=false;
		if(e.getKeyCode()== KeyEvent.VK_UP)
			oyuncu.yukari=false;
		if(e.getKeyCode()== KeyEvent.VK_DOWN)
			oyuncu.asagi=false;
	}

}
