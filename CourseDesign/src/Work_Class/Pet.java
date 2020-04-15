package Work_Class;

public  class Pet {
	private String type;
	private String color;
	private String name;
	private int age;
	public String getType() {
		return type;
	}
	void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	void setAge(int age) {
		this.age = age;
	}
	public Pet(String N,String T,String C,int A) {
		this.setType(T);
		this.setColor(C);
		this.setName(N);
		this.setAge(A);
	}
	public  String toString() {
		return "";
	}
	public void printf() {
		System.out.println("Name:"+name+" Type:"+type+" Color:"+color+" Age:"+age);
	}
}
