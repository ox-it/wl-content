package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Colin Hebert
 */
public class StringMetadataType extends MetadataType<String>
{
	private int minLength;
	private int maxLength;
	private String regularExpression;
	//Renders textareas instead of textboxes
	private boolean longText;

	protected static MetadataConverter<String> metadataConverter = new StringMetadataConverter();

	@Override
	public MetadataRenderer getRenderer()
	{
		return null;	//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return metadataConverter;
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new StringMetadataValidator();
	}

	private final class StringMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String value)
		{
			if (value == null || value.isEmpty()) return isRequired();
			if (minLength > 0 && value.length() < minLength) return false;
			if (maxLength > 0 && value.length() > maxLength) return false;
			if (regularExpression != null && !value.matches(regularExpression)) return false;
			return true;
		}
	}

	private static final class StringMetadataConverter implements MetadataConverter<String>
	{
		public String toString(String object)
		{
			return object;
		}

		public String toObject(String string)
		{
			return string;
		}
	}
}
