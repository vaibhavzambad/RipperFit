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
	public Boolean registerEmployee(Employee employee) {

		boolean result = false;
		int i=0;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			i = (Integer) session.save(employee);
			if(i > 0) {
				result = true;
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
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
			Employee emp = null;
			emp = (Employee) session.load(Employee.class, id);
			return emp;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
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
			Employee emp = null;
			emp = (Employee) session.createCriteria(Employee.class).add( Restrictions.eq("email",email) ).uniqueResult();
			return emp;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Method to update employee
	 * @param employee : employee object
	 */
	public void updateEmployee(Employee employee) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(employee);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
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
			Employee emp = null;
			emp = (Employee) session.createCriteria( Employee.class).add( Restrictions.eq("email",email) ).add( Restrictions.eq("password",password) ).uniqueResult();
			return emp;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Employee> viewAllEmployee() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Employee> emp = session.createCriteria(Employee.class).list();
			return emp;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Employee getEmployeeByDesignation(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Employee where designation= :designation"); 
			query.setParameter("designation", designation);
			Employee employee = (Employee) query.uniqueResult();
			return employee;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public List<Employee> getEmployeeApprove(Employee employee) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Employee> emp = session.createCriteria(Employee.class).add( Restrictions.eq("approvalStatus","false")).add( Restrictions.eq("employee",employee) ).list();
			return emp;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public List<Employee> getAllEmployeesInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Employee> employee = session.createCriteria(Employee.class).add( Restrictions.eq("organization",organization)).list();
			return employee;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}