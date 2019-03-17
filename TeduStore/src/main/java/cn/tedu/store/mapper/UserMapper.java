package cn.tedu.store.mapper;

import cn.tedu.store.bean.User;

/*
 * 对用户管理模块的持久层完成数据库的操作
 */
public interface UserMapper {
	
	/*
	 * 通过id来完成查询的功能
	 * 如果id存在返回对象，否则返回null
	 */
	User selectById(Integer id);
	
	/*
	 * 向数据库插入数据
	 */
	void insert(User user);
	/*
	 * 通过用户名查询
	 * username:用户名
	 * 如果查询数据库有用户名，返回user对象；如果没有用户名，返回null
	 */
	User selectByUsername(String username);
	/*
	 * 通过email查询，如果email存在返回1，否则返回0
	 */
	Integer selectByEmail(String email);
	
	Integer selectByPhone(String phone);
	
	void update(User user);
	

}
