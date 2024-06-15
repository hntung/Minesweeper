package View;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelNotification extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p11, p12, p13;
	private GamePanel game;
	
	private JLabel lbTime, lbBoom;
	private ButtonSmile bt;
	private int timeLeft;
	private int totalTime;

    private ScheduledExecutorService executorService;
	public PanelNotification(GamePanel game) {
		this.game = game;
		this.totalTime = game.getGameFrame().getTotalTime();
		this.timeLeft = this.totalTime;
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
		startTimer();			
		p13.add(bt = new ButtonSmile(this));
		
		bt.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent e) {
				bt.setStage(ButtonSmile.now);
				bt.repaint();
				
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi ván mới?", "Notification"
															,JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					stopTimer();
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
		
		game.getGameFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(
			            game,
			            "Bạn có chắc chắn muốn thoát không?",
			            "Xác nhận thoát",
			            JOptionPane.YES_NO_OPTION
			        );

			        if (confirmed == JOptionPane.YES_OPTION) {
			        	stopTimer();
			            game.getGameFrame().dispose();
			        }
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
	private void startTimer() {
	    executorService = Executors.newScheduledThreadPool(1);
	    executorService.scheduleAtFixedRate(() -> {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                if (game.getWorld().isEnd() || game.getWorld().isComplete()) {

	                	stopTimer();
	                } else {
	                    timeLeft--;
	                    String cTime = String.valueOf(timeLeft);
	                    if (cTime.length() == 1) {
	                        ((LabelNumber) lbTime).setNumber("00" + cTime);
	                    } else if (cTime.length() == 2) {
	                        ((LabelNumber) lbTime).setNumber("0" + cTime);
	                    } else {
	                        ((LabelNumber) lbTime).setNumber(cTime);
	                    }
	                    lbTime.repaint();
	                    if (timeLeft <= 0) {
	                    	stopTimer();
	                        game.getWorld().setComplete(true);
	                        int option = JOptionPane.showConfirmDialog(game.getGameFrame(), "Game Over!\nDo you want play again?",
									"Notification",JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION) {
								game.getGameFrame().setVisible(false);
								new GameFrame(game.getW(),game.getH(),game.getBoom());
							}else {
								game.getWorld().fullTrue();
							}	
	                    }
	                }
	            }
	        });
	    }, 1, 1, TimeUnit.SECONDS);
	}
	
	public void stopTimer() {
	    if (executorService != null && !executorService.isShutdown()) {
	        executorService.shutdown();
	    }
	}
	public ButtonSmile getBt() {
		return bt;
	}

	public void setBt(ButtonSmile bt) {
		this.bt = bt;
	}


    public int timeComplete() {
    	return totalTime - timeLeft;
    }


    

	
	
}
