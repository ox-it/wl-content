package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public interface MetadataValidator<T>
{
	boolean validate(T object);
}
