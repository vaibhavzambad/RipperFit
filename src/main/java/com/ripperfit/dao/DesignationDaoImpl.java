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
public class DesignationDaoImpl implements DesignationDao {

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

	@SuppressWarnings("unchecked")
	public List<Designation> getAllDesignationsInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Designation.class).add( Restrictions.eq("organization",organization)).list();
		}catch(Exception ex){
			throw ex;
		}finally{
		
		}
	}

	@SuppressWarnings("unchecked")
	public List<Designation> getDesignationsInDepartment(Department department) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Designation.class).add( Restrictions.eq("department",department)).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean addDesignation(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.save(designation);
			return true;
		} catch(Exception ex) {
			throw ex;
		}finally{
			
		}
	}

	public Designation getDesignationById(int designationId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationId= :designationId"); 
			query.setParameter("designationId", designationId);
			return (Designation) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Designation getDesignationByName(String designationName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationName= :designationName"); 
			query.setParameter(" designationName", designationName);
			return (Designation) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
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
			throw ex;
		}finally{
			
		}
	}

	public boolean updateLevels(List<Designation> designationList) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			for(Designation des : designationList) {
				session.update(des);
			}
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Designation> designationListAboveLevel(Designation designation) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationLevel >= :designationLevel and department = :department"); 
			query.setParameter("designationLevel", designation.getDesignationLevel()+1);
			query.setParameter("department", designation.getDepartment());
			return query.list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Designation getDesignationBynameInOrganization(String designationName , Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Designation where designationName= :designationName and organization= :organization"); 
			query.setParameter("designationName", designationName);
			query.setParameter("organization", organization);
			return (Designation) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{

		}
	}

	public boolean updateDesignation(Designation designation) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(designation);
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}
}