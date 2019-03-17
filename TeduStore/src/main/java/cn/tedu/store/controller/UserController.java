package cn.tedu.store.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource(name="userService")
	private IUserService ius;
	
	//显示页面
	@RequestMapping("showRegister.do")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("showLogin.do")
	public String showLogin() {
		return "login";
	}
	@RequestMapping("/exit.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:../main/showIndex.do";
	}
	@RequestMapping("/showPassword.do")
	public String showPassword() {
		return "personal_password";
	}
	
	//显示个人信息
	@RequestMapping("/showPersonInfo.do")
	public String showPersonInfo() {
		return "personInfo";
	}
	
	/*
	 * 修改个人信息
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updateUser(HttpSession session,String username,String phone,String email,Integer gender){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		try {
			User user=(User)session.getAttribute("user");
			session.setAttribute("user", ius.getUserById(this.getId(session)));
			Integer id=0;
			if(user!=null) {
				id=user.getId();
			}
			ius.updateUser(this.getId(session), username, gender, email, phone);
			rr.setState(1);
			rr.setMessage("用户修改成功");
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage("用户修改失败");
		}
		return rr;
		
	}
	
	
	
	@RequestMapping("/password.do")
	@ResponseBody
	public ResponseResult<Void> password(HttpSession session,String oldPwd,String newPwd){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		try {
			User user=(User)session.getAttribute("user");
			Integer id=0;
			if(user!=null) {
				id=user.getId();
			}
			ius.changePassword(id, oldPwd, newPwd);
			rr.setState(1);
			rr.setMessage("密码修改成功");
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username,String password,HttpSession session){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		try {
			User user=ius.login(username, password);
			//登录成功之后，user对象存到session中
			session.setAttribute("user", user);
			rr.setState(1);
			rr.setMessage("登录成功");
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	

	
	/*
	 * 验证用户名是否可用
	 */
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String uname){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		if(ius.checkUsername(uname)) {
			rr.setState(0);
			rr.setMessage("用户名已经存在");
		}else {
			rr.setState(1);
			rr.setMessage("用户名可以使用");
		}
		return rr;
	}
	
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		if(ius.checkEmail(email)) {
			rr.setState(0);
			rr.setMessage("邮箱已存在");
		}else {
			rr.setState(1);
			rr.setMessage("邮箱可以使用");
		}
		return rr;
	}
	
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		if(ius.checkPhone(phone)) {
			rr.setState(0);
			rr.setMessage("电话已被占用");
		}else {
			rr.setState(1);
			rr.setMessage("电话可以使用");
		}
		return rr;
	}
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public ResponseResult<Void> register(
			@RequestParam("uname")String username,
			@RequestParam("upwd")String password,
			String email,String phone){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		try {
			ius.register(user);
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage("用户名已经存在");
		}
		return rr;
		
	}
/*~~~~~~~~~~~~~~~~~~~图片或文档的下载~~~~~~~~~~~~~~~~~~~~~*/
	// UserController
		@RequestMapping(value="/img.do",
				produces="image/png")
		@ResponseBody
		public byte[] image( 
				HttpServletResponse response)
			throws IOException{
			//@ResponseBody 与 返回值byte[] 配合时候
			//Spring MVC会将byte[]数组填充到响应
			//的消息正文中发送到浏览器
			String file = 
				URLEncoder.encode("演示.png", "utf-8");
			//还需要指定两个响应头 
			//Content-Type ,
			//Content-Disposition: attachment; filename="fname.ext"
			response.setContentType("image/png");
			response.setHeader(
					"Content-Disposition", 
					"attachment; filename=\""+file+"\""); 
			byte[] body = createImage();
			//response.setContentLength(body.length);
			return body;
		}
		//创建一个图片
		private byte[] createImage()
			throws IOException{
			BufferedImage img=
					new BufferedImage(100, 50, 
					BufferedImage.TYPE_3BYTE_BGR);
			img.setRGB(50, 25, 0xffffff);
			//out相当于酱油瓶
			ByteArrayOutputStream out=
					new ByteArrayOutputStream();
			//将图片的数据导入酱油瓶out
			ImageIO.write(img, "png", out);
			out.close();//关闭out
			//将酱油瓶out中的数据到出来
			byte[] bytes = out.toByteArray();
			return bytes;
		}
	
}
