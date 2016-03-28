package com.jomm.terroir.business.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This Class is an {@link Entity} representing a label.
 * It extends {@link AbstractEntity}, thus it indirectly implements 
 * {@link java.io.Serializable} and has a default serial version ID.
 * It uses {@link com.jomm.terroir.business.ServiceCategory} for all its business operations.
 * The categories are organized in a tree, thanks to the Adjacency List Model.
 * So each category may include a other {@link Category} as its parent, allowing to navigate in the tree.
 * Its properties are persisted in the {@link javax.persistence.Table} named {@code category}.
 * @author Maic
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category extends AbstractEntity {

	/** Serial version ID. Do not modify unless the type undergoes structural changes affecting serialization. */
	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	@NotNull
	@Column(name = "category_name", unique = true)
	private String name;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Designation> designations;
	
	// Getters and Setters
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return the designations
	 */
	public List<Designation> getDesignations() {
		return designations;
	}

	/**
	 * @param designations the designations to set
	 */
	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}
}