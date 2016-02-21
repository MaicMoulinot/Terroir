package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.generator.DateSequenceValueGenerator;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * This abstract Class should be extended by all DAO test cases.
 * It defines a method <code>cleanInsertData()</code> that is launched only if necessary,
 * thanks to the use of the attribute <code>DbSetupTracker</code>.
 * @author Maic
 */
public abstract class UtilData {
	
	// Attributes
	protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	private static final String DESTINATION_URL = "jdbc:derby:memory:testDB";
	private static final String DESTINATION_USER = "";
	private static final String DESTINATION_PASSWORD = "";
	private static final DateSequenceValueGenerator GENERATOR_LOCAL_DATE = ValueGenerators.dateSequence();
	private static final DateSequenceValueGenerator GENERATOR_ZONED_DATE_TIME = 
			ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault());
	
	private static final Operation DELETE_ALL_DATA = deleteAllFrom("tr_admin", "tr_product", "tr_site", "tr_seller", 
			"tr_enterprise", "tr_customer", "tr_image");
	private static final Operation INSERT_BASIC_DATA =
			sequenceOf(
					insertInto("tr_enterprise")
					.columns("enterprise_id", "trade_name", "legal_name", "legal_identification", "date_creation", 
							"number_employees", "date_signup", 
							"address_street", "address_complement", "address_post_code",
							"address_town", "address_country", "address_coordinates")
					.values(111111, "Janichon&Sons", "GAEC Janichon", "XXDGQG", 
							GENERATOR_LOCAL_DATE.nextValue(), 2, GENERATOR_ZONED_DATE_TIME.nextValue(), 
							"Dagallier Haut", null, "01400", "Sulignat", "France", "46.182194, 4.970275")
					.values(222222, "Les Vergers de Saint Jean", "SCEA Les Vergers de Saint Jean", "CHSGFQN", 
							GENERATOR_LOCAL_DATE.nextValue(), 4, GENERATOR_ZONED_DATE_TIME.nextValue(), 
							"Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", "43.589423, 3.827251")
					.build(),
					insertInto("tr_site")
					.columns("site_id", "site_name", "legal_identification", "address_street", "address_complement", 
							"address_post_code", "address_town", "address_country", "address_coordinates", 
							"enterprise_enterprise_id")
					.values(111111, "Dagallier", "4123512DFSJ677", "Dagallier Haut", null, "01400", "Sulignat", 
							"France", "46.182194, 4.970275", 111111)
					.values(222222, "Cerises", "562FQVC56", "Allée Pioch Redon", null, "34430", "St Jean de Védas", 
							"France", "43.589423, 3.827251", 222222)
					.values(333333, "Pommes", "562FQVC57", "Rue des Prés", null, "34430", "St Jean de Védas", 
							"France", "43.577740, 3.816562", 222222)
					.build());

	/**
	 * Clean the data from previous tests and insert new data. This method is used before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public final void cleanInsertData() throws Exception {
		Operation operation = sequenceOf(DELETE_ALL_DATA, INSERT_BASIC_DATA);
		DbSetup dbSetup = new DbSetup(DriverManagerDestination.with(DESTINATION_URL, DESTINATION_USER, 
				DESTINATION_PASSWORD), operation);
		dbSetupTracker.launchIfNecessary(dbSetup);
	}
}