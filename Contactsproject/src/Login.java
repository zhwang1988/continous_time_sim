import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();
	private JLabel[] jlArray={new JLabel("用户名"),new JLabel("密 码"), new JLabel("")};
	private JButton[] jbArray={new JButton("登录"),new JButton("注册"),new JButton("修改密码"),new JButton("删除用户")};
	private JTextField jtf=new JTextField();
	private JPasswordField jpf=new JPasswordField();
	
	public Login(){
		jp.setLayout(null);
		for(int i=0;i<2;i++){
			jlArray[i].setBounds(30,20+i*50,80,26);
			jbArray[i].setBounds(50+i*110,120,90,26);
			//将标签与按钮添加到JPanel容器中
			jp.add(jlArray[i]);
			jp.add(jbArray[i]);
			jbArray[i].addActionListener(this);
		}
		
		for(int i=0;i<2;i++)
		{
			jbArray[i+2].setBounds(50+i*110,160,90,26);
			jp.add(jbArray[i+2]);
			jbArray[i+2].addActionListener(this);//为按钮注册动作事件监听器
		}
		
		jtf.setBounds(80,20,180,30);
		jp.add(jtf);
		jtf.addActionListener(this);
		jpf.setBounds(80,70,180,30);
		jp.add(jpf);
		jpf.setEchoChar('*');
		jpf.addActionListener(this);
		
		jlArray[2].setBounds(10,180,300,30);
		jp.add(jlArray[2]);
        
		Image icon=Toolkit.getDefaultToolkit().getImage("img/ico.gif");
		
		this.setIconImage(icon);
		this.add(jp);
		this.setTitle("登录");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setBounds(100,100,300,250);
		this.setVisible(true);
		
	}
	
	public void clear(){
		jtf.setText("");
		jpf.setText("");
		jtf.requestFocus();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String user=jtf.getText().trim();
		String pwd=String.valueOf(jpf.getPassword());
		String sql="";
		if(e.getSource()==jtf){
			jpf.requestFocus();
		}
		else if(e.getSource()==jbArray[0]||e.getSource()==jpf)
		{
			if(DBconnection.check(user,pwd))//succeess
			{
				/*MainFrame mf-new MainFrame(jtf.getText());*/
				System.out.println("登录成功");
				this.dispose();
			}
			else{
				jlArray[2].setText("对不起，非法的用户名和密码！！！");
				this.clear();
			}		
		}
		else if(e.getSource()==jbArray[1]){
			if(user.equals("")||pwd.equals("")){
				jlArray[2].setText("用户名密码不为空！！！");
				this.clear();
			}
			else{
				sql="select uid from user where uid='"+user+"'";
				if(DBconnection.isExist(sql)){
					jlArray[2].setText("对不起，用户名已经存在！！！");
					this.clear();
				}
				else{
					sql="insert into user values('"+user+"','"+pwd+"')";
					if(DBconnection.update(sql)>0){
						jlArray[2].setText("恭喜，注册成功，请登录");
					}
				}
			
			}
		}
		else if(e.getSource()==jbArray[2]){
			if(user.equals("")||pwd.equals("")){
				jlArray[2].setText("请先输入正确地用户名和密码");
				this.clear();
			}
			else if(DBconnection.check(user,pwd)){
				String password=JOptionPane.showInputDialog(this,"修改密码:","请输入新密码",
						JOptionPane.PLAIN_MESSAGE);
				if(password==null||password.equals(""))
				{
				JOptionPane.showMessageDialog(this,"密码不得为空！！！","错误",
							JOptionPane.WARNING_MESSAGE);	
				}
				else{
					sql="update user set pwd='"+password+"' where uid='"+user+"'";
					if(DBconnection.update(sql)>0){
						this.clear();
						jlArray[2].setText("恭喜密码修改成功，请用新密码登录");
					}
				}
				
			}
			else{
				JOptionPane.showMessageDialog(this,"用户名密码错误","错误",JOptionPane.WARNING_MESSAGE);
			    this.clear();
			}
			
		}
		
		else if(e.getSource()==jbArray[3]){
			if(DBconnection.check(user,pwd)){
				int yn=JOptionPane.showConfirmDialog(this, "是否删除？","delete",JOptionPane.YES_NO_OPTION);
				if(yn==JOptionPane.YES_OPTION){
					int count=DBconnection.delUser(user);
					jlArray[2].setText("用户"+user+"删除成功"+"共删除了"+count+"个联系人");
					this.clear();//清空输入文本框
				}
				else{
					jlArray[2].setText("对不起，非法的用户名和密码！！！");
					this.clear();//清空输入文本框
				}
			}
			
		}
	}
		// TODO Auto-generated method stub
		public static void main(String[] args){
			new Login();
	}

}
