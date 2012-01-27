package uk.ac.ox.oucs.content.metadata.model;

import java.util.Map;

/**
 * Allows values associated to a certain kind of metadata to be converted in the application
 *
 * @author Colin Hebert
 */
public interface MetadataConverter<T>
{
	/**
	 * Transforms the object in parameter into a simple String.
	 * The string can be later parsed with {@link MetadataConverter#toObject(String)}
	 *
	 * @param object Object to convert as a String
	 * @return String value of the parameter
	 */
	String toString(T object);

	/**
	 * Converts a String into an object of the appropriate type.
	 * The given String should be generated with {@link MetadataConverter#toString(Object)}
	 *
	 * @param string String to convert into an Object
	 * @return The converted result
	 */
	T toObject(String string);

	/**
	 * Fetches relevant information in a map (usually the parameters from a HttpServletRequest) to build an Object.
	 *
	 * @param properties		 Map containing the information
	 * @param propertySuffix Optional suffix which can be used in HTML forms
	 * @return The converted result
	 */
	T toObject(Map<Object, Object> properties, String propertySuffix);

	/**
	 * Transforms the object into a properties map.
	 * The map should only contain String and List&lt;String&gt; elements.
	 *
	 * @param object Object to convert
	 * @return a Map containing every relevant values
	 */
	Map<Object, Object> toProperties(T object);

}
