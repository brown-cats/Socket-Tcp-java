package Window;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;

import Work_Class.PetShop;

import javax.swing.JOptionPane;

public class Window_first extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private JPanel containterPanel;
	private PetShop ps;
	/**
	 * Create the frame.
	 */
	//声明变量
	JLabel sizelabel;
	JTextField sizeText;
	JButton okButton;		
	JLabel lblNewLabel;	
	JMenu funcMenu;
	JMenuItem builditem,delitem,seritem,homeitem;
	JMenuBar menubar;
	JPanel shownow;
	//构造函数
	public Window_first() {
		//窗口声明及设置
		setTitle("宠物管理系统");
		setBounds(400, 150, 988, 700);
		//变量声明
		//lblNewLabel 欢迎栏
		lblNewLabel= new JLabel("欢迎来到宠物管理系统");
		lblNewLabel.setForeground(Color.RED);

		sizelabel=new JLabel("宠物店容量: ");
		sizeText=new JTextField(5);
		okButton=new JButton("确定");
		//声明并定义各个面板对象 
		JPanel welPanel,sizePanel,okPanel;
		welPanel=new JPanel();
		sizePanel=new JPanel();
		okPanel=new JPanel();
		containterPanel=new JPanel();
		funcMenu=new JMenu("功能");
		//menubar=new JMenuBar();
		builditem=new JMenuItem("加入宠物");
		delitem=new JMenuItem("删除宠物");
		seritem=new JMenuItem("查找宠物");
		homeitem=new JMenuItem("主页");
		//字体设置 
		Font font=new Font("微软雅黑", Font.PLAIN, 24);	
		sizelabel.setFont(font);
		lblNewLabel.setFont(font);		
		funcMenu.setFont(font);
		builditem.setFont(font);
		delitem.setFont(font);	
		seritem.setFont(font);	
		homeitem.setFont(font);
		okButton.setFont(font);
		sizeText.setFont(font);
		//菜单加入 
		menubar.add(funcMenu);
		funcMenu.add(homeitem);
		funcMenu.add(builditem);
		funcMenu.add(delitem);
		funcMenu.add(seritem);
		this.setJMenuBar(menubar);
		
		//监听 
		okButton.addActionListener(this);
		funcMenu.addActionListener(this);
		homeitem.addActionListener(this);
		builditem.addActionListener(this);
		seritem.addActionListener(this);
		delitem.addActionListener(this);
		//设置面板属性
		//welPanel.setBackground(new Color(255,240,245));
		//sizePanel.setBackground(new Color(230,230,250));
		//okPanel.setBackground(new Color(238,180,180));
		//把组件加到面板中
		welPanel.add(lblNewLabel);
		sizePanel.add(sizelabel);
		sizePanel.add(sizeText);
		okPanel.add(okButton);
		//布局管理器
		containterPanel.setLayout(new GridLayout(3,1));
		containterPanel.add(welPanel,BorderLayout.CENTER) ;
		containterPanel.add(sizePanel) ;
		containterPanel.add(okPanel) ;
		shownow=containterPanel; //初始化shownow
		this.setLayout(new BorderLayout());
		this.getContentPane().add(containterPanel,BorderLayout.CENTER);
	}
	
	//实现接口里的一个抽象函数  监听事件操作
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() instanceof JButton) {
			JButton jb=(JButton)e.getSource();	
			//判断事件源
			if(jb==okButton) {
				String size=sizeText.getText();
				//异常处理 NumberFormatException
				try {
					int len=Integer.parseInt(size);//用parseInt算法会抛出异常NumberFormatException
					if(len>0) {
						ps=new PetShop(len);
						if(ps!=null) {
							JOptionPane.showMessageDialog(this, "创建成功");
							ps.readDB();
							//调用下一个界面
							this.seeunvis(false);
							window_home wh = null;
							window_home.getShop(ps);
							wh=new window_home();
							this.add(wh.getP());
							this.remove(this.getshownow());
							this.changeshownowJP(wh.getP());
						}
					}
					else {
						NumberFormatException e2=new NumberFormatException();
						throw e2;
					}
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(this,"输入不合法，请重新输入");
				}
			}
		}
		else if(e.getSource() instanceof JMenuItem) {
			JMenuItem jm=(JMenuItem)e.getSource();
			
		if(jm==builditem) {
			window_build.getShop(ps);
			window_build wb=new window_build();
			this.add(wb.getP());
			this.getshownow().setVisible(false);
			this.remove(getshownow());
			this.changeshownowJP(wb.getP());
		}
		else if(jm==seritem) {
			window_serach.getShop(ps);
			window_serach ws=new window_serach();
			this.getshownow().setVisible(false);
			this.add(ws.getP());
			this.remove(this.getshownow());
			this.changeshownowJP(ws.getP());
		}
		else if(jm==delitem) {
			window_delete.getShop(ps);		
			window_delete wd=new window_delete();
			this.getshownow().setVisible(false);
			this.add(wd.getcontent());
			this.remove(getshownow());
			this.changeshownowJP(wd.getcontent());
		}
		else if(jm==homeitem) {
			window_home wh=new window_home();
			this.getshownow().setVisible(false);
			this.add(wh.getP());
			window_home.getShop(ps);
			this.remove(getshownow());
			this.changeshownowJP(wh.getP());
		}
		}
}
	public JPanel getP() {
		return	containterPanel;
	}	
	//设置本界面里的panel不可见
	public void seeunvis(boolean flag) { 
		containterPanel.setVisible(flag);
	}
	public void changeshownowJP(JPanel jp) {
		shownow=jp;
	}
	public JPanel getshownow() {
		return shownow;
	}
	//程序入口
	public static void main(String[] args) {
		Window_first frame = new Window_first();
		frame.setVisible(true);
	}
	
}
