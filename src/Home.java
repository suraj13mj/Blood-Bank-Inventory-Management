import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mysql.cj.xdevapi.Table;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import java.sql.*;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.CompoundBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Home extends JFrame {

	private JFrame Frame;
	private LoginFrame f;
	private JPanel contentPane;
	private JPanel header_panel;
	private JTabbedPane tabbedPane;
	private JPanel Home_tab;
	private JPanel Donate_tab;
	private JPanel Request_tab;
	private JPanel Bloodbank_tab;
	private JPanel Faq_tab;
	
	private JPanel ht_info_panel;
	//private JPanel ht_login_panel;
	private JPanel ht_register_panel;
	private JPanel ht_availability_panel;
	private JPanel ht_normal_panel;
	
	private JPanel dt_donate_panel; 
	private JPanel dt_login_panel;
	private JPanel rt_display_panel;
	
	
	private JTable table_availability;
	
	
	private JLabel hp_label_login;
	private JLabel hp_label_register;
	private JLabel hp_label_loggedinas;
	private JLabel hp_label_username;
	

	
	private JTextField dt_txt_name;
	private JTextField dt_txt_weight;
	private JTextField dt_txt_age;
	private JTextField txtUsername;
	private JTextField txtUsername1;
	private JLabel lbleligible1;
	private JButton hp_btn_logout;
	private JButton btn_login_submit;
	private JButton btn_login_submit1;
	
	
	private boolean login_status;
	private boolean firsttimestatus=false;
	private String q1,q2,q3,q4,login_name;
	
	
	
	
	Connection connection=null;
	
	private JTable bb_table;
	private JTable bbbdetails_table;
	private JTable bbddetails_table;
	private JTable bbrdetails_table;
	private JTable request_table;
	private JTextField jTextFieldBB;
	private JTextField jTextFieldNO;
	private JTextField jTextFieldBG;
	
	private JComboBox<String> CB_request_bgroup;
	private JComboBox<String> CB_donate_bgroup;
	private JComboBox<String> CB_donate_units;
	private JComboBox<String> CB_donate_bbank; 
	private JComboBox<String> CB_request_units;
	
	private JComboBox<String> CB_home_units;
	private JDateChooser dt_DOD;
	
	private JDateChooser dt_DLD;
	
	private ButtonGroup btngrp;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbx_firsttime;
	
	private boolean checkloginstatus;
	
	private JTextField place;
	private JTextField jTextFieldPA;
	
	
	
	
	//Constructor
	
	public Home() 
	{
		Frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1039, 665);
		Frame.setResizable(false);
		this.application();
	}


	
	
	//GUI
	
	/*private void request_tableMouseClicked(java.awt.event.MouseEvent evt) 
	{                                     
	        
	        // get the model from the request_table
	       DefaultTableModel dm = (DefaultTableModel)request_table.getModel();

	        // get the selected row index
	       int selectedRowIndex = request_table.getSelectedRow();
	       
	        // set the selected row data into jtextfields
	       jTextFieldBB.setText(dm.getValueAt(selectedRowIndex, 0).toString());
	       jTextFieldNO.setText(CB_request_units.getSelectedItem().toString());
	       jTextFieldBG.setText(CB_request_bgroup.getSelectedItem().toString());
	      
   } */ 
	
	
	public void application()
	{   
		//Content Pane
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Header Panel
		
		header_panel = new JPanel();
		header_panel.setBackground(new Color(255, 0, 0));
		header_panel.setForeground(Color.PINK);
		header_panel.setBounds(0, 0, 1028, 73);
		contentPane.add(header_panel);
		header_panel.setLayout(null);
		
		JLabel hp_labelbbank = new JLabel("BLOOD BANK ");
		hp_labelbbank.setForeground(Color.WHITE);
		hp_labelbbank.setBackground(Color.WHITE);
		hp_labelbbank.setBounds(99, 13, 154, 20);
		header_panel.add(hp_labelbbank);
		hp_labelbbank.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel hp_labelmsys = new JLabel("MANAGEMENT SYSTEM");
		hp_labelmsys.setForeground(Color.WHITE);
		hp_labelmsys.setBackground(Color.RED);
		hp_labelmsys.setBounds(70, 30, 207, 40);
		header_panel.add(hp_labelmsys);
		hp_labelmsys.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		/*hp_label_login = new JLabel("LOGIN");
		hp_label_login.setVisible(false);
		hp_label_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Frame.setVisible(false);
				f=new LoginFrame();
				f.setVisible(true);
				login_name=f.displayFrame();
				if(f.getloginstatus()==true)
				{ 
			     
			     try {
			    	 connection=DBMS_Test.getConnection();
					 String q1="select dno from registration where username=?";
					 String q2="select d.dname,d.dob,d.dweight,d.dbgroup,d.dlastdonated from donor d where dno=?";

					 PreparedStatement ps1=connection.prepareStatement(q1);
					 ps1.setString(1,login_name);
					 
					 ResultSet rs1 = ps1.executeQuery();
					 rs1.next();
					 
					 PreparedStatement ps2=connection.prepareStatement(q2);
					 ps2.setString(1,rs1.getString(1));
					 
					 ResultSet rs2 = ps2.executeQuery();
					 rs2.next();
					 
					 
					hp_label_login.setVisible(false);
				    hp_label_register.setVisible(false);
					hp_label_loggedinas.setVisible(true);
					hp_label_username.setVisible(true);
					hp_btn_logout.setVisible(true);
					hp_label_username.setText(txtUsername.getText());
					dt_login_panel.setVisible(false);
					dt_donate_panel.setVisible(true);
					
					
					dt_txt_name.setText(rs2.getString(1));
					dt_txt_weight.setText(rs2.getString(3));
					CB_donate_bgroup.setSelectedItem(rs2.getObject(4));
					dt_DLD.setDate(ConvertSqlDate.FrontEndDate((rs2.getDate(5))));
					int age=ConvertSqlDate.getAge(rs2.getDate(2));
					dt_txt_age.setText(""+age);
					
					int gap=ConvertSqlDate.getGap(rs2.getDate(5));
					//System.out.println("Gap:"+gap);
					
					if((age>=18)&&(gap>=3))
					{
					   rdbtnYes.setSelected(true);
					}
					else 
					{
					   rdbtnNo.setSelected(true);
					   CB_donate_units.setEnabled(false);
					   CB_donate_bbank.setEnabled(false);
					   dt_DOD.setEnabled(false);
					   chckbxNewCheckBox.setEnabled(false);
					   lbleligible1.setVisible(true); 
					}
					connection.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(Home_tab, e);
				}
			}
			}
		});
		hp_label_login.setBounds(780, 13, 64, 40);
		header_panel.add(hp_label_login);
		hp_label_login.setFont(new Font("Arial Black", Font.BOLD, 16));
		hp_label_login.setForeground(Color.WHITE); */
		
		
		
	
			
			
		
		
		hp_label_register = new JLabel("REGISTER");
		hp_label_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home_tab.setVisible(true);
				ht_normal_panel.setVisible(false);
				ht_register_panel.setVisible(true);
				}
		});
		
		hp_label_register.setBounds(871, 13, 99, 40);
		header_panel.add(hp_label_register);
		hp_label_register.setForeground(Color.WHITE);
		hp_label_register.setFont(new Font("Arial Black", Font.BOLD, 16));
		
		JLabel hp_label_image = new JLabel("");
		hp_label_image.setBounds(12, 0, 46, 70);
		header_panel.add(hp_label_image);
		Image img1=new ImageIcon(this.getClass().getResource("/blood1.jpg")).getImage();
		hp_label_image.setIcon(new ImageIcon(img1));
		
		hp_label_loggedinas= new JLabel("Logged in as");
		hp_label_loggedinas.setVisible(false);
		hp_label_loggedinas.setFont(new Font("Tahoma", Font.BOLD, 15));
		hp_label_loggedinas.setForeground(Color.WHITE);
		hp_label_loggedinas.setBounds(760, 14, 99, 20);
		header_panel.add(hp_label_loggedinas);
		
		hp_btn_logout = new JButton("Log out");
		hp_btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPane, "Logged out successfully");
				//hp_label_login.setVisible(true);
				hp_label_register.setVisible(true);
				hp_label_loggedinas.setVisible(false);
				hp_label_username.setVisible(false);
				hp_btn_logout.setVisible(false);
				//btn_login_submit.setVisible(true);
				dt_donate_panel.setVisible(false);
				dt_login_panel.setVisible(true);
				login_status=false;
				firsttimestatus=false;
				//chckbx_firsttime.setSelected(false);
			}
		});
		hp_btn_logout.setVisible(false);
		hp_btn_logout.setBounds(805, 42, 91, 20);
		header_panel.add(hp_btn_logout);
		
		hp_label_username = new JLabel("");
		hp_label_username.setVisible(false);
		hp_label_username.setFont(new Font("Castellar", Font.BOLD, 15));
		hp_label_username.setForeground(Color.WHITE);
		hp_label_username.setBounds(866, 17, 104, 20);
		header_panel.add(hp_label_username);
		
		
		
		
		//JTabbed Pane 
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 73, 1028, 553);
		contentPane.add(tabbedPane);
		
		
		
		//HOME Tab
		
		Home_tab= new JPanel();
		Home_tab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ht_normal_panel.setVisible(true);
				//ht_login_panel.setVisible(false);
				ht_info_panel.setVisible(true);
			}
		});
		tabbedPane.addTab("     HOME     ", null, Home_tab, null);
		Home_tab.setLayout(null);
		
		//Home Tab -- Normal Panel
		
		ht_normal_panel = new JPanel();
		ht_normal_panel.setBounds(12, 12, 999, 497);
		ht_normal_panel.setLayout(null);
		Home_tab.add(ht_normal_panel);
		
		//btn_login_submit.setVisible(false);
		
		//Home Tab -- Normal Panel -- Availability Panel 
		
	    ht_availability_panel = new JPanel();
		ht_availability_panel.setLayout(null);
		ht_availability_panel.setBounds(12, 9, 513, 201);
		ht_normal_panel.add(ht_availability_panel);
		//btn_login_submit.setVisible(false);
		
		JLabel lblFindAvailability = new JLabel("Find Availability");
		lblFindAvailability.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFindAvailability.setBounds(130, 0, 183, 37);
		ht_availability_panel.add(lblFindAvailability);
		
		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBloodGroup.setBounds(109, 97, 108, 16);
		ht_availability_panel.add(lblBloodGroup);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(178, 62, 46, 16);
		ht_availability_panel.add(lblCity);
		
		
		JComboBox<String> CB_home_city = new JComboBox<String>();
		CB_home_city.setBounds(236, 50, 163, 22);
		ht_availability_panel.add(CB_home_city);
		CB_home_city.addItem("");
		CB_home_city.addItem("Hubballi");
		CB_home_city.addItem("Dharwad");
		
		
		JComboBox<String> CB_home_bgroup = new JComboBox<String>();
		CB_home_bgroup.setBounds(236, 91, 163, 22);
		ht_availability_panel.add(CB_home_bgroup);
		CB_home_bgroup.addItem("");
		CB_home_bgroup.addItem("A+");
		CB_home_bgroup.addItem("A-");
		CB_home_bgroup.addItem("B+");
		CB_home_bgroup.addItem("B-");
		CB_home_bgroup.addItem("O+");
		CB_home_bgroup.addItem("O-");
		CB_home_bgroup.addItem("AB+");
		CB_home_bgroup.addItem("AB-");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection=DBMS_Test.getConnection();
					String query ="select b.bname,b.baddress,b.bphone,bb.no_of_units from bloodbank b, bb_blooddetails bb where b.bno=bb.bno and b.bcity=? and bb.bbgroup=? and bb.no_of_units>=1";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, CB_home_city.getSelectedItem().toString());
					ps.setString(2, CB_home_bgroup.getSelectedItem().toString());
					//ps.setString(3, CB_home_units.getSelectedItem().toString());
					
					
					ResultSet rs =ps.executeQuery();
					table_availability.setModel(DbUtils.resultSetToTableModel(rs));
					connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBounds(198, 176, 97, 25);
		ht_availability_panel.add(btnSearch);
		
		JLabel lblunits = new JLabel("No of units");
		lblunits.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblunits.setBounds(109, 135, 108, 16);
		ht_availability_panel.add(lblunits);
		
		CB_home_units = new JComboBox<String>();
		CB_home_units.setBounds(236, 129, 163, 22);
		ht_availability_panel.add(CB_home_units);
		CB_home_units.addItem("");
		CB_home_units.addItem("1");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 223, 513, 261);
		ht_normal_panel.add(scrollPane);
		
		table_availability = new JTable();
		scrollPane.setViewportView(table_availability);
		
		
	
		
		//Home Tab -- Normal Panel -- Info Panel
		
		ht_info_panel = new JPanel();
		ht_info_panel.setLayout(null);
		ht_info_panel.setBounds(552, 9, 435, 475);
		ht_normal_panel.add(ht_info_panel);
		
		JTextArea txtrBloodFactsBlood = new JTextArea("                             INTERESTING FACTS\r\n\r\n 1. Nearly 7% of the body weight of a human is made \r\n     up of blood.\r\n\r\n 2. Every two seconds, someone in this world will \r\n     need blood.\r\n\r\n 3. Humans can have artificial heart but there is no \r\n     substitute for human blood. There is no such thing \r\n     called artificial blood.\r\n\r\n 4. Donating blood never reduces a person\u2019s physical \r\n     energy.\r\n\r\n 5. One pint of blood is capable of saving 3 lives.\r\n\r\n\r\n");
		txtrBloodFactsBlood.setEditable(false);
		txtrBloodFactsBlood.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 17));
		txtrBloodFactsBlood.setBackground(SystemColor.info);
		txtrBloodFactsBlood.setBounds(12, 13, 411, 449);
		ht_info_panel.add(txtrBloodFactsBlood);
		
		
		
		//Home Tab -- Normal Panel -- Login Panel
		
		/*ht_login_panel = new JPanel();
		ht_login_panel.setVisible(false);
		ht_login_panel.setBackground(SystemColor.activeCaptionBorder);
		ht_login_panel.setBounds(552, 9, 435, 475);
		ht_login_panel.setLayout(null);
		ht_normal_panel.add(ht_login_panel);
		
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		lblLogin.setBounds(174, 162, 73, 16);
		ht_login_panel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(48, 212, 101, 16);
		ht_login_panel.add(lblUsername);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword_1.setBounds(48, 267, 101, 16);
		ht_login_panel.add(lblPassword_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		txtUsername.setBounds(174, 211, 210, 22);
		ht_login_panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JPasswordField pwd_login = new JPasswordField();
		pwd_login.setFont(new Font("Tahoma", Font.BOLD, 14));
		pwd_login.setBounds(174, 266, 210, 22);
		ht_login_panel.add(pwd_login);
		
		btn_login_submit = new JButton("Submit");
		btn_login_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection=DBMS_Test.getConnection();
					String query ="select * from registration where Username=? and Password=?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, txtUsername.getText());
					ps.setString(2, pwd_login.getText());
					
					ResultSet rs =ps.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(ht_login_panel,"Login Successful");
						login_status=true;
						login_name=txtUsername.getText();
						hp_label_login.setVisible(false);
						hp_label_register.setVisible(false);
						hp_label_loggedinas.setVisible(true);
						hp_label_username.setVisible(true);
						hp_btn_logout.setVisible(true);
						hp_label_username.setText(txtUsername.getText());
						ht_info_panel.setVisible(true);
						dt_login_panel.setVisible(false);
						dt_donate_panel.setVisible(true);
						btn_login_submit.setVisible(false);
						
						if(login_status==true)
						{ 
						 String q1="select dno from registration where username=?";
					     String q2="select d.dname,d.dob,d.dweight,d.dbgroup,d.dlastdonated from donor d where dno=?";

					     PreparedStatement ps1=connection.prepareStatement(q1);
					     ps1.setString(1,login_name);
					     
				         ResultSet rs1 = ps1.executeQuery();
					     rs1.next();
					     
					     PreparedStatement ps2=connection.prepareStatement(q2);
					     ps2.setString(1,rs1.getString(1));
					     
				         ResultSet rs2 = ps2.executeQuery();
					     rs2.next();
					     
					     
					
					    dt_txt_name.setText(rs2.getString(1));
					    dt_txt_weight.setText(rs2.getString(3));
					    CB_donate_bgroup.setSelectedItem(rs2.getObject(4));
					    dt_DLD.setDate(ConvertSqlDate.FrontEndDate((rs2.getDate(5))));
					    int age=ConvertSqlDate.getAge(rs2.getDate(2));
					    dt_txt_age.setText(""+age);
					    
					    int gap=ConvertSqlDate.getGap(rs2.getDate(5));
						//System.out.println("Gap:"+gap);
						
					    if((age>=18)&&(gap>=3))
						{
					       rdbtnYes.setSelected(true);
						}
				        else 
				        {
					       rdbtnNo.setSelected(true);
					       CB_donate_units.setEnabled(false);
					       CB_donate_bbank.setEnabled(false);
					       dt_DOD.setEnabled(false);
					       chckbxNewCheckBox.setEnabled(false);
					       lbleligible1.setVisible(true); 
				        }
						
					}
					else if(count>1) {
						JOptionPane.showMessageDialog(ht_login_panel,"Duplicate UserName or Password");
					}
					else {
						JOptionPane.showMessageDialog(ht_login_panel,"Invalid UserName or Password");
				    }
					connection.close();
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(ht_login_panel,e);
					
				}
				
			}
		});
		btn_login_submit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_login_submit.setBounds(164, 328, 97, 25);
		ht_login_panel.add(btn_login_submit);*/
		
		
		/*JLabel lblNewDonor = new JLabel("New Donor..?");
		lblNewDonor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewDonor.setBounds(122, 366, 114, 16);
		ht_login_panel.add(lblNewDonor);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegister.setBounds(248, 363, 73, 22);
		ht_login_panel.add(lblRegister);*/
		
		/*JLabel label = new JLabel("");
		label.setBounds(173, 25, 101, 110);
		ht_login_panel.add(label);
		Image img2=new ImageIcon(this.getClass().getResource("/login icon.png")).getImage();
		label.setIcon(new ImageIcon(img2)); */  
		
		
		
		
		//Home Tab -- Register Panel
		
		
		ht_register_panel = new JPanel();
		ht_register_panel.setVisible(false);
		ht_register_panel.setBackground(SystemColor.activeCaption);
		ht_register_panel.setBounds(12, 12, 999, 497);
		ht_register_panel.setLayout(null);
		Home_tab.add(ht_register_panel);
		
		JLabel lblNewDonorRegistration = new JLabel("New Donor Registration");
		lblNewDonorRegistration.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewDonorRegistration.setBounds(395, 13, 224, 28);
		ht_register_panel.add(lblNewDonorRegistration);
		
		JLabel lblReg_Name = new JLabel("Name");
		lblReg_Name .setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReg_Name .setBounds(44, 71, 56, 16);
		ht_register_panel.add(lblReg_Name );
		
		JLabel lblRegEmailid = new JLabel("Username");
		lblRegEmailid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegEmailid.setBounds(44, 104, 78, 16);
		ht_register_panel.add(lblRegEmailid);
		
		JLabel lblRegPassword = new JLabel("Password");
		lblRegPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegPassword.setBounds(44, 137, 78, 16);
		ht_register_panel.add(lblRegPassword);
		
		JLabel lblRegDateOfBirth = new JLabel("Date of Birth");
		lblRegDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegDateOfBirth.setBounds(44, 170, 107, 16);
		ht_register_panel.add(lblRegDateOfBirth);
		
		JLabel lblRegWeight = new JLabel("Weight");
		lblRegWeight.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegWeight.setBounds(44, 203, 56, 17);
		ht_register_panel.add(lblRegWeight);
		
		JLabel lblRegGender = new JLabel("Gender");
		lblRegGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegGender.setBounds(480, 203, 56, 16);
		ht_register_panel.add(lblRegGender);
		
		JLabel lblRegBloodGroup= new JLabel("Blood group");
		lblRegBloodGroup.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegBloodGroup.setBounds(44, 236, 99, 19);
		ht_register_panel.add(lblRegBloodGroup);
		
		JLabel lblRegDateLastDonated = new JLabel("Date last donated");
		lblRegDateLastDonated.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegDateLastDonated.setBounds(44, 269, 139, 16);
		ht_register_panel.add(lblRegDateLastDonated);
		
		JLabel lblRegPhone = new JLabel("Phone");
		lblRegPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegPhone.setBounds(44, 302, 56, 16);
		ht_register_panel.add(lblRegPhone);
		
		JLabel lblRegAddress = new JLabel("Address");
		lblRegAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegAddress.setBounds(44, 335, 66, 16);
		ht_register_panel.add(lblRegAddress);
		
		JDateChooser Reg_dateChooser_DLD = new JDateChooser();
		Reg_dateChooser_DLD.setBounds(195, 269, 162, 22);
		ht_register_panel.add(Reg_dateChooser_DLD);
		
		JDateChooser Reg_dateChooser_DOB = new JDateChooser();
		Reg_dateChooser_DOB.setBounds(195, 164, 162, 22);
		ht_register_panel.add(Reg_dateChooser_DOB);
		
		JTextField txt_Username = new JTextField();
		txt_Username.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 13));
		txt_Username.setColumns(10);
		txt_Username.setBounds(195, 102, 224, 22);
		ht_register_panel.add(txt_Username);
		
		
		JTextField txt_Name = new JTextField();
		txt_Name.setColumns(10);
		txt_Name.setBounds(195, 69, 224, 22);
		ht_register_panel.add(txt_Name);
		
		JTextField txt_weight = new JTextField();
		txt_weight.setColumns(10);
		txt_weight.setBounds(195, 201, 116, 22);
		ht_register_panel.add(txt_weight);
		
		JPasswordField pwd_register = new JPasswordField();
		pwd_register.setBounds(195, 135, 224, 22);
		ht_register_panel.add(pwd_register);
		
		JTextField txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(195, 300, 224, 22);
		ht_register_panel.add(txt_phone);
		
		JTextField txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(195, 333, 672, 22);
		ht_register_panel.add(txt_address);
		
		JLabel lblKgs_1 = new JLabel("kgs");
		lblKgs_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKgs_1.setBounds(323, 202, 56, 19);
		ht_register_panel.add(lblKgs_1);
		
		JComboBox<String> CB_Reg_Bgroup = new JComboBox<String>();
		CB_Reg_Bgroup.setBounds(195, 235, 162, 22);
		ht_register_panel.add(CB_Reg_Bgroup);
		CB_Reg_Bgroup.addItem("");
		CB_Reg_Bgroup.addItem("A+");
		CB_Reg_Bgroup.addItem("A-");
		CB_Reg_Bgroup.addItem("B+");
		CB_Reg_Bgroup.addItem("B-");
		CB_Reg_Bgroup.addItem("O+");
		CB_Reg_Bgroup.addItem("O-");
		CB_Reg_Bgroup.addItem("AB+");
		CB_Reg_Bgroup.addItem("AB-");
		
		JComboBox<String> CB_Reg_Gender = new JComboBox<String>();
		CB_Reg_Gender.setBounds(644, 201, 162, 22);
		ht_register_panel.add(CB_Reg_Gender);
		CB_Reg_Gender.addItem("");
		CB_Reg_Gender.addItem("Male");
		CB_Reg_Gender.addItem("Female");
		
		JButton btn_Reg_Submit = new JButton("SUBMIT");
		btn_Reg_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection=DBMS_Test.getConnection();
					String query1 ="insert into Donor (dname,dob,dweight,dbgroup,dlastdonated,dgender,dphone,daddress) values(?,?,?,?,?,?,?,?)";
					PreparedStatement ps1=connection.prepareStatement(query1);
					ps1.setString(1, txt_Name.getText());
					ps1.setString(2, ConvertSqlDate.SQLDate(Reg_dateChooser_DOB.getDate()).toString());
					ps1.setString(3, txt_weight.getText());
					ps1.setString(4, CB_Reg_Bgroup.getSelectedItem().toString());
					if(!firsttimestatus)
					{
					  ps1.setString(5, ConvertSqlDate.SQLDate(Reg_dateChooser_DLD.getDate()).toString());
					}
					else
					{
						ps1.setString(5, "2010-11-11");
					}
					ps1.setString(6, CB_Reg_Gender.getSelectedItem().toString());
					ps1.setString(7, txt_phone.getText());
					ps1.setString(8, txt_address.getText());
					
					int count1=ps1.executeUpdate();
					
					String query2 ="select dno from donor where dname=? and dweight=?";
					PreparedStatement ps2=connection.prepareStatement(query2);
					ps2.setString(1, txt_Name.getText());
					ps2.setString(2, txt_weight.getText());
					
					ResultSet rs=ps2.executeQuery();
					rs.next();
					
					String query3 ="insert into registration values(?,?,?)";
					PreparedStatement ps3=connection.prepareStatement(query3);
					ps3.setInt(1, rs.getInt(1));
					ps3.setString(2, txt_Username.getText());
					ps3.setString(3, pwd_register.getText());
					
					int count2=ps3.executeUpdate();
					
					
					if(count1>0&&count2>0)
					{
						JOptionPane.showMessageDialog(ht_register_panel,"Registration Successful - LOGIN to Donate");
					}
					connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(ht_register_panel,e);
					e.printStackTrace();
					
				}
			
			}
			
			
		});
		btn_Reg_Submit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_Reg_Submit.setBounds(439, 395, 97, 25);
		ht_register_panel.add(btn_Reg_Submit);
		
		chckbx_firsttime = new JCheckBox("First time Donor");
		chckbx_firsttime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reg_dateChooser_DLD.setEnabled(!chckbx_firsttime.isSelected());
				if(chckbx_firsttime.isSelected())
				{
					firsttimestatus=true;
				}
				
			}
		});
		chckbx_firsttime.setBounds(400, 265, 130, 23);
		ht_register_panel.add(chckbx_firsttime);
		
		
		
		
		
		
		//DONATE Tab
		
	
		Donate_tab = new JPanel();
		Donate_tab.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(login_status==true)
				{
					dt_login_panel.setVisible(false);
					dt_donate_panel.setVisible(true);
				}
				else
				{
					dt_login_panel.setVisible(true);
					dt_donate_panel.setVisible(false);
				}
			}
		});
		tabbedPane.addTab("     DONATE     ", null, Donate_tab, null);
		Donate_tab.setLayout(null);
		
		
		dt_donate_panel = new JPanel();
		dt_donate_panel.setBounds(12, 12, 999, 497);
		Donate_tab.add(dt_donate_panel);
		dt_donate_panel.setLayout(null);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Donor Information");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(391, 13, 184, 28);
		dt_donate_panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(44, 72, 56, 16);
		dt_donate_panel.add(lblNewLabel_5);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAge.setBounds(44, 105, 56, 20);
		dt_donate_panel.add(lblAge);
		
		JLabel lblB = new JLabel("Blood group");
		lblB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblB.setBounds(44, 138, 89, 20);
		dt_donate_panel.add(lblB);
		
		JLabel lblDateLastDonated = new JLabel("Date last donated");
		lblDateLastDonated.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateLastDonated.setBounds(44, 171, 143, 16);
		dt_donate_panel.add(lblDateLastDonated);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWeight.setBounds(529, 105, 56, 20);
		dt_donate_panel.add(lblWeight);
		
		JLabel lblEligibility = new JLabel("Eligibility");
		lblEligibility.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEligibility.setBounds(44, 204, 89, 20);
		dt_donate_panel.add(lblEligibility);
		
		JLabel lblDateOfDonation = new JLabel("Date of donation");
		lblDateOfDonation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateOfDonation.setBounds(44, 237, 128, 16);
		dt_donate_panel.add(lblDateOfDonation);
		
		JLabel lblNoOfUnits_2 = new JLabel("No of units");
		lblNoOfUnits_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNoOfUnits_2.setBounds(44, 270, 89, 16);
		dt_donate_panel.add(lblNoOfUnits_2);
		
		JLabel lblBloodBank_1 = new JLabel("Blood Bank");
		lblBloodBank_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBloodBank_1.setBounds(44, 303, 89, 16);
		dt_donate_panel.add(lblBloodBank_1);
		
		dt_txt_name = new JTextField();
		dt_txt_name.setBounds(188, 70, 293, 22);
		dt_txt_name.setColumns(10);
		dt_donate_panel.add(dt_txt_name);
		
		dt_txt_age = new JTextField();
		dt_txt_age.setColumns(10);
		dt_txt_age.setBounds(188, 105, 116, 22);
		dt_donate_panel.add(dt_txt_age);
		
		dt_txt_weight = new JTextField();
		dt_txt_weight.setColumns(10);
		dt_txt_weight.setBounds(635, 105, 149, 22);
		dt_donate_panel.add(dt_txt_weight);
		
		CB_donate_bgroup = new JComboBox<String>();
		CB_donate_bgroup.setBounds(188, 138, 209, 22);
		dt_donate_panel.add(CB_donate_bgroup);
		CB_donate_bgroup.addItem("");
		CB_donate_bgroup.addItem("A+");
		CB_donate_bgroup.addItem("A-");
		CB_donate_bgroup.addItem("B+");
		CB_donate_bgroup.addItem("B-");
		CB_donate_bgroup.addItem("O+");                            
		CB_donate_bgroup.addItem("O-");
		CB_donate_bgroup.addItem("AB+");
		CB_donate_bgroup.addItem("AB-");
		
		
		CB_donate_units = new JComboBox<String>();        
		CB_donate_units.setBounds(188, 268, 209, 22);                         
		dt_donate_panel.add(CB_donate_units);
		CB_donate_units.addItem("");
		CB_donate_units.addItem("1");
		
		
		CB_donate_bbank = new JComboBox<String>();
		CB_donate_bbank.setBounds(188, 301, 209, 22);
		dt_donate_panel.add(CB_donate_bbank);
		CB_donate_bbank.addItem("");
		CB_donate_bbank.addItem("Lifeline Blood Bank");
		CB_donate_bbank.addItem("Rotary Blood Bank");
		CB_donate_bbank.addItem("SDM Blood Bank");
		CB_donate_bbank.addItem("KIMS Blood Bank");
		
		
		JLabel lblKgs = new JLabel("kgs");                               
		lblKgs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKgs.setBounds(791, 108, 56, 20);
		dt_donate_panel.add(lblKgs);
		
		JButton btnNewButton_1 = new JButton("Donate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					connection=DBMS_Test.getConnection();
					
					String query1 ="select dno from donor where dname=?";
					PreparedStatement ps1=connection.prepareStatement(query1);
					ps1.setString(1, dt_txt_name.getText());
					
					ResultSet rs1=ps1.executeQuery();
					rs1.next();
					
					String query2 ="select bno from bloodbank where bname=?";
					PreparedStatement ps2=connection.prepareStatement(query2);
					ps2.setString(1, CB_donate_bbank.getSelectedItem().toString());
					
					
					ResultSet rs2=ps2.executeQuery();
					rs2.next();
					
					String query3 ="select no_of_units from bb_blooddetails where bno=? and bbgroup=?";
					PreparedStatement ps3=connection.prepareStatement(query3);
					ps3.setInt(1, rs2.getInt(1));
					ps3.setString(2, CB_donate_bgroup.getSelectedItem().toString());
					
					
					ResultSet rs3=ps3.executeQuery();
					rs3.next();
					
					String query4 ="select binventory from bloodbank where bno=?";// and bname=?";
					PreparedStatement ps4=connection.prepareStatement(query4);
					ps4.setInt(1, rs2.getInt(1));
					//ps4.setString(2, CB_donate_bbank.getSelectedItem().toString());
					
					
					ResultSet rs4=ps4.executeQuery();
					rs4.next();
					
					String nunits1=CB_donate_units.getSelectedItem().toString();
					int nunits2=Integer.parseInt(nunits1);
					int nunits3=rs3.getInt(1)+(nunits2);
					
					
					int binv3=rs4.getInt(1)+(nunits2);
					
					String query5 ="update bb_blooddetails set no_of_units=? where bno=? and bbgroup=?";
					PreparedStatement ps5=connection.prepareStatement(query5);
					ps5.setInt(1, nunits3);
					ps5.setInt(2, rs2.getInt(1));
					ps5.setString(3, CB_donate_bgroup.getSelectedItem().toString());
					
					int count1=ps5.executeUpdate();
					
					String query6 ="update bloodbank set binventory=? where bno=?";// and bname=?";
					PreparedStatement ps6=connection.prepareStatement(query6);
					ps6.setInt(1, binv3);
					ps6.setInt(2, rs2.getInt(1));
					//ps6.setString(3, CB_donate_bbank.getSelectedItem().toString());
					
					int count2=ps6.executeUpdate();
					
					String query7 ="insert into bb_donordetails (bno,dno,date_of_donation) values (?,?,?)";
					PreparedStatement ps7=connection.prepareStatement(query7);
					ps7.setInt(1, rs2.getInt(1));
					ps7.setInt(2, rs1.getInt(1));
					ps7.setString(3, ConvertSqlDate.SQLDate(dt_DOD.getDate()).toString());
					
					int count3=ps7.executeUpdate();					
					
					String query8="update donor set dlastdonated=? where dno=?";
					PreparedStatement ps8=connection.prepareStatement(query8);
					ps8.setString(1, ConvertSqlDate.SQLDate(dt_DOD.getDate()).toString());
					ps8.setInt(2, rs1.getInt(1));
					
					int count4=ps8.executeUpdate();
					
					
					
				   if(count1>0&&count2>0&&count3>0&&count4>0)
					{
						JOptionPane.showMessageDialog(dt_donate_panel,"Donation Successful");
					}
					connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
				
				
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(436, 428, 161, 38);
		btnNewButton_1.setEnabled(false);
		dt_donate_panel.add(btnNewButton_1);
		
		chckbxNewCheckBox = new JCheckBox("I, here by agree to all the conditions and give my complete consent to donate my blood to the above mentioned blood bank.");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnNewButton_1.setEnabled(chckbxNewCheckBox.isSelected());
			}
		});
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(63, 376, 879, 25);
		dt_donate_panel.add(chckbxNewCheckBox);            
		
		dt_DLD = new JDateChooser();
		dt_DLD.setBounds(188, 171, 209, 22);
		dt_donate_panel.add(dt_DLD);
		
		dt_DOD = new JDateChooser();
		dt_DOD.setBounds(188, 231, 209, 22);
		dt_donate_panel.add(dt_DOD);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(188, 203, 65, 25);
		dt_donate_panel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(257, 203, 65, 25);
		dt_donate_panel.add(rdbtnNo);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnYes);
		btngrp.add(rdbtnNo);
		
		lbleligible1 = new JLabel("( ! You are not eligible to donate )");
		lbleligible1.setForeground(Color.RED);
		lbleligible1.setBounds(341, 207, 209, 16);
		dt_donate_panel.add(lbleligible1);
		lbleligible1.setVisible(false);
		
		
		JLabel lblPleaseLoginOr = new JLabel("Please LOGIN or REGISTER inorder to donate blood.");
		lblPleaseLoginOr.setVisible(false);
		lblPleaseLoginOr.setForeground(Color.BLUE);
		lblPleaseLoginOr.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseLoginOr.setBounds(308, 13, 407, 16);
		Donate_tab.add(lblPleaseLoginOr);
		
		
		
		
		dt_login_panel = new JPanel();
		dt_login_panel.setBackground(SystemColor.activeCaptionBorder);
		dt_login_panel.setBounds(280, 50, 435, 450);
		dt_login_panel.setLayout(null);
		Donate_tab.add(dt_login_panel);
		
		
		JLabel lblLogin1 = new JLabel("LOGIN");
		lblLogin1.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		lblLogin1.setBounds(174, 162, 73, 16);
		dt_login_panel.add(lblLogin1);
		
		JLabel lblUsername1 = new JLabel("UserName");
		lblUsername1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername1.setBounds(48, 212, 101, 16);
		dt_login_panel.add(lblUsername1);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword_2.setBounds(48, 267, 101, 16);
		dt_login_panel.add(lblPassword_2);
		
		txtUsername1 = new JTextField();
		txtUsername1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
		txtUsername1.setBounds(174, 211, 210, 22);
		dt_login_panel.add(txtUsername1);
		txtUsername1.setColumns(10);
		
		JPasswordField pwd_login1 = new JPasswordField();
		pwd_login1.setFont(new Font("Tahoma", Font.BOLD, 14));
		pwd_login1.setBounds(174, 266, 210, 22);
		dt_login_panel.add(pwd_login1);
		
		btn_login_submit1 = new JButton("Submit");
		btn_login_submit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection=DBMS_Test.getConnection();
					String query ="select * from registration where Username=? and Password=?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, txtUsername1.getText());
					ps.setString(2, pwd_login1.getText());
					
					ResultSet rs =ps.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(dt_login_panel, "Login Successfull");
						login_status=true;
						login_name=txtUsername1.getText();
						dt_donate_panel.setVisible(true);
						dt_login_panel.setVisible(false);
						//hp_label_login.setVisible(false);
						hp_label_register.setVisible(false);
						hp_label_loggedinas.setVisible(true);
						hp_label_username.setVisible(true);
						hp_btn_logout.setVisible(true);
						hp_label_username.setText(txtUsername1.getText());
						ht_info_panel.setVisible(true);
						//firsttimestatus=false;
						
						if(login_status==true)
						{  
							     String q1="select dno from registration where username=?";
							     String q2="select d.dname,d.dob,d.dweight,d.dbgroup,d.dlastdonated from donor d where dno=?";

							     PreparedStatement ps1=connection.prepareStatement(q1);
							     ps1.setString(1,login_name);
							     
						         ResultSet rs1 = ps1.executeQuery();
							     rs1.next();
							     
							     PreparedStatement ps2=connection.prepareStatement(q2);
							     ps2.setString(1,rs1.getString(1));
							     
						         ResultSet rs2 = ps2.executeQuery();
							     rs2.next();
							     
							     
							
							    dt_txt_name.setText(rs2.getString(1));
							    dt_txt_weight.setText(rs2.getString(3));
							    CB_donate_bgroup.setSelectedItem(rs2.getObject(4));
							    dt_DLD.setDate(ConvertSqlDate.FrontEndDate((rs2.getDate(5))));
						
							    int age=ConvertSqlDate.getAge(rs2.getDate(2));
							    System.out.println("Age:"+age);
							    dt_txt_age.setText(""+age);
							    
							    int elgweight=Integer.parseInt(rs2.getString(3));
							    System.out.println("Weight:"+elgweight);
							    int gap;
							    if((firsttimestatus==false)&&(age>=18)&&((gap=ConvertSqlDate.getGap(rs2.getDate(5)))>=3)&&(elgweight>=50))
							    {
							     //gap=ConvertSqlDate.getGap(rs2.getDate(5));
								 System.out.println("Gap:"+gap);
							     rdbtnYes.setSelected(true);
								 CB_donate_units.setEnabled(true);
								 CB_donate_bbank.setEnabled(true);
								 dt_DOD.setEnabled(true);
								 chckbxNewCheckBox.setEnabled(true);
								 lbleligible1.setVisible(false); 
								 btnNewButton_1.setEnabled(true);
							    } 
							    else if((firsttimestatus==true)&&(age>=18)&&(elgweight>=50))
							    {
							    	rdbtnYes.setSelected(true);
							    	CB_donate_units.setEnabled(true);
									CB_donate_bbank.setEnabled(true);
									dt_DOD.setEnabled(true);
									chckbxNewCheckBox.setEnabled(true);
									lbleligible1.setVisible(false); 
									btnNewButton_1.setEnabled(true);
							    }
								else 
								{
									rdbtnNo.setSelected(true);
									CB_donate_units.setEnabled(false);
									CB_donate_bbank.setEnabled(false);
									dt_DOD.setEnabled(false);
									chckbxNewCheckBox.setEnabled(false);
									lbleligible1.setVisible(true); 
									btnNewButton_1.setEnabled(false);
								}
						}
					}
					else if(count>1) {
						JOptionPane.showMessageDialog(dt_login_panel,"Duplicate UserName or Password");
					}
					else {
						JOptionPane.showMessageDialog(dt_login_panel,"Invalid UserName or Password");
				    }
					connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					e.printStackTrace();
					
				}
				
			}
		});
		btn_login_submit1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_login_submit1.setBounds(164, 328, 97, 25);
		dt_login_panel.add(btn_login_submit1);
		
		
		/*JLabel lblNewDonor1 = new JLabel("New Donor..?");
		lblNewDonor1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewDonor1.setBounds(122, 366, 114, 16);
		dt_login_panel.add(lblNewDonor1);
		
		JLabel lblRegister1 = new JLabel("Register");
		lblRegister1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegister1.setBounds(248, 363, 73, 22);
		dt_login_panel.add(lblRegister1);*/
		
		JLabel label1 = new JLabel("");
		label1.setBounds(173, 25, 101, 110);
		dt_login_panel.add(label1);
		Image img3=new ImageIcon(this.getClass().getResource("/login icon.png")).getImage();
		label1.setIcon(new ImageIcon(img3));
		
		if(login_status==true) {
			dt_donate_panel.setVisible(true);
			dt_login_panel.setVisible(false);
		}
		else
		{
			lblPleaseLoginOr.setVisible(true);
			dt_donate_panel.setVisible(false);
			dt_login_panel.setVisible(true);
		}
		
		
		
		
		//REQUEST Tab
		
		
		Request_tab = new JPanel();
		tabbedPane.addTab("     REQUEST     ", null, Request_tab, null);
		Request_tab.setLayout(null);
		
		JPanel rt_request_panel = new JPanel();
		rt_request_panel.setBackground(Color.WHITE);
		rt_request_panel.setForeground(new Color(0, 0, 0));
		rt_request_panel.setBounds(12, 45, 999, 157);
		Request_tab.add(rt_request_panel);
		rt_request_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Receipent Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(43, 23, 145, 16);
		rt_request_panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Blood Group");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(43, 62, 128, 16);
		rt_request_panel.add(lblNewLabel_3);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhoneNo.setBounds(43, 103, 92, 16);
		rt_request_panel.add(lblPhoneNo);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHospital.setBounds(572, 23, 92, 16);
		rt_request_panel.add(lblHospital);
		
		JLabel lblNoOfUnits_1 = new JLabel("No of units");
		lblNoOfUnits_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNoOfUnits_1.setBounds(572, 62, 92, 16);
		rt_request_panel.add(lblNoOfUnits_1);
		
		JTextField rname = new JTextField();
		rname.setBounds(194, 21, 340, 22);
		rt_request_panel.add(rname);
		rname.setColumns(10);
		
		JTextField phone = new JTextField();
		phone.setBounds(194, 101, 221, 22);
		rt_request_panel.add(phone);
		phone.setColumns(10);
		
		JTextField hospital = new JTextField();
		hospital.setBounds(689, 21, 227, 22);
		rt_request_panel.add(hospital);
		hospital.setColumns(10);
		
		CB_request_units = new JComboBox<String>();
		CB_request_units.setBounds(689, 60, 167, 22);
		rt_request_panel.add(CB_request_units);
		CB_request_units.addItem("");
		CB_request_units.addItem("1");
		
		
		CB_request_bgroup = new JComboBox<String>();
		CB_request_bgroup.setBounds(193, 60, 167, 22);
		CB_request_bgroup.addItem("");
		CB_request_bgroup.addItem("A+");
		CB_request_bgroup.addItem("A-");
		CB_request_bgroup.addItem("B+");
		CB_request_bgroup.addItem("B-");
		CB_request_bgroup.addItem("O+");
		CB_request_bgroup.addItem("O-");
		CB_request_bgroup.addItem("AB+");
		CB_request_bgroup.addItem("AB-");
		rt_request_panel.add(CB_request_bgroup);
		
		JLabel lblPlace = new JLabel("Place");
		lblPlace.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlace.setBounds(572, 104, 92, 16);
		lblPlace.setVisible(true);
		rt_request_panel.add(lblPlace);
		
		place = new JTextField();
		place.setColumns(10);
		place.setBounds(689, 101, 227, 22);
		place.setVisible(true);
		rt_request_panel.add(place);
		
		
		JLabel lblNewLabel_1 = new JLabel("Receipent Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(12, 13, 202, 16);
		Request_tab.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rt_display_panel.setVisible(true);
				int temp=Integer.parseInt(CB_request_units.getSelectedItem().toString());
				ArrayList<BloodBank> bblist=DBMS_Test.fetchBloodBank(CB_request_bgroup.getSelectedItem().toString(),temp);
				DefaultTableModel dm=new DefaultTableModel();
				dm.addColumn("Blood Bank");
				dm.addColumn("City");
				dm.addColumn("Address");
				dm.addColumn("Phone No");
				dm.addColumn("No of units");
				
				
				for(BloodBank bb :bblist)
				{
					dm.addRow(new Object[] {bb.getBname(),bb.getBcity(),bb.getBaddress(),bb.getBphone(),bb.getNo_of_units()});
				}
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(12, 280, 700, 194);
				Request_tab.add(scrollPane_1);
				
				request_table = new JTable(dm);
				scrollPane_1.setViewportView(request_table);
				
				request_table.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	//request_tableMouseClicked(evt);
		            	 DefaultTableModel dm = (DefaultTableModel)request_table.getModel();

		     	        // get the selected row index
		     	       int selectedRowIndex = request_table.getSelectedRow();
		     	       
		     	        // set the selected row data into jtextfields
		     	       jTextFieldBB.setText(dm.getValueAt(selectedRowIndex, 0).toString());
		     	       jTextFieldNO.setText(CB_request_units.getSelectedItem().toString());
		     	       jTextFieldBG.setText(CB_request_bgroup.getSelectedItem().toString());
		               jTextFieldPA.setText(place.getText().toString());
		            }
		        });
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(428, 215, 139, 25);
		Request_tab.add(btnNewButton);
		
		rt_display_panel = new JPanel();
		rt_display_panel.setBounds(753, 255, 258, 238);
		Request_tab.add(rt_display_panel);
		rt_display_panel.setLayout(null);
		rt_display_panel.setVisible(false);
		
		jTextFieldBB = new JTextField();
		jTextFieldBB.setBounds(27, 65, 199, 22);
		rt_display_panel.add(jTextFieldBB);
		jTextFieldBB.setColumns(10);
		
		JLabel lblBloodGroup_1 = new JLabel("Blood Group");
		lblBloodGroup_1.setBounds(37, 100, 97, 16);
		rt_display_panel.add(lblBloodGroup_1);
		lblBloodGroup_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		jTextFieldBG = new JTextField();
		jTextFieldBG.setBounds(138, 98, 88, 22);
		rt_display_panel.add(jTextFieldBG);
		jTextFieldBG.setColumns(10);
		
		JLabel lblNoOfUnits = new JLabel("No of units");
		lblNoOfUnits.setBounds(37, 131, 97, 16);
		rt_display_panel.add(lblNoOfUnits);
		lblNoOfUnits.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		jTextFieldNO = new JTextField();
		jTextFieldNO.setBounds(138, 129, 88, 22);
		rt_display_panel.add(jTextFieldNO);
		jTextFieldNO.setColumns(10);
		
		JButton btnRequest = new JButton("REQUEST");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					connection=DBMS_Test.getConnection();
					String query1 ="insert into Receipent (rname,rbgroup,rphone,hospital) values(?,?,?,?)";
					PreparedStatement ps1=connection.prepareStatement(query1);
					ps1.setString(1, rname.getText());
					ps1.setString(2, CB_request_bgroup.getSelectedItem().toString());
					ps1.setString(3, phone.getText());
					ps1.setString(4, hospital.getText());					
					
					int count1=ps1.executeUpdate();
					
					String query2 ="select rno from receipent where rname=?";
					PreparedStatement ps2=connection.prepareStatement(query2);
					ps2.setString(1, rname.getText());
					//ps2.setString(2, hospital.getText());
					
					ResultSet rs1=ps2.executeQuery();
					rs1.next();
					
					String query3 ="select bno from bloodbank where bname=?";
					PreparedStatement ps3=connection.prepareStatement(query3);
					ps3.setString(1, jTextFieldBB.getText());
					
					
					ResultSet rs2=ps3.executeQuery();
					rs2.next();
					
					String query4 ="select no_of_units from bb_blooddetails where bno=? and bbgroup=?";
					PreparedStatement ps4=connection.prepareStatement(query4);
					ps4.setInt(1, rs2.getInt(1));
					ps4.setString(2, CB_request_bgroup.getSelectedItem().toString());
					
					
					ResultSet rs3=ps4.executeQuery();
					rs3.next();
					
					String nunits1=jTextFieldNO.getText();
					int nunits2=Integer.parseInt(nunits1);
					int nunits3=rs3.getInt(1)-(nunits2);
					
					String query5 ="update bb_blooddetails set no_of_units=? where bno=? and bbgroup=?";
					PreparedStatement ps5=connection.prepareStatement(query5);
					ps5.setInt(1, nunits3);
					ps5.setInt(2, rs2.getInt(1));
					ps5.setString(3, CB_request_bgroup.getSelectedItem().toString());
					
					int count2=ps5.executeUpdate();
					
					int emp2=Integer.parseInt(jTextFieldNO.getText());
					String query6 ="insert into bb_receipentdetails (bno,rno,bbgroup,no_of_units) values (?,?,?,?)";
					PreparedStatement ps6=connection.prepareStatement(query6);
					ps6.setInt(1, rs2.getInt(1));
					ps6.setInt(2, rs1.getInt(1));
					ps6.setString(3, CB_request_bgroup.getSelectedItem().toString());
					ps6.setInt(4,emp2);
					
					int count3=ps6.executeUpdate();
					
					String query7 ="select binventory from bloodbank where bno=? and bname=?";
					PreparedStatement ps7=connection.prepareStatement(query7);
					ps7.setInt(1, rs2.getInt(1));
					ps7.setString(2, jTextFieldBB.getText());
					
					
					ResultSet rs4=ps7.executeQuery();
					rs4.next();
					
					String binv1=jTextFieldNO.getText();
					int binv2=Integer.parseInt(binv1);
					int binv3=rs4.getInt(1)-(binv2);
					
					String query8 ="update bloodbank set binventory=? where bno=? and bname=?";
					PreparedStatement ps8=connection.prepareStatement(query8);
					ps8.setInt(1, binv3);
					ps8.setInt(2, rs2.getInt(1));
					ps8.setString(3, jTextFieldBB.getText());
					
					int count4=ps8.executeUpdate();
					
					
					
					if(count1>0&&count2>0&&count3>0&&count4>0)
					{
						JOptionPane.showMessageDialog(Request_tab,"Blood Requested Successful");
					}
					connection.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
		});
		btnRequest.setBounds(63, 173, 139, 25);
		rt_display_panel.add(btnRequest);
		btnRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblSelectedBloodBank = new JLabel("Selected Blood Bank");
		lblSelectedBloodBank.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectedBloodBank.setBounds(48, 13, 166, 16);
		rt_display_panel.add(lblSelectedBloodBank);
		
		jTextFieldPA = new JTextField();
		jTextFieldPA.setColumns(10);
		jTextFieldPA.setBounds(27, 30, 199, 22);
		rt_display_panel.add(jTextFieldPA);
		
		
		
		
		
		
		
		
		//BLOODBANK Tab
		
		
		Bloodbank_tab = new JPanel();
		tabbedPane.addTab("     BLOOD BANK     ", null, Bloodbank_tab, null);
		Bloodbank_tab.setLayout(null);
		
		JLabel lblBloodbank = new JLabel("Blood Bank");
		lblBloodbank.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBloodbank.setBounds(320, 13, 87, 24);
		Bloodbank_tab.add(lblBloodbank);
		
		JComboBox<String> CB_btab = new JComboBox<String>();
		CB_btab.addItem("");
		CB_btab.addItem("Lifeline Blood Bank");
		CB_btab.addItem("Rotary Blood Bank");
		CB_btab.addItem("SDM Blood Bank");
		CB_btab.addItem("KIMS Blood Bank");
		
		CB_btab.setBounds(419, 15, 189, 22);
		Bloodbank_tab.add(CB_btab);
		
		JScrollPane scrollPane_bb = new JScrollPane();
		scrollPane_bb.setBounds(37, 63, 939, 44);
		Bloodbank_tab.add(scrollPane_bb);
		
		bb_table = new JTable();
		scrollPane_bb.setViewportView(bb_table);
		
		JScrollPane scrollPane_bbb = new JScrollPane();
		scrollPane_bbb.setBounds(37, 170, 216, 340);
		Bloodbank_tab.add(scrollPane_bbb);
		
		bbbdetails_table = new JTable();
		scrollPane_bbb.setViewportView(bbbdetails_table);
		
		JScrollPane scrollPane_bbd = new JScrollPane();
		scrollPane_bbd.setBounds(293, 170, 324, 340);
		Bloodbank_tab.add(scrollPane_bbd);
		
		bbddetails_table = new JTable();
		scrollPane_bbd.setViewportView(bbddetails_table);
		
		JScrollPane scrollPane_bbr = new JScrollPane();
		scrollPane_bbr.setBounds(652, 170, 324, 340);
		Bloodbank_tab.add(scrollPane_bbr);
		
		bbrdetails_table = new JTable();
		scrollPane_bbr.setViewportView(bbrdetails_table);
		
		JLabel lblNewLabel_6 = new JLabel("BloodBank  Blood Details");
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblNewLabel_6.setBounds(48, 141, 194, 16);
		Bloodbank_tab.add(lblNewLabel_6);
		
		JLabel lblBloodbankDonorDetails = new JLabel("BloodBank  Donor Details");
		lblBloodbankDonorDetails.setForeground(Color.BLUE);
		lblBloodbankDonorDetails.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblBloodbankDonorDetails.setBounds(348, 141, 198, 16);
		Bloodbank_tab.add(lblBloodbankDonorDetails);
		
		JLabel lblBloodbankReceipentDetails = new JLabel("BloodBank  Receipent Details");
		lblBloodbankReceipentDetails.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblBloodbankReceipentDetails.setForeground(Color.BLUE);
		lblBloodbankReceipentDetails.setBounds(703, 140, 231, 16);
		Bloodbank_tab.add(lblBloodbankReceipentDetails);
		
		
		
		JButton btngetdetails = new JButton("Get Details");
		btngetdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					q1="select * from bloodbank where bno=?";
					q2="select b.bbgroup,b.no_of_units from bb_blooddetails b where bno=?";
					q3="select d.dname,d.dbgroup,b.date_of_donation from donor d, bb_donordetails b where b.dno=d.dno and b.bno=?";
					q4="select r.rname,r.rbgroup,b.no_of_units from receipent r,bb_receipentdetails b where b.rno=r.rno and b.bno=?";
		
				connection=DBMS_Test.getConnection();
				try {
					
					PreparedStatement ps1=connection.prepareStatement(q1);
					ps1.setInt(1,CB_btab.getSelectedIndex());
					PreparedStatement ps2=connection.prepareStatement(q2);
					ps2.setInt(1,CB_btab.getSelectedIndex());
					PreparedStatement ps3=connection.prepareStatement(q3);
					ps3.setInt(1,CB_btab.getSelectedIndex());
					PreparedStatement ps4=connection.prepareStatement(q4);
					ps4.setInt(1,CB_btab.getSelectedIndex());
					
					
					ResultSet rs1 =ps1.executeQuery();
					ResultSet rs2 =ps2.executeQuery();
					ResultSet rs3 =ps3.executeQuery();
					ResultSet rs4 =ps4.executeQuery();
					
					bb_table.setModel(DbUtils.resultSetToTableModel(rs1));
					bbbdetails_table.setModel(DbUtils.resultSetToTableModel(rs2));
					bbddetails_table.setModel(DbUtils.resultSetToTableModel(rs3));
					bbrdetails_table.setModel(DbUtils.resultSetToTableModel(rs4));
					
					
					
					connection.close();
					
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(Bloodbank_tab,e);
				}
			}
		});
		
		btngetdetails.setBounds(617, 14, 97, 25);
		Bloodbank_tab.add(btngetdetails);
		
	
		
			
		//FAQs Tab
		
		Faq_tab = new JPanel();
		tabbedPane.addTab("       FAQs       ", null, Faq_tab, null);
		Faq_tab.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1. Why should I donate Blood? ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 13, 266, 30);
		Faq_tab.add(lblNewLabel);
		
		JTextArea txtrBloodIsRequired = new JTextArea();
		txtrBloodIsRequired.setEditable(false);
		txtrBloodIsRequired.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrBloodIsRequired.setText("Blood is required everyday by hospitals. There are about 80 million units of blood that are donated each year by the\r\nvoluntary and paid donors. However there is still a shortage of blood more so in developing counties. Only 38% of the \r\ntotal blood collected is from the developing countries, where a staggering 82% of the world's population live. Several \r\nof these countries are dependent on paid donors. \r\n");
		txtrBloodIsRequired.setBounds(23, 43, 959, 86);
		Faq_tab.add(txtrBloodIsRequired);
		
		JLabel lblHowMuch = new JLabel("2. How much blood is removed during donation and how soon does it get replaced in the body? \r\n");
		lblHowMuch.setForeground(Color.RED);
		lblHowMuch.setFont(new Font("Arial", Font.BOLD, 15));
		lblHowMuch.setBounds(23, 137, 676, 30);
		Faq_tab.add(lblHowMuch);
		
		JTextArea txtrTheAmountOf = new JTextArea();
		txtrTheAmountOf.setEditable(false);
		txtrTheAmountOf.setText("The amount of blood withdrawn varies from 350ml- 450ml. It normally takes 24hrs for the blood volume to be replaced \r\nand red cells get replaced in about 6 weeks.\r\n");
		txtrTheAmountOf.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrTheAmountOf.setBounds(23, 166, 959, 43);
		Faq_tab.add(txtrTheAmountOf);
		
		JLabel lblWhatIs = new JLabel("3. What is the normal safe interval between blood donations? ");
		lblWhatIs.setForeground(Color.RED);
		lblWhatIs.setFont(new Font("Arial", Font.BOLD, 15));
		lblWhatIs.setBounds(23, 222, 442, 30);
		Faq_tab.add(lblWhatIs);
		
		JTextArea txtrYouMustWait = new JTextArea();
		txtrYouMustWait.setText("You must wait at least eight weeks (56 days) between donations of whole blood and 16 weeks (112 days) between Power Red \r\ndonations. Platelet apheresis donors may give every 7 days up to 24 times per year. Regulations are different for those \r\ngiving blood for themselves (autologous donors).");
		txtrYouMustWait.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrYouMustWait.setEditable(false);
		txtrYouMustWait.setBounds(23, 252, 959, 63);
		Faq_tab.add(txtrYouMustWait);
		
		JLabel lblHowMuch_1 = new JLabel("4. How much blood is taken?");
		lblHowMuch_1.setForeground(Color.RED);
		lblHowMuch_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblHowMuch_1.setBounds(23, 322, 221, 30);
		Faq_tab.add(lblHowMuch_1);
		
		JTextArea txtrDuringARegular = new JTextArea();
		txtrDuringARegular.setText("During a regular donation, you will give around 470 ml of whole blood. This is about eight per cent of the average \r\nadult's blood volume.");
		txtrDuringARegular.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrDuringARegular.setEditable(false);
		txtrDuringARegular.setBounds(23, 352, 959, 43);
		Faq_tab.add(txtrDuringARegular);
		
		
		
		
	}
}
