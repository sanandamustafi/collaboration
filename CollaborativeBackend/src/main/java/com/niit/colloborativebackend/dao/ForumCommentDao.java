package com.niit.colloborativebackend.dao;

import java.util.List;

import com.niit.colloborativebackend.model.ForumComment;

public interface ForumCommentDao {
	public boolean save(ForumComment forumComment);
	public boolean update(ForumComment forumComment);
	public boolean saveOrUpdate(ForumComment forumComment) ;
	public boolean delete(ForumComment forumComment) ;
	public ForumComment get(int id) ;
	public List<ForumComment> list(int id) ;
	
}
