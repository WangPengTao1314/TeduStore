package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Goods;

public interface IGoodsService {
	//获取记录数
	Integer getCount(Integer categoryId);
	//获取商品的集合
	List<Goods> getGoodsByCategoryId(Integer categoryId,Integer offset,Integer count);

}
