package org.simple.javabase.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Test;

public class JavaBaseReflectionTest {
	public void testJavaBeanReflect() {
		try {
			Class bean = Class
					.forName("org.simple.javabase.reflection.SimpleJavaBean");
			try {
				SimpleJavaBean newBean = (SimpleJavaBean) bean.newInstance();
				assertThat(newBean).isInstanceOf(SimpleJavaBean.class);
				String simpleName = bean.getSimpleName();
				assertThat(simpleName).isEqualToIgnoringCase("SimpleJavaBean");
				Package pack = bean.getPackage();
				String packageName = pack.getName();
				assertThat(packageName).isEqualToIgnoringCase(
						"org.simple.javabase.reflection");

				Constructor[] constructors = bean.getDeclaredConstructors();
				Constructor constructor = bean.getConstructor();
				assertThat(constructor).isIn(constructors);
				SimpleJavaBean newBean2 = (SimpleJavaBean) constructor
						.newInstance();
				assertThat(newBean2).isInstanceOf(SimpleJavaBean.class);

				Constructor constructor2 = bean.getConstructor(long.class,
						String.class);

				assertThat(constructor2).isIn(constructors);
				long id = 3L;
				String name = "test";
				Object[] args = new Object[] { id, name };
				SimpleJavaBean newBean3 = (SimpleJavaBean) constructor2
						.newInstance(args);
				assertThat(newBean3).isInstanceOf(SimpleJavaBean.class);
				assertThat(newBean3.getId()).isEqualTo(id);
				assertThat(newBean3.getName()).isEqualTo(name);

				String idPropertyName = "id";
				Field[] fields = bean.getDeclaredFields();
				Field idField = bean.getDeclaredField(idPropertyName);
				String idFieldName = idField.getName();
				assertThat(idFieldName).isEqualTo(idPropertyName);

				Method[] methods = bean.getDeclaredMethods();
				Method setIdMethod = bean.getMethod("setId", long.class);
				Object[] idArg = new Object[] { 1 };
				setIdMethod.invoke(newBean, idArg);
				assertThat(newBean.getId()).isEqualTo(1);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void testJavaInterfaceTest() {
		try {
			Class interfaceInstance = Class
					.forName("org.simple.javabase.reflection.SipleJavaInterface");
			assertThat(interfaceInstance.getSimpleName()).isEqualTo(
					"SimpleJavaInterface");

			Field[] fields = interfaceInstance.getDeclaredFields();
			String fieldName = "name";
			Field field = interfaceInstance.getField(fieldName);
			assertThat(field.getName()).isEqualTo(fieldName);
			assertThat(field.get(interfaceInstance)).isEqualTo("");
			String beanName = "bean";
			Field field2 = interfaceInstance.getField(beanName);
			assertThat(field2.getType().getSimpleName()).isEqualTo(
					"SimpleJavaBean");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Interospector test
	 */
	public void testBeanInfo() {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(SimpleJavaBean.class);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			MethodDescriptor[] methodDescriptors = beanInfo
					.getMethodDescriptors();
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				String propertyName = descriptor.getName();
				// System.out.println("property name:"+propertyName);
			}
			for (MethodDescriptor methodDescriptor : methodDescriptors) {
				String methodName = methodDescriptor.getName();
				// System.out.println("method name:"+methodName);

			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * dynamic proxy by jdk
	 */
	@Test
	public void testJdkProxy() {
		SimpleJavaBean worker = new SimpleJavaBean();
		InvocationHandler handler = new Handler(worker);
		SimpleJavaInterface proxyBean = (SimpleJavaInterface) Proxy
				.newProxyInstance(SimpleJavaBean.class.getClassLoader(),
						SimpleJavaBean.class.getInterfaces(), handler);
		System.out.println(proxyBean);
		System.out.println(proxyBean.getSimpleName());
	}

	class Handler implements InvocationHandler {
		private SimpleJavaBean worker;

		public Handler(SimpleJavaBean worker) {
			this.worker = worker;
		}

		public Object invoke(Object obj, Method mtd, Object[] args)
				throws Throwable {
			Object res = null;
			res = mtd.invoke(worker, args);
			return res;
		}
	}

	/**
	 * dynamic proxy by cglib
	 */
	@Test
	public void testCglibProxy() {
		Enhancer enhancer = new Enhancer();
		MethodInterceptor methodInterceptor = new SimpleMethodInterceptor();
		enhancer.setSuperclass(SimpleJavaInterface.class);
		// 回调方法
		enhancer.setCallback(methodInterceptor);
		// 创建代理对象
		SimpleJavaInterface interFace = (SimpleJavaInterface) enhancer.create();
		System.out.println("[proxy interface:]" + interFace);
		System.out.println("interface property:" + interFace.num);

		Enhancer enhancer1 = new Enhancer();
		enhancer1.setSuperclass(SimpleJavaBean.class);
		enhancer1.setCallback(methodInterceptor);
		SimpleJavaBean bean = (SimpleJavaBean) enhancer1.create();
		System.out.println("[proxy bean:]" + bean);
		String simpleName = bean.getSimpleName();
		System.out.println("bean method:" + simpleName);

		Enhancer enhancer2 = new Enhancer();
		enhancer2.setSuperclass(SimpleAbstractJavaBean.class);
		enhancer2.setCallback(methodInterceptor);
		SimpleAbstractJavaBean abstracBean = (SimpleAbstractJavaBean) enhancer2
				.create();
		System.out.println("[proxy abstract bean:]" + bean);
		String name = abstracBean.str;
		System.out.println("bean method:" + name);
		// abstracBean.hello("");//errror
	}

	class SimpleMethodInterceptor implements MethodInterceptor {

		public Object intercept(Object obj, Method m, Object[] args,
				MethodProxy proxy) throws Throwable {
			return proxy.invokeSuper(obj, args);
		}

	}

}
