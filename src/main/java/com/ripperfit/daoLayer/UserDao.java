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
	 * @param employee : employee object
	 * @return : boolean
	 */
	@Transactional
	public Boolean registerEmployee(Employee employee) {

		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(employee);
			if(i > 0) {
				result = true;
			}
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * method to get Employee By EmployeeId
	 * @param id : employee id
	 * @return : Employee Object
	 */
	@Transactional
	public Employee getEmployeeById(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Employee emp = null;
		try {
			emp = (Employee) session.load(Employee.class, id);
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return emp;
	}
	
	/**
	 * method to get Employee By Email
	 * @param email : employee's email address
	 * @return : Employee Object
	 */
	@Transactional
	public Employee getEmployeeByEmail(String email) {

		Session session = this.sessionFactory.getCurrentSession();
		Employee emp = null;
		try {
			emp = (Employee) session.createCriteria(Employee.class).add( Restrictions.eq("email",email) ).uniqueResult();
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return emp;
	}
	
	/**
	 * Method to update employee
	 * @param employee : employee object
	 */
	@Transactional
	public void updateEmployee(Employee employee) {
		
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
	}
	
	/**
	 * method to register employee
	 * @param email : employee's email address
	 * @param password : employee's password
	 * @return Employee object
	 */
	public Employee login(String email, String password) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Employee emp = null;
		try {
			emp = (Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).add( Restrictions.eq("password",password) ).uniqueResult();
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return emp;
	}
}