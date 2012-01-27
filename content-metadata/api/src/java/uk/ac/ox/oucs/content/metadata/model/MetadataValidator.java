package uk.ac.ox.oucs.content.metadata.model;

/**
 * Provide a simple validation tool for a specific type of metadata
 *
 * @author Colin Hebert
 */
public interface MetadataValidator<T>
{
	/**
	 * Tests the validity of a given value
	 * <p/>
	 * TODO: Use a more advanced validation tool (JSR-303?)
	 *
	 * @param object Object to test
	 * @return true if the parameter is valid, false otherwise.
	 */
	boolean validate(T object);
}
