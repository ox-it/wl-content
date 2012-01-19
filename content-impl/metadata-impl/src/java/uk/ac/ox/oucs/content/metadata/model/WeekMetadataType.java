package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public class WeekMetadataType extends MetadataType<Integer>
{
	@Override
	public MetadataRenderer getRenderer()
	{
		return null;
	}

	@Override
	public MetadataConverter<Integer> getConverter()
	{
		return new WeekMetadataConverter();
	}

	@Override
	public MetadataValidator<Integer> getValidator()
	{
		return new WeekMetadataValidator();
	}

	private final class WeekMetadataValidator implements MetadataValidator<Integer>
	{
		public boolean validate(Integer object)
		{
			return object >= 0 && object <= 52;
		}
	}

	private final class WeekMetadataConverter implements MetadataConverter<Integer>
	{
		public String toString(Integer object)
		{
			return object.toString();
		}

		public Integer toObject(String string)
		{
			return Integer.parseInt(string);
		}
	}

}
