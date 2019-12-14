package Javassist;
@Author(name="aa",salary=1200)
public class Emp {
	private int empno;
	private int ename;
	
	
	public Emp() {
		super();
	}
	public void hi(int a) {
		System.out.println("a="+a);
	}
	public int getNumber() {
		return empno;
	}
	public void setNumber(int number) {
		this.empno = number;
	}
	public int getEname() {
		return ename;
	}
	public void setEname(int ename) {
		this.ename = ename;
	}
	
	

}
