import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.Color;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	private JFrame Frame;
	private JFrame HFrame;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	Connection connection;
	private int count;

	
	
	public LoginFrame() {
		Frame=this;
		Frame.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 511);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	}
	
	public String displayFrame()
	{
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(221, 213, 93, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword.setBounds(221, 261, 93, 16);
		contentPane.add(lblPassword);
		
	    txtUsername = new JTextField();
	    txtUsername.setBounds(341, 211, 188, 22);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 260, 188, 22);
		contentPane.add(passwordField);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(341, 145, 66, 16);
		contentPane.add(lblLogin);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(328, 36, 110, 114);
		contentPane.add(lblImage);
		Image img2=new ImageIcon(this.getClass().getResource("/login icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img2));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection=DBMS_Test.getConnection();
					String query ="select * from registration where Username=? and Password=?";
					java.sql.PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, txtUsername.getText());
					ps.setString(2, passwordField.getText());
					
					ResultSet rs =ps.executeQuery();
					count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(contentPane,"Login Successful");
						Frame.setVisible(false);
						HFrame=new Home();
						HFrame.setVisible(true);
					}
					else if(count>1) {
						JOptionPane.showMessageDialog(contentPane,"Duplicate UserName or Password");
						Frame.setVisible(false);
						HFrame=new Home();
						HFrame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Invalid UserName or Password");
						Frame.setVisible(false);
						HFrame=new Home();
						HFrame.setVisible(true);
						
				    }
                    connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(contentPane,e);
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(328, 320, 97, 25);
		contentPane.add(btnNewButton);
		
		return(txtUsername.getText());
    }
	
	
	public boolean getloginstatus()
	{
		if(count==1) {
			return(true);
		}
		else {
			return false;
		}
	}
}
