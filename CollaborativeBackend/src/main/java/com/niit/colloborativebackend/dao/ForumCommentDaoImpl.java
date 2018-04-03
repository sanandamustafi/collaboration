package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloborativebackend.model.ForumComment;

@EnableTransactionManagement
@Repository(value="forumCommentDao")

public class ForumCommentDaoImpl implements ForumCommentDao{
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public boolean save(ForumComment forumComment){
		try {
			
			sessionFactory.getCurrentSession().save(forumComment);
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(ForumComment forumComment){
		try {
			
			sessionFactory.getCurrentSession().update(forumComment);
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean saveOrUpdate(ForumComment forumComment) {
		try {
			
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean delete(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}



@Transactional
public ForumComment get(int id) {
	
	String hql = "from ForumComment where id = " + "'" + id + "'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings("unchecked")
	List<ForumComment> list = query.list();
	
	if(list != null && !list.isEmpty()) {
		
		return list.get(0);
	}
	else {
		return null;
	}
}
@SuppressWarnings("unchecked")
@Transactional
public List<ForumComment> list(int id) {
	
	String hql = "from ForumComment where forumId="+id;
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
	return query.list();
}	

}



