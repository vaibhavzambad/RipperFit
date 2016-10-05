package com.ripperfit.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.daoLayer.RipperFitDaoImpl;

public class RipperFitServiceImpl {
	
	@Autowired
	private RipperFitDaoImpl ripperFitDao;

	/**
	 * @return the ripperFitDao
	 */
	public RipperFitDaoImpl getRipperFitDao() {
		return ripperFitDao;
	}

	/**
	 * @param ripperFitDao the ripperFitDao to set
	 */
	public void setRipperFitDao(RipperFitDaoImpl ripperFitDao) {
		this.ripperFitDao = ripperFitDao;
	}
}