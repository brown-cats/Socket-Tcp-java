package Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Work_Class.Pet;
import Work_Class.PetShop;
public class window_build extends JFrame implements ActionListener, ListSelectionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义变量
	static PetShop ps;
	private JPanel contentPane, JPanel1,jp1;
	JLabel showlabel,titlelabel,namelabel,colorlabel,agelabel,typelabel;
	JTextField nameText,ageText;
	JButton okButton,cancelButton;
	JLabel lblNewLabel;	
	JList colorlist,typelist;
	String color[]= { "brown","gray","light-blue","black","white","orange","silvery-grey","golden"	};
	String type[]= { "dog","cat","other"};
	//构造函数
	public window_build() {
		//label对象声明
		showlabel=new JLabel("已加入的宠物信息");
		titlelabel=new JLabel("请在下面输入宠物信息");
		namelabel=new JLabel("名字:");
		colorlabel=new JLabel("颜色:");
		agelabel=new JLabel("年龄:");
		typelabel=new JLabel("类型:");
		//文本输入框对象声明
		nameText=new JTextField(10);
		ageText=new JTextField(10);
		//按钮对象声明
		okButton=new JButton("确定");
		cancelButton=new JButton("取消");

		colorlist=new JList(color);
		typelist=new JList(type);
		//加入监听列表
		colorlist.addListSelectionListener(this);
		typelist.addListSelectionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//选择栏的选择属性 只能单选
		colorlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		typelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//字体设置
		Font font=new Font("微软雅黑", Font.PLAIN, 24);
		typelist.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		colorlist.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showlabel.setFont(font);
		titlelabel.setFont(font);
		namelabel.setFont(font);
		colorlabel.setFont(font);
		agelabel.setFont(font);
		typelabel.setFont(font);
		okButton.setFont(font);
		cancelButton.setFont(font);
		ageText.setFont(font);
		nameText.setFont(font);

		//声明并定义各个面板对象
		JPanel JPanel2,JPanel3,JPanel4,JPanel5,JPanel6,JPanel7,JPanel8;
		JPanel1=new JPanel();
		JPanel2=new JPanel();
		JPanel3=new JPanel();
		JPanel4=new JPanel();
		JPanel5=new JPanel();
		JPanel6=new JPanel();
		JPanel7=new JPanel();
		JPanel8=new JPanel();
		//把组件加到各个小面板中
		JPanel1.add(showlabel);
		JPanel2.add(titlelabel);
		JPanel3.add(namelabel);
		JPanel3.add(nameText);
		JPanel4.add(colorlabel);
		JPanel4.add(colorlist);
		//添加color的选择栏
		JPanel5.add(agelabel);
		JPanel5.add(ageText);
		//添加type的选择栏
		JPanel6.add(typelabel);
		JPanel6.add(typelist);
		JPanel7.add(okButton);
		JPanel8.add(cancelButton);
		okButton.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
		//属性设置
		jp1=new JPanel();
		JPanel jp2=new JPanel();
		//JPanel的大小设置
		//左边的panel
		JPanel1.setPreferredSize(new Dimension(400,700));
		//右边的大panel
		jp1.setPreferredSize(new Dimension(572,700));
		//右下panel
		jp2.setPreferredSize(new Dimension(572,550));
		//右上panel
		JPanel2.setPreferredSize(new Dimension(572,140));

		//右下JPanel的布局管理器以及panel加入
		jp2.setLayout(new GridLayout(3,2));
		jp2.add(JPanel3);
		jp2.add(JPanel4);
		jp2.add(JPanel5);
		jp2.add(JPanel6);
		jp2.add(JPanel7);
		jp2.add(JPanel8);
				
		//将三个JPanel添加到右边的大panel里
		jp1.add(JPanel2);	
		jp1.add(jp2);
		//最后全加到contentPane里 更改这个panel的可见性
		contentPane=new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		this.panelread(JPanel1);
		contentPane.add(JPanel1);
		contentPane.add(jp1);
		
	}
	//监听okbutton和cancelbutton
	public void actionPerformed(ActionEvent e) {
		JButton jb=(JButton)e.getSource();
		String name,color,type,tage;
		int age;
		if(jb==okButton) {
			name=nameText.getText();
			color=(String)colorlist.getSelectedValue();
			type=(String)typelist.getSelectedValue();
			tage=ageText.getText();
			try {
				age=Integer.parseInt(tage);
				NumberFormatException e2=new NumberFormatException();
				if(age<=0||name==null||color==null||type==null) {
					throw e2;
				}
				else {
					if(ps.addpet(name, type, color, age))
						JOptionPane.showMessageDialog(this,"创建成功");
					else if(ps.isIn(name)){
						JOptionPane.showMessageDialog(this, "名字已存在，请重新输入");
					}
				}
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(this,"输入不合法，请重新输入");
			}
		}
		else if(jb==cancelButton) {
			
		}
	}
	//改变this.panel的可见性
	public void seeunvis(boolean flag) {
		this.setVisible(flag);
	}
	//获取this.panel
	public JPanel getP() {
		return contentPane;
	}
	
	public void panelread(JPanel jp) {
		JScrollPane crolpanel;
		JPanel itemlist=new JPanel();
		JPanel listtitle=new JPanel();
		JLabel name,color,type,age;
		Font font=new Font("微软雅黑", Font.PLAIN, 18);
		name=new JLabel("名字");
		color=new JLabel("颜色");
		type=new JLabel("类型");
		age=new JLabel("年龄");
		name.setFont(font);
		color.setFont(font);
		type.setFont(font);
		age.setFont(font);
		listtitle.setPreferredSize(new Dimension(400,40));
		//布局管理以及组件添加
		listtitle.setLayout(new GridLayout(1,4));
		listtitle.add(name);
		listtitle.add(type);
		listtitle.add(color);
		listtitle.add(age);
		jp.add(listtitle);
		Pet pp=null;
		Iterator<Pet> pit=ps.pets.iterator();
		JLabel rname,rcolor,rtype,rage;
		JPanel listpanel;
		while(pit.hasNext()) {
			listpanel=new JPanel();
			pp=pit.next();
			rname=new JLabel(pp.getName());
			rcolor=new JLabel(pp.getColor());
			rtype=new JLabel(pp.getType());
			rage=new JLabel(pp.getAge()+"");
			rname.setFont(font);
			rcolor.setFont(font);
			rtype.setFont(font);
			rage.setFont(font);
			listpanel.setLayout(new GridLayout(1,4));
			listpanel.setPreferredSize(new Dimension(400,40));
			//按照 名字 类型 颜色 年龄的顺序加入
			listpanel.add(rname);
			listpanel.add(rtype);
			listpanel.add(rcolor);
			listpanel.add(rage);
			itemlist.add(listpanel);
		}
		itemlist.setPreferredSize(new Dimension(400,(ps.pets.size()*40+60)));
		crolpanel=new JScrollPane();
		crolpanel.setPreferredSize(new Dimension(400,520));
		crolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		crolpanel.setViewportView(itemlist);
		jp.add(crolpanel);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	static void getShop(PetShop p) {
		window_build.ps=p;
	}
}
