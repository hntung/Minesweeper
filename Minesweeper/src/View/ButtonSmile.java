package View;

import java.awt.Graphics;

import javax.swing.JButton;

import java.awt.Dimension;
public class ButtonSmile extends JButton {
	
	private PanelNotification p;
	
	public ButtonSmile(PanelNotification p) {
		this.p = p;
		setPreferredSize(new Dimension(50,50));
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smile"), 0, 0
				,getPreferredSize().width,getPreferredSize().height, null);
	}
	
	
}
