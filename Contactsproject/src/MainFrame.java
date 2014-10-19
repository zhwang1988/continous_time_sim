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
    
    private JButton jba=new JButton("���");
	private JButton jbs=new JButton("����");
	private JTextField jtfs=new JTextField();//��������Ϣ������ϵ����Ϣ
	//ѡ����ҷ�ʽ�ĵ�ѡ��ť
	private JRadioButton jrbxm=new JRadioButton("����������",true);
	private JRadioButton jrbbh=new JRadioButton("����Ų���");
	private ButtonGroup bg=new ButtonGroup();//��ѡ��ť��	
	
	private JPanel jpbr=new JPanel();//��ѡ��ť���	
    
	//�������µ��� ������ģ�� ָ���ڵ�"��ϵ��"Ϊ���ڵ�
		DefaultMutableTreeNode root=
							new DefaultMutableTreeNode(new NodeValue("��ϵ��",0));
		DefaultTreeModel dtm=new DefaultTreeModel(root);
		private JTree jtz=new JTree();//�����°벿����ߵ�JTree  		
		private JScrollPane jspz=new JScrollPane(jtz);//JTree�Ĺ�����	
		private DefaultTreeCellRenderer dtcr=new DefaultTreeCellRenderer();//���ڵ�Ļ�����		
		private JPanel jpy=new JPanel();//�����°벿���ұ߽��棬���ֹ�����Ϊ��Ƭ����	
		private JPanel jpyInfo=new JPanel();//�Ҳ���ʾ������Ϣ�����	
		
		//�����°벿���ұߵ�JPanel�����ĸ�����Ϣ��Ŀ��Ŀؼ�	
		private JLabel[] jlInfo={new JLabel("�û����:"),new JLabel("����:"),
								 new JLabel("�Ա�:"),new JLabel("����:"),
								 new JLabel("�绰����:"),new JLabel("Email:"),
								 new JLabel("������:"),new JLabel("������Ƭ:"),
								 new JLabel("�ʱ�:"),new JLabel("��ַ:"),
								 new JLabel("�����Ƭ")};
		private JButton[] jbInfo={new JButton("�༭"),new JButton("����"),
								  new JButton("ɾ��"),new JButton("���"),
								  new JButton("��ӷ���"),new JButton("ɾ������"),
								  new JButton("���"),new JButton("�ϴ�"),
								  new JButton("ɾ��")};
		//��ʼĬ�ϵ�һЩ����
		private String[] str={"����","ͬ��","��ͥ","��Ҫ��ʿ","����"};
		private JComboBox jcb=new JComboBox(str);//���������б�ؼ�
		private JLabel jlPhoto=new JLabel();//��ʾͼ���JLabel�ؼ�
		private JTextField[] jtfInfo=new JTextField[10];	
		private JTextField jtfPhoto=new JTextField();//�����Ƭ������·��	
		private JFileChooser jfcPic=new JFileChooser("f:\\");//�ϴ�ͼ����ļ�ѡ����	
		private JFileChooser jfcPho=new JFileChooser("f:\\");//�ϴ���Ƭ���ļ�ѡ����		
		//�Ա𲿷�
		private JRadioButton jrbMale=new JRadioButton("��",true);
		private JRadioButton jrbFemale=new JRadioButton("Ů");
		private ButtonGroup bgGender=new ButtonGroup();	
		private JPanel jpGender=new JPanel();//��ѡ��ť���	
		private JPanel jpyview=new JPanel();//�Ҳ���ʾ�����Ƭ�����	
		private JScrollPane jspyview=new JScrollPane(jpyview);//������	
		private JLabel jlDetail=new JLabel();//�Ҳ���ʾһ��ͼƬ�ı�ǩ	
		private JScrollPane jspydetail=new JScrollPane(jlDetail);//��ʾһ��ͼƬ��ǩ�Ĺ�����
		private JLabel jlNoPic=new JLabel("û����Ƭ");//û����Ƭ����ʾJLabel	
		//ͼƬ���ؽ���������
		private JLabel jpProgress=new JLabel();//�Ҳ���ʾͼƬ���ؽ��ȵ����
		private JLabel jlProgress=new JLabel("Ԥ��ͼƬ������.....");
		private JProgressBar jpb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		//ѡ�в�ͬ���ڵ�ʱ����ʾ��Ϣ����
		private JLabel jlRoot=new JLabel(icon,JLabel.LEFT);
		private JLabel jlGroup=new JLabel();//����ڵ��JLabel
		private CardLayout cl=new CardLayout();//������Ƭ���ֹ�����
		private JLabel[] jla=null;//��Ƭ��������	
		private JSplitPane jspOuter=//���·ָ��JSplitPane
							new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);	
		private JSplitPane jspInner=//�����ҵ�JSplitPane
							new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jpy);
		//ϵͳ���̲���						
		private PopupMenu popup=new PopupMenu();	
		private SystemTray tray;//����SystemTray��Ա����	
		private TrayIcon trayIcon;//����TrayIcon��Ա����
		private MenuItem exit=new MenuItem("�˳�����");//����˵�
		private MenuItem currently=new MenuItem("��ʾ��ǰ�û�");//����˵�
		
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
