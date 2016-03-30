package com.jomm.terroir.dao;

import javax.ejb.Stateless;

import com.jomm.terroir.business.model.Image;

/**
 * This Class defines all CRUD operations involving a {@link Image}.
 * It implements {@link DaoImage}, and it extends {@link DaoGenericJpa} using a {@link Image} parameter.
 * @author Maic
 */
@Stateless
public class DaoImageJpa extends DaoGenericJpa<Image> implements DaoImage {
	
}