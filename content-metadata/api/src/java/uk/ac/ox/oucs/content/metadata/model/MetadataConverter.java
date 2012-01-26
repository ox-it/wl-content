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
}
