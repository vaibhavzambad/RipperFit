package com.ripperfit.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Designation;


@Repository
@Transactional
public class RoleDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired(required=true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Boolean addRole(Designation designation) {


		Boolean flag=false;
		return flag;
	}

	public Boolean deleteRole(String designation) {

		return null;
	}



	public Boolean updateRoleByName(String designation,
			String updatedDesignation) {
		// TODO Auto-generated method stub
		return null;
	}

	/*int  getIdByRole(String designation)
	   {

	   }*/
}
