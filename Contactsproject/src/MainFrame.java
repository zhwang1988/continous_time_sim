import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MainFrame extends JFrame implements ActionListener, ItemListener{
    private String uname=null;
    private String perNameBefor=null;
    private String perGroupBefor=null;
    private boolean searchByName=true;
    private boolean isInsert=false;
    Image image=Toolkit.getDefaultToolkit().getImage("img/txl.jpg");
    Icon icon=new ImageIcon(image);
    private JPanel jps=new JPanel();
    
    private JButton jba=new JButton("添加");
	private JButton jbs=new JButton("查找");
	private JTextField jtfs=new JTextField();//按给出信息查找联系人信息
	//选择查找方式的单选按钮
	private JRadioButton jrbxm=new JRadioButton("按姓名查找",true);
	private JRadioButton jrbbh=new JRadioButton("按编号查找");
	private ButtonGroup bg=new ButtonGroup();//单选按钮组	
	
	private JPanel jpbr=new JPanel();//单选按钮面板	
    
	//界面左下的树 创建树模型 指定节点"联系人"为根节点
		DefaultMutableTreeNode root=
							new DefaultMutableTreeNode(new NodeValue("联系人",0));
		DefaultTreeModel dtm=new DefaultTreeModel(root);
		private JTree jtz=new JTree();//界面下半部分左边的JTree  		
		private JScrollPane jspz=new JScrollPane(jtz);//JTree的滚动条	
		private DefaultTreeCellRenderer dtcr=new DefaultTreeCellRenderer();//树节点的绘制器		
		private JPanel jpy=new JPanel();//界面下半部分右边界面，布局管理器为卡片布局	
		private JPanel jpyInfo=new JPanel();//右侧显示个人信息的面板	
		
		//界面下半部分右边的JPanel容器的个人信息栏目里的控件	
		private JLabel[] jlInfo={new JLabel("用户编号:"),new JLabel("姓名:"),
								 new JLabel("性别:"),new JLabel("年龄:"),
								 new JLabel("电话号码:"),new JLabel("Email:"),
								 new JLabel("所属组:"),new JLabel("更改照片:"),
								 new JLabel("邮编:"),new JLabel("地址:"),
								 new JLabel("添加相片")};
		private JButton[] jbInfo={new JButton("编辑"),new JButton("保存"),
								  new JButton("删除"),new JButton("浏览"),
								  new JButton("添加分组"),new JButton("删除分组"),
								  new JButton("浏览"),new JButton("上传"),
								  new JButton("删除")};
		//初始默认的一些分组
		private String[] str={"朋友","同事","家庭","重要人士","其他"};
		private JComboBox jcb=new JComboBox(str);//分组下拉列表控件
		private JLabel jlPhoto=new JLabel();//显示图像的JLabel控件
		private JTextField[] jtfInfo=new JTextField[10];	
		private JTextField jtfPhoto=new JTextField();//添加照片到相册的路径	
		private JFileChooser jfcPic=new JFileChooser("f:\\");//上传图像的文件选择器	
		private JFileChooser jfcPho=new JFileChooser("f:\\");//上传照片的文件选择器		
		//性别部分
		private JRadioButton jrbMale=new JRadioButton("男",true);
		private JRadioButton jrbFemale=new JRadioButton("女");
		private ButtonGroup bgGender=new ButtonGroup();	
		private JPanel jpGender=new JPanel();//单选按钮面板	
		private JPanel jpyview=new JPanel();//右侧显示多幅照片的面板	
		private JScrollPane jspyview=new JScrollPane(jpyview);//滚动条	
		private JLabel jlDetail=new JLabel();//右侧显示一幅图片的标签	
		private JScrollPane jspydetail=new JScrollPane(jlDetail);//显示一幅图片标签的滚动条
		private JLabel jlNoPic=new JLabel("没有照片");//没有照片的显示JLabel	
		//图片加载进度条部分
		private JLabel jpProgress=new JLabel();//右侧显示图片加载进度的面板
		private JLabel jlProgress=new JLabel("预览图片加载中.....");
		private JProgressBar jpb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		//选中不同树节点时的提示信息部分
		private JLabel jlRoot=new JLabel(icon,JLabel.LEFT);
		private JLabel jlGroup=new JLabel();//分组节点的JLabel
		private CardLayout cl=new CardLayout();//创建卡片布局管理器
		private JLabel[] jla=null;//照片缓冲数组	
		private JSplitPane jspOuter=//上下分割的JSplitPane
							new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);	
		private JSplitPane jspInner=//下面右的JSplitPane
							new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jpy);
		//系统托盘部分						
		private PopupMenu popup=new PopupMenu();	
		private SystemTray tray;//定义SystemTray成员变量	
		private TrayIcon trayIcon;//定义TrayIcon成员变量
		private MenuItem exit=new MenuItem("退出程序");//定义菜单
		private MenuItem currently=new MenuItem("显示当前用户");//定义菜单
		
		public MainFrame(String uname){
			this.uname=uname;
			this.initJps();
			this.initInfo();
			this.initJpy();
			this.initTray();
			this.initTree();
			jtz.addTreeSelectionListener(new TreeSelectionListener(){

				@Override
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode cdmtn=(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
					NodeValue cnv=(NodeValue)cdmtn.getUserObject();
					if(cnv.classCode==0){
						cl.show(jpy,"root");
					}
					else if(cnv.classCode==1){
						String group=cnv.toString();
						jlGroup.setText(group);
						cl.show(jpy, "group");
					}
					else if(cnv.classCode==2){
						String sql="select pid,pname,pgender,page,pnumber,pemail,pgroup,ppostalcode,"+
					"paddress from lxy wher uid='"+MainFrame.this.uname+"'and pname='"+cnv.toString()+",";
						
						
					}
					
					
				}
				
			
			});
		}
		
		
	
	private void initTree() {
			// TODO Auto-generated method stub
			
		}



	private void initTray() {
			// TODO Auto-generated method stub
			
		}



	private void initJpy() {
			// TODO Auto-generated method stub
			
		}



	private void initInfo() {
			// TODO Auto-generated method stub
			
		}



	private void initJps() {
			// TODO Auto-generated method stub
			
		}



	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class NodeValue{

	public NodeValue(String string, int i) {
		// TODO Auto-generated constructor stub
	}

	protected int classCode;
	
}
