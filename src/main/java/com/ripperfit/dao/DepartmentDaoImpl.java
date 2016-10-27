package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Department;
import com.ripperfit.model.Organization;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

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

	public boolean addDepartment(Department department) {

		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(department);
			return true;
		} catch(Exception e) {
			throw e;
		}finally{
		
		}
	}

	public Department getHelpdeskDepartmentByOrganization(Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Department where organization= :organization and "
					+ "departmentName= :departmentName");
			query.setParameter("organization", organization);
			query.setParameter("departmentName", "helpdesk");
			return (Department) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}

	}

	public Department getAdminDepartmentByOrganization(Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Department where organization= :organization and "
					+ "departmentName= :departmentName");
			query.setParameter("organization", organization);
			query.setParameter("departmentName", "admin");
			return (Department) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> viewAllDepartments() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Department.class).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentByName(String departmentName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Department where departmentName= :departmentName"); 
			query.setParameter("departmentName", departmentName);
			return query.list();
		}catch(Exception ex){
			throw ex;
		}finally{
		
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentListByNameInOrganization(String departmentName,Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Department where departmentName= :departmentName and organization= :organization"); 
			query.setParameter("departmentName", departmentName);
			query.setParameter("organization", organization);
			return query.list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Department getDepartmentBynameInOrganization(String departmentName , Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Department where departmentName= :departmentName and organization= :organization"); 
			query.setParameter("departmentName", departmentName);
			query.setParameter("organization", organization);
			return (Department) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	/**
	 * method to get Department By DepartmentId
	 * @param id : department id
	 * @return : Department Object
	 */
	public Department getDepartmentById(int id) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return (Department) session.load(Department.class, id);		
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean updateDepartment(Department department) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(department);
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartmentsInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Department.class).add( Restrictions.eq("organization",organization)).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}
}