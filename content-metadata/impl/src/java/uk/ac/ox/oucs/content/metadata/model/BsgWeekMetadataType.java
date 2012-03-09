package uk.ac.ox.oucs.content.metadata.model;

import org.sakaiproject.component.cover.ComponentManager;
import uk.ac.ox.oucs.termdates.TermConverterService;

import java.util.Map;

/**
 * @author Colin Hebert
 */
public class BsgWeekMetadataType extends WeekMetadataType
{
	private final Map<Integer, String> weekNames;

	public BsgWeekMetadataType()
	{
		weekNames = ((TermConverterService) ComponentManager.get("uk.ac.ox.oucs.termdates.BsgTermConverterService")).getWeekNames();
	}

	public Map<Integer, String> getWeekNames()
	{
		return weekNames;
	}

	//Special renderer with BSG weeks
	@Override
	public MetadataRenderer getRenderer()
	{
		return new BsgWeekMetadataRenderer();
	}

	private class BsgWeekMetadataRenderer implements MetadataRenderer
	{
		public String getMetadataTypeEditTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataTypeDisplayTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataValueEditTemplate()
		{
			return "meta_edit_bsg_week";
		}

		public String getMetadataValueDisplayTemplate()
		{
			return "meta_display_string";
		}
	}
}
