package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Config.DatabaseConfig;
import View.ButtonPlay;
import View.ButtonSmile;
import View.DangNhap;
import View.GameFrame;
import View.GamePanel;
import View.PanelNotification;

public class World {
	
	private Random rd;
	private ButtonPlay[][] arrayButton;
	private int[][] arrayMin;
	private boolean[][] arrayBoolean;
	private boolean[][] arrayFlag;
	private boolean isComplete;
	private boolean isEnd;
	private ButtonSmile buttonSmile;
	private JLabel lbTime, lbBoom;
	private int boom;
	private int Flag;
	private GamePanel game;
	private int levelID;
	private int timeComplete;
	private String username = DangNhap.getUsername();
	private PanelNotification p1;
	public World(int w, int h, int boom, GamePanel game) {
		this.boom = boom;
		this.game = game;
		arrayButton = new ButtonPlay[w][h];
		arrayFlag = new boolean[w][h];
		arrayMin = new int[w][h];
		arrayBoolean = new boolean[w][h];
		rd = new Random();
		createArrayMin(boom,w,h);
		if(boom == 10) {
			levelID = 1;
		} else if(boom == 40) {
			levelID = 2;
		} else {
			levelID = 3;
		}
		fillNumber();
		System.out.println(boom);
		for(int i = 0;i < arrayButton.length; i++){
			for(int j = 0;j < arrayButton[i].length; j++){
				System.out.print(arrayMin[i][j] + " ");
				
			}
			System.out.println();
		}
	}
	public boolean open(int i, int j) {	
		if(!isComplete && !isEnd) {
			if(!arrayBoolean[i][j]) {		
				if(arrayMin[i][j] == 0) {	
					arrayBoolean[i][j] = true;
					arrayButton[i][j].setNumber(0);
					arrayButton[i][j].repaint();
					if(checkWin()) {
						isEnd = true;
						return false;
					}
					for(int x = i - 1; x <= i + 1; x++) {
						for(int y = j - 1; y <= j + 1; y++) {
							if(x >= 0 && x <= arrayMin.length - 1 && y >= 0 && y <= arrayMin[i].length - 1) {
								if(!arrayBoolean[x][y]) {
									open(x,y);				
								}
							}
						}
					}
				}else {	
					int number = arrayMin[i][j];
					if(number != -1) {
						arrayBoolean[i][j] = true;
						arrayButton[i][j].setNumber(number);
						arrayButton[i][j].repaint();
						if(checkWin()) {
							isEnd = true;
							return false;
						}
						return true;
					}
				}
			}
			
			if(arrayMin[i][j] == -1) {		
				arrayButton[i][j].setNumber(12);
				arrayButton[i][j].repaint();
				isComplete = true;		
				openAllBoomBoxes(i, j);
				return false;
			}else {
				if(checkWin()) {
					isEnd = true;
					return false;
				}
				return true;
			}
		}else {
			return false;
		}
	}
	
	public boolean checkWin() {
		int count = 0;
		for(int i = 0;i < arrayBoolean.length; i++){
			for(int j = 0;j < arrayBoolean[i].length; j++){
				if(!arrayBoolean[i][j]) {
					count++;
				}
			}
		}
		if(count == boom) {
			timeComplete =  game.getP1().timeComplete() ;
			return true;
		}else {
			return false;
		}
	}

	public void saveResult() {
        try {
        	Class.forName(DatabaseConfig.DRIVER);
            Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASS);


            int currentResult = timeComplete;
            int previousResult = 0; 
            boolean hasPreviousResult = false; 

            String selectQuery = "SELECT timeComplete FROM bangxephang WHERE levelid = ? AND username = ?";
            
            PreparedStatement psSelect = conn.prepareStatement(selectQuery);
            psSelect.setInt(1, levelID); 
            psSelect.setString(2, username);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                previousResult = rs.getInt("timeComplete");
                hasPreviousResult = true;
            }


            if (!hasPreviousResult || currentResult < previousResult) {

                if (hasPreviousResult) {
                    String deleteQuery = "DELETE FROM bangxephang WHERE levelid = ? AND username = ?";
                    PreparedStatement psDelete = conn.prepareStatement(deleteQuery);
                    psDelete.setInt(1, levelID);
                    psDelete.setString(2, username);
                    psDelete.executeUpdate();
                }


                String insertQuery = "INSERT INTO bangxephang (levelid, username, timeComplete) VALUES (?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insertQuery);
                psInsert.setInt(1, levelID);
                psInsert.setString(2, username);
                psInsert.setInt(3, currentResult);
                psInsert.executeUpdate();

                JOptionPane.showMessageDialog(null, "Lưu kết quả thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Kết quả lần này không đủ tốt để lưu vào bảng xếp hạng.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu kết quả: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void fillNumber() {
		for(int i = 0;i < arrayMin.length; i++){
			for(int j = 0;j < arrayMin[i].length; j++){
				if(arrayMin[i][j] == 0) {
					int count = 0;
					for(int x = i - 1; x <= i + 1; x++) {
						for(int y = j - 1; y <= j + 1; y++) {
							if(x >= 0 && x <= arrayMin.length - 1 && y >= 0 && y <= arrayMin[i].length - 1) {
								if(arrayMin[x][y] == -1) {
								count++;
								}
							}
						}
					}
					arrayMin[i][j] = count;
				}
				
			}
		}
	}
	
	public void createArrayMin(int boom,int w, int h) {
		int locationX = rd.nextInt(w);
		int locationY = rd.nextInt(h);
		
		arrayMin[locationX][locationY] = -1;
		int count = 1;
		while(count != boom) {
			locationX = rd.nextInt(w);
			locationY = rd.nextInt(h);
			if(arrayMin[locationX][locationY] != -1) {
			arrayMin[locationX][locationY] = -1;
			
			count = 0;
			for(int i = 0;i < arrayMin.length; i++){
				for(int j = 0;j < arrayMin.length; j++){
					if(arrayMin[i][j] == -1) {
						count++;
						}
					
					}
				}
			}
		}
	}
	
	public void fullTrue() {
		for(int i = 0;i < arrayBoolean.length; i++){
			for(int j = 0;j < arrayBoolean[i].length; j++){
				if(!arrayBoolean[i][j]) {
						arrayBoolean[i][j] = true;
					}
				
				}
			}
	}
	
	public void openAllBoomBoxes(int i, int j) {
		for(int i2 = 0;i2 < arrayBoolean.length; i2++){
			for(int j2 = 0;j2 < arrayBoolean[i].length; j2++){
				if(arrayMin[i2][j2] == -1 && !arrayBoolean[i2][j2]) {
					if(i2 != i || j2 != j) {
						arrayButton[i2][j2].setNumber(10);
						arrayButton[i2][j2].repaint();
					}
				}
			}
		}
	}
	
	public void camCo(int i, int j) {
		if(!arrayBoolean[i][j]) {
			if(arrayFlag[i][j]) {
				Flag--;
				arrayFlag[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateBoom();
			}else if(Flag < boom){
				Flag++;
				arrayFlag[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateBoom();
			}
			
		}
	}
	
	public boolean clickDouble(int i, int j) {
		boolean isCoBoom = false;
		for(int x = i - 1; x <= i + 1; x++) {
			for(int y = j - 1; y <= j + 1; y++) {
				if(x >= 0 && x <= arrayMin.length - 1 && y >= 0 && y <= arrayMin[i].length - 1) {
					if(!arrayFlag[x][y]) {
						if(arrayMin[x][y] == -1) {
							isCoBoom = true;
							arrayButton[x][y].setNumber(11);
							arrayButton[x][y].repaint();
							arrayBoolean[x][y] = true;
						} else if(!arrayBoolean[x][y]) {
							if(arrayMin[x][y] == 0) {
								open(x,y);
							}else{
								arrayButton[x][y].setNumber(arrayMin[x][y]);
								arrayButton[x][y].repaint();
								arrayBoolean[x][y] = true;								
							}
						}
					}
				}
			}
		}
		
		if(isCoBoom) {
			for(int l = 0; l < arrayBoolean.length; l++) {
				for(int k = 0; k < arrayBoolean[i].length; k++) {
					if(arrayMin[l][k] == -1 && !arrayBoolean[l][k]) {
						arrayButton[l][k].setNumber(10);
						arrayButton[l][k].repaint();
					}
				}
			}
			return false;
		}
		
		return true;
	}
	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public ButtonSmile getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(ButtonSmile buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public void setLbTime(JLabel lbTime) {
		this.lbTime = lbTime;
	}

	public JLabel getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(JLabel lbBoom) {
		this.lbBoom = lbBoom;
	}
	public boolean[][] getArrayBoolean() {
		return arrayBoolean;
	}
	public void setArrayBoolean(boolean[][] arrayBoolean) {
		this.arrayBoolean = arrayBoolean;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	public boolean[][] getArrayFlag() {
		return arrayFlag;
	}
	public void setArrayFlag(boolean[][] arrayFlag) {
		this.arrayFlag = arrayFlag;
	}
	public int getFlag() {
		return Flag;
	}
	public void setFlag(int flag) {
		Flag = flag;
	}
	public PanelNotification getP1() {
		return p1;
	}
	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}
	
	
}
