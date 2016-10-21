package com.ripperfit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Department;
import com.ripperfit.model.Organization;

@Repository
public class DepartmentDao {

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

		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(department);
			if(i > 0) {
				result = true;
			}
		} catch(Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public Department getHelpdeskDepartmentByOrganization(Organization organization){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department where organization= :organization and "
				+ "departmentName= :departmentName");
		query.setParameter("organization", organization);
		query.setParameter("departmentName", "helpdesk");
		Department department = (Department) query.uniqueResult();
		return department;
		
	}
	

	public List<Department> viewAllDepartments() {
		
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Department> departments = session.createCriteria(Department.class).list();
		return departments;
	}
	
	public List<Department> getDepartmentByName(String departmentName){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department where departmentName= :departmentName"); 
		query.setParameter("departmentName", departmentName);
		@SuppressWarnings("unchecked")
		List<Department> departmentList = query.list();
		
		return departmentList;
	}
	
	public List<Department> getDepartmentListByNameInOrganization(String departmentName,Organization organization){
		
		System.out.println("in dao");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department where departmentName= :departmentName and organization= :organization"); 
		query.setParameter("departmentName", departmentName);
		query.setParameter("organization", organization);
		@SuppressWarnings("unchecked")
		List<Department> departmentList = query.list();
		return departmentList;
	}
	
	public Department getDepartmentBynameInOrganization(String departmentName , Organization organization){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department where departmentName= :departmentName and organization= :organization"); 
		query.setParameter("departmentName", departmentName);
		query.setParameter("organization", organization);
		Department department = null;
		@SuppressWarnings("unchecked")
		List<Department> departmentList = query.list();
		if(! departmentList.isEmpty()){
			
			department = departmentList.get(0);
		}
		
		return department;
	}
	
	/**
	 * method to get Department By DepartmentId
	 * @param id : department id
	 * @return : Department Object
	 */
	@Transactional
	public Department getDepartmentById(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Department department = null;
		try {
			department =(Department) session.load(Department.class, id);
		} catch(Exception e) {

			e.printStackTrace();
		}
		return department;
	}
	
	@Transactional
	public void updateDepartment(Department department) {

		Session session = this.sessionFactory.getCurrentSession();
		session.update(department);
	}
	
	/**
	 * done
	 * @return
	 */
	public List<Department> getAllDepartmentsInAnOrganization(Organization organization) {

		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Department> department = session.createCriteria(Department.class).add( Restrictions.eq("organization",organization)).list();
		return department;
	}
	
}
