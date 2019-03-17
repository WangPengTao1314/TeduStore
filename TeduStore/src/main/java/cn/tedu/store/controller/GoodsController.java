package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.service.IGoodsService;

@RequestMapping("/goods")
@Controller
public class GoodsController extends BaseController {
	@Resource
	private IGoodsService goodsService;
	
	@RequestMapping("/showSearch.do")
	public String showSearch(Integer page,Integer categoryId,ModelMap map){
	//从index.jsp超链接进入控制器，page为null
		//应该显示第一页的数据
		if(page==null) {
			page=1;
	     }
		/**
		 * limit  offset,count;
		 * offset:偏移量；
		 * count:偏移数量；
		 * counts:总页数；
		 * 
		 * 
		 */
		//偏移量的算法
	int offset=(page-1)*12;
	List<Goods>	goodsList=goodsService.getGoodsByCategoryId(categoryId, offset, 12);
	int counts=goodsService.getCount(categoryId);
	//在map中设置页数
	int pages=counts%12==0?counts/12:counts/12+1;
	
	
	map.addAttribute("pages", pages);
	//在map设置记录数
	map.addAttribute("count", counts);
	//把集合设置到map
	map.addAttribute("goodsList", goodsList);
	map.addAttribute("categoryId", categoryId);
	//
	map.addAttribute("currentPage", page);
	return "search";
	
	}
	/*<div  align="right" style="margin-right: 80px">
    共${count}种商品，共${pages}页|
    <c:forEach var="i" begin="1" end="${pages}">
      <a 
      href="../goods/showSearch.do?categoryId=${categoryId}&page=${i}"
      <c:if test="${currentPage==i}">
      style="color:#ff0000"
      </c:if>
      >
      ${i}
      </a>
    </c:forEach>
  </div>*/
	

}
