package com.niit.colloborativebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloborativebackend.dao.BlogCommentDao;
import com.niit.colloborativebackend.model.BlogComment;
import com.niit.colloborativebackend.model.ForumComment;


@RestController
public class BlogCommentController {
	@Autowired
	BlogCommentDao blogCommentDao;
	@GetMapping(value = "/blogComments")
	public ResponseEntity<List<BlogComment>> listForumComments() {
		List<BlogComment> blogComment = blogCommentDao.list();
		if(blogComment.isEmpty()) {
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogComment>>(blogComment, HttpStatus.OK);
	}


@PostMapping(value = "/blogComment/")
public ResponseEntity<BlogComment> createBlogComment(@RequestBody BlogComment blogComment) {
	if(blogCommentDao.get(blogComment.getBlogId()) == null) {
		blogCommentDao.save(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	blogComment.setErrormessage("BlogComment already exist with id : " +blogComment.getBlogId());
	return new ResponseEntity<BlogComment>(HttpStatus.OK);
}

@PutMapping(value = "/blogComment/{id}")
public ResponseEntity<BlogComment> updateBlogComment(@PathVariable("id") int id, @RequestBody BlogComment blogComment) {
	if(blogCommentDao.get(id) == null) {
		blogComment = new BlogComment();
		blogComment.setErrormessage("No BlogComment exist with id : " +blogComment.getBlogId());
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
	}
	blogCommentDao.update(blogComment);
	return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
}
@DeleteMapping(value = "/blogComment/{id}")
public ResponseEntity<BlogComment> deleteForumComment(@PathVariable("id") int id) {
	BlogComment blogComment = blogCommentDao.get(id);
	if(blogComment == null) {
		blogComment = new BlogComment();
		blogComment.setErrormessage("No BlogComment exist with id : " + id);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
	}
	blogCommentDao.delete(blogComment);
	return new ResponseEntity<BlogComment>(HttpStatus.OK);		
}
@GetMapping(value = "/blogComment/{id}")
public ResponseEntity<BlogComment> getForumComment(@PathVariable("id") int id) {
	BlogComment blogComment = blogCommentDao.get(id);
	if(blogComment == null) {
		blogComment = new BlogComment();
		blogComment.setErrormessage("No BlogComment exist with id : " + id);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
}

}
