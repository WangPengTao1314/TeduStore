package cn.tedu.store.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.ClassNameAlreadyExistException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNameAlreadyExsitException;
import cn.tedu.store.service.ex.UserNotFoundException;

@Service
public class UserService implements IUserService{
	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	public void register(User user) {
		//判断用户名是否存在，如果存在，抛出异常，否则，实现插入数据的操作
		if(userMapper.selectByUsername(user.getUsername())==null) {
			userMapper.insert(user);
		}else {
			throw new ClassNameAlreadyExistException("用户名已经存在");
		}
	}
	public boolean checkEmail(String email) {
		/*
		 * 如果邮箱存在，返回true,否则返回false
		 */
		
		return userMapper.selectByEmail(email)>0;
	}
	public boolean checkPhone(String phone) {
		
		return userMapper.selectByPhone(phone)>0;
	}
	public boolean checkUsername(String username) {
		//调用selectByUsername(username)
		User user=userMapper.selectByUsername(username);
		//判断返回值是否为null
		if(user==null) {
			//如果为null,返回false
			return false;
		}else {
			//否则返回true
			return true;
		}
		
	}
	public User login(String username, String password) {
		
		User user=userMapper.selectByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("此用户尚未注册");
		}else{
			if(user.getPassword().equals(password)) {
				return user;
			}else {
				throw new PasswordNotMatchException("密码不匹配");
			}
		}
		
	}
	public void changePassword(Integer id, String oldPwd, String newPwd) {
		User user;
		user=userMapper.selectById(id);
		if(user!=null) {
			if(user.getPassword().equals(oldPwd)) {
				user.setId(id);
				user.setPassword(newPwd);
				userMapper.update(user);
			}else {
				throw new PasswordNotMatchException("密码错误");
			}
		}else {
			throw new UserNotFoundException("没有此用户");
		}
		
	}
	public void updateUser(Integer id, String username, Integer gender, String email, String phone) {
		User user=new User();
		User u1=userMapper.selectByUsername(username);
		if(u1==null) {
			user.setUsername(username);
		}else {
			//用户名存在
			User u2=userMapper.selectById(id);
			//不修改登录的用户名
			if(u2.getUsername().equals(username)) {
				
			}else {
				throw new UserNameAlreadyExsitException("该帐号已经存在");
				
			}
				
		}
		user.setId(id);
		user.setGender(gender);
		user.setEmail(email);
		user.setPhone(phone);
		userMapper.update(user);
		
	}
	public User getUserById(Integer id) {
		//返回user对象
		return userMapper.selectById(id);
	}
	
	

}
