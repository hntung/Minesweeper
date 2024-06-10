package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Model.LoadData;
public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private LoadData loadData;

	private JMenuBar mnb;
	private JMenu menu;
	private JMenuItem basic, nomal, hard, newGame, exit;
	private int totalTime;
	public GameFrame(int w, int h, int boom) {
		loadData = new LoadData();
		setJMenuBar(mnb = new JMenuBar());
		mnb.add(menu = new JMenu("Game"));
		
		menu.add(newGame = new JMenuItem("New game"));
		menu.addSeparator();
		menu.add(basic = new JMenuItem("Basic"));
		menu.add(nomal = new JMenuItem("Nomal"));
		menu.add(hard = new JMenuItem("Hard"));
		menu.addSeparator();
		menu.add(exit = new JMenuItem("Exit"));

		if (boom == 10) {
			basic.setIcon(new ImageIcon(loadData.getListImage().get("DauTich")));
			setTotalTime(150);
		} else if (boom == 40) {
			nomal.setIcon(new ImageIcon(loadData.getListImage().get("DauTich")));
			setTotalTime(400);
		} else{
			hard.setIcon(new ImageIcon(loadData.getListImage().get("DauTich")));
			setTotalTime(999);
		}
//		totalTime = 60;
		
		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GameFrame(9, 9, 10);
			}
		});

		nomal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GameFrame(16, 16, 40);
			}
		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GameFrame(16, 30, 99);
			}
		});

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GameFrame(w, h, boom);
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		add(gamePanel = new GamePanel(w,h,boom, this));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameFrame(9, 9, 10);
	}

	private void confirmExit() {
        int confirmed = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn thoát không?",
            "Xác nhận thoát",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(confirmed);
        }
    }
	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
}
