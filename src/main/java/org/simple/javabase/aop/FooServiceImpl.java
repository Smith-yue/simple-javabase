package org.simple.javabase.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class FooServiceImpl implements FooService {

	@Autowired
	private FooServiceImpl2 foolService2;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Foo getFoo(String fooName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Foo getFoo(String fooName, String barName) {
		throw new UnsupportedOperationException();
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void insertFoo(Foo foo) {
		boolean isNewTransAction = TransactionAspectSupport
				.currentTransactionStatus().isNewTransaction();
		System.out.println(TransactionAspectSupport.currentTransactionStatus());
		System.out.println(" insertFoo() method is new Transaction :"
				+ isNewTransAction);
		// RuntimeException 时roll back成功
		throw new UnsupportedOperationException();
		// checkedException 捕获 时正常提交
		// try {
		// throw new IllegalAccessException();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
	}

	@Transactional
	public void test() {
		try {
			// boolean isNewTransAction = TransactionAspectSupport
			// .currentTransactionStatus().isNewTransaction();
			// System.out
			// .println(" FooServiceImpl test() method is new Transaction :"
			// + isNewTransAction);
			System.out.println(TransactionAspectSupport
					.currentTransactionStatus());
			this.insertFoo(new Foo());

			/**
			 * catch RuntimeException 不会roll back，需要手动roll back
			 */
			// foolService2.insertFoo(new Foo());
		} catch (UnsupportedOperationException e) {
			System.out.println("failue");
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
	}

	@Override
	public void updateFoo(Foo foo) {
		throw new UnsupportedOperationException();
	}
//	@Transactional
	public void test2(){
		jdbcTemplate.execute("insert into exam_user values(1,'test1')");
		foolService2.test();
	}

}
