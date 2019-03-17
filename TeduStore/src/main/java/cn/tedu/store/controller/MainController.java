package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.GoodsCategoryService;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@RequestMapping("/main")
@Controller
public class MainController {
	
	
	
	@Resource
	private IGoodsCategoryService gcService;
	@Resource
	private IGoodsService goodsService;
	
	@RequestMapping("/showIndex.do")
	public String showIndex(ModelMap map) {
		List<GoodsCategory> computerList=new ArrayList<GoodsCategory>();
		computerList=gcService.getCategoryByParentId(161, 0, 3);
		List<List<GoodsCategory>> category161List=new ArrayList<List<GoodsCategory>>();
		for(GoodsCategory gc:computerList) {
			category161List.add(gcService.getCategoryByParentId(gc.getId(), null, null));
		}
		map.addAttribute("computerList",computerList);
		
		map.addAttribute("category161List",category161List);
		
		//设置热门商品到map中
		List<Goods> goodsList=goodsService.getGoodsByCategoryId(163,0,3);
		map.addAttribute("goodsList",goodsList);
		return "index";
	}

}
