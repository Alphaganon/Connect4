import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static int turn = 1;

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		int playerturn = turn%3 ;
		if(Game.winningMove()) {
			g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
			g.drawString("Player " + playerturn + " wins !", 200, 55);
		}
		else {
		g.drawString("Player " + playerturn + "'s turn", 10, 15);
		}
	}
}
