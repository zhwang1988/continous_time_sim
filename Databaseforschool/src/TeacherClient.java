import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import javax.swing.tree.*;


public class TeacherClient extends JFrame {
	private String host;
	String coll_id;
	private DefaultMutableTreeNode dmtnRoot=
			new DefaultMutableTreeNode(new MyNode ("操作项目","0"));
	private DefaultMutableTreeNode dmtn1=
			new DefaultMutableTreeNode(new MyNode ("系统选项","1"));
	private DefaultMutableTreeNode dmtn2=
			new DefaultMutableTreeNode(new MyNode ("学生信息管理","2"));
	private DefaultMutableTreeNode dmtn3=
			new DefaultMutableTreeNode(new MyNode ("课程管理","3"));
	private DefaultMutableTreeNode dmtn4=
			new DefaultMutableTreeNode(new MyNode ("班级设置","4"));
	private DefaultMutableTreeNode dmtn11=
			new DefaultMutableTreeNode(new MyNode ("退出","11"));
	private DefaultMutableTreeNode dmtn13=
			new DefaultMutableTreeNode(new MyNode ("密码修改","13"));
	private DefaultMutableTreeNode dmtn21=
			new DefaultMutableTreeNode(new MyNode ("新生报到","21"));
	private DefaultMutableTreeNode dmtn22=
			new DefaultMutableTreeNode(new MyNode ("学生信息查询","22"));
	private DefaultMutableTreeNode dmtn221=
			new DefaultMutableTreeNode(new MyNode ("基本信息查询","221"));	
	private DefaultMutableTreeNode dmtn222=
			new DefaultMutableTreeNode(new MyNode ("成绩查询","222"));	
	private DefaultMutableTreeNode dmtn31=
			new DefaultMutableTreeNode(new MyNode ("开课选项查询","31"));
	private DefaultMutableTreeNode dmtn32=
			new DefaultMutableTreeNode(new MyNode ("课程成绩录入","32"));
	private DefaultMutableTreeNode dmtn34=
			new DefaultMutableTreeNode(new MyNode ("添加课程","34"));
	private DefaultMutableTreeNode dmtn42=
			new DefaultMutableTreeNode(new MyNode ("增加班级","42"));
	private DefaultTreeModel dtm=new DefaultTreeModel(dmtnRoot);
	private JTree jt=new JTree(dtm);  //创建树状列表控件
	private JScrollPane jspz=new JScrollPane(jt);  //创建滚动窗口
	private JPanel jpy=new JPanel(); //创建面板
	private JSplitPane jspl=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jpy);//分割窗口
	private ChangePwdTeacher changepwdteacher;
	private NewStu newstu;
	CardLayout cl;	//卡片布局
	public TeacherClient(String coll_id, String host){
		this.host=host;
		this.coll_id=coll_id;
		this.initialTree();
		this.initialPanel();
		this.addListener();
		this.initialJpy();
		this.initialFrame();
	}


public void initialPanel(){
    changepwdteacher=new ChangePwdTeacher(host);
    newstu=new NewStu(coll_id,host);
}

public void initialTree(){
	dmtnRoot.add(dmtn1);dmtnRoot.add(dmtn2);dmtnRoot.add(dmtn3);
	dmtnRoot.add(dmtn4);dmtn1.add(dmtn11);dmtn1.add(dmtn13);
	dmtn2.add(dmtn21);dmtn2.add(dmtn22);dmtn22.add(dmtn221);
	dmtn22.add(dmtn222);dmtn3.add(dmtn31);dmtn3.add(dmtn32);
	dmtn3.add(dmtn34);dmtn4.add(dmtn42);
	
}
public void initialJpy(){
	jpy.setLayout(new CardLayout());//面板设置为卡片面板
	cl=(CardLayout)jpy.getLayout(); //卡片布局的引用
	jpy.add(changepwdteacher,"changepwdteacher");
	jpy.add(newstu,"newstu");
}
public void initialFrame(){
	this.add(jspl);
	jspl.setDividerLocation(200);//窗体位置
	jspl.setDividerSize(4); //窗体宽度
    Image image= new ImageIcon("").getImage();
    this.setIconImage(image);this.setTitle("教师客户端");
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    int centerX=screenSize.width/2;
	int centerY=screenSize.height/2;
	int w=900;
	int h=650;
	this.setBounds(centerX-w/2,centerY-h/2-30,w,h);
	this.setVisible(true);
	//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
}

public void addListener(){
	jt.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					DefaultMutableTreeNode dmtntemp=   //得到当前节点对象
							(DefaultMutableTreeNode)jt.getLastSelectedPathComponent();
					MyNode mynode=(MyNode)dmtntemp.getUserObject(); //该自定义节点对象
					String id=mynode.getId();   //显示不同卡片
					if(id.equals("0")){/*欢迎页面*/}
					else if(id.equals("11")){
						int i=JOptionPane.showConfirmDialog(jpy, "您确认要退出系统吗？",
								"询问",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(i==0){System.exit(0);}}
					else if (id.equals("13")){
						//更改密码页面
					cl.show(jpy, "changepwdteacher");
					changepwdteacher.setFocus();
					}
					else if (id.equals("21")){/*添加学生页面*/}
					else if (id.equals("221")){/*学生信息查询页面*/}
					else if (id.equals("222")){/*成绩查询页面*/}
					else if (id.equals("31")){/*选课管理页面*/}
					else if (id.equals("32")){/*成绩录入页面*/}
					else if (id.equals("34")){/*添加课程页面*/}
					else if (id.equals("42")){/*添加班级页面*/}
					}
			});
	 			jt.setToggleClickCount(1);
}

class MyNode{
	private String values;
	private String id;
	public MyNode(String values,String id){
		this.values=values;
		this.id=id;
	}
	public String toString(){return this.values;}
	public String getId(){return this.values;}
}


public static void main(String args[]){
	new TeacherClient("01","127.0.0.1:3306");
}
}