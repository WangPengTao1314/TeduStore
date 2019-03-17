package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.service.IDictService;

public class TestAdd {
	private DictMapper dm;
	private IDictService ds;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		dm=ac.getBean("dictMapper",DictMapper.class);
		ds=ac.getBean("dictService",IDictService.class);
	}
	
	/*****************************************************持久层****************************************************************/
	@Test
	public void testSelectProvinceNameByCode() {
		System.out.println(dm.selectProvinceNameByCode("140000"));
		System.out.println(dm.selectCityNameByCode("130100"));
		System.out.println(dm.selectAreaNameByCode("130102"));
	}
	
	@Test
	public void testSelectArea() {
		System.out.println(dm.selectArea("650000"));
	}
	
	@Test
	public void testSelectCity(){
		System.out.println(dm.selectCity("130000"));
	}
	
	@Test
	public void testSelectProvince() {
		
		System.out.println(dm.selectProvince());
	}


/*****************************************************业务层****************************************************************/
	@Test
	public void testGetArea() {
		System.out.println(ds.getArea("130100"));
	}
	
	@Test
	public void testGetCity() {
		System.out.println(ds.getCity("130000"));
	}

	@Test
	public void testGetProvince1() {
		System.out.println(ds.getProvince());
	}
		

}










