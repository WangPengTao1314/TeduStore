package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;

public class TestAddr {
	private AddressMapper am;
	private IAddressService as;
	@Before
	public void init() {
		ApplicationContext ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml","spring-service.xml");
		am=ac.getBean("addressMapper",AddressMapper.class);
		as=ac.getBean("addressService",IAddressService.class);
	}
	
	
	
	/*************************************************持久层************************************************************/
	@Test
	public void testDelById() {
		am.deleteById(3);
	}
	
	
	
	@Test
	public void testUpdateById() {
		Address address=new Address();
		address.setId(2);
		address.setRecvName("张三");
		address.setRecvArea("中天");
		address.setRecvProvince("河北省");
		address.setRecvCity("石家庄");
		address.setRecvAddress("河北");
		address.setRecvPhone("13500000000");
		am.updateById(address);
		System.out.println(am.selectAddressById(2));
	}
	
	
	
	@Test
	public void testSelectById() {
		System.out.println(am.selectAddressById(1));
	}
	
	
	
	
	@Test
	public void testDefault() {
		System.out.println(am.setCancel(2));
	}
	
	@Test
	public void testCancel() {
		
	}
	
	@Test
	public void testSelectByUid() {
		System.out.println(am.selectByUid(2));
	}

	@Test
	public void testaddins() {
		Address address=new Address();
		address.setUid(2);
		address.setRecvName("小王");
		address.setRecvProvince("130000");
		address.setRecvCity("130100");
		address.setRecvArea("130102");
		address.setRecvDistrict("河北省石家庄市长安区");
		address.setRecvAddress("中鼎大厦");
		address.setRecvPhone("13800138000");
		am.insert(address);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***************************************************业务层**********************************************************/
	@Test
	public void testUpById() {
		Address address=new Address();
		address.setId(2);
		address.setRecvName("张三");
		address.setRecvArea("中天");
		address.setRecvProvince("河北省");
		address.setRecvCity("石家庄");
		address.setRecvAddress("河北");
		address.setRecvPhone("13500000000");
		as.updateAddressById(address);
		System.out.println(am.selectAddressById(2));
	}
	
	@Test
	public void testById() {
		System.out.println(as.getAddressById(1));
	}
	
	
	@Test
	public void testSetDef() {
		as.setDefault(2, 5);
	}
	
	@Test
	public void testGetAddressByUid() {
		System.out.println(as.getAddressByUid(2).size());
	}

	@Test
	public void testAddAddress() {
		Address address= new Address();
		address.setUid(2);
		address.setRecvName("admin");
		address.setRecvProvince("130000");
		address.setRecvCity("130100");
		address.setRecvArea("130102");
		address.setRecvAddress("中鼎大厦");
		address.setRecvPhone("13000002222");
		as.addAddress(address);
	}
}
