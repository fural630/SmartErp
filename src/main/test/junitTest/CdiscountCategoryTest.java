package junitTest;

import static org.junit.Assert.*;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartErp.system.dao.UserDao;

public class CdiscountCategoryTest {
	private static SqlSessionFactory sqlSessionFactory;  
	  
	@Test
	public void test() {
//	   ApplicationContext context = new ClassPathXmlApplicationContext("etc/spring/mysql.xml");
//	   sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
//	   SqlSession sqlSession = sqlSessionFactory.openSession();  
//	   UserDao userDao = sqlSession.getMapper(UserDao.class);  
//	   userDao.findAll();
		
		
		
	}
	
}
