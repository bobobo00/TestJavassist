package Javassist;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * ����Javassist��API
 * @author dell
 *
 */

public class Demo02 {
	/**
	 * ������Ļ����÷�
	 * @param args
	 * @throws Exception 
	 */
	public static void test01() throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass ct=pool.get("Javassist.Emp");
		
		byte[] bytes=ct.toBytecode();
		System.out.println(Arrays.toString(bytes));
		
		System.out.println(ct.getName());
		System.out.println(ct.getSimpleName());
		System.out.println(ct.getSuperclass());
		System.out.println(ct.getInterfaces());
	}
	/**
	 * ���Բ����µķ���
	 * @throws Exception 
	 */
	public static void test02() throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass cc=pool.get("Javassist.Emp");
		
		CtMethod m=new CtMethod(CtClass.intType,"add",
				new CtClass[] {CtClass.intType,CtClass.intType},cc);
		m.setModifiers(Modifier.PUBLIC);
		m.setBody("{return $1+$2;}");
		cc.addMethod(m);
		
		//ͨ��������������ɵķ���
		Class clz=cc.toClass();
		Object obj=clz.newInstance();
		Method method=clz.getDeclaredMethod("add",int.class,int.class);
		Object result=method.invoke(obj, 200,300);
		System.out.println(result);
	}
	/**
	 * �����޸ķ���
	 * @throws Exception
	 */
	public static void test03() throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass ct=pool.get("Javassist.Emp");
		
		CtMethod cm=ct.getDeclaredMethod("hi",new CtClass[] {CtClass.intType});
		cm.insertBefore("System.out.println(\"start!\");");
		cm.insertAfter("System.out.println(\"end!\");");
		cm.insertAt(12, "int b=3;System.out.println(\"b=\"+b);");
		
		Class clz=ct.toClass();
		Object obj=clz.newInstance();
		Method method=clz.getDeclaredMethod("hi", int.class);
		method.invoke(obj, 10);
	}
	/**
	 * �����޸�����
	 * @param args
	 * @throws Exception 
	 * @throws Exception
	 */
	public static void test04() throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass ct=pool.get("Javassist.Emp");
		
		CtField f1=new CtField(CtClass.intType,"salary",ct);
		f1.setModifiers(Modifier.PRIVATE);
		ct.addField(f1);
		CtField f=ct.getDeclaredField("salary");
		System.out.println(f.getModifiers()+f.getName());
		//������Ӧ��set��get����
		ct.addMethod(CtNewMethod.getter("getSalary", f1));
		ct.addMethod(CtNewMethod.getter("setSalary", f1));
		CtMethod method=ct.getDeclaredMethod("getSalary");
		Object obj=method.getReturnType();
		System.out.println(obj);
	}
	/**
	 * ���췽���Ĳ���
	 * @param args
	 * @throws NotFoundException 
	 * @throws Exception
	 */
	public static void test05() throws NotFoundException {
		ClassPool pool=ClassPool.getDefault();
		CtClass ct=pool.get("Javassist.Emp");
		
		CtConstructor[] constructors=ct.getDeclaredConstructors();
		for(CtConstructor con:constructors) {
			System.out.println(con.getLongName());
		}
	}
	/**
	 * ע��Ĳ���
	 * @param args
	 * @throws Exception 
	 * @throws Exception
	 */
	public static void test06() throws Exception {
		ClassPool pool=ClassPool.getDefault();
		CtClass ct=pool.get("Javassist.Emp");
		
		Object[] an=ct.getAnnotations();
		Author a=(Author)an[0];
		System.out.println(a.name());
		System.out.println(a.salary());
	}
	public static void main(String[] args) throws Exception {
		test06();
	}

}
