package Window;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import Work_Class.Pet;
import Work_Class.PetShop;

public class window_delete extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentpanel;
	static PetShop ps;
	JPanel panel1,panel2,panel3,panel4,JPanel1;
	JLabel titlelabel,namelabel,showlabel;
	JTextField nametext;
	JButton delButton,cancelButton;
	//private Window_first window;
	public window_delete(){
		//��������
		titlelabel=new JLabel("�������ɾ��Ŀ����Ϣ");
		namelabel=new JLabel("���֣�");
		showlabel=new JLabel("�Ѽ���ĳ�����Ϣ");
		delButton=new JButton("ɾ��");
		cancelButton=new JButton("ȡ��");
		nametext=new JTextField(10);	
		contentpanel=new JPanel();
		panel1=new JPanel();
		JPanel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		//��������б�
		delButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//��������
		Font font=new Font("΢���ź�", Font.PLAIN, 24);
		titlelabel.setFont(font);
		namelabel.setFont(font);
		nametext.setFont(font);
		delButton.setFont(font);
		cancelButton.setFont(font);
		showlabel.setFont(font);
		//panel��С����
		contentpanel.setPreferredSize(new Dimension(900,700));//��������
		JPanel1.setPreferredSize(new Dimension(400,700)); //��� 
		JPanel jp=new JPanel();   //�ұߴ�
		jp.setPreferredSize(new Dimension(500,700));
		panel1.setPreferredSize(new Dimension(500,140));//����
		panel2.setPreferredSize(new Dimension(500,500));//����
		//���ֹ���
		panel2.setLayout(new GridLayout(2,1));
		JPanel1.add(showlabel);
		panel1.add(titlelabel);

		panel3.add(namelabel);
		panel3.add(nametext);
		panel4.add(delButton);
		panel4.add(cancelButton);
		panel3.setVisible(true);
		panel4.setVisible(true);
		panel2.add(panel3);
		panel2.add(panel4);
		//���齨���뵽panel��

		jp.add(panel1);
		jp.add(panel2);
		this.panelread(JPanel1);
		contentpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		contentpanel.add(JPanel1);
		contentpanel.add(jp);
	}

public JPanel getcontent() {
	return contentpanel;
}
public void seeunvis(boolean flag) {
	contentpanel.setVisible(flag);
}
public void panelread(JPanel jp) {
	JScrollPane crolpanel;
	JPanel listtitle=new JPanel();
	JPanel itemlist=new JPanel();
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
	itemlist.setPreferredSize(new Dimension(400,(ps.pets.size()*40+70)));
	crolpanel=new JScrollPane();
	crolpanel.setPreferredSize(new Dimension(400,520));
	crolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	crolpanel.setViewportView(itemlist);
	jp.add(listtitle);
	jp.add(crolpanel);
	
}

@Override
public void actionPerformed(ActionEvent e) {
	JButton jb=(JButton)e.getSource();
	String name;
	if(jb==delButton) {
		name=nametext.getText();
		if(name==null) {
			JOptionPane.showMessageDialog(this,"����Ϊ�գ�����������");
		}
		else {
			//��name���ڴ������ ��������� �򷵻�false
			Pet pp=null;
			pp=ps.search(name);
			if(pp==null) 
				JOptionPane.showMessageDialog(this,"���벻���ڣ�����������");
			else 
				if(ps.deletePet(pp))
					JOptionPane.showMessageDialog(this,"ɾ���ɹ�");
		}
	}
	else {
		//jb=cancelButton ���ص���ҳ
		
	}
	
}
static void getShop(PetShop p) {
	window_delete.ps=p;
}

}
