package com.jomm.terroir.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This Class is the specific Tool for serialize/deserialize operations.
 * It relates on the file's path of the {@link Object} to manipulate.
 * @author Maic
 */
public class XMLTools {

	/** No-arg constructor. */
	private XMLTools() {}

	/**
	 * Serialize an object in file.
	 * @param object {@link Object} to serialize.
	 * @param fileName {@link String} file's path.
	 * @throws FileNotFoundException if file is not found.
	 * @throws IOException if any IOException occurs.
	 */
	public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
		// open encoder
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
		try {
			// serialize object
			encoder.writeObject(object);
			encoder.flush();
		} finally {
			// close encoder
			encoder.close();
		}
	}
	
	/**
	 * Deserialization of an object from a file.
	 * @param fileName {@link String} file's path.
	 * @return {@link Object} the deserialized object from file.
	 * @throws FileNotFoundException if file is not found.
	 * @throws IOException if any IOException occurs.
	 */
	public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
	    Object object = null;
	    // open decoder
	    XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
	    try {
	        // deserialization of object
	        object = decoder.readObject();
	    } finally {
	        // close decoder
	        decoder.close();
	    }
	    return object;
	}
}
