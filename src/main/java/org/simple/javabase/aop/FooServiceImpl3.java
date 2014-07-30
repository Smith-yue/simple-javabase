package org.simple.javabase.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FooServiceImpl3 {

	@Autowired
	private JdbcTemplate template;

	@Transactional
	public void insertFoo(Foo foo) {
		template.execute("insert into exam_user values(3)");
	}

}
