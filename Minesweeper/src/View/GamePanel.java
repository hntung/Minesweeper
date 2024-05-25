package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.World;

public class GamePanel extends JPanel{
	private PanelNotification p1;
	private PanelPlayer p2;
	
	private GameFrame gameFrame;
	private World world;
	
	private int w,h;
	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.h = h;
		this.w = w;
		
		world = new World(w,h,boom);
		
		setLayout(new BorderLayout(20,20));
		
		add(p1 = new PanelNotification(), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this),BorderLayout.CENTER);
		p2.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
	}
	public int getW() {
		return w;
	}
	
	public void setW(int w) {
		this.w = w;
	}
	
	public int getH() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	
}
