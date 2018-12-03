package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mushroom extends GameObject{

	private Handler handler;
	private BufferedImage picture;
	public Mushroom(int x, int y, ID id, int health, Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan);
		if(health == 3){
			
			try {
				picture = ImageIO.read(getClass().getResourceAsStream("mymushroom.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g.drawImage(picture,x,y,20,20,null);
			//g.fillRect(x, y, 20, 20);
		}else if(health == 2){
			g.fillRect(x, y, 20, 15);
		}
		else if(health == 1){
			g.fillRect(x, y, 20, 10);
		}
	}

	public void reborn(){
		health = 3;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y, 20, 20);
	}

}
