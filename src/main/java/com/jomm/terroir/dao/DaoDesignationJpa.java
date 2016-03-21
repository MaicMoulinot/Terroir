package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Designation;

/**
 * This Class defines all CRUD operations involving a {@link Designation}.
 * It implements {@link DaoDesignation}, and it extends {@link DaoGenericJpa} using a {@link Designation} parameter.
 * @author Maic
 */
@Stateless
public class DaoDesignationJpa extends DaoGenericJpa<Designation> implements DaoDesignation {
	
}