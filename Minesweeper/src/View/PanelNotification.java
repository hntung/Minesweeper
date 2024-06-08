package View;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelNotification extends JPanel {
	private JPanel p11, p12, p13;
	private GamePanel game;
	private JLabel lbTime, lbBoom;
	private ButtonSmile bt;
	public PanelNotification(GamePanel game) {
		this.game = game;
		
		lbTime = game.getWorld().getLbTime();
		lbBoom = game.getWorld().getLbTime();
		bt = game.getWorld().getButtonSmile();
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		add(p11 = new JPanel(), BorderLayout.WEST);
		add(p12 = new JPanel(), BorderLayout.EAST);
		add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new LabelNumber(this,"000"));
		updateBoom();
		
		p12.add(lbTime = new LabelNumber(this,"000"));
		p13.add(bt = new ButtonSmile(this));
		
		bt.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent e) {
				bt.setStage(ButtonSmile.now);
				bt.repaint();
				
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi ván mới?", "Notification"
															,JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					getGame().getGameFrame().setVisible(false);
					new GameFrame(game.getW(),game.getH(),game.getBoom());
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(getGame().getWorld().isEnd() || getGame().getWorld().isComplete()) {
					getGame().getGameFrame().setVisible(false);
					new GameFrame(game.getW(),game.getH(),game.getBoom());
				}else {
					bt.setStage(ButtonSmile.press);
					bt.repaint();					
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public GamePanel getGame() {
		return game;
	}
	public void setGame(GamePanel game) {
		this.game = game;
	}
	public void updateBoom() {
		String boom = String.valueOf(game.getBoom() - game.getWorld().getFlag());
		if(boom.length() == 1) {
			((LabelNumber) lbBoom).setNumber("00" + boom);
		} else if(boom.length() == 2) {
			((LabelNumber) lbBoom).setNumber("0" + boom);
		} else {
			((LabelNumber) lbBoom).setNumber("0" + boom);
		}
		lbBoom.repaint();
	}

	public ButtonSmile getBt() {
		return bt;
	}

	public void setBt(ButtonSmile bt) {
		this.bt = bt;
	}
	
}
