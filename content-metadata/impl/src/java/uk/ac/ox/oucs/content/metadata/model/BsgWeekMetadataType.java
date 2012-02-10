package uk.ac.ox.oucs.content.metadata.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class BsgWeekMetadataType extends WeekMetadataType
{
	private static final Map<Integer, String> weekNames = new LinkedHashMap<Integer, String>(52);

	public BsgWeekMetadataType()
	{
		weekNames.put(0, "Presession 1/Michaelmas -2");
		weekNames.put(1, "Presession 2/Michaelmas -1");
		weekNames.put(2, "Michaelmas 0");
		weekNames.put(3, "Michaelmas 1");
		weekNames.put(4, "Michaelmas 2");
		weekNames.put(5, "Michaelmas 3");
		weekNames.put(6, "Michaelmas 4");
		weekNames.put(7, "Michaelmas 5");
		weekNames.put(8, "Michaelmas 6");
		weekNames.put(9, "Michaelmas 7");
		weekNames.put(10, "Michaelmas 8");
		weekNames.put(11, "Michaelmas 9");
		weekNames.put(12, "Michaelmas 10");
		weekNames.put(13, "Winter Break 1/Michaelmas 11");
		weekNames.put(14, "Winter Break 2/Michaelmas 12");
		weekNames.put(15, "Winter Break 3/Hilary -1");
		weekNames.put(16, "Hilary 0");
		weekNames.put(17, "Hilary 1");
		weekNames.put(18, "Hilary 2");
		weekNames.put(19, "Hilary 3");
		weekNames.put(20, "Hilary 4");
		weekNames.put(21, "Hilary 5");
		weekNames.put(22, "Hilary 6");
		weekNames.put(23, "Hilary 7");
		weekNames.put(24, "Hilary 8");
		weekNames.put(25, "Hilary 9");
		weekNames.put(26, "Hilary 10");
		weekNames.put(27, "Spring Break 1/Hilary 11");
		weekNames.put(28, "Spring Break 2/Hilary 12");
		weekNames.put(29, "Spring Break 3/Trinity -1");
		weekNames.put(30, "Trinity 0");
		weekNames.put(31, "Trinity 1");
		weekNames.put(32, "Trinity 2");
		weekNames.put(33, "Trinity 3");
		weekNames.put(34, "Trinity 4");
		weekNames.put(35, "Trinity 5");
		weekNames.put(36, "Trinity 6");
		weekNames.put(37, "Trinity 7");
		weekNames.put(38, "Trinity 8");
		weekNames.put(39, "Trinity 9");
		weekNames.put(40, "Trinity 10");
		weekNames.put(41, "Trinity 11");
		weekNames.put(42, "Trinity 12");
		weekNames.put(43, "Summer 1");
		weekNames.put(44, "Summer 2");
		weekNames.put(45, "Summer 3");
		weekNames.put(46, "Summer 4");
		weekNames.put(47, "Summer 5");
		weekNames.put(48, "Summer 6");
		weekNames.put(49, "Summer 7");
		weekNames.put(50, "Summer 8");
		weekNames.put(51, "Summer 9");
		weekNames.put(52, "Summer 10");
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
