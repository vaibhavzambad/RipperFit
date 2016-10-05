package com.ripperfit.daoLayer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RipperFitDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}