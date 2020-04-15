package Work_Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class PetShop {
	public Set<Pet> pets;
	private int size=0;

	public PetShop(int size) {
		this.size=size;
		pets=new HashSet<Pet>(size);
	}
	/*
	 * �ڳ�������ʱ �½�һ������ Ҫ���뵽������ ͬʱ��Ҫ���뵽���ݿ��� 
	 */
	public boolean insert(Pet pp) {
		boolean flag=false;
		if(pets.size()<size) {  
			pets.add(pp);
			this.addintoDB(pp);
			flag=true;
		}
		else {
			System.out.println("���������������ʧ�ܡ�");
		}
		return flag;
	}
	public boolean insertfirst(String name,String Type,String Color,int age) {
		Pet pp;
		boolean flag=false;		
		if(pets.size()<size) {
			pp=new Pet(name,Type,Color,age);
			this.pets.add(pp);
			flag=true;
		}
		return flag;
	}
	
	public void printf() {  //��������������еĳ������
		Iterator<Pet> pit = pets.iterator();  //���õ��������� 
		while(pit.hasNext()) {
			Pet tem=pit.next();
			tem.printf();
		}
	}
	/*
	 * ���ڴ���ɾ��һ������ ͬʱҪ�������������ݿ���ɾ��
	 */
	public boolean deletePet(Pet pp) {  
		boolean flag=false;
		if(pp!=null) {
			pets.remove(pp);
			this.delfromDB(pp.getName());
			flag=true;
		}
		return flag;
	}
	/*
	 * �������в���Ŀ����Ǹ� û���򷵻�null
	 */

	public Pet search(String name) {
		Pet pp=null;
		Iterator<Pet> pit=pets.iterator();
		while(pit.hasNext()) {
			pp=pit.next();
			if(pp.getName().contentEquals(name)) 
				return pp;
		}
		return null;
	}
	/*
	 * �жϴ�������Ƿ���� �������򷵻�false ���ڷ���true
	 */
	public boolean isIn(String name) {
		Pet pp=null;
		boolean flag=false;
		Iterator<Pet> pit=pets.iterator();
		while(pit.hasNext()) {
			pp=pit.next();
			if(pp.getName().contentEquals(name))
				break;
		}
		return flag;
	}
	/*
	 * ���ڴ��м������ Ҫ�ж��Ƿ��Ѿ�����
	 */
	public boolean addpet(String name,String Type,String Color,int age) {
		Pet pp;
		boolean flag=false;
		// �����жϷ��� 
		if(!this.isIn(name)){
			pp=new Pet(name,Type,Color,age);
			return this.insert(pp);
		}
		else 
			return flag;
	}
	
	public int getnowsize() {
		int s=this.pets.size();
		return s;
	}
	
	public int sizegetter() {
		return size;
	}

	/*
	 * ���ڴ��еĳ�Ա���뵽���ݿ���
	 */

	/*
	 * ���ݿ����������
	 */
	
	private static Connection getConn() {
		// ����MySQL�����ݿ���������
		String DBDRIVER = "com.mysql.cj.jdbc.Driver" ;	
				// ����MySQL���ݿ�����ӵ�ַ
		String DBURL = "jdbc:mysql://localhost:3306/petshop?serverTimezone=GMT" ;
				//public static final  String URL="jdbc:mysql://localhost:3306/jdbc01?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
				// MySQL���ݿ�������û���
		String DBUSER = "root" ;
				// MySQL���ݿ����������
		String DBPASS = "software95SQL" ;
		Connection conn = null ;					// ���ݿ�����
		// ����MySQL���ݿ�ʱ��Ҫд�����ӵ��û��������룬���쳣
	    try {
	        Class.forName(DBDRIVER); 				//classLoader,���ض�Ӧ���� ���쳣
	        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // ���쳣 
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return conn;
	}
	/*
	 *�����ݿ��ж�ȡ��Ա���ڴ��� 
	 */
	public  void readDB() {
	    Connection conn = getConn();
	    String sql = "select * from petslist";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {   							   		//resultset.next �õ����ÿһ�� û���򷵻�false
	        	int age=Integer.parseInt(rs.getString(4));
	        	this.insertfirst(rs.getString(1), rs.getString(2), rs.getString(3), age);
	        }
	          rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/*
	 * �����ݿ���ɾ��ĳ����Ա name������
	 */
	public boolean delfromDB(String name) {
		boolean flag=false;
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from petslist where Name='" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();  //ִ�гɹ�����1
	        if(i==1)
	        	flag=true;
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return flag;
	}
	/*
	 * �����ݿ������ĳ����Ա
	 */
	public boolean addintoDB(Pet p) {
		boolean flag=false;
		String sql = "INSERT INTO petshop.petslist(name,type,color,age) "+ " VALUES (?,?,?,?)";	// ��дԤ����SQL
		PreparedStatement pstmt;
		Connection conn = getConn();
		try {		
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getType());
			pstmt.setString(3, p.getColor());
			pstmt.setInt(4, p.getAge());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}	