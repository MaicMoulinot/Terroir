package com.jomm.terroir.dao;

import java.util.HashMap;
import java.util.Map;

public abstract class DaoStatic<K, E> implements Dao<K, E> {

	protected Map<K, E> daoFixedMap;

	public DaoStatic() {
		if (daoFixedMap == null) {
			daoFixedMap = new HashMap<K, E>();
		}
	}
}
