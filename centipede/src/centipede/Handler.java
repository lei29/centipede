package centipede;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	private Random r;
	private int gameover = 0;
	public void tick(){
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);

		}
		
		g.drawString("Score: "+Bullet.score, 15, 64);
		g.drawString("Lives: "+Player1.lives, 15, 80);
		if(Player1.lives == 0){
			if(gameover == 0 ){
				g.drawString("Do you want to restart?", 300, 330);
				g.drawString("Press enter to start, press esc to exit.", 300, 350);
			}
			else if(gameover == 1){
				g.drawString("Game Over", 300, 350);

			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void GameOver(){
		gameover = 1;
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public void restartGame(){
		
		for(int i=0;i<this.object.size();i++){
			GameObject tempObject = this.object.get(i);
			this.removeObject(tempObject);
		}
			
		new Game();
	}
	public void restart(int life){
		int hasspider = 0;
		r = new Random();
		this.addObject(new Player1(100,100,ID.Player1,life, this));
		for(int i=0;i<this.object.size();i++){
			GameObject tempObject = this.object.get(i);
			
			if(tempObject.getId() == ID.Mushroom){
				tempObject.health = 3;
			}else if(tempObject.getId() == ID.Spider){
				hasspider = 1;
				tempObject.health = 3;
			}		
		}
		if(hasspider == 0){
			this.addObject(new Spider(r.nextInt(640),r.nextInt(480), ID.Spider, 2, this));

		}
	}
}
