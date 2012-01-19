package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public interface MetadataConverter<T>
{
	String toString(T object);

	T toObject(String string);
}
