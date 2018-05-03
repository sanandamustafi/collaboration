package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
	protected Session getSession(){
	return sessionFactory.openSession();	
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
			Session s=getSession();
			s.save(friend);
			s.flush();
			s.close();
			
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	

	}

	 @Transactional
	public boolean update(Friend friend){
		try {
			Session s=getSession();
			s.update(friend);
			s.flush();
			s.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	
		
		
	}

	
		@Transactional
		public Friend get(String userId, String friendId) {
			
			String hql = "from Friend where  ( userId = '" + friendId + "' and friendId = '" + userId + "' and status='A') or " +"( userId='"+ userId +"' and friendId= '"+ friendId + "' and status = 'A')";
			Session s=getSession();
			Query query = s.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friend> list = (List<Friend>) query.list();
			s.close();
			if(list != null && !list.isEmpty()) {
				return list.get(0);
			}
			
			return null;
		}
		
	
		@Transactional
	public List<Friend> getMyFriends(String userId) {
		// TODO Auto-generated method stub
		String hql = "from Friend where ( userId = '" + userId + "' and status = 'A') or (friendId='"+ userId + "' and status ='A')";
		Session s=getSession();
		Query query = s.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		s.close();
		return list;
	}
	

	
@Transactional
	public List<Friend> getNewFriendRequests(String friendId) {
		// TODO Auto-generated method stub

		
		String hql = "from Friend where friendId = '" + friendId + "' and status = 'N'";
		Session s=getSession();
		Query query = s.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		s.close();
		return list;
	}
	

	
		@Transactional
	public void setOnline(String userId) {
		
		String hql = "update Friend set isOnline = 'Y' where userId = '" + userId + "'";
		Session s=getSession();
		Query query = s.createQuery(hql);
		query.executeUpdate();
		s.close();
		
	}

		
		@Transactional
	public void setOffline(String userId) {
		String hql = "update Friend set isOnline = 'N' where userId = '" + userId + "'";
		Session s=getSession();
		Query query = s.createQuery(hql);
		query.executeUpdate();
		
	}		
public Friend getRequest(String userId, String friendId )
{
	String hql="from Friend where userId = '"+ friendId + "' and friendId ='" + userId + "' and status ='N'";
	Session s=getSession();
	Query query=s.createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> list=(List<Friend>) query.list();
	s.close();
	if(list!=null && !list.isEmpty()){
		return list.get(0);
	}
return null;
}
public boolean isFriendRequestExists(String userId, String friendId) {
	// TODO Auto-generated method stub
	String hql="from Friend where  ( userId = '" + friendId + "' and friendId = '" + userId + "' and status='N') or " +"( userId='"+ userId +"' and friendId= '"+ friendId + "' and status = 'N')";
	Session s=getSession();
	Query query=s.createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> list=(List<Friend>) query.list();
	s.close();
	if(list.size()>0)
	
	return true;
	else
		return false;
}
	}

