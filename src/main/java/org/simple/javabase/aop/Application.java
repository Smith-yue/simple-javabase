package org.simple.javabase.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 基于spring aop的事务管理配置
 * @author shiya
 *
 */
public class Application {

	public static final String XML_PATH = "spring-aop-tx2.xml";

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				XML_PATH);

		FooServiceImpl service = (FooServiceImpl) context.getBean("fooService");
//		service.insertFoo(new Foo());	
		service.test2();
	}

}
