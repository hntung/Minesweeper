package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
        topPanel.setBackground(Color.WHITE);
        levelComboBox = new JComboBox<>(new String[]{"Dễ", "Trung bình", "Khó"});
        viewButton = new JButton("Xem bảng xếp hạng");
        viewButton.setBackground(Color.LIGHT_GRAY);
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));

        topPanel.add(levelComboBox);
        topPanel.add(viewButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Top", "Username", "Time Complete"}, 0);
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setBackground(Color.WHITE);
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 14));
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setEnabled(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        leaderboardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        leaderboardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

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
            String query = "SELECT username, timeComplete FROM bangxephang WHERE levelid = ? ORDER BY timeComplete ASC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, levelId);
            ResultSet rs = ps.executeQuery();

            tableModel.setRowCount(0);

            int rank = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                int timeComplete = rs.getInt("timeComplete");
                tableModel.addRow(new Object[]{rank++, username, timeComplete});
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
