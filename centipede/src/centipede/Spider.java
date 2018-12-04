package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Spider extends GameObject{
	private Handler handler;
	private Random r;
	private BufferedImage picture;
	public Spider(int x, int y, ID id, int health,Handler handler) {
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		r = new Random();
	}

	@Override
	public void tick() {
		velX = r.nextInt(100)-50;
		velY = r.nextInt(100)-50;
		//for(int i=0;i<10;i++){
			x +=velX;
			y +=velY;
		//}
		x = Game.clamp(x, 0, Game.WIDTH-37);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		if(y<=0||y>=Game.HEIGHT- 32) velY *=-1;
		if(x<=0||x>=Game.WIDTH - 16) velX *=-1;
		collision();
		
	}
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player1){
				if(getBounds().intersects(tempObject.getBounds())){
					Player1.lives--;
					int life = Player1.lives;
					if(life>0){
						handler.removeObject(this);
						handler.removeObject(tempObject);
						handler.restart(life);
					}else{
						handler.removeObject(this);
						handler.removeObject(tempObject);
						//handler.restartGame();
					}
					
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);
		if(health == 2){
			try {
				picture = ImageIO.read(getClass().getResourceAsStream("spider.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g.drawImage(picture,x,y,15,15,null);
		}
		else if(health == 1){
			g.setColor(Color.pink);
			g.fillRect(x, y, 13, 13);
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y, 15, 15);
	}

}
