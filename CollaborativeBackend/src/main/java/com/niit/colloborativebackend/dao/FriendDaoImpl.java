package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloborativebackend.model.Friend;

@EnableTransactionManagement
@Repository(value="friendDao")

public class FriendDaoImpl implements FriendDao {
	

	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
public FriendDaoImpl() { 		
		
	}	
	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public boolean save(Friend friend) {
		// TODO Auto-generated method stub
		try {
			
			sessionFactory.getCurrentSession().save(friend);
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	

	}

	 @Transactional
	public boolean update(Friend friend){
		try {
			
			sessionFactory.getCurrentSession().update(friend);
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	
		
		
	}

	
		@Transactional
		public Friend get(String userId, String friendId) {
			
			String hql = "from Friend where userId = '" + userId + "' and friendId = '" + friendId + "'";
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friend> list = (List<Friend>) query.list();
			
			if(list != null && !list.isEmpty()) {
				return list.get(0);
			}
			
			return null;
		}
		
	
		@Transactional
	public List<Friend> getMyFriends(String userId) {
		// TODO Auto-generated method stub
		String hql = "from Friend where userId = '" + userId + "' and status = 'A'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		return list;
	}
	

	
@Transactional
	public List<Friend> getNewFriendRequests(String friendId) {
		// TODO Auto-generated method stub

		
		String hql = "from Friend where friendId = '" + friendId + "' and status = 'N'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		return list;
	}
	

	
		@Transactional
	public void setOnline(String userId) {
		
		String hql = "update Friend set isOnline = 'Y' where userId = '" + userId + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

		
		@Transactional
	public void setOffline(String userId) {
		String hql = "update Friend set isOnline = 'N' where userId = '" + userId + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}		

	}
