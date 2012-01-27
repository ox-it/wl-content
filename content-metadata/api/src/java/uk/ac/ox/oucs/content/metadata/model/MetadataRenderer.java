package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public interface MetadataRenderer
{
	/**
	 * Specifies the Velocity template used in the rendering part when the user is editing the metadata configuration.
	 *
	 * @return a path to a Velocity template
	 */
	String getMetadataTypeEditTemplate();

	/**
	 * Specifies the Velocity template used in the rendering part when the user is viewing the metadata configuration.
	 *
	 * @return a path to a Velocity template
	 */
	String getMetadataTypePrintTemplate();

	/**
	 * Specifies the Velocity template used in the rendering part when the user is editing the metadata of a resource.
	 *
	 * @return a path to a Velocity template
	 */
	String getMetadataValueEditTemplate();

	/**
	 * Specifies the Velocity template used in the rendering part when the user is viewing the metadata of a resource.
	 *
	 * @return a path to a Velocity template
	 */
	String getMetadataValuePrintTemplate();
}
