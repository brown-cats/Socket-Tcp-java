package Window;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import Work_Class.Pet;
import Work_Class.PetShop;
public class window_serach extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentpanel;
	static PetShop ps;
	JPanel jr3;
	JPanel jleft,jright;
	JPanel jr1,jr2,jr4;
	JButton okButton;
	JLabel showlabel,titlelabel,namelabel;
	JTextField nametext;
	public window_serach() {
		//申请变量
		jleft=new JPanel();
		jright=new JPanel();
		jr1=new JPanel();
		jr2=new JPanel();
		jr3=new JPanel();
		jr4=new JPanel();
		okButton=new JButton("查找");
		showlabel=new JLabel("已加入的宠物信息");
		titlelabel=new JLabel("请输入带查找目标信息");
		namelabel=new JLabel("名字：");
		nametext=new JTextField(10);
		okButton.addActionListener(this);
		//字体
		Font font=new Font("微软雅黑", Font.PLAIN, 24);	
		okButton.setFont(font);
		showlabel.setFont(font);
		titlelabel.setFont(font);
		namelabel.setFont(font);
		nametext.setFont(font);
		
		//panel大小设置
		jleft.setPreferredSize(new Dimension(400,700));
		jright.setPreferredSize(new Dimension(500,700));
		jr1.setPreferredSize(new Dimension(500,140));
		jr2.setPreferredSize(new Dimension(500,140));
		jr3.setPreferredSize(new Dimension(500,100));
		jr4.setPreferredSize(new Dimension(500,320));
		
		jleft.add(showlabel);
		jr1.add(titlelabel);
		jr2.add(namelabel);
		jr2.add(nametext);
		jr4.add(okButton);
		contentpanel=new JPanel();
		jright.add(jr1);
		jright.add(jr2);
		jright.add(jr3);
		jright.add(jr4);
		this.panelread(jleft);
		contentpanel.add(jleft);
		contentpanel.add(jright);
	}
	public JPanel getP() {
		return contentpanel;
	}
	public JPanel showitem(Pet pp) {
		JPanel contentpanel;
		contentpanel=new JPanel();
		JPanel jp1;
		JLabel name,type,color,age;
		JLabel pname,ptype,pcolor,page;
		Font font=new Font("微软雅黑", Font.PLAIN, 24);	
		name=new JLabel("名字");
		type=new JLabel("类型");
		color=new JLabel("颜色");
		age=new JLabel("年龄");
		pname=new JLabel(pp.getName());
		ptype=new JLabel(pp.getType());
		pcolor=new JLabel(pp.getColor());
		page=new JLabel(pp.getAge()+"");
		// 字体设置
		name.setFont(font);type.setFont(font);type.setFont(font);color.setFont(font);
		pname.setFont(font);ptype.setFont(font);ptype.setFont(font);pcolor.setFont(font);
		jp1=new JPanel();
		jp1.setPreferredSize(new Dimension(500,200));
		jp1.setLayout(new GridLayout(2,4));
		// 将组件加入到panel
		jp1.add(name);jp1.add(type);jp1.add(color);jp1.add(age);
		jp1.add(pname);jp1.add(ptype);jp1.add(pcolor);jp1.add(page);
		return contentpanel;
	}
	public void additem(JPanel j) {
		jr3.add(j);
	}
	static void getShop(PetShop p) {
		window_serach.ps=p;
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
		itemlist.setPreferredSize(new Dimension(400,(ps.pets.size()*40+70)));
		crolpanel=new JScrollPane();
		crolpanel.setPreferredSize(new Dimension(400,520));
		crolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		crolpanel.setViewportView(itemlist);
		jp.add(listtitle);
		jp.add(crolpanel);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton jb=(JButton)arg0.getSource();
		String name=nametext.getText();
		Pet pp=null;
		if(jb==okButton) {
			pp=ps.search(name);
			if(name.contentEquals("")) {
				JOptionPane.showMessageDialog(this,"输入为空，请重新输入");
			}
			else if(pp==null) {
				JOptionPane.showMessageDialog(this,"目标不存在");
			}
			else {
				this.additem(this.showitem(pp));
				this.validate();
				
				JOptionPane.showMessageDialog(this,"查找成功,在宠物店中存在");
			}
		}
	}
	
}
