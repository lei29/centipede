package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private Handler handler;
	public BasicEnemy(int x, int y, ID id, int health, Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		velX = 5;
		velY = 5;
		this.handler = handler;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}
	public void tick(){
		
		x +=velX;
		y +=velY;
		if(y<=0||y>=Game.HEIGHT- 32) velY *=-1;
		if(x<=0||x>=Game.WIDTH - 16) velX *=-1;
		
		//handler.addObject(new Trial(x,y,ID.Trial,1, Color.red, 16,16,0.1f, handler));
		/*
		y +=velY;
		if(y<=0||y>=Game.HEIGHT- 32) {
			x+=velX;
			velY *=-1;
		}
		*/
	}
	
	public void render(Graphics g ){
		g.setColor(Color.red);
		g.fillRect(x, y, 20, 20);
	}
}
