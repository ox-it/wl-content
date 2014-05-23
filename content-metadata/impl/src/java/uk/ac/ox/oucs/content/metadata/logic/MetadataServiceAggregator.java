package uk.ac.ox.oucs.content.metadata.logic;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * Created by IntelliJ IDEA.
 * User: oucs0164
 * Date: 01/02/2012
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class MetadataServiceAggregator implements MetadataService
{
	private final List<MetadataService> metadataServices;

	public MetadataServiceAggregator(List<MetadataService> metadataServices) {this.metadataServices = metadataServices;}

	public List<MetadataType> getMetadataAvailable(String resourceType)
	{
		List<MetadataType> metadataTypes = new ArrayList<MetadataType>();
		for (MetadataService metadataService : metadataServices)
		{
			metadataTypes.addAll(metadataService.getMetadataAvailable(resourceType));
		}
		return metadataTypes;
	}

	public List<MetadataType> getMetadataAvailable(String siteId, String resourceType)
	{
		List<MetadataType> metadataTypes = new ArrayList<MetadataType>();
		for (MetadataService metadataService : metadataServices)
		{
			metadataTypes.addAll(metadataService.getMetadataAvailable(siteId, resourceType));
		}
		return metadataTypes;
	}
}
