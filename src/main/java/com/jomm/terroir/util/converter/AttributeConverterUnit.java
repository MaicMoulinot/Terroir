package com.jomm.terroir.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is a Converter.
 * <br />It implements {@link AttributeConverter}<{@link Unit}, {@link String}>, and
 * converts a {@link Unit} (type of entity attribute) to/from a short String (type of database column), 
 * which will be used as the database representation.
 * These conversions are using the methods {@code convertToDatabaseColumn()} and {@code convertToEntityAttribute()}.
 * <br />It is annotated {@link Converter} to be registered as a Converter, with its parameter 
 * {@code autoApply=true} in order to be applied on each related entity's attributes by default.
 * @author Maic
 */
@Converter(autoApply = true)
public final class AttributeConverterUnit implements AttributeConverter<Unit, String> {
	
	// Methods //-------------------------------------------------
	@Override
	public String convertToDatabaseColumn(Unit attribute) {
        return attribute != null ? attribute.getSymbol() : null;
	}
	
	@Override
	public Unit convertToEntityAttribute(String dbData) {
        return dbData != null ? Unit.getValue(dbData) : null;
	}	
}