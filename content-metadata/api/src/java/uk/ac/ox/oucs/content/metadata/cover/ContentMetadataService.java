package uk.ac.ox.oucs.content.metadata.cover;

import org.sakaiproject.component.cover.ComponentManager;
import uk.ac.ox.oucs.content.metadata.logic.MetadataService;

/**
 * Static cover for MetadataService
 *
 * @author Colin Hebert
 */
public final class ContentMetadataService
{
	private static MetadataService instance;

	/**
	 * Access the component instance: special cover only method.
	 * <p/>
	 * Automatically caches the instance if required.
	 *
	 * @return the component instance.
	 */
	public static MetadataService getInstance()
	{
		if (ComponentManager.CACHE_COMPONENTS)
		{
			if (instance == null)
				instance = (MetadataService) ComponentManager.get(MetadataService.class);
			return instance;
		} else
		{
			return (MetadataService) ComponentManager.get(MetadataService.class);
		}
	}
}
