package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Designation;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;

@Repository
public class UserDaoImpl implements UserDao {

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
	public boolean registerEmployee(Employee employee) {
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(employee);
			return true;
		} catch(Exception ex) {
			throw ex;
		}finally{
			
		}
	}

	/**
	 * method to get Employee By EmployeeId
	 * @param id : employee id
	 * @return : Employee Object
	 */
	public Employee getEmployeeById(int id) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return ((Employee) session.load(Employee.class, id));
		} catch(Exception ex) {
			throw ex;
		}finally{
			
		}
	}

	/**
	 * method to get Employee By Email
	 * @param email : employee's email address
	 * @return : Employee Object
	 */
	public Employee getEmployeeByEmail(String email) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return ((Employee) session.createCriteria(Employee.class).add( Restrictions.eq("email",email) ).uniqueResult());
		} catch(Exception ex) {
			throw ex;
		}finally{
			
		}
	}

	/**
	 * Method to update employee
	 * @param employee : employee object
	 */
	public boolean updateEmployee(Employee employee) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(employee);
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{

		}
	}

	/**
	 * method to register employee
	 * @param email : employee's email address
	 * @param password : employee's password
	 * @return Employee object
	 */
	public Employee login(String email, String password) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return ((Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).add( Restrictions.eq("password",password) ).uniqueResult());
		} catch(Exception e) {
			throw e;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> viewAllEmployee() {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Employee.class).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Employee getEmployeeByDesignation(Designation designation) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Employee where designation= :designation"); 
			query.setParameter("designation", designation);
			return (Employee) query.list().get(0);
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeApprove(Employee employee) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Employee.class).add( Restrictions.eq("approvalStatus","false")).add( Restrictions.eq("employee",employee) ).list();
		}catch(Exception ex){
			throw ex;
		}finally{
		
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployeesInAnOrganization(Organization organization) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Employee.class).add( Restrictions.eq("organization",organization)).list();
		}catch(Exception ex){
			throw ex;
		}finally{
		
		}
	}
}