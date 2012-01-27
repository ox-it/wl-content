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

		public boolean validate(Number value)
		{

			if (value == null)
				return isRequired();
			if (!acceptFloat && value instanceof Float)
				return false;
			if (!acceptNegative && value.doubleValue() < 0)
				return false;
			if (minimumValue != null && minimumValue.doubleValue() > value.doubleValue())
				return false;
			if (maximumValue != null && maximumValue.doubleValue() < value.doubleValue())
				return false;

			return true;
		}
	}

	private final class NumberMetadataConverter implements MetadataConverter<Number>
	{

		public String toString(Number object)
		{
			if (object == null)
				return null;
			return object.toString();
		}

		public Number toObject(String string)
		{
			if (string == null)
				return null;
			if (acceptFloat)
				return Float.parseFloat(string);
			else
				return Integer.parseInt(string);
		}

		public Map<Object, Object> toProperties(Number object)
		{
			return Collections.<Object, Object>singletonMap(getUuid(), toString(object));

		}

		public Number toObject(Map properties, String propertySuffix)
		{
			return toObject((String) properties.get(getUuid() + propertySuffix));
		}
	}
}
