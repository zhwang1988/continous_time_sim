import java.io.UnsupportedEncodingException;
import java.sql.*;

import com.mysql.jdbc.Connection;

public class GetStuInfo {
	private String host;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DataConnection DC;
	public GetStuInfo(String host){this.host=host;}
	public String[] getBaseInfo(String stu_id){
		String[] message=new String[13];
		try{
			DC.initialConnection();
			String sql="select stu_id, stu_name, stu_gender, stu_birth, nativeplace,"+
			"coll_name, dept_name, class_name, cometiome from student, dept, college,"+
			"class where stu_id='"+stu_id+"' and student.coll_id=college.coll_id"+" and "
			+ "student.dept_id=dep.dept_id and student.class_id=class.class_id";
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				message[0]=rs.getString(1);
				message[1]=new String(rs.getString(2).getBytes("ISO-8859-1"));
				message[2]=new String(rs.getString(3).getBytes("ISO-8859-1"));
				Date stu_birth=rs.getDate(4);
				message[3]=stu_birth.getYear()+1900+"";
				message[4]=stu_birth.getMonth()+1+"";
				message[5]=stu_birth.getDate()+"";
				message[6]=new String(rs.getString(5).getBytes("ISO-8859-1"));
				message[7]=new String(rs.getString(6).getBytes("ISO-8859-1"));
				message[8]=new String(rs.getString(7).getBytes("ISO-8859-1"));
				message[9]=new String(rs.getString(8).getBytes("ISO-8859-1"));
				Date cometime=rs.getDate(9);
				message[10]=cometime.getYear()+1900+"";
				message[11]=cometime.getMonth()+1+"";
				message[12]=cometime.getDate()+"";		
			}
			DC.closeConn();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return message;
		
	}
}
