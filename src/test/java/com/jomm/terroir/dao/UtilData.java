package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.destination.DriverManagerDestination.with;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
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

	// Private constants used for database configuration
	private static final String DESTINATION_URL = "jdbc:derby:memory:testDB";
	private static final String DESTINATION_USER = "";
	private static final String DESTINATION_PASSWORD = "";

	// Protected constants used in concrete child
	protected static final long NON_EXISTING_ENTITY_ID = 999999;
	protected static final long EXISTING_SITE_ID = 333333;
	protected static final long EXISTING_ENTERPRISE_ID = 111111;
	protected static final long EXISTING_DESIGNATION_ID = 111111;
	protected static final long EXISTING_LABEL_ID = 111111;
	protected static final long EXISTING_CATEGORY_ID = 333333;

	// DBSetup attributes
	protected static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	private static final DateSequenceValueGenerator GENERATOR_LOCAL_DATE = ValueGenerators.dateSequence();
	private static final DateSequenceValueGenerator GENERATOR_ZONED_DATE_TIME = 
			ValueGenerators.dateSequence().startingAt(new Date(), TimeZone.getDefault());
	private static final Operation DELETE_ALL_DATA = deleteAllFrom("administrator", "customer", "product", 
			"site", "seller", "enterprise", "designationlabel", "designation", "label", "image", "category");
	private static final Operation INSERT_BASIC_DATA = sequenceOf(
			// table category
			insertInto("category")
			.columns("category_id", "category_name", "parent_id")
			.values(111111, "Charcuterie", null)
			.values(222222, "Saucisson", 111111)
			.values(EXISTING_CATEGORY_ID, "Saucisson Sec", 222222)
			.build(),
			// table enterprise
			insertInto("enterprise")
			.columns("enterprise_id", "trade_name", "legal_name", "legal_identification", 
					"description", "web_site", "creation_date", "number_employees", "registration_date", 
					"addr_street", "addr_complement", "addr_post_code",
					"addr_city", "addr_country", "addr_coordinates")
			.values(EXISTING_ENTERPRISE_ID, "Janichon&Sons", "GAEC Janichon", "XXDGQG", 
					"L'élevage bovin est l'ensemble des opérations visant à reproduire des animaux de l'espèce "
							+ "Bos taurus au profit de l'activité humaine. Il permet de fournir de la viande, du lait, "
							+ "des peaux des animaux reproducteurs, un travail de traction, du fumier et l'entretien "
							+ "des espaces ouverts…", null, 
							GENERATOR_LOCAL_DATE.nextValue(), 2, GENERATOR_ZONED_DATE_TIME.nextValue(),
							"Dagallier Haut", null, "01400", "Sulignat", "France", "46.182194, 4.970275")
			.values(222222, "Les Vergers de Saint Jean", "SCEA Les Vergers de Saint Jean", "CHSGFQN", 
					"Nous sommes producteurs de fruits à noyaux, cerise, abricot, pêche, nectarine, brugnon et prune "
							+ "ainsi que de fruits à pépins, pomme. Dans un souci de développement durable de notre activité, "
							+ "nous avons une multitude de variétés pour chaque type de fruits. Ainsi nous étalons nos récoltes "
							+ "en fonction de la maturité de la variété.", "http://www.lesvergersdesaintjean.fr", 
							GENERATOR_LOCAL_DATE.nextValue(), 4, GENERATOR_ZONED_DATE_TIME.nextValue(),
							"Allée Pioch Redon", null, "34430", "St Jean de Védas", "France", "43.589423, 3.827251")
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
							+ "également des légumes de saisons issus majoritairement de l’agriculture local tel que le navet "
							+ "de Pardailhan, la fraise de Mauguio ou encore les oignons des Cévennes, ainsi que des produits "
							+ "transformés avec la même authenticité que les nôtres.")
			.values(EXISTING_SITE_ID, "Pommes", "562FQVC57", "Rue des Prés", null, "34430", "St Jean de Védas", 
					"France", "43.577740, 3.816562", 222222, 
					"Les amateurs de pommes vont trouver leur bonheur. Plus de 20 variétés composent notre verger, "
							+ "Reine des Reinettes, Golden, Fuji, Chantecler, Pinova, Patte de Loup,.... Toutes produites "
							+ "avec le plus grand soin et ")
			.build(),
			// table label
			insertInto("label")
			.columns("label_id", "official_name", "acronym", "fk_image_id", "definition")
			.values(EXISTING_LABEL_ID, "Appellation d'origine contrôlée", "AOC", null, 
					"L'appellation d'origine contrôlée (AOC) est un label officiel français identifiant un produit "
							+ "dont les étapes de fabrication (production et transformation) sont réalisées dans une même "
							+ "zone géographique et selon un savoir-faire reconnu. C'est la combinaison d'un milieu physique "
							+ "et biologique avec une communauté humaine traditionnelle qui fonde la spécificité d'un produit AOC.")
			.build(),
			// table designation
			insertInto("designation")
			.columns("designation_id", "registered_name", "transcripted_name", "local_name", "legal_act", 
					"registration_date", "web_site", "median_price", "fk_image_logo_id", "fk_image_picture_id",
					"addr_street", "addr_complement", "addr_post_code",
					"addr_city", "addr_country", "addr_coordinates",
					"fk_category_id", "definition", "season")
			.values(EXISTING_DESIGNATION_ID, "Roquefort", null, "ròcafòrt", "Décret du 22 janvier 2001 relatif à "
					+ "l'appellation d'origine contrôlée 'Roquefort' NOR: AGRP0001838D Version consolidée au 21 mars 2016", 
					GENERATOR_LOCAL_DATE.nextValue(), "https://fr.wikipedia.org/wiki/Roquefort", null, null, null, 
					null, null, "12250", "Roquefort-sur-Soulzon", "France", "43.973724, 2.991373", EXISTING_CATEGORY_ID, 
					"Le fromage bénéficiant de l'appellation d'origine contrôlée 'Roquefort' est un fromage fabriqué "
							+ "exclusivement avec du lait de brebis mis en oeuvre à l'état cru et emprésuré, de forme "
							+ "cylindrique de 19 à 20 cm de diamètre et de 8,5 à 11,5 cm de hauteur, d'un poids de 2,5 à 3 kg, "
							+ "à pâte persillée, ensemencée avec des spores de Penicillium roqueforti, non pressée, non cuite, "
							+ "fermentée et salée, à croûte humide, renfermant au minimum 52 g de matière grasse pour 100 g de "
							+ "fromage après complète dessiccation et dont la teneur en matière sèche ne doit pas être "
							+ "inférieure à 55 g pour 100 g de fromage.", "Mois de mars")
			.build(),
			// table designationlabel
			insertInto("designationlabel")
			.columns("fk_designation_id", "fk_label_id")
			.values(EXISTING_DESIGNATION_ID, EXISTING_LABEL_ID)
			.build());

	/**
	 * Clean the data from previous tests and insert new data. This method is used before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public final void cleanInsertData() throws Exception {
		DbSetup dbSetup = new DbSetup(with(DESTINATION_URL, DESTINATION_USER, DESTINATION_PASSWORD), 
				sequenceOf(DELETE_ALL_DATA, INSERT_BASIC_DATA));
		dbSetupTracker.launchIfNecessary(dbSetup);
	}
}