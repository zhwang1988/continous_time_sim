import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class DataConnection {
private String host;
private Connection conn;
private Statement stmt;
private ResultSet rs;

public void initialConnection() {
		try{
		Class.forName("org.gjt.mm.mysql.Driver");
		conn=DriverManager.getConnection( "jdbc:mysql://"+host+"/test","root","");
		stmt=conn.createStatement();
	}
		catch(SQLException e){
		e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
}
public void closeConn() {
 try{
	 if (rs!=null){
		 rs.close();
	 }
	 if(stmt!=null){
		 stmt.close();
	 }
	 if(conn!=null){
		 conn.close();
	 }
 }catch(SQLException e){
	 e.printStackTrace(); 
 }
}	
	
	
}
