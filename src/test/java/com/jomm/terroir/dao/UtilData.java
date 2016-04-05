package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.destination.DriverManagerDestination.with;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;

import com.jomm.terroir.util.Constants.Unit;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.generator.DateSequenceValueGenerator;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * This abstract Class should be extended by all DAO test cases.
 * It defines a method {@code cleanInsertData()} that is launched only if necessary,
 * thanks to the use of the attribute {@link DbSetupTracker}.
 * @author Maic
 */
public abstract class UtilData {

	// Constants //-----------------------------------------------
	// Private constants used for database configuration
	private static final String DESTINATION_URL = "jdbc:derby:memory:testDB";
	private static final String DESTINATION_USER = "";
	private static final String DESTINATION_PASSWORD = "";

	// Protected constants to be used in concrete child
	protected static final long NON_EXISTING_ENTITY_ID = 999999;
	protected static final long EXISTING_PRODUCT_ID_FIRST_CALL = 111111;
	protected static final long EXISTING_PRODUCT_ID_SECOND_CALL = 222222;
	protected static final long EXISTING_SITE_ID = 333333;
	protected static final long EXISTING_ENTERPRISE_ID = 111111;
	protected static final long EXISTING_DESIGNATION_ID = 111111;
	protected static final long EXISTING_LABEL_ID = 111111;
	protected static final long EXISTING_CATEGORY_ID = 333333;
	protected static final long EXISTING_IMAGE_ID_FIRST_CALL = 111111;
	protected static final long EXISTING_IMAGE_ID_SECOND_CALL = 222222;
	protected static final long IMAGE_FOR_SITE_ID = 333333;

	// DBSetup private constants
	private static final DateSequenceValueGenerator GENERATOR_LOCAL_DATE = ValueGenerators.dateSequence();
	private static final DateSequenceValueGenerator GENERATOR_ZONED_DATE_TIME = 
			ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault());
	private static final Operation DELETE_ALL_DATA = deleteAllFrom("administrator", "customer", "stock", "product", 
			"siteimage", "site", "seller", "enterprise", "designationlabel", "designation", "label", "image", "category");
	
	// DBSetup protected operations to be used in concrete child
	/**
	 * A sequence of 2 {@link Operation}s.
	 * It requires {@link UtilData#INSERT_LABEL} and {@link UtilData#INSERT_CATEGORIES} to be called first.
	 * <br />Insert data in table designation with id {@link UtilData#EXISTING_DESIGNATION_ID}.
	 * <br />Insert data in join table designationlabel ({@link UtilData#EXISTING_LABEL_ID}, 
	 * {@link UtilData#EXISTING_DESIGNATION_ID}).
	 */
	protected static final Operation INSERT_DESIGNATION_LABEL = sequenceOf(
			// table designation
			insertInto("designation")
			.columns("designation_id", "registered_name", "transcripted_name", "local_name", "legal_act", 
					"registration_date", "web_site", "median_price", "fk_image_logo_id", "fk_image_picture_id",
					"addr_street", "addr_complement", "addr_post_code",
					"addr_city", "addr_country", "addr_coordinates",
					"fk_category_id", "definition", "season")
			.values(EXISTING_DESIGNATION_ID, "Roquefort", null, "ròcafòrt", "Décret du 22 janvier 2001 relatif à "
					+ "l'appellation d'origine contrôlée 'Roquefort' NOR: AGRP0001838D Version consolidée au 21 mars 2016", 
					GENERATOR_LOCAL_DATE.nextValue(), "https://fr.wikipedia.org/wiki/Roquefort", 19.99, null, null, 
					null, null, "12250", "Roquefort-sur-Soulzon", "France", "43.973724, 2.991373", EXISTING_CATEGORY_ID, 
					"Le fromage bénéficiant de l'appellation d'origine contrôlée 'Roquefort' est un fromage fabriqué "
							+ "exclusivement avec du lait de brebis mis en oeuvre à l'état cru et emprésuré, de forme "
							+ "cylindrique de 19 à 20 cm de diamètre et de 8,5 à 11,5 cm de hauteur, d'un poids de 2,5 "
							+ "à 3 kg, à pâte persillée, ensemencée avec des spores de Penicillium roqueforti, non "
							+ "pressée, non cuite, fermentée et salée, à croûte humide, renfermant au minimum 52 g de "
							+ "matière grasse pour 100 g de fromage après complète dessiccation et dont la teneur en "
							+ "matière sèche ne doit pas être inférieure à 55 g pour 100 g de fromage.", "Mois de mars")
			.build(),
			// table designationlabel
			insertInto("designationlabel")
			.columns("fk_designation_id", "fk_label_id")
			.values(EXISTING_DESIGNATION_ID, EXISTING_LABEL_ID)
			.build());
	
	/**
	 * Insert data in table label with id {@link UtilData#EXISTING_LABEL_ID}.
	 */
	protected static final Operation INSERT_LABEL = insertInto("label")
			.columns("label_id", "official_name", "acronym", "fk_image_id", "definition")
			.values(EXISTING_LABEL_ID, "Appellation d'origine contrôlée", "AOC", null, 
					"L'appellation d'origine contrôlée (AOC) est un label officiel français identifiant un produit "
							+ "dont les étapes de fabrication (production et transformation) sont réalisées dans une "
							+ "même zone géographique et selon un savoir-faire reconnu. C'est la combinaison d'un "
							+ "milieu physique et biologique avec une communauté humaine traditionnelle qui fonde la "
							+ "spécificité d'un produit AOC.")
			.build();

	/**
	 * A sequence of 3 {@link Operation}s.
	 * It requires {@link UtilData#INSERT_ENTERPRISES} to be called first.
	 * <br />Insert data in table image with id {@link UtilData#IMAGE_FOR_SITE_ID}.
	 * <br />Insert data in table site with ids 111111, 222222, and {@link UtilData#EXISTING_SITE_ID}.
	 * <br />Insert data in join table siteimage ({@link UtilData#EXISTING_SITE_ID}, 
	 * {@link UtilData#IMAGE_FOR_SITE_ID}).
	 */
	protected static final Operation INSERT_SITES = sequenceOf(
			// table image
			insertInto("image")
			.columns("image_id", "imag_title", "imag_description", "imag_data")
			.values(IMAGE_FOR_SITE_ID, "vache.jpg", "Une très belle vache croquante", null)
			.build(),
			// table site
			insertInto("site")
			.columns("site_id", "site_name", "legal_identification", "addr_street", "addr_complement", 
					"addr_post_code", "addr_city", "addr_country", "addr_coordinates", 
					"fk_enterprise_id", "description")
			.values(111111, "Dagallier", "4123512DFSJ677", "Dagallier Haut", null, "01400", "Sulignat", 
					"France", "46.182194, 4.970275", EXISTING_ENTERPRISE_ID,
					"L'élevage bovin est l'ensemble des opérations visant à reproduire des animaux de l'espèce "
							+ "Bos taurus au profit de l'activité humaine. Il permet de fournir de la viande, du lait, "
							+ "des peaux des animaux reproducteurs, un travail de traction, du fumier et l'entretien "
							+ "des espaces ouverts…")
			.values(222222, "Cerises", "562FQVC56", "Allée Pioch Redon", null, "34430", "St Jean de Védas", 
					"France", "43.589423, 3.827251", 222222,  
					"Soucieux de produire des fruits de qualités pour vous les faire apprécier, nous vous proposons "
							+ "également des légumes de saisons issus majoritairement de l’agriculture local tel que "
							+ "le navet de Pardailhan, la fraise de Mauguio ou encore les oignons des Cévennes, ainsi "
							+ "que des produits transformés avec la même authenticité que les nôtres.")
			.values(EXISTING_SITE_ID, "Pommes", "562FQVC57", "Rue des Prés", null, "34430", "St Jean de Védas", 
					"France", "43.577740, 3.816562", 222222, 
					"Les amateurs de pommes vont trouver leur bonheur. Plus de 20 variétés composent notre verger, "
							+ "Reine des Reinettes, Golden, Fuji, Chantecler, Pinova, Patte de Loup,.... Toutes "
							+ "produites avec le plus grand soin et ")
			.build(),
			// table siteimage
			insertInto("siteimage")
			.columns("fk_site_id", "fk_image_id")
			.values(EXISTING_SITE_ID, IMAGE_FOR_SITE_ID)
			.build());

	/**
	 * Insert data in table enterprise with ids {@link UtilData#EXISTING_ENTERPRISE_ID} and 222222.
	 */
	protected static final Operation INSERT_ENTERPRISES = insertInto("enterprise")
			.columns("enterprise_id", "trade_name", "legal_name", "legal_identification", "fk_image_id",
					"description", "web_site", "creation_date", "number_employees", "registration_date", 
					"addr_street", "addr_complement", "addr_post_code",
					"addr_city", "addr_country", "addr_coordinates")
			.values(EXISTING_ENTERPRISE_ID, "Janichon&Sons", "GAEC Janichon", "XXDGQG", null, 
					"L'élevage bovin est l'ensemble des opérations visant à reproduire des animaux de l'espèce "
							+ "Bos taurus au profit de l'activité humaine. Il permet de fournir de la viande, du lait, "
							+ "des peaux des animaux reproducteurs, un travail de traction, du fumier et l'entretien "
							+ "des espaces ouverts…", null, 
							GENERATOR_LOCAL_DATE.nextValue(), 2, GENERATOR_ZONED_DATE_TIME.nextValue(),
							"Dagallier Haut", null, "01400", "Sulignat", "France", "46.182194, 4.970275")
			.values(222222, "Les Vergers de Saint Jean", "SCEA Les Vergers de Saint Jean", "CHSGFQN", null, 
					"Nous sommes producteurs de fruits à noyaux, cerise, abricot, pêche, nectarine, brugnon et prune "
							+ "ainsi que de fruits à pépins, pomme. Dans un souci de développement durable de notre "
							+ "activité, nous avons une multitude de variétés pour chaque type de fruits. Ainsi nous "
							+ "étalons nos récoltes en fonction de la maturité de la variété.", 
							"http://www.lesvergersdesaintjean.fr", 
							GENERATOR_LOCAL_DATE.nextValue(), 4, GENERATOR_ZONED_DATE_TIME.nextValue(),
							"Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", "43.589423, 3.827251")
			.build();

	/**
	 * Insert data in table image with ids {@link UtilData#EXISTING_IMAGE_ID_FIRST_CALL}, 
	 * and {@link UtilData#EXISTING_IMAGE_ID_SECOND_CALL}.
	 */
	protected static final Operation INSERT_IMAGES = insertInto("image")
			.columns("image_id", "imag_title", "imag_description", "imag_data")
			.values(EXISTING_IMAGE_ID_FIRST_CALL, "pomme.jpg", "Une très belle pomme croquante", null)
			.values(EXISTING_IMAGE_ID_SECOND_CALL, "poire.jpg", "Une très belle poire croquante", null)
			.build();

	/**
	 * Insert data in table category with ids 111111, 222222 and {@link UtilData#EXISTING_CATEGORY_ID}.
	 */
	protected static final Operation INSERT_CATEGORIES = insertInto("category")
			.columns("category_id", "category_name", "parent_id")
			.values(111111, "Charcuterie", null)
			.values(222222, "Saucisson", 111111)
			.values(EXISTING_CATEGORY_ID, "Saucisson Sec", 222222)
			.build();

	/**
	 * It requires {@link UtilData#INSERT_SITES} and {@link UtilData#INSERT_DESIGNATION_LABEL} to be called first.
	 * Insert data in product table with ids {@link UtilData#EXISTING_PRODUCT_ID_FIRST_CALL}, 
	 * and {@link UtilData#EXISTING_PRODUCT_ID_SECOND_CALL}.
	 */
	protected static final Operation INSERT_PRODUCTS = insertInto("product")
			.columns("product_id", "active_for_sale", "price_per_unit", "tax_percentage", "title", "unit", 
					"fk_designation_id", "fk_site_id")
			.values(EXISTING_PRODUCT_ID_FIRST_CALL, true, 968.25, 5.5, "Une très bonne vache, première main", 
					Unit.GRAM.ordinal(), EXISTING_DESIGNATION_ID, EXISTING_SITE_ID)
			.values(EXISTING_PRODUCT_ID_SECOND_CALL, false, 22.99, 19.6, "Une vache, deuxième main, bon état général", 
					Unit.GRAM.ordinal(), EXISTING_DESIGNATION_ID, EXISTING_SITE_ID)
			.build();

	// Variables //-----------------------------------------------
	protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();

	// Test methods //--------------------------------------------
	/**
	 * Clean the data from previous tests and insert basic data. This method is used before each test.
	 * @throws Exception.
	 */
	@Before
	public final void cleanInsertBasicData() throws Exception {
		DbSetup dbSetup = new DbSetup(getDestination(), DELETE_ALL_DATA);
		dbSetupTracker.launchIfNecessary(dbSetup);
	}

	/**
	 * Execute the specified operation (i.e. insert new data) in database.
	 * This should be called at the very beginning of a test relying on this set of data.
	 * @param operation the {@link Operation}.
	 */
	public final void insertData(Operation operation) {
		DbSetup dbSetup = new DbSetup(getDestination(), operation);
		dbSetup.launch();
	}

	/**
	 * Get the {@link DriverManagerDestination} used in the {@link DbSetup}.
	 * @return the {@link DriverManagerDestination}.
	 */
	private DriverManagerDestination getDestination() {
		return with(DESTINATION_URL, DESTINATION_USER, DESTINATION_PASSWORD);
	}
}