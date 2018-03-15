package com.niit.colloborativebackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloborativebackend.dao.UserDetailsDao;
import com.niit.colloborativebackend.model.UserDetails;

@RestController
	public class UserController {

		@Autowired
		UserDetailsDao userdetailsDao;
		@RequestMapping(value = "/test", method = RequestMethod.GET)
		public String test() {
			return "Test";
		}
		
		
		@RequestMapping(value = "/userdetails", method = RequestMethod.GET)
		public ResponseEntity<List<UserDetails>> listUsers() {
			List<UserDetails> userdetails = userdetailsDao.getAllUserDetails();
			if(userdetails.isEmpty()) {
				return new ResponseEntity<List<UserDetails>>(userdetails,HttpStatus.NO_CONTENT);
			}
			System.out.println("kkkkkkkk");
			return new ResponseEntity<List<UserDetails>>(userdetails,HttpStatus.OK);
		}
		
		//	http://localhost:8081/BinderServer/user/
		@RequestMapping(value = "/userdetail/", method = RequestMethod.POST)
		public ResponseEntity<UserDetails> createUser(@RequestBody UserDetails userdetails) {
			if(userdetailsDao.getUserByUserId(userdetails.getUserId()) == null) {
				userdetailsDao.saveUserDetails(userdetails);
				return new ResponseEntity<UserDetails>(userdetails,HttpStatus.OK);
			}
			userdetails.setErrormessage("User already exist with id : " +userdetails.getUserId());
			return new ResponseEntity<UserDetails>(userdetails,HttpStatus.OK);
		}
		
		//	http://localhost:8081/BinderServer/user/sovan001
		@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
		public ResponseEntity<UserDetails> updateUser(@PathVariable("id") String id, @RequestBody UserDetails userdetails) {
			if(userdetailsDao.getUserByUserId(id) == null) {
				userdetails = new UserDetails();
				userdetails.setErrormessage("User does not exist with id : " +userdetails.getUserId());
				return new ResponseEntity<UserDetails>(userdetails, HttpStatus.NOT_FOUND);
			}
			userdetailsDao.updateUserDetails(userdetails);
			return new ResponseEntity<UserDetails>(userdetails, HttpStatus.OK);
		}
		
		//	http://localhost:8081/BinderServer/user/sovan001
		@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<UserDetails> deleteUser(@PathVariable("id") String id) {
			UserDetails userdetails = userdetailsDao.getUserByUserId(id);
			if(userdetails == null) {
				userdetails = new UserDetails();
				userdetails.setErrormessage("User does not exist with id : " + id);
				return new ResponseEntity<UserDetails>(userdetails, HttpStatus.NOT_FOUND);
			}
			userdetailsDao.deleteUserDetails(userdetails);
			return new ResponseEntity<UserDetails>(userdetails,HttpStatus.OK);		
		}
		
		//	http://localhost:8081/BinderServer/user/sovan001
		@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
		public ResponseEntity<UserDetails> getUser(@PathVariable("id") String id) {
			UserDetails userdetails = userdetailsDao.getUserByUserId(id);
			if(userdetails == null) {
				userdetails = new UserDetails();
				userdetails.setErrormessage("User does not exist with id : " + id);
				return new ResponseEntity<UserDetails>(userdetails, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserDetails>(userdetails, HttpStatus.OK);
		}
		
		//	http://localhost:8081/BinderServer/user/authenticate/
		@RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
		public ResponseEntity<UserDetails> authenticateUser(@RequestBody UserDetails userdetails, HttpSession session) {
			userdetails = userdetailsDao.authenticateUser(userdetails.getUserId(), userdetails.getUserPassword());
			if(userdetails == null) {
				userdetails = new UserDetails();
				userdetails.setErrormessage("Invalid userId or password...");
			}
			else {
				session.setAttribute("loggedInUser", userdetails);
				session.setAttribute("loggedInUserID", userdetails.getUserId());
			}
			return new ResponseEntity<UserDetails>(userdetails, HttpStatus.OK);
		}
	}





