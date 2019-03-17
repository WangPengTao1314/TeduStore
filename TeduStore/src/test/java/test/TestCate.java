package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.IGoodsCategoryService;


public class TestCate {
	
	private GoodsCategoryMapper gm;
	private IGoodsCategoryService ics;
	
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		gm=ac.getBean("goodsCategoryMapper",GoodsCategoryMapper.class);
		ics=ac.getBean("goodsCategoryService",IGoodsCategoryService.class);
	}
	
	
	
	/*********************************持久层***********************************/
	
	@Test
	public void testSelectCategoryByParentId() {

		System.out.println(gm.selectCategoryByParentId(161, 0, 3).size());
		
	}
	
	
	
	
	/*********************************业务层***********************************/
	@Test
	public void testCategoryBp() {
		System.out.println(ics.getCategoryByParentId(161, 0, 3));
	}
	

}
