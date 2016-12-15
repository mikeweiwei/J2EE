package com.mydy.gyw.dao;

import com.mydy.gyw.bean.Gyw_user;

public interface UserDao {
	//验证用户是否存在 返回boolean
	public boolean QueryUser(Gyw_user user);
	//添加user
	public boolean insertUser(Gyw_user user);
	//删除user
	public boolean deleteUser(String name);
	//修改USer
	public boolean modify(Gyw_user user);
	
}
