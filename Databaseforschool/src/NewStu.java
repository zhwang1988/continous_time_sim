import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import javax.swing.tree.*;
import java.sql.*;
import java.util.Date;



public class NewStu extends JPanel implements ActionListener{
    private String host;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String coll_id;
    private Map<String, String> map_dept=new HashMap<String,String>();
	private Map<String, Map> map_class=new HashMap<String, Map>();
	private DataConnection DC;
	static int year=new Date().getYear()+1900;
	private JLabel[] jlArray={new JLabel("学 号"), new JLabel("姓 名"), new JLabel("性 别"),
			new JLabel("出生日期"), new JLabel("籍 贯"), new JLabel("学 院"),
			new JLabel("专 业"), new JLabel("班 级"), new JLabel("入学时间"),new JLabel("年"),
			new JLabel("月"), new JLabel("日"), new JLabel("年"),new JLabel("月"), new JLabel("日")
	};
	private JTextField[] jtfArray={new JTextField(), new JTextField(), new JTextField()};
	String[] str_gender={"men","women"};
	static String[] str_year1=new String[20];
	static {
		for (int i=15; i<35; i++)
		{
			str_year1[i-15]=year-i+"";
		}
	}
	String [] str_year={year+"",year-1+"",year-2+"",year-3+"",year-4+"",year-5+""};
	static String[] str_month=new String[12];
	static{
		for(int i=1;i<=12;i++)
		{
			str_month[i-1]=i+"";
		}
	}
	static String[] str_day=new String[31];
	static{
		for(int i=1;i<=31;i++)
		{
			str_day[i-1]=i+"";
		}	
	}
	private JComboBox[] jcb={new JComboBox(str_gender), new JComboBox(str_year1),
			new JComboBox(str_month), new JComboBox(str_day), new JComboBox(), new JComboBox(),
            new JComboBox(),new JComboBox(str_year),
            new JComboBox(str_month),new JComboBox(str_day)
	};
	
	JButton[] jbArray={
			new JButton("提交"),new JButton("重置")
	};
	
	public NewStu(String coll_id, String host){
		this.host=host;
		this.coll_id=coll_id;
		this.initialData();
		this.initialFrame();
		this.addListener();
		
	}
	
	private void initialData() {
		// TODO Auto-generated method stub
		try{
			DC.initialConnection();
			String sql1="select coll_name from college where coll_id='"+coll_id+"'";
			rs=stmt.executeQuery(sql1);
			if(rs.next()){
				String coll_name=new String(rs.getString(1).getBytes("gb2312"));
				jcb[4].addItem(coll_name);
			}
			rs.close();
			String sql2="select dept_name, dept_id from dept where coll_id='"+coll_id+"'";
			rs=stmt.executeQuery(sql2);
			while(rs.next()){
				String dept_name=new String(rs.getString(1).getBytes("ISO-8859-1"));
				String dept_id=rs.getString(2);
				map_dept.put(dept_name, dept_id);
			}
			rs.close();
			Set keyset=map_dept.keySet();
			Iterator ii=keyset.iterator();
			int i=0;
			String initial_dept_name=null;
			while(ii.hasNext()){
				String dept_name=(String)ii.next();
				if(i==0){
					initial_dept_name=dept_name;
				}
				jcb[5].addItem(dept_name);
			    String dept_id=map_dept.get(dept_name);
			    String sql3="select class_id, class_name from class where dept_id='"+dept_id+"'";
			    rs=stmt.executeQuery(sql3);
			    Map class_map=new HashMap();
			    while(rs.next()){
			    	String class_id=rs.getString(1);
			    	String class_name=new String(rs.getString(2).getBytes("gb2312"));
			    	class_map.put(class_name, class_id); 	
			    }
			    rs.close();
			    map_class.put(dept_id,class_map);
			    i++;
			}
			DC.closeConn();
			jcb[5].setSelectedItem(initial_dept_name);
			String initial_dept_id=map_dept.get(initial_dept_name);
			Map classmap=(HashMap)map_class.get(initial_dept_id);
			Set keyset1=classmap.keySet();
			Iterator ii1=keyset1.iterator();
			while(ii1.hasNext()){
				String s=(String)ii1.next();
				jcb[6].addItem(s);
			}
		}catch(Exception e){e.printStackTrace();
		}
		
	}

	private void initialFrame() {
		this.setLayout(null);
		jlArray[0].setBounds(30,50,100,30);
		this.add(jlArray[0]);
		jlArray[1].setBounds(30,100,100,30);
		this.add(jlArray[1]);
		jlArray[2].setBounds(30,150,100,30);
		this.add(jlArray[2]);
		jlArray[3].setBounds(30,200,100,30);
		this.add(jlArray[3]);
		jlArray[4].setBounds(30,250,100,30);
		this.add(jlArray[4]);
		jlArray[5].setBounds(30,300,100,30);
		this.add(jlArray[5]);
		jlArray[6].setBounds(30,350,100,30);
		this.add(jlArray[6]);
		jlArray[7].setBounds(30,400,100,30);
		this.add(jlArray[7]);
		jlArray[8].setBounds(30,450,100,30);
		this.add(jlArray[8]);
		
		jtfArray[0].setBounds(130,50,150,30);
		this.add(jtfArray[0]);
		jtfArray[1].setBounds(130,100,150,30);
		this.add(jtfArray[1]);
		jtfArray[2].setBounds(130,250,500,30);
		this.add(jtfArray[2]);
		jcb[0].setBounds(130,150,60,30);
		this.add(jcb[0]);
		jcb[1].setBounds(130,200,55,30);
		this.add(jcb[1]);
		jlArray[9].setBounds(185,200,20,30);
		this.add(jlArray[9]);
		jcb[2].setBounds(205,200,45,30);
		this.add(jcb[2]);
		jlArray[10].setBounds(250,200,20,30);
		this.add(jlArray[10]);
		jcb[3].setBounds(270,200,45,30);
		this.add(jcb[3]);
		jlArray[11].setBounds(315,200,20,30);
		this.add(jlArray[11]);
		jcb[4].setBounds(130,300,200,30);
		this.add(jcb[4]);
		jcb[5].setBounds(130,350,200,30);
		this.add(jcb[5]);
		jcb[6].setBounds(130,400,100,30);
		this.add(jcb[6]);
		jcb[7].setBounds(130,450,55,30);
		this.add(jcb[7]);
		jlArray[12].setBounds(185,450,20,30);
		this.add(jlArray[12]);
		jcb[8].setBounds(205,450,45,30);
		this.add(jcb[8]);
		jlArray[13].setBounds(250,450,20,30);
		this.add(jlArray[13]);
		jcb[9].setBounds(270,450,45,30);
		this.add(jcb[9]);
		jlArray[14].setBounds(315,450,20,30);
		this.add(jlArray[14]);
		jbArray[0].setBounds(150,510,80,30);
		this.add(jbArray[0]);
		jbArray[1].setBounds(280,510,80,30);
		this.add(jbArray[1]);	
	}

	private void addListener() {
		jtfArray[0].addActionListener(this);
		jtfArray[1].addActionListener(this);
		jbArray[0].addActionListener(this);
		jbArray[1].addActionListener(this);
		jcb[5].addActionListener(this);		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jcb[5]){
			String deptname=(String) jcb[5].getSelectedItem();
			String deptid=map_dept.get(deptname);
			Map classmap=(HashMap)map_class.get(deptid);
			Set keyset=classmap.keySet();
			Iterator ii=keyset.iterator();
			jcb[6].removeAllItems();
			while(ii.hasNext()){
				String s=(String)ii.next();
				jcb[6].addItem(s);
			}
		}
		else if(e.getSource()==this.jbArray[0]){
			this.submitStu();}
		else if(e.getSource()==this.jbArray[1]){
			for(int i=0;i<jtfArray.length;i++){
				jtfArray[i].setText("");
			}
		}
		else if(e.getSource()==jtfArray[0]){
			jtfArray[1].requestFocus(true);
		}
		else if(e.getSource()==jtfArray[1]){
			jtfArray[0].requestFocus(true);
		}
	}
	public void submitStu(){
		String stu_id=jtfArray[0].getText().trim();
		String patternStr="[0-9]{12}";
		if(stu_id.equals("")){
			JOptionPane.showMessageDialog(this,"please put the number","wrong",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!stu_id.matches(patternStr)){
			JOptionPane.showMessageDialog(this,"12 numbers required","wrong",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String stu_name=jtfArray[1].getText().trim();
		if(stu_name.equals("")){
			JOptionPane.showMessageDialog(this,"please input your name","wrong",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(stu_name.length()>10){
			JOptionPane.showMessageDialog(this,"请输入姓名长度过长,请检查是否正确","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String stu_gender=((String)jcb[0].getSelectedItem()).trim();
		String bir_year=((String)jcb[1].getSelectedItem()).trim();
		String bir_month=((String)jcb[2].getSelectedItem()).trim();
		String bir_day=((String)jcb[3].getSelectedItem()).trim();
		String stu_birth=bir_year+"-"+bir_month+"-"+bir_day;
		String nativeplace=jtfArray[2].getText().trim();
		if(nativeplace.equals("")){
			JOptionPane.showMessageDialog(this, "籍贯","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(nativeplace.length()>30){
			JOptionPane.showMessageDialog(this, "籍贯过长","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String coll_id=this.coll_id;
		String dept_id=map_dept.get((String)jcb[5].getSelectedItem());
		String class_id=(String)(((HashMap)map_class.get(dept_id)).get(jcb[6].getSelectedItem()));
		String come_year=(String)jcb[7].getSelectedItem();
		String come_month=(String)jcb[8].getSelectedItem();
		String come_day=(String)jcb[9].getSelectedItem();
		String cometime=come_year+"-"+come_month+"-"+come_day;
	    DC.initialConnection();
	    try{
	    	        String sql="insert into student values('"+stu_id+"',"+
					"'"+new String(stu_name.getBytes(),"ISO-8859-1")+"',"+
					"'"+new String(stu_gender.getBytes(),"ISO-8859-1")+"',"+
					"'"+stu_birth+"',"+
					"'"+new String(nativeplace.getBytes(),"ISO-8859-1")+"',"+
					"'"+coll_id+"','"+dept_id+"','"+class_id+"',"+
		            "'"+cometime+"')";
	    	        conn.setAutoCommit(false);
		        	int i=stmt.executeUpdate(sql);
		        	String sql1="insert into user_stu values("+
		        	                "'"+stu_id+"','"+stu_id+"')";
		        	int j=stmt.executeUpdate(sql1);
		        	if(i==1&&j==1)
		            {
		            	conn.commit();
		            	conn.setAutoCommit(true);
		            	JOptionPane.showMessageDialog(this,"OK","hint",
		            	                    JOptionPane.INFORMATION_MESSAGE);
		            }
		            else
		            {
		            	conn.rollback();
		            	conn.setAutoCommit(true);
		            	JOptionPane.showMessageDialog(this,"failed","error",
		            	                         JOptionPane.ERROR_MESSAGE);
		            }
	    }catch(SQLException ea){
	    	ea.printStackTrace();
	    }
	    catch(UnsupportedEncodingException eb){
	    	eb.printStackTrace();
	    }
	    finally{DC.closeConn();}
	}
	public void setFocus()
	{
		this.jtfArray[0].requestFocus(true);
	}
	public static void main(String args[])
	{
		NewStu ns=new NewStu("01","127.0.0.1:3306");
		JFrame jframe=new JFrame();
		jframe.add(ns);
		jframe.setBounds(70,20,700,650);
		jframe.setVisible(true);
	}
}
