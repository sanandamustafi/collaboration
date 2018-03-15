package com.niit.colloborativebackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloborativebackend.dao.FriendDao;
import com.niit.colloborativebackend.model.Friend;

import com.niit.colloborativebackend.model.UserDetails;
@RestController

public class FriendController {
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDao friendDao;
	@GetMapping(value = "/myFriends")
	public ResponseEntity<List<Friend>> myFriends(HttpSession session) {
		

		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		List<Friend> myFriends = friendDao.getMyFriends(loggedInUser.getUserId());
		
		return new ResponseEntity<List<Friend>> (myFriends, HttpStatus.OK);

	}

	@PostMapping(value = "/addFriend/{friendId}")			
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("N");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.save(friend);
		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	@PutMapping(value = "/unFriend/{friendId}")			
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friendId, HttpSession session) {
		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("U");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	@PutMapping(value = "/rejectFriend/{friendId}")				
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("R");	// N = New, A = Accepted, R = Rejected, U = Unfriend  
		friendDao.update(friend);
		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	@PutMapping(value = "/acceptFriend/{friendId}")			
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("A");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	@GetMapping(value = "/newFriendRequests")			
	public ResponseEntity<Friend> newFriendRequests(HttpSession session) {
		
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		friendDao.getNewFriendRequests(loggedInUser.getUserId());
		
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}	




}
