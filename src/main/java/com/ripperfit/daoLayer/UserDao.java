package com.ripperfit.daoLayer;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Employee;

@Repository
@Transactional
public class UserDao {

	private SessionFactory sessionFactory;
	/**
	 * method to get SessionFactory object
	 * @return : SessionFactory object
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * method to set SessionFactory object
	 * @param sessionFactory
	 */
	@Autowired(required=true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * method to register employee
	 * 
	 */
	@Transactional
	public Boolean registerUser(Employee employee) {

		Session session = this.sessionFactory.getCurrentSession();
		int i=0;
		boolean result=false;
		try
		{
			System.out.println("start");
			System.out.println("dfdf"+employee.getEmployeeId());
			i=(Integer)session.save(employee);
			if(i>0){
				result = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}
	/**
	 * method to get Employee By EmployeeId 
	 * @return : Employee Object
	 */
	@Transactional
	public Employee getUserById(int id) {
		Session session=this.sessionFactory.getCurrentSession();
		Employee emp=null;
		try
		{
			emp=(Employee) session.load(Employee.class,id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return emp;
	}
	/**
	 * method to get Employee By Email
	 * @return : Employee Object
	 */
	@Transactional
	public Employee getUserByEmail(String email) {

		Session session=this.sessionFactory.getCurrentSession();
		Employee emp=null;
		try
		{
			emp=(Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return emp;
	}
	/**
	 * Method to update employee
	 */
	@Transactional
	public void updateUser(Employee employee)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
	}
	/**
	 * method to register employee
	 * 
	 */
	public Employee login(String email, String password) {
		Session session=this.sessionFactory.getCurrentSession();
		Employee emp=null;
		try
		{
			emp=(Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).add( Restrictions.eq("password",password) ).uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return emp;
	}
	/**
	 * method to check that employee exists or not
	 */
	public Boolean ifUserExists(String email) {

		Session session=this.sessionFactory.getCurrentSession();
		Employee employee=null;
		boolean result=false;
		try
		{
			employee=(Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).uniqueResult();
			if(employee != null){
				result = true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}