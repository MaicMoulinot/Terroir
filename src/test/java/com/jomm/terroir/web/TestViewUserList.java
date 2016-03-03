package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.faces.component.html.HtmlDataTable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is a Junit test case testing the methods of {@link ViewUserList}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link ViewUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestViewUserList {

	private ViewUserList view;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestViewUserList#childToTest()}.
	 * @param view the concrete child of {@link ViewUserList}.
	 */
    public TestViewUserList(ViewUserList view) {
        this.view = view;
    }

	/**
	 * Test method for {@link ViewSellerList}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// HtmlDataTable
		HtmlDataTable dataTable = new HtmlDataTable();
		view.setDataTable(dataTable);
		assertEquals("HtmlDataTable should be " + dataTable, dataTable, view.getDataTable());
	}
	
	/**
	 * Reference a list of all {@link ViewUserList}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomerList()},
			{new ViewSellerList()}
			}
		);
	}
}