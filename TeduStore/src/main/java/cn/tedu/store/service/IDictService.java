package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface IDictService {
	
	List<Area> getArea(String cityCode);
	List<City> getCity(String provinceCode);
	//返回province的list集合
	List<Province> getProvince();

}
