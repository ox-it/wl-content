package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public class BsgWeekMetadataType extends WeekMetadataType
{
	//TODO : Temporary
	private static enum BSGWeekName
	{
		WEEK_00("Presession 1/Michaelmas -2"),
		WEEK_01("Presession 2/Michaelmas -1"),
		WEEK_02("Michaelmas 0"),
		WEEK_03("Michaelmas 1"),
		WEEK_04("Michaelmas 2"),
		WEEK_05("Michaelmas 3"),
		WEEK_06("Michaelmas 4"),
		WEEK_07("Michaelmas 5"),
		WEEK_08("Michaelmas 6"),
		WEEK_09("Michaelmas 7"),
		WEEK_10("Michaelmas 8"),
		WEEK_11("Michaelmas 9"),
		WEEK_12("Michaelmas 10"),
		WEEK_13("Winter Break 1/Michaelmas 11"),
		WEEK_14("Winter Break 2/Michaelmas 12"),
		WEEK_15("Winter Break 3/Hilary -1"),
		WEEK_16("Hilary 0"),
		WEEK_17("Hilary 1"),
		WEEK_18("Hilary 2"),
		WEEK_19("Hilary 3"),
		WEEK_20("Hilary 4"),
		WEEK_21("Hilary 5"),
		WEEK_22("Hilary 6"),
		WEEK_23("Hilary 7"),
		WEEK_24("Hilary 8"),
		WEEK_25("Hilary 9"),
		WEEK_26("Hilary 10"),
		WEEK_27("Spring Break 1/Hilary 11"),
		WEEK_28("Spring Break 2/Hilary 12"),
		WEEK_29("Spring Break 3/Trinity -1"),
		WEEK_30("Trinity 0"),
		WEEK_31("Trinity 1"),
		WEEK_32("Trinity 2"),
		WEEK_33("Trinity 3"),
		WEEK_34("Trinity 4"),
		WEEK_35("Trinity 5"),
		WEEK_36("Trinity 6"),
		WEEK_37("Trinity 7"),
		WEEK_38("Trinity 8"),
		WEEK_39("Trinity 9"),
		WEEK_40("Trinity 10"),
		WEEK_41("Trinity 11"),
		WEEK_42("Trinity 12"),
		WEEK_43("Summer 1"),
		WEEK_44("Summer 2"),
		WEEK_45("Summer 3"),
		WEEK_46("Summer 4"),
		WEEK_47("Summer 5"),
		WEEK_48("Summer 6"),
		WEEK_49("Summer 7"),
		WEEK_50("Summer 8"),
		WEEK_51("Summer 9"),
		WEEK_52("Summer 10");
		private String weekName;

		private BSGWeekName(String weekName)
		{
			this.weekName = weekName;
		}

		public String getWeekName()
		{
			return weekName;
		}
	}

	//Special renderer with BSG weeks
	@Override
	public MetadataRenderer getRenderer()
	{
		return super.getRenderer();		//To change body of overridden methods use File | Settings | File Templates.
	}
}
