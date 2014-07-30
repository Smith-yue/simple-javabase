package org.simple.javabase.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class FooServiceImpl2{
	
	@Autowired
	private FooServiceImpl3 fooService3;
	
	@Autowired
	private JdbcTemplate template;

	public Foo getFoo(String fooName) {
		throw new UnsupportedOperationException();
	}

	public Foo getFoo(String fooName, String barName) {
		throw new UnsupportedOperationException();
	}

	@Transactional
	public void insertFoo(Foo foo) {
		boolean isNewTransAction=TransactionAspectSupport.currentTransactionStatus().isNewTransaction();
		System.out.println(" FoolServiceImpl2 insertFoo() method is new Transaction :"+isNewTransAction);
		//RuntimeException 时roll back成功
		throw new UnsupportedOperationException();
		//checkedException 捕获 时正常提交
		// try {
		// throw new IllegalAccessException();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
	}
//	@Transactional
	public void test(){
		template.execute("insert into exam_user values(2,'test2')");
		fooService3.insertFoo(new Foo());
	}

	public void updateFoo(Foo foo) {
		throw new UnsupportedOperationException();
	}

}
