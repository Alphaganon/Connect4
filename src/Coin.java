import java.awt.Color;
import java.awt.Graphics;

public class Coin extends GameObject{
	
	private Color color = Color.gray;

	public Coin(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void tick() {
				
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x* Game.cellWidth + 20, y* Game.cellWidth + 20, Game.cellWidth - 20, Game.cellWidth - 20);
		
	}

}
