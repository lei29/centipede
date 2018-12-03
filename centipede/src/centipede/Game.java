package centipede;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	
	private boolean running = false;
	
	private Random r;
	
	private Handler handler;
	
	private HUD hud;
	
	private Mouse mouse;
	
	public Game(){
		new Window(WIDTH,HEIGHT,"lETS BUILDA GAME!", this);
		handler = new Handler();
		generateMushrooms(0);//0 1 2 
		this.addKeyListener(new KeyInput(handler));
		mouse = new Mouse(handler);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(mouse);
		
		r = new Random();
		handler.addObject(new Player1(100,100,ID.Player1,3, handler));
		handler.addObject(new Spider(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.Spider, 2, handler));
		//handler.addObject(new Centipede(640,0, ID.Centipede, 1, handler));
		//for(int i=0;i<3;i++){
		//	handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy,1, handler));
		//}
		generateCentipede();
		//hud = new HUD();

	}

	private void generateCentipede(){
		//ArrayList<Segment> centipede = new ArrayList<Segment>(2);
		int x= 0;
		for(int i=0;i<4;i++){
			x = 500-i*25;
			System.out.println(x);
			
				Segment seg = new Segment(x,0,ID.Segment,2,handler);
				handler.addObject(seg);
			

			//i++;
		}
		
	}
	
	private void generateMushrooms(int level){
		r = new Random();
		int x,y,m,n,e,f;
		int xx = 0;
		int yy = 0;
		int mm=0;
		int nn=0;
		int ee=0;
		int ff =0;
		int a, b,c;
		int temp;
		if(level == 0){
			
			temp = 3+ r.nextInt(27);//0-31
			e = temp+1;
			f = e-2;
			a = temp;
			handler.addObject(new Mushroom(temp*20,1*20,ID.Mushroom,3,handler));
			temp = 3+ r.nextInt(27);//0-31
			
			while(temp == a){
				temp =  3+ r.nextInt(27);//0-31
			}
			x = temp+1;
			y = x-2;
			b = temp;
			handler.addObject(new Mushroom(temp*20,1*20,ID.Mushroom,3,handler));
			temp = 3+ r.nextInt(27);//0-31
			while(temp == a || temp ==b ){
				temp = 3+ r.nextInt(27);//0-31
			}
			m = temp+1;
			n = m-2;
			handler.addObject(new Mushroom(temp*20,1*20,ID.Mushroom,3,handler));
			for(int i=1;i<15;i++){
				for(int j=0;j<3;j++){
					temp = 3+ r.nextInt(27);
					if(j == 0){
						while(temp == x || temp==y ||temp == m || temp==n||temp == f || temp==e){
							temp = 3+ r.nextInt(27);
						}
						xx = temp+1;
						yy = xx-2;
						a = temp;
					}else if(j == 1){
						while(temp == a||temp == x || temp==y ||temp == m || temp==n||temp == f || temp==e){
							temp = 3+ r.nextInt(27);
						}
						mm = temp+1;
						nn = mm-2;
						b = temp;
					}else if(j==2){
						while(temp == a||temp==b||temp == x || temp==y ||temp == m || temp==n||temp == f || temp==e){
							temp = 3+ r.nextInt(27);
						}
						ff = temp+1;
						ee = ff-2;
						x=xx;
						y=yy;
						m = mm;
						n=nn;
						e=ee;
						f=ff;
					}
					handler.addObject(new Mushroom(temp*20,(i+1)*20,ID.Mushroom,3,handler));

				}
			}
		}

	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer>1000){
				timer+=1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
			
		}
		stop();
		

	}
	
	private void tick(){
		handler.tick();
		//hud.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		//hud.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		new Game();
		
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
