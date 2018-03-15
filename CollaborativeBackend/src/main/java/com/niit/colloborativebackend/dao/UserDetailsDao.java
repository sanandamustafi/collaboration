package com.niit.colloborativebackend.dao;

import java.util.List;

import com.niit.colloborativebackend.model.UserDetails;

public interface UserDetailsDao {
	
		public boolean saveUserDetails(UserDetails userdetails);
		public boolean updateUserDetails(UserDetails userdetails);
		public boolean deleteUserDetails(UserDetails userdetails);
		public List<UserDetails> getAllUserDetails();
		public UserDetails authenticateUser(String userId,String userPassword);
		public UserDetails getUserByUserId(String userId);

	}

