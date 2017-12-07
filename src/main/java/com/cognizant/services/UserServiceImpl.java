package com.cognizant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.dao.UserDAO;
import com.cognizant.model.User;


@Service
public class UserServiceImpl implements UserService {

	// @Autowired
	// private UserRepository userRepository;
	// @Autowired
	// private RoleRepository roleRepository;
	@Autowired
	UserDAO userDAO;

/*	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// user.setRoles(new HashSet<>(roleRepository.findAll()));
		//userRepository.save(user);
	} */

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findByUsername(username);
	}

}
