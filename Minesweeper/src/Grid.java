import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class Grid extends JPanel {
	
	private int bound = Game.GRIDSIZE * Game.GRIDSIZE;
	
	private static ArrayList<Integer> mines = new ArrayList<Integer>();
	
	private boolean picked = false;
	
	public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();
	
	public Grid(GridLayout g) {
		super(g);
		createCells();
		addCells();
	}
	
	public void createCells() {
		for(int i = 1; i <= Game.MINECOUNT; i++) {
            while(!picked) {
                int minePosition = (int) (Math.random() * bound);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    picked = true;
                }
            }
            picked = false;
        }

        for(int i = 0; i < bound; i++) {
            if(mines.contains(i)) {
                cellGrid.add(new Cell(1, i, false, false));
            } else if(i % Game.GRIDSIZE == 0){
                if(mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(2, i, false, false));
                } else {
                    cellGrid.add(new Cell(0, i, false, false));
                }
            } else if(i % Game.GRIDSIZE == Game.GRIDSIZE - 1){
                if(mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + Game.GRIDSIZE ) ||
                        mines.contains(i + Game.GRIDSIZE)) {
                    cellGrid.add(new Cell(2, i, false, false));
                } else {
                    cellGrid.add(new Cell(0, i, false, false));
                }
            }else {
                if(mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE - 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(2, i, false, false));
                } else {
                    cellGrid.add(new Cell(0, i, false, false));
                }
            }
        }
	}
	
	public void addCells() {
		for(int i = 0; i <= cellGrid.size(); i++) {
			add(cellGrid.get(i));
		}
	}
}
