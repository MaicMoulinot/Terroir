package com.jomm.terroir.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLTools {

	private XMLTools() {}

	/**
	 * Serialize an object in file.
	 * @param object {@link Object} to serialize.
	 * @param filename {@link String} file's path.
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
	 * @param filename {@link String} file's path.
	 * @return {@link Object} the deserialized object from file.
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
