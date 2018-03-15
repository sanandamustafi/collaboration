package com.niit.colloborativebackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloborativebackend.model.JobApplication;
@Repository("jobApplicationDao")
@Transactional

public class JobApplicationDaoImpl implements JobApplicationDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean saveJobApplication(JobApplication jobapplication) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
					session.save(jobapplication);  ///  insert into table 
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

	public boolean updateJobApplication(JobApplication jobapplication) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
					session.update(jobapplication);  ///  insert into table 
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

	public JobApplication getJobApplicationByJobApplicationId(int jobapplicationId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		
		Query query = session.createQuery("from JobApplication where jobapplicationId = ?");
		query.setInteger(0, jobapplicationId);

		JobApplication jobapplication=(JobApplication) query.uniqueResult();
		return jobapplication;
	
	}

	public List <JobApplication>getjobapplicationsByJobId(int jobId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from JobApplication where jobId=?");
		query.setParameter(0,jobId);
		List <JobApplication>jobapplication= query.list();
		session.close();
        return jobapplication;
	}

	public List<JobApplication> jobApplicationByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from JobApplication where userId=?");
		query.setParameter("userId", userId);
		List<JobApplication> jobApplicationList = query.list();
        session.close();
        return jobApplicationList;
	}

	public boolean isJobExist(String userId, int jobId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
			Query query=session.createQuery("From JobApplication where userId=? and jobId=?");
			query.setString(0, userId);
			query.setInteger(1, jobId);
			return!query.list().isEmpty();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
			
		}
		
		
	}

	public List<JobApplication> getAllJobApplication() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from JobApplication");
		List<JobApplication> jobapplicationList = query.list();
		return jobapplicationList;
	}

}
