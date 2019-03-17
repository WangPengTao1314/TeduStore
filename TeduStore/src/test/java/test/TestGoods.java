package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

public class TestGoods {
	private GoodsMapper  gm;
	private IGoodsService igs;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		gm=ac.getBean("goodsMapper",GoodsMapper.class);
	  igs=ac.getBean("goodsService",IGoodsService.class);

	}
	
	
	/*************************************************持久层***********************************************/
	
	@Test
	public void testCount() {
		System.out.println(gm.selectCount(163));
	}
	
	@Test
	public void testselectBy() {
		System.out.println(gm.selectBy(163, 0, 3));
	}
	
	
	/***************************************************业务层*********************************************/
	@Test
	public void testSCount() {
		System.out.println(igs.getCount(163));
	}
	
	@Test
	public void testSelBy() {
		System.out.println(igs.getGoodsByCategoryId(163, 0, 3));
	}
	
	

}
