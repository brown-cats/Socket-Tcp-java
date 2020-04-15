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
	//��������
	JLabel sizelabel;
	JTextField sizeText;
	JButton okButton;		
	JLabel lblNewLabel;	
	JMenu funcMenu;
	JMenuItem builditem,delitem,seritem,homeitem;
	JMenuBar menubar;
	JPanel shownow;
	//���캯��
	public Window_first() {
		//��������������
		setTitle("�������ϵͳ");
		setBounds(400, 150, 988, 700);
		//��������
		//lblNewLabel ��ӭ��
		lblNewLabel= new JLabel("��ӭ�����������ϵͳ");
		lblNewLabel.setForeground(Color.RED);

		sizelabel=new JLabel("���������: ");
		sizeText=new JTextField(5);
		okButton=new JButton("ȷ��");
		//������������������� 
		JPanel welPanel,sizePanel,okPanel;
		welPanel=new JPanel();
		sizePanel=new JPanel();
		okPanel=new JPanel();
		containterPanel=new JPanel();
		funcMenu=new JMenu("����");
		//menubar=new JMenuBar();
		builditem=new JMenuItem("�������");
		delitem=new JMenuItem("ɾ������");
		seritem=new JMenuItem("���ҳ���");
		homeitem=new JMenuItem("��ҳ");
		//�������� 
		Font font=new Font("΢���ź�", Font.PLAIN, 24);	
		sizelabel.setFont(font);
		lblNewLabel.setFont(font);		
		funcMenu.setFont(font);
		builditem.setFont(font);
		delitem.setFont(font);	
		seritem.setFont(font);	
		homeitem.setFont(font);
		okButton.setFont(font);
		sizeText.setFont(font);
		//�˵����� 
		menubar.add(funcMenu);
		funcMenu.add(homeitem);
		funcMenu.add(builditem);
		funcMenu.add(delitem);
		funcMenu.add(seritem);
		this.setJMenuBar(menubar);
		
		//���� 
		okButton.addActionListener(this);
		funcMenu.addActionListener(this);
		homeitem.addActionListener(this);
		builditem.addActionListener(this);
		seritem.addActionListener(this);
		delitem.addActionListener(this);
		//�����������
		//welPanel.setBackground(new Color(255,240,245));
		//sizePanel.setBackground(new Color(230,230,250));
		//okPanel.setBackground(new Color(238,180,180));
		//������ӵ������
		welPanel.add(lblNewLabel);
		sizePanel.add(sizelabel);
		sizePanel.add(sizeText);
		okPanel.add(okButton);
		//���ֹ�����
		containterPanel.setLayout(new GridLayout(3,1));
		containterPanel.add(welPanel,BorderLayout.CENTER) ;
		containterPanel.add(sizePanel) ;
		containterPanel.add(okPanel) ;
		shownow=containterPanel; //��ʼ��shownow
		this.setLayout(new BorderLayout());
		this.getContentPane().add(containterPanel,BorderLayout.CENTER);
	}
	
	//ʵ�ֽӿ����һ��������  �����¼�����
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() instanceof JButton) {
			JButton jb=(JButton)e.getSource();	
			//�ж��¼�Դ
			if(jb==okButton) {
				String size=sizeText.getText();
				//�쳣���� NumberFormatException
				try {
					int len=Integer.parseInt(size);//��parseInt�㷨���׳��쳣NumberFormatException
					if(len>0) {
						ps=new PetShop(len);
						if(ps!=null) {
							JOptionPane.showMessageDialog(this, "�����ɹ�");
							ps.readDB();
							//������һ������
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
					JOptionPane.showMessageDialog(this,"���벻�Ϸ�������������");
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
	//���ñ��������panel���ɼ�
	public void seeunvis(boolean flag) { 
		containterPanel.setVisible(flag);
	}
	public void changeshownowJP(JPanel jp) {
		shownow=jp;
	}
	public JPanel getshownow() {
		return shownow;
	}
	//�������
	public static void main(String[] args) {
		Window_first frame = new Window_first();
		frame.setVisible(true);
	}
	
}
