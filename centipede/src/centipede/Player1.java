package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player1 extends GameObject{


	Handler handler;
	public static int lives;
	private BufferedImage picture;
	public Player1(int x, int y, ID id, int health, Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		lives = health;
		
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}	
	public void tick(){

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
		try {
			picture = ImageIO.read(getClass().getResourceAsStream("player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(picture,x,y,32,32,null);

	}

}
