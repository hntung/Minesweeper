package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.World;

public class GamePanel extends JPanel implements MouseListener{
	private PanelNotification p1;
	private PanelPlayer p2;
	
	private GameFrame gameFrame;
	private World world;
	
	private int w, h, boom;
	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.boom = boom;
		this.h = h;
		this.w = w;
		
		world = new World(w,h,boom);
		
		setLayout(new BorderLayout(20,20));
		
		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this),BorderLayout.CENTER);
		p2.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		ButtonPlay[][] arrayButton = p2.getArrayButton();
		for(int i = 0; i < arrayButton.length; i++) {
			for(int j = 0; j< arrayButton[i].length; j++) {
				if(e.getButton() == 1 && e.getSource() == arrayButton[i][j]) {
					if(!world.open(i,j)) {
						if(world.isComplete()) {
							int option = JOptionPane.showConfirmDialog(this, "Game Over!\nDo you want play again?",
									"Notification",JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w,h,boom);
							}else {
								world.fullTrue();
							}	
						}else if(world.isEnd()) {
							int option = JOptionPane.showConfirmDialog(this, "You Win!\nDo you want play again?",
									"Notification",JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w,h,boom);
							}
						}
					}
				}
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
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
