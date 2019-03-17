package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface AddressMapper {
	//删除收获地址
	void deleteById(Integer id);
	//通过id来修改收获地址的其他信息
	void updateById(Address address);
	//通过id查询收货地址信息
	Address selectAddressById(Integer id);
	
	Integer setDefault(Integer id);
	
	Integer setCancel(Integer uid);
	//查询登录人的收获地址，如果存在收获地址，list结合size()>0,否则=0
	List<Address> selectByUid(Integer id);
	//插入地址信息
	void insert(Address address);

}
