package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface IAddressService {
	
	void deleteAddress(Integer id);
	//修改收货地址
	void updateAddressById(Address address);
	
	Address getAddressById(Integer id);
	
	void setDefault(Integer uid,Integer id);
	//获取登录用户的收货地址
	List<Address> getAddressByUid(Integer uid);
	//添加收获地址
	void addAddress(Address address);

}
