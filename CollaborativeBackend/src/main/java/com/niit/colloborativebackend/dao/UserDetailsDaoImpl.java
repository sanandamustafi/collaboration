package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.niit.colloborativebackend.model.UserDetails;
@Repository("userdetailsDao")
@Transactional

public class UserDetailsDaoImpl implements UserDetailsDao{
	@Autowired
	private SessionFactory sessionFactory;


	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}


	public boolean saveUserDetails(UserDetails userdetails) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
					session.save(userdetails);  ///  insert into table 
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

	public boolean updateUserDetails(UserDetails userdetails) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
		
					session.update(userdetails);  ///  insert into table 
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

	public boolean deleteUserDetails(UserDetails userdetails) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
		
					session.delete(userdetails);  ///  insert into table 
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

	public List<UserDetails> getAllUserDetails() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from UserDetails");
		List<UserDetails> userdetailsList = query.list();
		return userdetailsList;
	}

	public UserDetails authenticateUser(String userId, String userPassword) {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from UserDetails where userId = ? and userPassword= ?");
		query.setParameter(0, userId);
		query.setParameter(1, userPassword);
		

	UserDetails userdetails=(UserDetails)query.uniqueResult();
	return userdetails;
	}


	public UserDetails getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from UserDetails where userId = ?");
		query.setString(0, userId);
		UserDetails userdetails=(UserDetails) query.uniqueResult();
		return userdetails;
		

	}

	

}
