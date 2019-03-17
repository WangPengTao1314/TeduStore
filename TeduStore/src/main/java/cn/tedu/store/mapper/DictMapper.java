package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface DictMapper {
	//通过编号获取省的名称
	String selectProvinceNameByCode(String provinceCode);
	//通过城市的编号来获取城市的名称
	String selectCityNameByCode(String cityCode);
	//通过区县的编号来获取名称
	String selectAreaNameByCode(String areaCode);
	
	
	List<Area> selectArea(String cityCode);
	//查询城市信息封装成city对象
	List<City> selectCity(String provinceCode);
	//查询省信息，封装成province对象，通过list返回
	List<Province> selectProvince();
}
