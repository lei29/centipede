package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	
	Handler handler;
	public Player(int x, int y, ID id,int health, Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}	
	public void tick(){

		x +=velX;
		y +=velY;
		x = Game.clamp(x, 0, Game.WIDTH-37);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		
		collision();
	}
	
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -=2;
				}
			}
		}
	}
	
	public void render(Graphics g){
		if(id == ID.Player){
			g.setColor(Color.white);	
		}else if(id==ID.BasicEnemy){
			g.setColor(Color.red);
		}
		g.fillRect(x, y, 32, 32);
	}

}
