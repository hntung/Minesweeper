package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Config.DatabaseConfig;

public class LeaderBoard extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> levelComboBox;
    private JButton viewButton;
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;

 

    public LeaderBoard() {
        setTitle("Bảng xếp hạng");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	confirmClose();
            }
        });
        JPanel topPanel = new JPanel();
        levelComboBox = new JComboBox<>(new String[] { "Dễ", "Trung bình", "Khó" });
        viewButton = new JButton("Xem bảng xếp hạng");

        topPanel.add(levelComboBox);
        topPanel.add(viewButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[] { "Top", "Username", "Time Complete" }, 0);
        leaderboardTable = new JTable(tableModel);
        add(new JScrollPane(leaderboardTable), BorderLayout.CENTER);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLevel = (String) levelComboBox.getSelectedItem();
                int levelId = getLevelId(selectedLevel);
                loadLeaderboard(levelId);
            }
        });
    }

    private int getLevelId(String levelName) {
        switch (levelName) {
            case "Dễ":
                return 1;
            case "Trung bình":
                return 2;
            case "Khó":
                return 3;
            default:
                return 1;
        }
    }

    private void loadLeaderboard(int levelId) {
        try {
        	Class.forName(DatabaseConfig.DRIVER);
            Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASS);
            String query = "SELECT username, timeComplete FROM bangxephang WHERE levelid = ? ORDER BY timeComplete DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, levelId);
            ResultSet rs = ps.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows

            int rank = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                int timeComplete = rs.getInt("timeComplete");
                tableModel.addRow(new Object[] { rank++, username, timeComplete });
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmClose() {
        int confirmed = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn thoát không?",
                "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmed == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
}
