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
	 * 在程序运行时 新建一个对象 要插入到容器中 同时还要插入到数据库中 
	 */
	public boolean insert(Pet pp) {
		boolean flag=false;
		if(pets.size()<size) {  
			pets.add(pp);
			this.addintoDB(pp);
			flag=true;
		}
		else {
			System.out.println("宠物店已满！存入失败。");
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
	
	public void printf() {  //输出函数将容器中的宠物输出
		Iterator<Pet> pit = pets.iterator();  //利用迭代器迭代 
		while(pit.hasNext()) {
			Pet tem=pit.next();
			tem.printf();
		}
	}
	/*
	 * 从内存中删除一个对象 同时要把这个对象从数据库中删除
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
	 * 在容器中查找目标的那个 没有则返回null
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
	 * 判断传入参数是否存在 不存在则返回false 存在返回true
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
	 * 往内存中加入宠物 要判断是否已经存在
	 */
	public boolean addpet(String name,String Type,String Color,int age) {
		Pet pp;
		boolean flag=false;
		// 调用判断方法 
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
	 * 将内存中的成员插入到数据库中
	 */

	/*
	 * 数据库的驱动程序
	 */
	
	private static Connection getConn() {
		// 定义MySQL的数据库驱动程序
		String DBDRIVER = "com.mysql.cj.jdbc.Driver" ;	
				// 定义MySQL数据库的连接地址
		String DBURL = "jdbc:mysql://localhost:3306/petshop?serverTimezone=GMT" ;
				//public static final  String URL="jdbc:mysql://localhost:3306/jdbc01?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
				// MySQL数据库的连接用户名
		String DBUSER = "root" ;
				// MySQL数据库的连接密码
		String DBPASS = "software95SQL" ;
		Connection conn = null ;					// 数据库连接
		// 连接MySQL数据库时，要写上连接的用户名和密码，有异常
	    try {
	        Class.forName(DBDRIVER); 				//classLoader,加载对应驱动 有异常
	        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 有异常 
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return conn;
	}
	/*
	 *从数据库中读取成员到内存中 
	 */
	public  void readDB() {
	    Connection conn = getConn();
	    String sql = "select * from petslist";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {   							   		//resultset.next 得到表的每一行 没有则返回false
	        	int age=Integer.parseInt(rs.getString(4));
	        	this.insertfirst(rs.getString(1), rs.getString(2), rs.getString(3), age);
	        }
	          rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/*
	 * 从数据库中删除某个成员 name是主键
	 */
	public boolean delfromDB(String name) {
		boolean flag=false;
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from petslist where Name='" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();  //执行成功返回1
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
	 * 往数据库里添加某个成员
	 */
	public boolean addintoDB(Pet p) {
		boolean flag=false;
		String sql = "INSERT INTO petshop.petslist(name,type,color,age) "+ " VALUES (?,?,?,?)";	// 编写预处理SQL
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