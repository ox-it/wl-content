package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collections;
import java.util.Map;

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
		public boolean validate(Integer metadataValue)
		{
			if (metadataValue == null)
				return isRequired();

			return metadataValue >= 0 && metadataValue <= 52;
		}
	}

	private final class WeekMetadataConverter implements MetadataConverter<Integer>
	{
		public String toString(Integer metadataValue)
		{
			return metadataValue != null ? metadataValue.toString() : null;
		}

		public Integer fromString(String stringValue)
		{
			if (stringValue == null || stringValue.isEmpty())
				return null;
			return Integer.parseInt(stringValue);
		}

		public Map<String, ?> toProperties(Integer metadataValue)
		{
			String value = toString(metadataValue);
			return (value != null) ? Collections.singletonMap(getUniqueName(), value) : Collections.<String, Object>emptyMap();
		}

		public Integer fromProperties(Map<String, ?> properties)
		{
			return fromString((String) properties.get(getUniqueName()));
		}

		public Integer fromHttpForm(Map<String, ?> parameters, String parameterSuffix)
		{
			return fromString((String) parameters.get(getUniqueName() + parameterSuffix));
		}
	}

}
