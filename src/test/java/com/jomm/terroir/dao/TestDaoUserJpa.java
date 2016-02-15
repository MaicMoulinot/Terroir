package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.TestAbstractUser;

/**
 * This Class is a Junit test case testing {@link DaoUserJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link AbstractUser} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class TestDaoUserJpa extends TestDaoGenericJpa<AbstractUser> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoUserJpa();
		entity = TestAbstractUser.generateAbstractUser();
	}

	@Override
	@Test
	public final void testBehavior() {
		// EntityManager is not working, it is mocked
		dao.setEntityManager(Mockito.mock(EntityManager.class));

		super.testBehavior();
	}

	@Override
	@Test
	@Ignore
	public final void testState() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			dao.setEntityManager(UtilEntityManager.prepareEntityManager());

			Long initialId = entity.getId();
			assertNull("Before persistence, id should be null", initialId);

			// FindAll
//			assertNotNull("Before persistence, the list should not be null", dao.findAll());
//			assertTrue("Before persistence, the list should be empty", dao.findAll().isEmpty());
//			assertEquals("Before persistence, the list's size should be 0", 0, dao.findAll().size());

			// Create
			Long persistedId = dao.create(entity).getId();
			assertNotNull("After persistence, id should not be null", persistedId);

			//FindAll
			//assertEquals("After persistence, the list's size should be 1", 1, dao.findAll().size());

			// FindById
			AbstractUser persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getEmail(), persistedEntity.getEmail());

			// Update
			String initialValue = persistedEntity.getEmail();
			persistedEntity.setEmail("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getEmail();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			dao.deleteById(persistedId);
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Delete
			entity = TestAbstractUser.generateAbstractUser();
			dao.create(entity);
			assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
			dao.delete(entity);
			assertNull("After Delete, entity should be null", dao.find(entity.getId()));
		} catch (Exception exception) {
			assertNull(exception);
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
}