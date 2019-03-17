package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.GoodsCategory;

public interface IGoodsCategoryService {
	//获取商品的分类信息
	List<GoodsCategory> getCategoryByParentId(Integer parentId,Integer offset,Integer count);

}
