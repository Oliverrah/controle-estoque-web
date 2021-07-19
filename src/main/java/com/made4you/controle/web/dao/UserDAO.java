package com.made4you.controle.web.dao;

import com.made4you.controle.web.entity.User;

public interface UserDAO {
	
	User findByUserName(String username);	
	public User findById(int id);
	public void save(User user);
}
