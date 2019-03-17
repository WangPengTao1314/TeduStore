package cn.tedu.store.service;

import cn.tedu.store.bean.User;

public interface IUserService {
	//通过id获取user对象
	User getUserById(Integer id);
	
	void updateUser(Integer id,String username,Integer gender,String email,String phone);
	//注册功能的方法 user 封装了页面数据的对象
	void register(User user);
	/*
	 * 用来验证邮箱是否存在，如果存在返回true,否则返回false
	 */
	public boolean checkEmail(String email);
	/*
	 * 电话号码验证
	 */
	boolean checkPhone(String phone);
	boolean checkUsername(String username);
	User login(String username,String password);
	//修改密码功能
	void changePassword(Integer id,String oldPwd,String newPwd);

}
