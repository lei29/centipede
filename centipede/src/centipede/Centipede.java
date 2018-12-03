package centipede;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Centipede extends GameObject{
	private Handler handler;
	public static int numofSeg = 10;
	public Centipede(int x, int y, ID id, int health, Handler handler) {
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

		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
