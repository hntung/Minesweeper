package View;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class ButtonPlay extends JButton {
	private PanelPlayer p;
	
	public ButtonPlay(PanelPlayer p) {
		this.p = p;
		setPreferredSize(new Dimension(30,30));
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("noUse"), 0, 0,getPreferredSize().width,getPreferredSize().height, null);
	}
}
