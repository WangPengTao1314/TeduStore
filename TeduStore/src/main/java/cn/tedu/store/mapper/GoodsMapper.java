package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Goods;

public interface GoodsMapper {
	//查询记录数
	Integer selectCount(Integer categoryId);
	//查询热门商品
	List<Goods> selectBy(
			@Param("categoryId")Integer categoryId,
			@Param("offset")Integer offset,
			@Param("count")Integer count);

}
