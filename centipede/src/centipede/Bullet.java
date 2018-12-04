package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	private Handler handler;
	public static int score;
	public static int deadobj = 0;
	public Bullet(int x, int y, ID id, int health, Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		velX = 5;
		velY = 5;

		this.handler = handler;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y, 2, 16);
	}
	public void tick(){
		
		//x +=velX;
		y -=velY;
		if(y<=0||y>=Game.HEIGHT- 32) handler.removeObject(this);;
		//if(x<=0||x>=Game.WIDTH - 16) velX *=-1;
		/*
		y +=velY;
		if(y<=0||y>=Game.HEIGHT- 32) {
			x+=velX;
			velY *=-1;
		}
		*/
		collision();
	}
	
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Mushroom){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					score++;
					tempObject.health--;
					if(tempObject.health == 0){
						score = score+5;
						handler.removeObject(tempObject);
					}
					
				}
			}
			else if(tempObject.getId() == ID.Spider){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					tempObject.health--;
					score = score+100;
					if(tempObject.health == 0){
						score = score+100;
						handler.removeObject(tempObject);
					}		
				}
			}
			
			else if(tempObject.getId() == ID.Segment){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					tempObject.health--;
					score = score+2;
					if(tempObject.health == 0){
						deadobj++;
						score = score+5;
						if(deadobj == 9) {
							score = score+600;
						}
						handler.removeObject(tempObject);
					}		
				}
			}
		}
	}
	
	public int getScore(){
		return score;
	}
	public void render(Graphics g ){
		g.setColor(Color.red);
		g.fillRect(x, y, 2, 16);

	}
}
