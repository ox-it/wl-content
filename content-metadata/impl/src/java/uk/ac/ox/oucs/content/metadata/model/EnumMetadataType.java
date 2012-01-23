package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collection;

/**
 * @author Colin Hebert
 */
public class EnumMetadataType extends MetadataType<String>
{
	private Collection<String> allowedValues;

	@Override
	public MetadataRenderer getRenderer()
	{
		return null;	//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return StringMetadataType.metadataConverter;
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new EnumMetadataValidator();
	}


	private final class EnumMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String value)
		{
			if (value == null) return isRequired();
			if (allowedValues != null && allowedValues.contains(value)) return false;

			return true;
		}
	}

}
