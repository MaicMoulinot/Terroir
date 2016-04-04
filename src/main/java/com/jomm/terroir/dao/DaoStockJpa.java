package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Stock;

/**
 * This Class defines all CRUD operations involving a {@link Stock}.
 * It implements {@link DaoStock}, and it extends {@link DaoGenericJpa} using a {@link Stock} parameter.
 * @author Maic
 */
@Stateless
public class DaoStockJpa extends DaoGenericJpa<Stock> implements DaoStock {
	
}