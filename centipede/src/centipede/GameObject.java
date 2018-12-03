package centipede;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;//only be accessed by inherited class
	protected ID id;
	protected int velX, velY;
	protected int health;
	
	public GameObject(int x,int y, ID id, int health){
		this.x = x;
		this.y = y;
		this.id = id;
		this.health = health;		
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return id;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
}
