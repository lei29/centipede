package centipede;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Mouse extends MouseAdapter implements MouseMotionListener{
	
	
	private int mx,my;
	boolean mousemove = false;
	private Handler handler;
	
	public Mouse(Handler handler){
		this.handler = handler;
	}
	

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getX()+"."+arg0.getY());
		handler.addObject(new Bullet(mx,my,ID.Bullet, 1, handler));
		//System.out.println("lolololol");
	}


	
	public void mouseMoved(MouseEvent event){
		mx = event.getX();
		my = event.getY();
		mousemove = true;
		//System.out.println(mx+"."+my);
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player1){
				tempObject.x = mx;
				tempObject.y = my;
			}/*
			if(tempObject.getId() == ID.Player2){
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(5);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
			}*/
		}		
		event.consume();
	}
	
	public int getMx(){
		return mx;
	}
	
	public int getMy(){
		return my;
	}


	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
