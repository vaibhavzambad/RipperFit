package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;

@Repository
public class DesignationDao {

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

	public List<Designation> getAllDesignationsInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Designation> des = session.createCriteria(Designation.class).add( Restrictions.eq("organization",organization)).list();
			return des;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public List<Designation> getDesignationsInDepartment(Department department) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Designation> des = session.createCriteria(Designation.class).add( Restrictions.eq("department",department)).list();
			return des;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Boolean addDesignation(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			boolean result = false;
			int i=0;
			try {
				i = (Integer) session.save(designation);
				if(i > 0) {
					result = true;
				}
			} catch(Exception e) {

				e.printStackTrace();
			}
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Designation getDesignationById(int designationId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationId= :designationId"); 
			query.setParameter("designationId", designationId);
			@SuppressWarnings("unchecked")
			List<Designation> designationList = query.list();
			Designation designation = (Designation) designationList.get(0);
			return designation;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}

	public Designation getDesignationByName(String designationName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationName= :designationName"); 
			query.setParameter(" designationName", designationName);
			@SuppressWarnings("unchecked")
			List<Designation> designationList = query.list();
			Designation designation = (Designation) designationList.get(0);
			return designation;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Designation getDesignationByDepartment(Department department){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where department= :department"); 
			query.setParameter("department", department);
			Designation designation = (Designation) query.uniqueResult();
			return designation;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public void updateLevels(List<Designation> designationList) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			for(Designation des : designationList) {
				session.update(des);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public List<Designation> designationListAboveLevel(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationLevel >= :designationLevel and department = :department"); 
			query.setParameter("designationLevel", designation.getDesignationLevel()+1);
			query.setParameter("department", designation.getDepartment());
			@SuppressWarnings("unchecked")
			List<Designation> designationList = query.list();
			return designationList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Designation getDesignationBynameInOrganization(String designationName , Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationName= :designationName and organization= :organization"); 
			query.setParameter("designationName", designationName);
			query.setParameter("organization", organization);
			Designation designation = null;
			@SuppressWarnings("unchecked")
			List<Designation> designationList = query.list();
			if(! designationList.isEmpty()){

				designation = designationList.get(0);
			}
			return designation;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}

	public void updateDesignation(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(designation);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}