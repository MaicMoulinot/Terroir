package com.jomm.terroir.dao;

import javax.persistence.Entity;

/**
 * This abstract Class describes all persisting operations for an {@link Entity} using static maps.
 * @author Maic
 *
 * @param <K> {@link Long} is the Key's type.
 * @param <E> {@link Entity} is the Entity's type.
 */
public abstract class DaoStatic<K, E> implements Dao<K, E> {
	// Each map is constructed, accessed and updated in the proper DaoStatic's child.
}
