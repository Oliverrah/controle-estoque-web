package com.made4you.controle.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.made4you.controle.web.entity.User;
import com.made4you.controle.web.user.CtrlUser;

public interface UserService  extends UserDetailsService{

	User findByUserName(String username);
	public User findById(int id);
	public void save(CtrlUser ctrlUser);
}
