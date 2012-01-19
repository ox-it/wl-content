package uk.ac.ox.oucs.content.metadata.logic;

import uk.ac.ox.oucs.content.metadata.model.MetadataType;

import java.util.List;

/**
 * @author Colin Hebert
 */
public interface MetadataService
{
	List<MetadataType> getMetadataAvailable();

	List<MetadataType> getMetadataAvailable(String resourceType);
}
