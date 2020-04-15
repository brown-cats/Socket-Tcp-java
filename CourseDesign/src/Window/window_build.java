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
	//�������
	static PetShop ps;
	private JPanel contentPane, JPanel1,jp1;
	JLabel showlabel,titlelabel,namelabel,colorlabel,agelabel,typelabel;
	JTextField nameText,ageText;
	JButton okButton,cancelButton;
	JLabel lblNewLabel;	
	JList colorlist,typelist;
	String color[]= { "brown","gray","light-blue","black","white","orange","silvery-grey","golden"	};
	String type[]= { "dog","cat","other"};
	//���캯��
	public window_build() {
		//label��������
		showlabel=new JLabel("�Ѽ���ĳ�����Ϣ");
		titlelabel=new JLabel("�����������������Ϣ");
		namelabel=new JLabel("����:");
		colorlabel=new JLabel("��ɫ:");
		agelabel=new JLabel("����:");
		typelabel=new JLabel("����:");
		//�ı�������������
		nameText=new JTextField(10);
		ageText=new JTextField(10);
		//��ť��������
		okButton=new JButton("ȷ��");
		cancelButton=new JButton("ȡ��");

		colorlist=new JList(color);
		typelist=new JList(type);
		//��������б�
		colorlist.addListSelectionListener(this);
		typelist.addListSelectionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//ѡ������ѡ������ ֻ�ܵ�ѡ
		colorlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		typelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//��������
		Font font=new Font("΢���ź�", Font.PLAIN, 24);
		typelist.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		colorlist.setFont(new Font("΢���ź�", Font.PLAIN, 20));
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

		//�������������������
		JPanel JPanel2,JPanel3,JPanel4,JPanel5,JPanel6,JPanel7,JPanel8;
		JPanel1=new JPanel();
		JPanel2=new JPanel();
		JPanel3=new JPanel();
		JPanel4=new JPanel();
		JPanel5=new JPanel();
		JPanel6=new JPanel();
		JPanel7=new JPanel();
		JPanel8=new JPanel();
		//������ӵ�����С�����
		JPanel1.add(showlabel);
		JPanel2.add(titlelabel);
		JPanel3.add(namelabel);
		JPanel3.add(nameText);
		JPanel4.add(colorlabel);
		JPanel4.add(colorlist);
		//���color��ѡ����
		JPanel5.add(agelabel);
		JPanel5.add(ageText);
		//���type��ѡ����
		JPanel6.add(typelabel);
		JPanel6.add(typelist);
		JPanel7.add(okButton);
		JPanel8.add(cancelButton);
		okButton.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
		//��������
		jp1=new JPanel();
		JPanel jp2=new JPanel();
		//JPanel�Ĵ�С����
		//��ߵ�panel
		JPanel1.setPreferredSize(new Dimension(400,700));
		//�ұߵĴ�panel
		jp1.setPreferredSize(new Dimension(572,700));
		//����panel
		jp2.setPreferredSize(new Dimension(572,550));
		//����panel
		JPanel2.setPreferredSize(new Dimension(572,140));

		//����JPanel�Ĳ��ֹ������Լ�panel����
		jp2.setLayout(new GridLayout(3,2));
		jp2.add(JPanel3);
		jp2.add(JPanel4);
		jp2.add(JPanel5);
		jp2.add(JPanel6);
		jp2.add(JPanel7);
		jp2.add(JPanel8);
				
		//������JPanel��ӵ��ұߵĴ�panel��
		jp1.add(JPanel2);	
		jp1.add(jp2);
		//���ȫ�ӵ�contentPane�� �������panel�Ŀɼ���
		contentPane=new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		this.panelread(JPanel1);
		contentPane.add(JPanel1);
		contentPane.add(jp1);
		
	}
	//����okbutton��cancelbutton
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
						JOptionPane.showMessageDialog(this,"�����ɹ�");
					else if(ps.isIn(name)){
						JOptionPane.showMessageDialog(this, "�����Ѵ��ڣ�����������");
					}
				}
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(this,"���벻�Ϸ�������������");
			}
		}
		else if(jb==cancelButton) {
			
		}
	}
	//�ı�this.panel�Ŀɼ���
	public void seeunvis(boolean flag) {
		this.setVisible(flag);
	}
	//��ȡthis.panel
	public JPanel getP() {
		return contentPane;
	}
	
	public void panelread(JPanel jp) {
		JScrollPane crolpanel;
		JPanel itemlist=new JPanel();
		JPanel listtitle=new JPanel();
		JLabel name,color,type,age;
		Font font=new Font("΢���ź�", Font.PLAIN, 18);
		name=new JLabel("����");
		color=new JLabel("��ɫ");
		type=new JLabel("����");
		age=new JLabel("����");
		name.setFont(font);
		color.setFont(font);
		type.setFont(font);
		age.setFont(font);
		listtitle.setPreferredSize(new Dimension(400,40));
		//���ֹ����Լ�������
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
			//���� ���� ���� ��ɫ �����˳�����
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
