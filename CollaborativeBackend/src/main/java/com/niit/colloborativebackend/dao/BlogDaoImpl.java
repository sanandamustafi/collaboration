package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloborativebackend.model.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from Blog");
		List<Blog> blogList = query.list();
		return blogList;
	}

	public boolean saveBlog(Blog blog) {
		// TODO Auto-generated method stub
		
			Session session = getSession();
			try{
				blog.setBlogStatus("N");
					session.save(blog);  ///  insert into table 

					
					return true;
				}
					catch(HibernateException e)
					{ 
						e.printStackTrace();
					return false;
					}
		finally{
			session.flush();

			session.close();
		}
	}

	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		Session session = getSession();

		try{
			
					session.update(blog);  ///  insert into table 
					return true;
				}
					catch(HibernateException e)
					{ 
						e.printStackTrace();
					return false;
					}
		finally{
			session.flush();

			session.close();
		}
	}

	public boolean deleteBlog(Blog blog) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
          session.delete(blog);
			
			return true;
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
				return false;
				}
		finally{
			session.flush();

			session.close();
		}
	}
	

	public Blog getBlogByBlogId(int blogId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		
			Query query = session.createQuery("from Blog where blogId = ?");
			query.setInteger(0, blogId);

			Blog blog=(Blog) query.uniqueResult();
			return blog;
		
	}


	public List<Blog> getAllApprovedBlog() {
		// TODO Auto-generated method stub
		Session session = getSession();
		List<Blog> bloglist= session.createQuery("From Blog where blogStatus='A'").list();
		session.close();
		return bloglist;
	}

}
