public class Game {
	public static final int WIDTH = 720, HEIGHT = 720;
	public static final int GRIDSIZE = 10;
	public static final int MINECOUNT = 10;
	
	public Game() {
		new Window(WIDTH, HEIGHT, GRIDSIZE, "Minesweeper", this);
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
