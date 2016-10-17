package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	
	public List<Department> getDepartmentByName(String deptartmentName){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department where departmentName= :deptartmentName"); 
		query.setParameter("deptartmentName", deptartmentName);
		@SuppressWarnings("unchecked")
		List<Department> departmentList = query.list();
		
		return departmentList;
	}
}
