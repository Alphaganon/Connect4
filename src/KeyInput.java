import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_RIGHT) {
					int newX = tempObject.getX() + 1;
					if(newX <= 6) {
						tempObject.setX(newX);
					}
				}
				else if(key == KeyEvent.VK_LEFT) {
					int newX = tempObject.getX() - 1;
					if(newX >= 0) {
						tempObject.setX(newX);
					}
				}
				else if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
					int k = 1;
					while(k < 7 && Game.getGrid()[tempObject.getX()][k].getColor() == Color.gray) {
						k++;
					}
					if(k>1) {
						Game.getGrid()[tempObject.getX()][k-1].setColor(Player.getColor());
						handler.removeObject(tempObject);
						if(!Game.winningMove()) {
							handler.addObject(new Player2(0, 0, ID.Player2, this.handler));
							HUD.turn++;
						}
					}
				}
			} else if(tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_RIGHT) {
					int newX = tempObject.getX() + 1;
					if(newX <= 6) {
						tempObject.setX(newX);
					}
				} 
				else if(key == KeyEvent.VK_LEFT) {
					int newX = tempObject.getX() - 1;
					if(newX >= 0) {
						tempObject.setX(newX);
					}
				}
				else if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
					int k = 1;
					while(k < 7 && Game.getGrid()[tempObject.getX()][k].getColor() == Color.gray) {
						k++;
					}
					if(k>1) {
						Game.getGrid()[tempObject.getX()][k-1].setColor(Player2.getColor());
						handler.removeObject(tempObject);
						if(!Game.winningMove()) {
							handler.addObject(new Player(0, 0, ID.Player, this.handler));
							HUD.turn += 2;
						}
					}
				}
			}
		}

	}
	
	public void keyReleased(KeyEvent e) {
		
	}

}
