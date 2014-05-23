package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collections;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class NumberMetadataType extends MetadataType<Number>
{
	private boolean acceptFloat;
	private boolean acceptNegative;
	private Number minimumValue;
	private Number maximumValue;

	@Override
	public MetadataRenderer getRenderer()
	{
		return null;	//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public MetadataConverter<Number> getConverter()
	{
		return new NumberMetadataConverter();
	}

	@Override
	public MetadataValidator<Number> getValidator()
	{
		return new NumberMetadataValidator();
	}

	private final class NumberMetadataValidator implements MetadataValidator<Number>
	{

		public boolean validate(Number metadataValue)
		{

			if (metadataValue == null)
				return isRequired();
			if (!acceptFloat && metadataValue instanceof Float)
				return false;
			if (!acceptNegative && metadataValue.doubleValue() < 0)
				return false;
			if (minimumValue != null && minimumValue.doubleValue() > metadataValue.doubleValue())
				return false;
			if (maximumValue != null && maximumValue.doubleValue() < metadataValue.doubleValue())
				return false;

			return true;
		}
	}

	private final class NumberMetadataConverter implements MetadataConverter<Number>
	{

		public String toString(Number metadataValue)
		{
			return metadataValue != null ? metadataValue.toString() : null;
		}

		public Number fromString(String stringValue)
		{
			if (stringValue == null || stringValue.isEmpty())
				return null;

			if (acceptFloat)
				return Float.parseFloat(stringValue);
			else
				return Integer.parseInt(stringValue);
		}

		public Map<String, ?> toProperties(Number metadataValue)
		{
			String stringValue = toString(metadataValue);
			return Collections.singletonMap(getUniqueName(), stringValue);
		}

		public Number fromProperties(Map<String, ?> properties)
		{
			return fromString((String) properties.get(getUniqueName()));
		}

		public Number fromHttpForm(Map<String, ?> parameters, String parameterSuffix)
		{
			return fromString((String) parameters.get(getUniqueName() + parameterSuffix));
		}
	}
}
