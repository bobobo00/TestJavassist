package Javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

/**
 * ����ʹ��jvassist����һ���ࡣ
 * @author dell
 *
 */
public class Demo01 {
	public static void main(String[] args) throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass cc=pool.makeClass("Emp");
		
		//��������
		CtField f1=CtField.make("private int empno;",cc);
		CtField f2=CtField.make("private String ename;", cc);
		cc.addField(f1);
		cc.addField(f2);
		
		//��������
		CtMethod method1=CtMethod.make("public int getEmpno(){return empno;}", cc);
		CtMethod method2=CtMethod.make("public void setEmpno(String ename){this.ename=ename;}",cc);
		cc.addMethod(method1);
		cc.addMethod(method2);
		
		//��ӹ�����
		CtConstructor constructor=new CtConstructor(new CtClass[] {CtClass.intType,pool.get("java.lang.String")},cc);
		constructor.setBody("{this.empno=empno;this.ename=ename;}");
		cc.addConstructor(constructor);
		
		cc.writeFile("E:/javac");//��д�õ���д�뵽E��/javac��
		System.out.println("�ɹ���������");
		
		
	}
}
