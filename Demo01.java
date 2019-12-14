package Javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

/**
 * 测试使用jvassist生成一个类。
 * @author dell
 *
 */
public class Demo01 {
	public static void main(String[] args) throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass cc=pool.makeClass("Emp");
		
		//创建属性
		CtField f1=CtField.make("private int empno;",cc);
		CtField f2=CtField.make("private String ename;", cc);
		cc.addField(f1);
		cc.addField(f2);
		
		//创建方法
		CtMethod method1=CtMethod.make("public int getEmpno(){return empno;}", cc);
		CtMethod method2=CtMethod.make("public void setEmpno(String ename){this.ename=ename;}",cc);
		cc.addMethod(method1);
		cc.addMethod(method2);
		
		//添加构造器
		CtConstructor constructor=new CtConstructor(new CtClass[] {CtClass.intType,pool.get("java.lang.String")},cc);
		constructor.setBody("{this.empno=empno;this.ename=ename;}");
		cc.addConstructor(constructor);
		
		cc.writeFile("E:/javac");//将写好的类写入到E：/javac中
		System.out.println("成功，生成类");
		
		
	}
}
