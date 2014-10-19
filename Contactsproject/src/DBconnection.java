import java.sql.*;
import java.util.*;

public class DBconnection {
  private static String host=null;
  private static Connection conn=null;
  private static Statement stmt=null;
  private static ResultSet rs=null;
  private static PreparedStatement psInsert=null;
  private static String driver="org.gjt.mm.mysql.Driver";
  
  public static Connection getConnection(){
	  try{
		  Class.forName(driver);
		  conn=DriverManager.getConnection( "jdbc:mysql://"+host+"/Contacts","root","");
	  }catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return conn;
  }
  
  public static void closeConn() {
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
  
  public static boolean check(String user,String pwd){
	  boolean flag=false;
	  try{
		  conn=DBconnection.getConnection();
		  stmt=conn.createStatement();
		  rs=stmt.executeQuery("select pwd from user where uid='"+user+"'");
		  
		  rs.next();
		  String spwd=rs.getString(1);
		  if(spwd.equals(pwd)){
			  flag=true;
		  }
	  }
	  catch(Exception e){flag=false;}
	  finally{DBconnection.closeConn();}
      return flag;
  }
  
  public static int update(String sql){
	  int count=0;
	  try{
		  conn=DBconnection.getConnection();
		  stmt=conn.createStatement();
		  count=stmt.executeUpdate(sql);
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  count=-1;
	  }
	  finally{DBconnection.closeConn();}
	  return count;
  }
  
  public static boolean isExist(String sql){
	  boolean flag=false;
	  try{
		  conn=DBconnection.getConnection();
		  stmt=conn.createStatement();
		  rs=stmt.executeQuery(sql);
		  if(rs.next()){flag=true;}
	  }catch(Exception e){
		  e.printStackTrace();
		  flag=false;
	  }
	  finally{DBconnection.closeConn();}
	  return flag;
  }
  
  public static int delUser(String uid){
	  int count=0;
	  Vector<String> vpid=new Vector<String>();
	  try{
		  conn=DBconnection.conn;
		  stmt=conn.createStatement();
		  rs=stmt.executeQuery("select pid from lxy where uid='"+uid+"'");
		  
		  while(rs.next()){
			  String pid=rs.getString(1);
			  vpid.add(pid);
		  }
		  
		  stmt=conn.createStatement();
		  for(String s:vpid){
			  stmt.executeUpdate("delete from photo where pid='"+s+"'");
		  }
		  count=stmt.executeUpdate("delete from lxy where uid='"+uid+"'");
		  stmt.executeUpdate("delete from user where uid='"+uid+"'");
	  }
	  catch(Exception e){e.printStackTrace();}
	  finally{DBconnection.closeConn();}
	  return count;
	  
  }
  
  
  
}
