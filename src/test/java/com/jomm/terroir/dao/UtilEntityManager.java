package com.jomm.terroir.dao;

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
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

/**
 * This abstract Class is a test-specific utility exposing the {@link EntityManager} 
 * and related methods to all DAO test cases.
 * It cannot be instantiated and all methods are {@code static}.
 * The related Persistence Unit has a {@code transaction-type="RESOURCE_LOCAL"},
 * implying the explicit use of {@code session.beginTransaction(){@code  and {@code transaction.commit()}.
 * @author Maic
 */
public final class UtilEntityManager {
	
	// Constants
	private static final String PERSISTENCE_UNIT_TEST = "testPU";
	private static final String SCHEMA_TEST = "sql/schematest.ddl";
	private static final String TEXT_FILE_ENCODING = "UTF-8";
	private static final String SHUTDOWN_URL = "jdbc:derby:memory:testDB;shutdown=true";
	// SQL State is "08006" (one database) or "XJ015" (all databases)
	private static final String SHUTDOWN_SQL_STATE = "08006";

	private static final EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_TEST);
	
	// Attributes
	private static EntityManager entityManager;
	private static Connection connection;
	private static Transaction transaction;
	
	/**
	 * Constructor private to prevent instantiation.
	 */
	private UtilEntityManager() {}
	
	/**
	 * Get the {@link EntityManager}.
	 * If it is null, it is created from the {@link EntityManagerFactory}, and the {@link Connection} is set.
	 * @return the {@link EntityManager}.
	 */
	public static EntityManager prepareEntityManager() {
		// Set EntityManager if null
		if (entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
			setConnection();
		}
		// Get EntityManager
		return entityManager;
	}

	/**
	 * Close the {@link EntityManager}. This method should be used after each test.
	 */
	public static void closeEntityManager() {
		if (entityManager != null) {
			entityManager.close();
			entityManager = null;
		}
		connection = null;
	}
	
	/**
	 * Retrieve a {@link Transaction} from the current {@link Session} and begin it.
	 * This method should be used just after the create/update/delete actions that need to be immediately persisted.
	 */
	public static void beginTransaction() {
		Session session = entityManager.unwrap(Session.class);
		transaction = session.beginTransaction();
	}

	/**
	 * Persist immediately the create/update/delete changes in database.
	 * This method should be used just after the create/update/delete actions.
	 */
	public static void commit() {
		transaction.commit();
	}

	/**
	 * Create the database used for tests. This method should be used before all tests are being run.
	 */
	public static void setUp() {
		// Set EntityManager and Connection
		prepareEntityManager();

		// Create Database from SQL file
		createDatabase();
	}

	/**
	 * Shutdown the database used for tests. This method should be used after all tests have been run.
	 */
	public static void tearDown() {
		try {
			DriverManager.getConnection(SHUTDOWN_URL);
		} catch (SQLException exception) {
			if (!Objects.equals(exception.getSQLState(), SHUTDOWN_SQL_STATE)) {
				System.err.println(exception.getMessage());
			}
		}
	}

	/**
	 * Create the database used for tests.
	 */
	private static void createDatabase() {
		try {
			ij.runScript(connection, ClassLoader.getSystemClassLoader().getResourceAsStream(SCHEMA_TEST),
					TEXT_FILE_ENCODING, System.out, TEXT_FILE_ENCODING);
		} catch (UnsupportedEncodingException exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Set the Connection using a lambda expression.
	 */
	private static void setConnection() {
		Session session = entityManager.unwrap(Session.class);
		Work work = (connection) -> {
			UtilEntityManager.connection = connection;
			};
		session.doWork(work);
	}
}