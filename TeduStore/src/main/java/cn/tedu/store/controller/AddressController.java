package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IAddressService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	@Resource
	private IAddressService addrService;
	
	//显示addressAdmin.jsp页面
	/*
	 * 显示地址页面
	 */
	@RequestMapping("/showAddress.do")
	public String showAddress() {
		return "addressAdmin";
	}
	
	
	@RequestMapping("/getAddressById.do")
	@ResponseBody
	public ResponseResult<Address> getAddressById(Integer id){
		ResponseResult<Address> rr=new ResponseResult<Address>();
		Address addr=addrService.getAddressById(id);
		rr.setData(addr);
		return rr;
	}
	
	
	
	
	@RequestMapping("/getAddressByUid.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressByUid(HttpSession session){
		ResponseResult<List<Address>> rr=new ResponseResult<List<Address>>();
		List<Address> list=addrService.getAddressByUid(this.getId(session));
		System.out.println(":::::"+list.size());
		rr.setData(list);
		return rr;

	}
	
	 @RequestMapping("/addAddress.do")
		@ResponseBody
		public  ResponseResult<Void> addAddress(HttpSession session,
				@RequestParam("receiverName") String receiverName,
				@RequestParam("receiverState") String receiverState,
				@RequestParam("receiverCity") String receiverCity,
				@RequestParam("receiverDistrict") String receiverDistrict,
				@RequestParam("receiverAddress") String receiverAddress,
				@RequestParam(value="receiverMobile") String receiverMobile,
				@RequestParam(value="receiverPhone") String receiverPhone,
				@RequestParam("receiverZip") String receiverZip,
				@RequestParam("addressName") String recvTag){
			  ResponseResult<Void> rr=new ResponseResult<Void>();
			  System.out.println("------:"+receiverPhone);
			  Address address=new Address();
			  address.setUid(this.getId(session));
			  address.setRecvName(receiverName);
			  address.setRecvProvince(receiverState);
			  address.setRecvCity(receiverCity);
			  address.setRecvArea(receiverDistrict);
			  address.setRecvAddress(receiverAddress);
			  address.setRecvPhone(receiverMobile);
				address.setRecvTel(receiverPhone);
				address.setRecvZip(receiverZip);
				address.setRecvTag(recvTag);
			  
			  
				  addrService.addAddress(address);
				  rr.setState(1);
				  rr.setMessage("地址添加成功");
			  
			  return rr;
			


				}
	
	@RequestMapping("/updateAddress.do")
	@ResponseBody
	public  ResponseResult<Void> updateAddress(Integer id,
			@RequestParam("receiverName") String receiverName,
			@RequestParam("receiverState") String receiverState,
			@RequestParam("receiverCity") String receiverCity,
			@RequestParam("receiverDistrict") String receiverDistrict,
			@RequestParam("receiverAddress") String receiverAddress,
			@RequestParam(value="receiverMobile") String receiverMobile,
			@RequestParam(value="receiverPhone") String receiverPhone,
			@RequestParam("receiverZip") String receiverZip,
			@RequestParam("addressName") String recvTag){
		  ResponseResult<Void> rr=new ResponseResult<Void>();
		  System.out.println("------:"+receiverPhone);
		  Address address=new Address();
		  address.setId(id);
		  address.setRecvName(receiverName);
		  address.setRecvProvince(receiverState);
		  address.setRecvCity(receiverCity);
		  address.setRecvArea(receiverDistrict);
		  address.setRecvAddress(receiverAddress);
		  address.setRecvPhone(receiverMobile);
			address.setRecvTel(receiverPhone);
			address.setRecvZip(receiverZip);
			address.setRecvTag(recvTag);
		  
		  
			  addrService.updateAddressById(address);
			  rr.setState(1);
			  rr.setMessage("信息修改成功");
		  
		  return rr;
		


			}

	
	
	@RequestMapping("/setDefault.do")
	@ResponseBody
	public ResponseResult<Void> getDefault(HttpSession session,Integer id){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		try {
			addrService.setDefault(this.getId(session), id);
			rr.setState(1);
			rr.setMessage("修改成功");
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage("修改失败");
		}
		return rr;
		
		}
	
	
	@RequestMapping("/deleteAddress.do")
	@ResponseBody
	public ResponseResult<Void> deleteAddress(Integer id){
		//1.创建rr对象
		ResponseResult<Void> rr=new ResponseResult<Void>();
		//2.调用deleteAddress(id);
		addrService.deleteAddress(id);
		//3.rr 1 "删除成功"
		rr.setState(1);
		rr.setMessage("删除成功");
		return rr;
		}

}
