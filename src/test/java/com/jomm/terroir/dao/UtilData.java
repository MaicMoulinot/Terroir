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
 * It defines a method {@code cleanInsertData()} that is launched only if necessary,
 * thanks to the use of the attribute {@code DbSetupTracker}.
 * @author Maic
 */
public abstract class UtilData {

	// Protected constants
	protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	protected static final long NON_EXISTING_ENTITY_ID = 999999;
	protected static final long EXISTING_SITE_ID = 333333;
	protected static final long EXISTING_ENTERPRISE_ID = 111111;
	protected static final long EXISTING_LABEL_ID = 111111;

	// Private constants
	private static final String DESTINATION_URL = "jdbc:derby:memory:testDB";
	private static final String DESTINATION_USER = "";
	private static final String DESTINATION_PASSWORD = "";
	private static final DateSequenceValueGenerator GENERATOR_LOCAL_DATE = ValueGenerators.dateSequence();
	private static final DateSequenceValueGenerator GENERATOR_ZONED_DATE_TIME = 
			ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault());

	private static final Operation DELETE_ALL_DATA = deleteAllFrom("administrator", "customer", 
			"product", "site", "seller", "enterprise", "designation", "qualitylabel", "image");
	private static final Operation INSERT_BASIC_DATA = sequenceOf(
					insertInto("enterprise")
					.columns("enterprise_id", "trade_name", "legal_name", "legal_identification", "creation_date", 
							"number_employees", "registration_date", 
							"addr_street", "addr_complement", "addr_post_code",
							"addr_city", "addr_country", "addr_coordinates")
					.values(EXISTING_ENTERPRISE_ID, "Janichon&Sons", "GAEC Janichon", "XXDGQG", 
							GENERATOR_LOCAL_DATE.nextValue(), 2, GENERATOR_ZONED_DATE_TIME.nextValue(), 
							"Dagallier Haut", null, "01400", "Sulignat", "France", "46.182194, 4.970275")
					.values(222222, "Les Vergers de Saint Jean", "SCEA Les Vergers de Saint Jean", "CHSGFQN", 
							GENERATOR_LOCAL_DATE.nextValue(), 4, GENERATOR_ZONED_DATE_TIME.nextValue(), 
							"Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", "43.589423, 3.827251")
					.build(),
					insertInto("site")
					.columns("site_id", "site_name", "legal_identification", "addr_street", "addr_complement", 
							"addr_post_code", "addr_city", "addr_country", "addr_coordinates", 
							"fk_enterprise_id")
					.values(111111, "Dagallier", "4123512DFSJ677", "Dagallier Haut", null, "01400", "Sulignat", 
							"France", "46.182194, 4.970275", EXISTING_ENTERPRISE_ID)
					.values(222222, "Cerises", "562FQVC56", "Allée Pioch Redon", null, "34430", "St Jean de Védas", 
							"France", "43.589423, 3.827251", 222222)
					.values(EXISTING_SITE_ID, "Pommes", "562FQVC57", "Rue des Prés", null, "34430", "St Jean de Védas", 
							"France", "43.577740, 3.816562", 222222)
					.build(),
					insertInto("qualitylabel")
					.columns("qualitylabel_id", "official_name", "acronym", "definition", "fk_image_id")
					.values(EXISTING_LABEL_ID, "Appellation d'origine contrôlée", "AOC", "L'appellation d'origine contrôlée (AOC) "
//							+ "est un label officiel français identifiant un produit dont les étapes de fabrication "
//							+ "(production et transformation) sont réalisées dans une même zone géographique et "
//							+ "selon un savoir-faire reconnu. C'est la combinaison d'un milieu physique et biologique "
							+ "avec une communauté humaine traditionnelle qui fonde la spécificité d'un produit AOC.", 
							null)
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