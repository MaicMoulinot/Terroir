package com.jomm.terroir.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.derby.tools.ij;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 * @author Maic
 *
 */
public class PersistenceTest {

	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static Connection connection;
	
	/**
	 * Clean the data from previous tests and insert new data.
	 */
	public static void initData() {
		//TODO DatabaseOperation.CLEAN_INSERT.execute(mDBUnitConnection, mDataset);
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("testPU");
			//entityManager = entityManagerFactory.createEntityManager();
			//setUp();
		}
		return entityManagerFactory;
	}

	public static void setUp() {
		// Retrieve Connection
		WorkImpl work = new WorkImpl();
		Session session = getEntityManager().unwrap(Session.class);
		session.doWork(work);
		connection = work.getConnection();
		// Create Database from SQL file
		//createDatabase();
	}

	public static void createDatabase() {
		try {
			ij.runScript(connection, ClassLoader.getSystemClassLoader().getResourceAsStream("sql/schema.ddl"),
					"UTF-8", System.out, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			System.err.println("PB");
			System.err.println(exception.getMessage());
		}
	}
	
	public static void dropDatabase() {
		//ij kill
		try {
			//connection.
			DriverManager.getConnection("jdbc:derby:memory:testDB;drop=true");
		} catch (SQLException exception) {
			if (!Objects.equals(exception.getSQLState(), "08006")) {//08006 XJ015
				System.err.println(exception.getMessage());
			}
		}
		//TODO drop.. mais comment être sur que c'est le dernier test??
	}

	public static void tearDown() {
		entityManager.close();
		entityManager = null;
	}

	private static class WorkImpl implements Work {

		Connection connectionWork;

		@Override
		public void execute(Connection connection) throws SQLException {
			this.connectionWork = connection;
		}

		Connection getConnection() {
			return connectionWork;
		}
	}
}