import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;



public class DBMS_Test 
{
	static Connection conn=null;
    public static Connection getConnection()
	{
    	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","");
            //JOptionPane.showMessageDialog(null,"Connection Successful");
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		return conn;
		
	}
    
    public static ArrayList<BloodBank> fetchBloodBank(String group,int units)
    {    
    	ArrayList<BloodBank> bblist=new ArrayList<BloodBank>(); 
		try {
			 BloodBank bb=null;
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","");
			 String query = "select b.bname,b.bcity,b.baddress,b.bphone,bb.no_of_units from bloodbank b,bb_blooddetails bb where b.bno=bb.bno and bb.bbgroup=? and bb.no_of_units>=1";
			 PreparedStatement ps=conn.prepareStatement(query);
			 ps.setString(1, group);
			 //ps.setInt(2, units);
			 ResultSet rs=ps.executeQuery();
			 
			 while(rs.next())
			 {
				 bb=new BloodBank(rs.getString("bname"),rs.getString("bcity"),rs.getString("baddress"),rs.getString("bphone"),rs.getInt("no_of_units"));
				 bblist.add(bb);
			 }
			 conn.close();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return bblist;
    }



}
