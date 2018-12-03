package centipede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Segment extends GameObject{

	private Handler handler;
	private int i = 0;
	private int j = 0;
	public static int flag = 0;
	private BufferedImage picture;
	public Segment(int x, int y, ID id, int health,Handler handler){
		super(x, y, id, health);
		// TODO Auto-generated constructor stub
		velX = -2;
		velY = 20;
		this.handler = handler;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub


		if(y<300){

			
			i++;
			//System.out.println("velX:"+velX);
			//System.out.println(i);
			//System.out.println(x+","+y);
			x = x+velX;
			//
			//System.out.println("width"+Game.WIDTH);
			if(x<=0||x>=Game.WIDTH - 16) {
				j++;
				//System.out.println(i);
				
				y +=velY;
				velX *=-1;
			}	
		}else{
			x = x+velX;
			//System.out.println(x+","+y);
			//System.out.println("width"+Game.WIDTH);
			if(x<=0||x>=Game.WIDTH - 16) {
				velX *=-1;
			}	
		}

		collision();
		x = Game.clamp(x, 0, Game.WIDTH-16);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		

	}


	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Mushroom){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("bound with mushroom");
					y+=velY;
					velX *=-1;
				}
			}else if(tempObject.getId()==ID.Player1){
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
		if(health == 2){
			try {
				picture = ImageIO.read(getClass().getResourceAsStream("segment.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g.drawImage(picture,x,y,20,20,null);
		}else if(health == 1){
			g.setColor(Color.yellow);	
			g.fillRect(x, y, 20	, 20);
		}
		
		//System.out.println(x+","+y);
	}
	public int getX(){
		return x;
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y, 20, 20);
	}

}
