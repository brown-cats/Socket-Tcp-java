package Window;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.*;

import Work_Class.Pet;
import Work_Class.PetShop;
public class window_home {
	private JPanel contentpanel;
	static PetShop ps;
	JLabel titlelabel,sizelabel,showsizelabel,pettitle;
	JPanel petpanel[]; 
	JScrollPane crolpanel;
	JPanel j1,j2,j3,j4;	
	public window_home() {
		//先声明变量
		contentpanel=new JPanel();
		titlelabel=new JLabel("欢迎进入宠物商店");
		sizelabel=new JLabel("当前宠物店的容量:");
		pettitle=new JLabel("当前宠物店里的宠物");
		showsizelabel=new JLabel();//用于收集从内存中读到的容量信息 
		showsizelabel.setText(ps.sizegetter()+"");
		//分别插入 标题信息 宠物列表信息 宠物店容量信息
		j1=new JPanel();
		j2=new JPanel();
		j3=new JPanel();
		j4=new JPanel();
		
		this.Setting();
		
		j1.add(titlelabel);
		j2.add(pettitle);
		j3.add(sizelabel);
		j3.add(showsizelabel);
		
		panelread(j4); 
		
		contentpanel.setLayout(new FlowLayout());
		contentpanel.add(j1);
		contentpanel.add(j2);
		contentpanel.add(j4);	
		contentpanel.add(j3);
	}
	public JPanel getP() {
		return contentpanel;
	}
	public void panelread(JPanel jp) {
		JPanel listtitle=new JPanel();
		JPanel itemlist;
		itemlist=new JPanel();
		JLabel name,color,type,age;
		Font font=new Font("微软雅黑", Font.PLAIN, 20);
		name=new JLabel("名字");
		color=new JLabel("颜色");
		type=new JLabel("类型");
		age=new JLabel("年龄");
		name.setFont(font);
		color.setFont(font);
		type.setFont(font);
		age.setFont(font);
		listtitle.setPreferredSize(new Dimension(500,40));
		listtitle.setLayout(new GridLayout(1,4));
		listtitle.add(name);
		listtitle.add(type);
		listtitle.add(color);
		listtitle.add(age);
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
			listpanel.setPreferredSize(new Dimension(500,40));
			//按照 名字 类型 颜色 年龄的顺序加入
			listpanel.add(rname);
			listpanel.add(rtype);
			listpanel.add(rcolor);
			listpanel.add(rage);
			itemlist.add(listpanel);
		}
		itemlist.setPreferredSize(new Dimension(500,(ps.pets.size()*40+70)));
		crolpanel=new JScrollPane();
		crolpanel.setPreferredSize(new Dimension(600,370));
		crolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		crolpanel.setViewportView(itemlist);
		jp.add(listtitle);
		jp.add(crolpanel);
	}
	
	/*
	 * 对里面的panel和label等进行字体 大小设置
	 */
	
	public void Setting() {
		Font font=new Font("微软雅黑", Font.PLAIN, 24);
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		sizelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showsizelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		pettitle.setFont(font);
		contentpanel.setPreferredSize(new Dimension(900,700));
		j1.setPreferredSize(new Dimension(900,70));  // 显示窗口标题
		j2.setPreferredSize(new Dimension(900,50));	 //	显示宠物信息列表
		j3.setPreferredSize(new Dimension(900,50));  //显示宠物容量信息
		
		j4.setPreferredSize(new Dimension(800,430)); //显示宠物信息
		
	}
	static void getShop(PetShop p) {
		ps=p;
	}
	
}
