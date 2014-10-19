import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class ChangePwdTeacher extends JPanel implements ActionListener{
	private String host;
	private Connection conn;
	private Statement stmt;
    private ResultSet rs;
    private JLabel[] jlArray={new JLabel("用户名"),new JLabel("原始密码"),
    						new JLabel("新密码"), new JLabel("确认新密码")};
    private JTextField jtf=new JTextField();  //用户名输入
    private JPasswordField[] jpfArray={new JPasswordField(),
    		new JPasswordField(), new JPasswordField()};
    private JButton[] jbArray={new JButton("确认"), new JButton("重置")};
	public ChangePwdTeacher (String host){
		this.host=host;
		this.initialFrame();
		this.addListener();
	}
	public void addListener(){
		jtf.addActionListener(this);
		jpfArray[0].addActionListener(this);
		jpfArray[1].addActionListener(this);
		jpfArray[2].addActionListener(this);
		jbArray[0].addActionListener(this);
		jbArray[1].addActionListener(this);
	}
	public void initialFrame(){
		this.setLayout(null);
		for(int i=0;i<jlArray.length;i++)
		{
			jlArray[i].setBounds(30,20+50*i,150,30);
			this.add(jlArray[i]);
			if(i==0)
			{
				jtf.setBounds(130,20+50*i,150,30);
				this.add(jtf);
			}
			else
			{
				jpfArray[i-1].setBounds(130,20+50*i,150,30);
				this.add(jpfArray[i-1]);
			}
			
		}
		jbArray[0].setBounds(40,230,100,30);
		this.add(jbArray[0]);
		jbArray[1].setBounds(170,230,100,30);
		this.add(jbArray[1]);
	}
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jpfArray[0])
		{jpfArray[0].requestFocus(true);}
		if (e.getSource()==jpfArray[1])
		{jpfArray[1].requestFocus(true);}
		if (e.getSource()==jpfArray[2])
		{jpfArray[2].requestFocus(true);}
		else if(e.getSource()==jbArray[1]){
			for(int i=1; i<jpfArray.length;i++){
				jpfArray[i].setText("");
			}
		}
		else if(e.getSource()==jbArray[0]){
			String patternStr="[0-9a-zA-z]{6,12}";
			String user_name=jtf.getText().trim();
			if(user_name.equals("")){
				JOptionPane.showMessageDialog(this, "请输入用户名","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String oldPwd=jpfArray[0].getText().trim();
			if(oldPwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入原始密码","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String newPwd=jpfArray[1].getText().trim();
			if(newPwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入新密码","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!newPwd.matches(patternStr)){
				JOptionPane.showMessageDialog(this, "密码6-12位字母数字","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String newPwd1=jpfArray[2].getText().trim();
			if(!newPwd.equals(newPwd1))
			{
				JOptionPane.showMessageDialog(this,"密码不一致",
				                       "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try
			{
				this.initialConnection();
				String sql="update user_teacher set pwd='"+newPwd+"'"
				+" where uid='"+user_name+"'"+" and pwd='"+oldPwd+"'";
				int i=stmt.executeUpdate(sql);
				if (i==0){
					JOptionPane.showMessageDialog(this,"检查密码","错误",JOptionPane.ERROR_MESSAGE);
					
				}
				if (i==1){
					JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				this.closeConn();
			}catch(Exception ea){
				ea.printStackTrace();
			}
		}
		
	}
    public void setFocus(){
    	jtf.requestFocus(true);
    }

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

	public static void main(String args[]){
		ChangePwdTeacher 
		cpt= new ChangePwdTeacher("127.0.0.1:3306");
		JFrame jframe=new JFrame();
		jframe.add(cpt);
		jframe.setBounds(70,20,700,650);
		jframe.setVisible(true);
	}
	
}
