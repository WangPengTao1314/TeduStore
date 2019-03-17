package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.UserService;

public class TestUser {
	private IUserService us;
	private UserMapper um;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml"
				,"spring-service.xml");
		um=ac.getBean("userMapper",UserMapper.class);
		us=ac.getBean("userService",IUserService.class);
	}
	
	/*******************************控制器层测试*****************************************/
	
	
	/*******************************持久层测试*****************************************/
	
	
	@Test
	public void testSelectById() {
		User user=um.selectById(3);
		System.out.println(user);
	}

	@Test
	public void testUpdate() {
		User user=new User();
		user.setId(1);
		user.setPassword("222222");
		user.setGender(1);
		user.setEmail("wanghua@tedu.cn");
		user.setPhone("13801138000");
		um.update(user);
		System.out.println(user);
	}
	
	@Test
	public void textSelectByEmail() {
		System.out.println(um.selectByEmail("admin@tedu.cn"));
	}
	
	@Test
	public void testSelectByUsername() {
		User user=um.selectByUsername("admin123");
		System.out.println(user);
	}

	
	@Test
	public void testInsert() {
		
		User user=new User();
		user.setUsername("admin123");
		user.setPassword("123456");
		user.setEmail("11567@tt.com");
		user.setPhone("13800138000");
		um.insert(user);
	}
	
	
	

	/*******************************业务层测试*****************************************/
	@Test
	public void testGetUserById() {
		User user=us.getUserById(2);
		System.out.println(user);
	}
	
	
	@Test
	public void testUpdateUser() {
		
		us.updateUser(2, "admin1222", null,"admin111@tedu.cn" , null);
	}
	
	@Test
	public void testChagePassword() {
			us.changePassword(100, "111111", "111222");
		
	}

	@Test
	public void testLogin() {
		System.out.println(us.login("admin23", "123456"));
	}

	@Test
	public void testCheckUsername() {
		System.out.println(us.checkUsername("admin123"));
	}
	
	
	@Test
	public void testCheckPhone() {
		
		System.out.println(us.checkPhone("13800138000"));
	}
	
	@Test
	public void testCheckEmail() {
		
		System.out.println(us.checkEmail("11567@tt.com"));
	}
	
	@Test
	public void testRegister() {
		
		User user=new User();
		user.setUsername("admin123");
		user.setPassword("123456");
		user.setEmail("11567@tt.com");
		user.setPhone("13800138000");
		us.register(user);
	}
	
	
	

}
