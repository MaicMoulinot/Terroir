package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Label;

/**
 * This Class defines all CRUD operations involving a {@link Label}.
 * It implements {@link DaoLabel}, and it extends {@link DaoGenericJpa} using a {@link Label} parameter.
 * @author Maic
 */
@Stateless
public class DaoLabelJpa extends DaoGenericJpa<Label> implements DaoLabel {
	
}