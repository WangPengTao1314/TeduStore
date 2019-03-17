package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;
@Service
public class AddressService implements IAddressService{
	@Resource
	private AddressMapper addrMapper;
	@Resource
	private DictMapper dictMapper;
	
	
	public void addAddress(Address address) {
		//设置省市区名称(从address中获取省市区的code,调用dictMapper中提供的方法，
		//获取到code对应的name,再把3个name用+连接成新串，把此字符串设置到下边的方法)
		String proCode=dictMapper.selectProvinceNameByCode(address.getRecvProvince());
		String cityCode=dictMapper.selectCityNameByCode(address.getRecvCity());
		String areaCode=dictMapper.selectAreaNameByCode(address.getRecvArea());
		address.setRecvDistrict(proCode+cityCode+areaCode);
		
		List<Address> list=addrMapper.selectByUid(address.getId());
		if(list.size()>0) {
			address.setIsDefault(0);
		}else {
			address.setIsDefault(1);
		}
		
		addrMapper.insert(address);
	}


	public List<Address> getAddressByUid(Integer uid) {
		List<Address> list=addrMapper.selectByUid(uid);
		return list;
	}


	public void setDefault(Integer uid, Integer id) {
		Integer n1=addrMapper.setCancel(uid);
		if(n1==0) {
			throw new RuntimeException("修改失败");
		}
		Integer n2=addrMapper.setDefault(id);
		if(n2==0) {
			throw new RuntimeException("修改失败");
		}
		
		
	}


	public Address getAddressById(Integer id) {
		
		return addrMapper.selectAddressById(id);
	}


	public void updateAddressById(Address address) {
		String proCode=dictMapper.selectProvinceNameByCode(address.getRecvProvince());
		String cityCode=dictMapper.selectCityNameByCode(address.getRecvCity());
		String areaCode=dictMapper.selectAreaNameByCode(address.getRecvArea());
		address.setRecvDistrict(proCode+cityCode+areaCode);
		
		addrMapper.updateById(address);
	}


	public void deleteAddress(Integer id) {
		addrMapper.deleteById(id);
		
	}

}
