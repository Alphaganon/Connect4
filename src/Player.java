import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends GameObject{
	
	private static Color color = Color.red;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
	}
	
	public static Color getColor() {
		return Player.color;
	}

	@Override
	public void tick() {
		
	}
	
	public ArrayList<Coin> getNeighbors(Coin coin) {
		ArrayList<Coin> neighbors = new ArrayList<Coin>();
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				boolean sameCoords = (Game.clamp(coin.getX() + i, 0, 6) == coin.getX() && Game.clamp(coin.getY() + j, 1, 6) == coin.getY());
				if(Game.getGrid()[Game.clamp(coin.getX() + i, 0, 6)][Game.clamp(coin.getY() + j, 1, 6)].getColor() == Color.red && !sameCoords) {
					neighbors.add(Game.getGrid()[Game.clamp(coin.getX() + i, 0, 6)][Game.clamp(coin.getY() + j, 1, 6)]);
				}
			}
		}
		return neighbors;
	}
	
	public boolean winningMove() {
		
		return false;
	}

	
	@Override
	public void render(Graphics g) {
		g.setColor(Player.color);
		g.fillOval(x* Game.cellWidth + 20, y* Game.cellWidth + 20, Game.cellWidth - 20, Game.cellWidth - 20);
	}


}
