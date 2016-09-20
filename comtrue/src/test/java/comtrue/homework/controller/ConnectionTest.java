package comtrue.homework.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ConnectionTest {

	@Inject
	DataSource ds;
	
	@Inject
	SqlSessionFactoryBean sessionfactory;
	
	@Inject
	SqlSession session;
	
	@Test
	public void connectionTest() throws SQLException{
		 Connection con = ds.getConnection();
		 PreparedStatement prst = con.prepareStatement("select now()");
		 ResultSet rs = prst.executeQuery();
		 rs.next();
		 System.out.println(rs.getString(1));
		 con.close();
		 
	}
	
	@Test
	public void sessionfactoryTest() throws Exception{
		System.out.println(sessionfactory);
	}
	
	@Test
	public void sessionTest() throws Exception{
		System.out.println(session.selectOne("org.zerock.persistence.BoardMappers.Time"));		
	}
	
	
	
	
}
