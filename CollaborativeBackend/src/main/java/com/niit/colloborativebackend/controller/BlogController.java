package com.niit.colloborativebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloborativebackend.dao.BlogDao;
import com.niit.colloborativebackend.model.Blog;

@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listBlogs() {
		List<Blog> blog = blogDao.getAllBlogs();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(blog,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/", method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		if(blogDao.getBlogByBlogId(blog.getBlogId()) == null) {
			blogDao.saveBlog(blog);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		blog.setErrormessage("Blog already exist with id : " +blog.getBlogId());
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		if(blogDao.getBlogByBlogId(id) == null) {
			blog = new Blog();
			blog.setErrormessage("No blog exist with id : " +blog.getBlogId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDao.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id) {
		Blog blog = blogDao.getBlogByBlogId(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrormessage("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDao.deleteBlog(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);		
	}	
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		Blog blog = blogDao.getBlogByBlogId(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrormessage("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}	
		
	@RequestMapping(value = "/approvedblogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> ApprovedlistBlogs() {
		List<Blog> blog = blogDao.getAllApprovedBlog();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(blog,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);	
		
	}
	@PutMapping(value = "/approveBlog/{id}")				
	public ResponseEntity<Blog> approveBlog( @PathVariable("id") int id,@RequestBody Blog blog) {
		System.out.println("AAA");
		
		Blog blog1=blogDao.getBlogByBlogId(id);
		//blog1.setStatus(blog.getStatus());
		  blog1.setBlogStatus("A");	// A = Approve, R = Reject, N = New
		blogDao.updateBlog(blog1);
		
		
		return new ResponseEntity<Blog> (blog1, HttpStatus.OK);
	}

}




