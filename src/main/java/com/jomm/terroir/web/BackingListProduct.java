package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.Entity.PRODUCT;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CONFIRM_DELETE;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceProduct;
import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.util.exception.ExceptionService;

/** 
 * This Class is the Bean linked to {@code listproduct.xhtml}, displaying the list of {@link Product}s.
 * It extends {@link BackingBean} and defines specific attributes.
 * It indirectly implements {@link java.io.Serializable} and has a default serial version ID.
 * It relates to {@link ServiceProduct} to update or delete the {@link Product}s,
 * and to {@link Logger} to generate proper logging messages.
 * It is annotated {@link Named} for proper access from/to the view page,
 * and {@link ViewScoped} because of multiple AJAX requests.
 * @author Maic
 */
@Named
@ViewScoped
public class BackingListProduct extends BackingBean {
	
	// Constants //-----------------------------------------------
	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceProduct service;
	@Inject
	private Logger logger;
	
	// Variables //-----------------------------------------------
	private Product currentProduct;
	private List<Product> listProducts;
	
	// Methods //-------------------------------------------------
	/**
	 * This method instantiate all necessary attributes (i.e. prepare the list of all products).
	 * It replaces the constructor and it is annotated {@link PostConstruct}, 
	 * for proper call from the bean management framework 
	 * which uses proxies, such as CDI.
	 */
	@PostConstruct 
	public void init() {
		listProducts = new ArrayList<>();
		for (Product product : service.getAllProducts()) {
			if (product.getStock() == null) {
				// Instantiate a new Stock in case of an update
				Stock stock = new Stock();
				stock.setId(product.getId());
				product.addStock(stock);
			}
			listProducts.add(product);
		}
	}
	
	/**
	 * Is called when a row is edited.
	 * @param event {@link RowEditEvent} the AJAX event.
	 */
	public void onRowEdit(RowEditEvent event) {
		currentProduct = (Product) event.getObject();
		if (currentProduct != null) {
			try {
				service.update(currentProduct);
				addFacesMessageUpdate(PRODUCT, currentProduct.getId());
			} catch (ExceptionService exception) {
				String problem = generateReadableExceptionMessage(exception, currentProduct);
				addFacesMessageException(problem);
				logger.log(Level.FINE, problem, exception);
			}
		}
	}
	
	/**
	 * Is called when a edited row is back to normal state.
	 * @param event {@link RowEditEvent} the AJAX event.
	 */
	public void onRowCancel(RowEditEvent event) {
		// Do nothing.
	}
	
	/**
	 * Delete a product.
	 * @return a String for navigation.
	 */
	public String delete() {
		if (currentProduct != null) {
			try {
				service.delete(currentProduct);
				addFacesMessageDelete(PRODUCT, currentProduct.getId());
			} catch (ExceptionService exception) {
				String problem = generateReadableExceptionMessage(exception, currentProduct);
				addFacesMessageException(problem);
				logger.log(Level.FINE, problem, exception);
			}
		}
		return "listproduct" + "?faces-redirect=true";	// Navigation case.
	}
	
	/**
	 * Format a message for the delete confirmation.
	 * @return an empty String if the {@code currentProduct} is {@code null}, the formatted message otherwise.
	 */
	public String confirmeDeleteMessage() {
		String message = "";
		if (currentProduct != null) {
			Object[] argument = {currentProduct.getId()};
			message = MessageFormat.format(getValueFromKey(PRODUCT).replace("'", "''"), argument) 
					+ getValueFromKey(CONFIRM_DELETE);
		}
		return message;
	}
	
	// Getters and Setters //-------------------------------------
	/**
	 * @return the currentProduct
	 */
	public Product getCurrentProduct() {
		return currentProduct;
	}

	/**
	 * @param currentProduct the currentProduct to set
	 */
	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}

	/**
	 * @return the listProducts
	 */
	public List<Product> getListProducts() {
		return listProducts;
	}

	/**
	 * @param listProducts the listProducts to set
	 */
	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}
}