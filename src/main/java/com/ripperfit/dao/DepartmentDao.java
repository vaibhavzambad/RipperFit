package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Department;


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
	@Autowired(required=true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	
	public boolean addDepartment(Department department) {
		System.out.println("in add department dao");
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
}
