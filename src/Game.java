import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5864170491866819027L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH + 30;
	
	private Thread thread;
	
	private boolean running = false;
	
	public static final int gridWidth = 7, gridHeight = 7, cellWidth = (WIDTH-25)/gridWidth;
	
	private static Coin[][] grid = new Coin[gridWidth][gridHeight] ;

	
	private Handler handler;
	
	private HUD hud = new HUD();
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window (WIDTH, HEIGHT, "Connect 4", this);
		
		for(int i = 0; i < gridWidth; i++) {
			for(int j = 1; j < gridHeight; j++) {
				Coin coin = new Coin(i, j, ID.Coin);
				handler.addObject(coin);
				grid[i][j] = coin;
			}
		}
		
		handler.addObject(new Player(0, 0, ID.Player, handler));
	}
	
	public static Coin[][] getGrid() {
		return grid;
	}
	
	public static boolean winningMove() {
		for(int c = 0; c < gridWidth - 3; c++) {
			for(int r = 1; r < gridHeight; r++) {
				Color color = grid[c][r].getColor();
				if(color == Player.getColor() || color == Player2.getColor()) {
					if(color == grid[c+1][r].getColor() && color == grid[c+2][r].getColor() && color == grid[c+3][r].getColor()) {
						return true;
					}
					if(r>3) {
						if(color == grid[c+1][r-1].getColor() && color == grid[c+2][r-2].getColor() && color == grid[c+3][r-3].getColor()) {
							return true;
						}
					}
					if(r<4) {
						if(color == grid[c+1][r+1].getColor() && color == grid[c+2][r+2].getColor() && color == grid[c+3][r+3].getColor()) {
							return true;
						}
					}
				}
			}
		}
		for(int c = 0; c < gridWidth; c++) {
			for(int r = 1; r < gridHeight - 3; r++) {
				Color color = grid[c][r].getColor();
				if(color == Player.getColor() || color == Player2.getColor()) {
					if(color == grid[c][r+1].getColor() && color == grid[c][r+2].getColor() && color == grid[c][r+3].getColor()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta > 0) {
				delta--;
				if(frames%5==0) {
					tick();
				}
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS : " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, cellWidth);
		g.setColor(Color.blue);
		g.fillRect(0, cellWidth, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();		
	}

	public static void main(String args[]) {
		new Game();
	}
}
