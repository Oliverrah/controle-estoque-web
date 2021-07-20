package com.made4you.controle.web.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.made4you.controle.web.dao.UserDAO;
import com.made4you.controle.web.entities.Role;
import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.user.CtrlUser;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Override
	@Transactional
	public User findByUserName(String username) {
		// check the user
		return userDAO.findByUserName(username);
	}
	

	@Override
	@Transactional
	public User findById(int id) {
		
		return userDAO.findById(id);
	}
	
	@Override
	@Transactional
	public void save(CtrlUser ctrlUser) {
		User user = new User();

		// assing user details to the user object

		user.setUsername(ctrlUser.getUsername());
		user.setPassword(passwordEncoder.encode(ctrlUser.getPassword()));
		user.setFirstName(ctrlUser.getFirstName());
		user.setLastName(ctrlUser.getLastName());
		user.setEmail(ctrlUser.getEmail());

		// give user default role of "ADM"
		user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

		// save user to database

		userDAO.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}



}
