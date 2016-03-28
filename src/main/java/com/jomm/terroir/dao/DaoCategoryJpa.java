package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Category;

/**
 * This Class defines all CRUD operations involving a {@link Category}.
 * It implements {@link DaoCategory}, and it extends {@link DaoGenericJpa} using a {@link Category} parameter.
 * @author Maic
 */
@Stateless
public class DaoCategoryJpa extends DaoGenericJpa<Category> implements DaoCategory {
	
}