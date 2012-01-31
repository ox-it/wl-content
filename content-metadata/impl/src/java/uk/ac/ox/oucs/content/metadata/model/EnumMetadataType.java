package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class EnumMetadataType extends MetadataType<String>
{
	private Collection<String> allowedValues;

	public Collection<String> getAllowedValues()
	{
		return allowedValues;
	}

	public void setAllowedValues(Collection<String> allowedValues)
	{
		this.allowedValues = allowedValues;
	}

	@Override
	public MetadataRenderer getRenderer()
	{
		return new EnumMetadataRenderer();
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return new EnumMetadataConverter();
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new EnumMetadataValidator();
	}


	private final class EnumMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String metadataValue)
		{
			if (metadataValue == null)
				return isRequired();
			if (allowedValues != null && allowedValues.contains(metadataValue))
				return false;

			return true;
		}
	}

	private final class EnumMetadataRenderer implements MetadataRenderer
	{
		public String getMetadataTypeEditTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataTypePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataValueEditTemplate()
		{
			return "vm/metadata/meta_edit_enum.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}

	protected final class EnumMetadataConverter implements MetadataConverter<String>
	{
		public String toString(String metadataValue)
		{
			return (metadataValue != null && !metadataValue.isEmpty()) ? metadataValue : null;
		}

		public String fromString(String stringValue)
		{
			return (stringValue != null && !stringValue.isEmpty()) ? stringValue : null;
		}

		public Map<String, ?> toProperties(String metadataValue)
		{
			String stringValue = toString(metadataValue);
			return (stringValue != null) ? Collections.singletonMap(getUniqueName(), stringValue) : Collections.<String, Object>emptyMap();
		}

		public String fromProperties(Map<String, ?> properties)
		{
			return fromString((String) properties.get(getUniqueName()));
		}

		public String fromHttpForm(Map<String, ?> parameters, String parameterSuffix)
		{
			return fromString((String) parameters.get(getUniqueName() + parameterSuffix));
		}
	}
}
