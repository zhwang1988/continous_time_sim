import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();
	private JLabel[] jlArray={new JLabel("�û���"),new JLabel("�� ��"), new JLabel("")};
	private JButton[] jbArray={new JButton("��¼"),new JButton("ע��"),new JButton("�޸�����"),new JButton("ɾ���û�")};
	private JTextField jtf=new JTextField();
	private JPasswordField jpf=new JPasswordField();
	
	public Login(){
		jp.setLayout(null);
		for(int i=0;i<2;i++){
			jlArray[i].setBounds(30,20+i*50,80,26);
			jbArray[i].setBounds(50+i*110,120,90,26);
			//����ǩ�밴ť��ӵ�JPanel������
			jp.add(jlArray[i]);
			jp.add(jbArray[i]);
			jbArray[i].addActionListener(this);
		}
		
		for(int i=0;i<2;i++)
		{
			jbArray[i+2].setBounds(50+i*110,160,90,26);
			jp.add(jbArray[i+2]);
			jbArray[i+2].addActionListener(this);//Ϊ��ťע�ᶯ���¼�������
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
		this.setTitle("��¼");
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
				System.out.println("��¼�ɹ�");
				this.dispose();
			}
			else{
				jlArray[2].setText("�Բ��𣬷Ƿ����û��������룡����");
				this.clear();
			}		
		}
		else if(e.getSource()==jbArray[1]){
			if(user.equals("")||pwd.equals("")){
				jlArray[2].setText("�û������벻Ϊ�գ�����");
				this.clear();
			}
			else{
				sql="select uid from user where uid='"+user+"'";
				if(DBconnection.isExist(sql)){
					jlArray[2].setText("�Բ����û����Ѿ����ڣ�����");
					this.clear();
				}
				else{
					sql="insert into user values('"+user+"','"+pwd+"')";
					if(DBconnection.update(sql)>0){
						jlArray[2].setText("��ϲ��ע��ɹ������¼");
					}
				}
			
			}
		}
		else if(e.getSource()==jbArray[2]){
			if(user.equals("")||pwd.equals("")){
				jlArray[2].setText("����������ȷ���û���������");
				this.clear();
			}
			else if(DBconnection.check(user,pwd)){
				String password=JOptionPane.showInputDialog(this,"�޸�����:","������������",
						JOptionPane.PLAIN_MESSAGE);
				if(password==null||password.equals(""))
				{
				JOptionPane.showMessageDialog(this,"���벻��Ϊ�գ�����","����",
							JOptionPane.WARNING_MESSAGE);	
				}
				else{
					sql="update user set pwd='"+password+"' where uid='"+user+"'";
					if(DBconnection.update(sql)>0){
						this.clear();
						jlArray[2].setText("��ϲ�����޸ĳɹ��������������¼");
					}
				}
				
			}
			else{
				JOptionPane.showMessageDialog(this,"�û����������","����",JOptionPane.WARNING_MESSAGE);
			    this.clear();
			}
			
		}
		
		else if(e.getSource()==jbArray[3]){
			if(DBconnection.check(user,pwd)){
				int yn=JOptionPane.showConfirmDialog(this, "�Ƿ�ɾ����","delete",JOptionPane.YES_NO_OPTION);
				if(yn==JOptionPane.YES_OPTION){
					int count=DBconnection.delUser(user);
					jlArray[2].setText("�û�"+user+"ɾ���ɹ�"+"��ɾ����"+count+"����ϵ��");
					this.clear();//��������ı���
				}
				else{
					jlArray[2].setText("�Բ��𣬷Ƿ����û��������룡����");
					this.clear();//��������ı���
				}
			}
			
		}
	}
		// TODO Auto-generated method stub
		public static void main(String[] args){
			new Login();
	}

}
