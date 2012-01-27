package uk.ac.ox.oucs.content.metadata.model;

import java.util.Map;

/**
 * @author Colin Hebert
 */
public interface MetadataConverter<T>
{
	String toString(T object);

	T toObject(String string);

	T toObject(Map<Object, Object> properties, String propertySuffix);

	/**
	 * Transform the object into a properties map.
	 * The map should only contain String and List<String> elements
	 *
	 * @param object
	 * @return
	 */
	Map<Object, Object> toProperties(T object);

}
