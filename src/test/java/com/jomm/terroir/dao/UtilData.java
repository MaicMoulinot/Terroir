package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import org.junit.Before;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * This Class is an abstract Class that all DAO test cases should extend.
 * It defines a method <code>cleanInsertData()</code> that is launched only if necessary,
 * thanks to the use of <code>DbSetupTracker</code>.
 * @author Maic
 */
public abstract class UtilData {
	
	protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	
	public static final Operation DELETE_ALL_DATA = deleteAllFrom("tr_admin", "tr_product", "tr_site", "tr_seller", 
			"tr_enterprise", "tr_customer", "tr_image");
	public static final Operation INSERT_BASIC_DATA =
			sequenceOf(
					insertInto("tr_enterprise")
					.columns("enterprise_id", "trade_name", "legal_name", "legal_identification", "date_creation", 
							"number_employees", "date_signup", "address_street", "address_complement", "address_post_code",
							"address_town", "address_country", "address_coordinates")
					.values(1, "Janichon&Sons", "GAEC Janichon", "XXDGQG", null, 2, 
							//ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault()).nextValue(), 
							null,
							"Dagallier Haut", null, "01400", "Sulignat", "France", null)
					.values(2, "Les Vergers de Saint Jean", "SCEA Les Vergers de Saint Jean", "CHSGFQN", null, 4, 
							//ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault()).nextValue(), 
							null,
							"Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", null)
					.build(),
					insertInto("tr_site")
					.columns("site_id", "site_name", "legal_identification", "address_street", "address_complement", 
							"address_post_code", "address_town", "address_country", "address_coordinates", 
							"enterprise_enterprise_id")
					.values(1, "Dagallier", "4123512DFSJ677", "Dagallier Haut", null, "01400", "Sulignat", "France", null, 1)
					.values(2, "Cerises", "562FQVC56", "Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", null, 2)
					.values(3, "Pommes", "562FQVC57", "Rue des Prés", null, "34430", "St Jean de Védas", "France", null, 2)
					.build());

	/**
	 * Clean the data from previous tests and insert new data. This method is used before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void cleanInsertData() throws Exception {
		Operation operation = sequenceOf(DELETE_ALL_DATA, INSERT_BASIC_DATA);
		DbSetup dbSetup = new DbSetup(DriverManagerDestination.with("jdbc:derby:memory:testDB", "", ""), operation);
		dbSetupTracker.launchIfNecessary(dbSetup);
	}
}