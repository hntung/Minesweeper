package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class DangNhap extends JFrame {

    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPasswrod;
    private static String username;
    // Database connection details
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://LAPTOP-VF2P0PFO\\MSSQL2022:1433;databaseName=Minesweeper;"
            + "encrypt=false;trustServerCertificate=true;"
            + "hostNameInCertificate=LAPTOP-VF2P0PFO\\MSSQL2022";
    String user = "sa";
    String pass = "123";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DangNhap window = new DangNhap();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DangNhap() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 413, 279);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	confirmExit();
            }
        });
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        JLabel lblNewLabel = new JLabel("Tai khoản:");
        lblNewLabel.setBounds(52, 69, 70, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
        lblNewLabel_1.setBounds(52, 122, 57, 14);
        frame.getContentPane().add(lblNewLabel_1);

        txtUsername = new JTextField();
        txtUsername.setBounds(132, 66, 206, 20);
        frame.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        txtPasswrod = new JPasswordField();
        txtPasswrod.setBounds(132, 119, 206, 20);
        frame.getContentPane().add(txtPasswrod);

        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setBounds(52, 165, 97, 32);
        frame.getContentPane().add(btnDangNhap);

        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName(driver);
                    Connection conn = DriverManager.getConnection(dbURL, user, pass);
                    String sql = "SELECT * FROM users WHERE username=? and password=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, txtUsername.getText());
                    ps.setString(2, new String(txtPasswrod.getPassword()));
                    ResultSet rs = ps.executeQuery();

                    if (txtUsername.getText().equals("") || txtPasswrod.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(frame, "Tài khoản và mật khẩu không được để trống");
                        return;
                    } else if (rs.next()) {
                    	username = txtUsername.getText();
                        JOptionPane.showMessageDialog(frame, "Đăng nhập thành công");
                        MainBoard gb = new MainBoard();

                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        JButton btnDangKy = new JButton("Đăng ký");
        btnDangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DangKy formDangKy = new DangKy();
                frame.dispose();
            }
        });
        btnDangKy.setBounds(227, 165, 107, 32);
        frame.getContentPane().add(btnDangKy);
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
            System.exit(0);
        }
    }

    public static  String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DangNhap.username = username;
    }

    
    
}
