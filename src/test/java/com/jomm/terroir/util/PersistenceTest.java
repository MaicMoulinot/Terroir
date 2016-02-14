package com.jomm.terroir.util;

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
 * This Class is the test-specific Tool exposing {@link EntityManager} and related methods to all test cases.
 * It cannot be instantiated and all methods are <code>static</code>.
 * @author Maic
 */
public class PersistenceTest {

	// Constants
	private static final String PERSISTENCE_UNIT_TEST = "testPU";
	private static final String SCHEMA_TEST = "sql/schema.ddl";
	private static final String SHUTDOWN_URL = "jdbc:derby:memory:testDB;shutdown=true";
	// SQL State is "08006" (one database) or "XJ015" (all databases)
	private static final String SHUTDOWN_SQL_STATE = "08006";

	// Attributes
	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static Connection connection;

	/**
	 * Get the {@link EntityManager}.
	 * If it is null, it is created from the {@link EntityManagerFactory}, and the {@link Connection} is set.
	 * @return the {@link EntityManager}.
	 */
	public static EntityManager prepareEntityManager() {
		// Set EntityManager if null
		if (entityManager == null) {
			if (entityManagerFactory == null) {
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_TEST);
			}
			entityManager = entityManagerFactory.createEntityManager();
			setConnection();
		}
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
	 * Create the database used for tests. This method should be used before all tests are being run.
	 */
	public static void setUp() {
		// Use test-specific Persistence Unit
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_TEST);

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
					"UTF-8", System.out, "UTF-8");
		} catch (UnsupportedEncodingException exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Set the Connection using the private class {@link WorkImpl}.
	 */
	private static void setConnection() {
		WorkImpl work = new WorkImpl();
		Session session = entityManager.unwrap(Session.class);
		session.doWork(work);
		connection = work.getConnection();
	}

	/**
	 * Constructor private to prevent the class to be instantiated.
	 */
	private PersistenceTest() {}

	/**
	 * Private Class allowing the {@link Session} to retrieve the {@link Connection}.
	 * It implements {@link Work} and its method <code>execute(Connection)</code>.
	 * It defines a getter for its private attribute <code>connectionWork</code>.
	 * @author Maic
	 */
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