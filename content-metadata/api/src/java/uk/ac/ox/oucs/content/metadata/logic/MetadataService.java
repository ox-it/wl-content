package uk.ac.ox.oucs.content.metadata.logic;

import java.util.List;

import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * Service providing tools to handle metadata on Content
 *
 * @author Colin Hebert
 */
public interface MetadataService
{
	/**
	 * Get all metadata groups available on the server
	 *
	 * @return A list of metadata fields, or an empty list if there is none.
	 */
	List<MetadataType> getMetadataAvailable();

	/**
	 * Get all metadata groups available on the server for a specific site and resourceType
	 *
	 * @param siteId			 Site identifier
	 * @param resourceType Type of resource/content
	 * @return A list of metadata fields, or an empty list if there is none.
	 */
	List<MetadataType> getMetadataAvailable(String siteId, String resourceType);
}
